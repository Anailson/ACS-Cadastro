package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.SpinnerAdapter;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;
import tcc.acs_cadastro_mobile.interfaces.IAutoFillListener;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.persistence.RecordDataPersistence;
import tcc.acs_cadastro_mobile.subModels.Anthropometric;
import tcc.acs_cadastro_mobile.subModels.KidAndPregnant;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class AccompanyStepOneController extends StepsController {
    private Fragment fragment;
    private Listeners listener;

    public AccompanyStepOneController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    private Listeners getListener() {
        if (listener == null) {
            listener = new Listeners();
        }
        return listener;
    }

    public IAutoFillListener getSearchListener() {
        return getListener();
    }

    public ICalendarListener getCalendarListener() {
        return getListener();
    }

    public boolean isRequiredFieldsFilled(IRequiredView spnPlaceCare, IRequiredView spnTypeCare,
                                          IRequiredView edtNumSus, IRequiredView spnGender, IRequiredView edtBirthDate) {
        //TODO: Is required NumSus? Ever? isValidNumSus((EditText) edtNumSus);

        startErrors();
        applyError(spnPlaceCare);
        applyError(spnTypeCare);
        applyError(edtNumSus);
        applyError(spnGender);
        applyError(edtBirthDate);
        return hasError();
    }

    public RecordDataModel get(RecordDetails details, Anthropometric anthropometric, KidAndPregnant kidAndPregnant) {
        return RecordDataPersistence.get(details, anthropometric, kidAndPregnant);
    }

    public RecordDetails getDetails(EditText edtRecord, EditText edtNumSus, Spinner spnPlaceCare,
                        Spinner spnTypeCare, Spinner spnShift) {
        long record = AccompanyPersistence.getRecordIfBlack(getLong(edtRecord));
        CitizenModel citizen = CitizenPersistence.get(getLong(edtNumSus));
        return RecordDataPersistence.get(record, getFields(spnPlaceCare), getFields(spnTypeCare), getFields(spnShift), citizen);
    }

    public Anthropometric getAnthropometric(EditText edtWeight, EditText edtHeight, RadioGroup rgrpVaccinates) {
        boolean vaccinate = isYesGroup(rgrpVaccinates, R.id.rgrp_acc_vaccines_y);
        return RecordDataPersistence.get(getFloat(edtWeight), getInt(edtHeight), vaccinate);
    }

    public KidAndPregnant getKidAndPregnant(Spinner spnBreastFeeding, EditText edtDum,
                        RadioGroup rgrpPlannedPregnancy, EditText edtPregnancyWeeks, EditText edtPrevious,
                        EditText edtChildBirth, Spinner spnHomeCare) {
        boolean planned = isYesGroup(rgrpPlannedPregnancy, R.id.rgrp_acc_planned_pregnancy_y);
        return RecordDataPersistence.get(getFields(spnBreastFeeding), getFields(edtDum), planned, getInt(edtPregnancyWeeks),
                getInt(edtPrevious), getInt(edtChildBirth), getFields(spnHomeCare));
    }

    public ArrayAdapter getNumSusAdapter() {
        List<Long> numSus = CitizenPersistence.getNumSusAsList();
        return new SpinnerAdapter(fragment.getContext()).getAdapter(numSus);
    }

    public long getRecord(EditText edtRecord) {
        return getLong(edtRecord);
    }

    public long getNumSus(AutoCompleteTextView edtNumSus) {
        return getLong(edtNumSus);
    }

    public void fillPlaceCare(Spinner spnPlaceCare, String placeCare) {
        int position = getIndex(placeCare, R.array.place_care);
        fillField(spnPlaceCare, position);
    }

    public void fillTypeCare(Spinner spnTypeCare, String typeCare) {
        int position = getIndex(typeCare, R.array.type_care);
        fillField(spnTypeCare, position);
    }

    public void fillShift(Spinner spnShift, String shift) {
        fillField(spnShift, getIndex(shift, R.array.shift));
    }

    public void fillNumSus(RequiredAutoComplete edtNumSus, String s) {

        edtNumSus.setAutoFillListener(null);
        fillField(edtNumSus, s);
        edtNumSus.setAutoFillListener(getListener());
    }

    public void fillGender(Spinner spnGender, String gender) {
        int position = getIndex(gender, R.array.gender);
        fillField(spnGender, position);
    }

    public void fillVaccinates(RadioGroup rgrpVaccinates, boolean vaccinates) {
        int yes = R.id.rgrp_acc_vaccines_y;
        int no = R.id.rgrp_acc_vaccines_n;
        fillField(rgrpVaccinates, vaccinates, yes, no);
    }

    public void fillBreastFeeding(Spinner spnBreastFeeding, String breastFeeding) {
        int position = getIndex(breastFeeding, R.array.breast_feeding);
        fillField(spnBreastFeeding, position);
    }

    public void fillPlannedPregnancy(RadioGroup rgrpPlannedPregnancy, boolean plannedPregnancy) {
        int yes = R.id.rgrp_acc_planned_pregnancy_y;
        int no = R.id.rgrp_acc_planned_pregnancy_n;
        fillField(rgrpPlannedPregnancy, plannedPregnancy, yes, no);
    }

    public void fillHomeCare(Spinner spnHomeCare, String homeCare) {
        int position = getIndex(homeCare, R.array.home_care);
        fillField(spnHomeCare, position);
    }

    private void fillCitizenData(String text) {

        CitizenModel citizen = CitizenPersistence.get(Long.valueOf(text));
        Activity activity = fragment.getActivity();

        if (citizen != null) {

            Spinner spnGender = (Spinner) activity.findViewById(R.id.spn_acc_gender);
            EditText edtBirthDate = (EditText) activity.findViewById(R.id.edt_acc_birth_date);

            fillField(spnGender, getIndex(citizen.getGender(), R.array.gender));
            fillField(edtBirthDate, citizen.getBirthDate());
        } else {
            clearCitizenData();
        }
    }

    private void clearCitizenData() {

        Spinner spnGender = (Spinner) fragment.getActivity().findViewById(R.id.spn_acc_gender);
        fillField(spnGender, 0);

        EditText edtBirthDate = (EditText) fragment.getActivity().findViewById(R.id.edt_acc_birth_date);
        fillField(edtBirthDate, "");
    }

    private class Listeners implements IAutoFillListener, ICalendarListener {

        //RequiredAutoComplete.IAutoFillListener
        @Override
        public void searching(EditText editText) {
            fillCitizenData(editText.getText().toString());
        }

        @Override
        public void selectItem(EditText editText) {
            searching(editText);
        }

        @Override
        public void changedAfterSelected(EditText editText) {
            clearCitizenData();
        }

        //ICalendarListener
        @Override
        public void onOk(int id, CalendarActivity.Date date) {
            ((EditText) fragment.getActivity().findViewById(id)).setText(date.formattedDate(fragment.getContext()));
        }

        @Override
        public void onCancel() {
        }
    }
}

