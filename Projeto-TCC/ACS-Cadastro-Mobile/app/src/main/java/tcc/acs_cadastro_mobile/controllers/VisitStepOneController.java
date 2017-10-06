package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.SpinnerAdapter;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.interfaces.IAutoFillListener;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.models.VisitModel;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.persistence.RecordVisitPersistence;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class VisitStepOneController extends StepsController{

    private Listeners listener;
    private Fragment fragment;

    public VisitStepOneController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    private Listeners getListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public IAutoFillListener getAutoFill(){
        return getListener();
    }

    public boolean isRequiredFieldsFilled(IRequiredView edtNumSus, IRequiredView edtBirthDate, IRequiredView spnGender) {
        startErrors();
        applyError(edtNumSus);
        applyError(edtBirthDate);
        applyError(spnGender);
        return hasError();
    }

    public RecordVisitModel get(RecordDetails recordDetails, boolean shared) {
        return RecordVisitPersistence.get(recordDetails, shared);
    }

    public RecordDetails get(EditText edtRecord, Spinner spnShift, RequiredAutoComplete edtNumSus) {
        long record = AcsRecordPersistence.getMinorValueIfBlank(VisitModel.class, VisitModel.RECORD, getLong(edtRecord));
        CitizenModel citizen = CitizenPersistence.get(getLong(edtNumSus));
        return RecordVisitPersistence.get(record, getFields(spnShift), citizen);
    }

    public ArrayAdapter getNumSusAdapter() {
        List<Long> numSus = CitizenPersistence.getNumSusAsList();
        return new SpinnerAdapter(fragment.getContext()).getAdapter(numSus);
    }

    public boolean getShared(RadioGroup rgrpShared) {
        return isYesGroup(rgrpShared, R.id.rgrp_vst_shared_y);
    }

    public long getRecord(EditText edtRecord) {
        return getLong(edtRecord);
    }

    public void fillShift(Spinner spnShift, String shift) {
        int index = getIndex(shift, R.array.shift);
        fillField(spnShift, index);
    }

    public void fillGender(RequiredSpinner spnGender, String gender) {
        int index = getIndex(gender, R.array.gender);
        fillField(spnGender, index);
    }

    public void fillShared(RadioGroup rgrpShared, boolean shared) {
        int yes = R.id.rgrp_vst_shared_y;
        int no = R.id.rgrp_vst_shared_n;
        fillField(rgrpShared, shared, yes, no);
    }

    private void fillCitizen(long numSus){
        Activity activity = fragment.getActivity();
        CitizenModel citizen = CitizenPersistence.get(numSus);
        if(numSus > AcsRecordPersistence.DEFAULT_INT && citizen != null){
            int index = getIndex(citizen.getGender(), R.array.gender);
            fillField((Spinner) activity.findViewById(R.id.spn_vst_gender), index);
            fillField((EditText) activity.findViewById(R.id.edt_vst_birth_date), citizen.getBirthDate());
        }else {
            clearCitizenData();
        }
    }
    private void clearCitizenData(){
        Activity activity = fragment.getActivity();
        fillField((Spinner) activity.findViewById(R.id.spn_vst_gender), 0);
        fillField((EditText) activity.findViewById(R.id.edt_vst_birth_date), "");
    }

    private class Listeners implements IAutoFillListener {

        @Override
        public void searching(EditText editText) {
            fillCitizen(getLong(editText));
        }

        @Override
        public void selectItem(EditText editText) {
            fillCitizen(getLong(editText));
        }

        @Override
        public void changedAfterSelected(EditText editText) {
            clearCitizenData();
        }
    }

}
