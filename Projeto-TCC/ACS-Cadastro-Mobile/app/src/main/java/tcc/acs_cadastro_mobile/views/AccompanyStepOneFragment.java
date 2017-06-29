package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyStepOneController;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;
import tcc.acs_cadastro_mobile.customViews.RequiredCalendarEditText;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.interfaces.IAccompany;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.persistence.RecordDataPersistence;
import tcc.acs_cadastro_mobile.subModels.Anthropometric;
import tcc.acs_cadastro_mobile.subModels.KidAndPregnant;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class AccompanyStepOneFragment extends Fragment implements IRequiredFields{

    private static final String RECORD_DATA = "RECORD_DATA";

    private IAccompany accompany;
    private RecordDataModel recordData;
    private AccompanyStepOneController controller;

    private RequiredSpinner spnPlaceCare, spnTypeCare, spnGender;
    private RequiredCalendarEditText edtBirthDate;

    private RequiredAutoComplete edtNumSus;
    private EditText edtRecord, edtHeight, edtWeight, edtDum, edtPregnancyWeeks, edtPreviousPregnancy, edtChildBirth;
    private Spinner spnShift, spnBreastFeeding, spnHomeCare;
    private RadioGroup rgrpVaccinates, rgrpPlannedPregnancy;

    public static AccompanyStepOneFragment newInstance(RecordDataModel recordData){
        AccompanyStepOneFragment fragment = new AccompanyStepOneFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RECORD_DATA, recordData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accompany = (IAccompany) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.content_acc_add_1, container, false);

        recordData = (RecordDataModel) getArguments().getSerializable(RECORD_DATA);
        controller = new AccompanyStepOneController(this);

        edtRecord = (EditText) view.findViewById(R.id.edt_acc_record);
        spnPlaceCare = (RequiredSpinner) view.findViewById(R.id.spn_acc_place_care);
        spnTypeCare = (RequiredSpinner) view.findViewById(R.id.spn_acc_type_care);
        spnShift = (Spinner) view.findViewById(R.id.spn_acc_shift);
        edtNumSus = (RequiredAutoComplete) view.findViewById(R.id.edt_acc_num_sus);
        spnGender = (RequiredSpinner) view.findViewById(R.id.spn_acc_gender);
        edtBirthDate = (RequiredCalendarEditText) view.findViewById(R.id.edt_acc_birth_date);
        edtHeight = (EditText) view.findViewById(R.id.edt_acc_height);
        edtWeight = (EditText) view.findViewById(R.id.edt_acc_weight);
        rgrpVaccinates = (RadioGroup) view.findViewById(R.id.rgrp_acc_vaccines);
        spnBreastFeeding = (Spinner) view.findViewById(R.id.spn_acc_breastfeeding);
        edtDum = (EditText) view.findViewById(R.id.edt_acc_dum);
        rgrpPlannedPregnancy = (RadioGroup) view.findViewById(R.id.rgrp_acc_planned_pregnancy);
        edtPregnancyWeeks = (EditText) view.findViewById(R.id.edt_acc_pregnancy_weeks);
        edtPreviousPregnancy = (EditText) view.findViewById(R.id.edt_acc_previous_pregnancy);
        edtChildBirth = (EditText) view.findViewById(R.id.edt_acc_child_birth);
        spnHomeCare = (Spinner) view.findViewById(R.id.spn_acc_home_care);

        edtNumSus.setAdapter(controller.getNumSusAdapter());
        spnPlaceCare.setAdapter(controller.getAdapter(R.array.place_care));
        spnTypeCare.setAdapter(controller.getAdapter(R.array.type_care));
        spnShift.setAdapter(controller.getAdapter(R.array.shift));
        spnGender.setAdapter(controller.getAdapter(R.array.gender));
        spnBreastFeeding.setAdapter(controller.getAdapter(R.array.breast_feeding));
        spnHomeCare.setAdapter(controller.getAdapter(R.array.home_care));

        if(recordData != null){
            fillFields();
        }
        edtNumSus.setAutoFillListener(controller.getSearchListener());
        edtBirthDate.setShowCalendarListener(controller.getCalendarListener());

        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    @Override
    public boolean isRequiredFieldsFilled() {
        return controller.isRequiredFieldsFilled(spnPlaceCare, spnTypeCare, edtNumSus, spnGender, edtBirthDate);
    }

    private void getFields(){
        long record = controller.getRecord(edtRecord);
        String placeCare = controller.getFields(spnPlaceCare);
        String typeCare = controller.getFields(spnTypeCare);
        String shift = controller.getFields(spnShift);
        CitizenModel citizen = CitizenPersistence.get(controller.getNumSus(edtNumSus));
        RecordDetails details = RecordDataPersistence.get(record, placeCare, typeCare, shift, citizen);

        float weight = controller.getWeight(edtWeight);
        int height = controller.getHeight(edtHeight);
        boolean vaccinates = controller.getVaccinates(rgrpVaccinates);
        Anthropometric anthropometric = RecordDataPersistence.get(weight, height, vaccinates);

        String breastFeeding = controller.getFields(spnBreastFeeding);
        String dum = controller.getFields(edtDum);
        boolean plannedPregnancy = controller.getPlannedPregnancy(rgrpPlannedPregnancy);
        int weeks = controller.getWeeks(edtPregnancyWeeks);
        int previous = controller.getPrevious(edtPreviousPregnancy);
        int childBirth = controller.getChildBirth(edtChildBirth);
        String homeCare = controller.getFields(spnHomeCare);
        KidAndPregnant kidAndPregnant = RecordDataPersistence.get(breastFeeding, dum, plannedPregnancy, weeks,
                previous, childBirth, homeCare);

        recordData = RecordDataPersistence.get(details, anthropometric, kidAndPregnant);
        accompany.send(recordData);
    }

    private void fillFields(){

        RecordDetails details = recordData.getRecordDetails();
        controller.fillField(edtRecord, StepsController.getEmptyOrValue(details.getRecord()));
        controller.fillPlaceCare(spnPlaceCare, details.getPlaceCare());
        controller.fillTypeCare(spnTypeCare, details.getTypeCare());
        controller.fillShift(spnShift, details.getShift());
        controller.fillNumSus(edtNumSus, StepsController.getEmptyOrValue(details.getNumSus()));
        controller.fillField(edtBirthDate, details.getBirthDate());
        controller.fillGender(spnGender, details.getGender());

        Anthropometric anthropometric = recordData.getAnthropometric();
        controller.fillField(edtWeight, StepsController.getEmptyOrValue(anthropometric.getWeight()));
        controller.fillField(edtHeight, StepsController.getEmptyOrValue(anthropometric.getHeight()));
        controller.fillVaccinates(rgrpVaccinates, anthropometric.isVaccinates());

        KidAndPregnant kidAndPregnant = recordData.getKidsPregnant();
        controller.fillBreastFeeding(spnBreastFeeding, kidAndPregnant.getBreastFeeding());
        controller.fillField(edtDum, kidAndPregnant.getDum());
        controller.fillPlannedPregnancy(rgrpPlannedPregnancy, kidAndPregnant.isPlannedPregnancy());
        controller.fillField(edtPregnancyWeeks, StepsController.getEmptyOrValue(kidAndPregnant.getWeeks()));
        controller.fillField(edtPreviousPregnancy, StepsController.getEmptyOrValue(kidAndPregnant.getPrevious()));
        controller.fillField(edtChildBirth, StepsController.getEmptyOrValue(kidAndPregnant.getChildBirth()));
        controller.fillHomeCare(spnHomeCare, kidAndPregnant.getHomeCare());
    }
}
