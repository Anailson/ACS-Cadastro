package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.SpinnerAdapter;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete.AutoFillListener;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
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

    public AutoFillListener getSearchListener() {
        return getListener();
    }

    public ICalendarListener getCalendarListener() {
        return getListener();
    }

    public boolean isRequiredFieldsFilled(IRequiredView spnPlaceCare, IRequiredView spnTypeCare,
                                          IRequiredView edtNumSus, IRequiredView spnGender, IRequiredView edtBirthDate) {
        startErrors();
        applyError(spnPlaceCare);
        applyError(spnTypeCare);
        applyError(edtNumSus);
        applyError(spnGender);
        applyError(edtBirthDate);
        return hasError();
    }

    public ArrayAdapter getNumSusAdapter() {
        String[] numSus = CitizenPersistence.getNumSus();
        return new SpinnerAdapter(fragment.getContext()).getAdapter(numSus);
    }

    public long getRecord(EditText edtRecord) {
        return getLong(edtRecord);
    }

    public long getNumSus(AutoCompleteTextView edtNumSus) {
        return getLong(edtNumSus);
    }

    public float getWeight(EditText edtWeight) {
        return getFloat(edtWeight);
    }

    public int getHeight(EditText edtHeight) {
        return getInt(edtHeight);
    }

    public boolean getVaccinates(RadioGroup rgrpVaccinates) {
        return isYesGroup(rgrpVaccinates, R.id.rgrp_acc_vaccines_y);
    }

    public boolean getPlannedPregnancy(RadioGroup rgrpPlannedPregnancy) {
        return isYesGroup(rgrpPlannedPregnancy, R.id.rgrp_acc_planned_pregnancy_y);
    }

    public int getWeeks(EditText edtPregnanacyWeeks) {
        return getInt(edtPregnanacyWeeks);
    }

    public int getPrevious(EditText edtPreviousPregnancym) {
        return getInt(edtPreviousPregnancym);
    }

    public int getChildBirth(EditText edtChildBirth) {
        return getInt(edtChildBirth);
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

    private class Listeners implements AutoFillListener, ICalendarListener {

        //RequiredAutoComplete.AutoFillListener
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

