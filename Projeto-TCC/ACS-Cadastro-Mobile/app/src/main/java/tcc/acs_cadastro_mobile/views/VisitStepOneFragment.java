package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.controllers.VisitStepOneController;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;
import tcc.acs_cadastro_mobile.customViews.RequiredCalendarEditText;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.interfaces.IVisit;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.persistence.RecordVisitPersistence;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class VisitStepOneFragment extends Fragment implements IRequiredFields {

    private static final String RECORD_VISIT_MODEL = "RECORD_VISIT_MODEL";

    private VisitStepOneController controller;
    private IVisit visit;
    private RecordVisitModel details;

    private Spinner spnShift;
    private EditText edtRecord;
    private RequiredAutoComplete edtNumSus;
    private RequiredSpinner spnGender;
    private RequiredCalendarEditText edtBirthDate;
    private RadioGroup rgrpShared;


    public static Fragment newInstance(RecordVisitModel details){
        Fragment fragment = new VisitStepOneFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RECORD_VISIT_MODEL, details);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        visit = (IVisit) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_vst_add_1, container, false);
        details = (RecordVisitModel) getArguments().getSerializable(RECORD_VISIT_MODEL);

        controller = new VisitStepOneController(this);
        spnShift = (Spinner) view.findViewById(R.id.spn_vst_shift);
        edtRecord = (EditText) view.findViewById(R.id.edt_vst_record);
        edtNumSus = (RequiredAutoComplete) view.findViewById(R.id.edt_vst_num_sus);
        spnGender = (RequiredSpinner) view.findViewById(R.id.spn_vst_gender);
        edtBirthDate = (RequiredCalendarEditText) view.findViewById(R.id.edt_vst_birth_date);
        rgrpShared = (RadioGroup) view.findViewById(R.id.rgrp_vst_shared);

        edtBirthDate.setEnabled(false);
        spnGender.setEnabled(false);
        edtNumSus.setAdapter(controller.getNumSusAdapter());
        spnGender.setAdapter(controller.getAdapter(R.array.gender));
        spnShift.setAdapter(controller.getAdapter(R.array.shift));

        if(details != null){
            fillFields();
        }

        edtNumSus.setAutoFillListener(controller.getAutoFill());

        return view;
    }

    @Override
    public void onDetach() {
        RecordDetails recordDetails = controller.get(edtRecord, spnShift, edtNumSus);
        boolean shared = controller.getShared(rgrpShared);

        details = controller.get(recordDetails, shared);
        visit.send(details);
        super.onDetach();
    }

    @Override
    public boolean isRequiredFieldsFilled() {
        return controller.isRequiredFieldsFilled(edtNumSus, edtBirthDate, spnGender);
    }

    private void fillFields(){
        CitizenModel citizen = details.getDetails().getCitizen();
        controller.fillField(edtRecord, StepsController.getEmptyOrValue(details.getRecord()));
        controller.fillShift(spnShift, details.getDetails().getShift());
        controller.fillField(edtNumSus, citizen.getNumSus());
        controller.fillField(edtBirthDate, citizen.getBirthDate());
        controller.fillGender(spnGender, citizen.getGender());
        controller.fillShared(rgrpShared, details.isShared());
    }
}
