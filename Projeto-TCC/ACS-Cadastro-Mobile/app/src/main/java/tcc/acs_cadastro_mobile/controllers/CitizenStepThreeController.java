package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;

public class CitizenStepThreeController extends StepsController {

    private Fragment fragment;
    private RadioGroup.OnCheckedChangeListener listener;

    public CitizenStepThreeController(Fragment fragment) {
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public RadioGroup.OnCheckedChangeListener getRadioChangeListener() {
        if (listener == null) {
            listener = new OnClickListener();
        }
        return listener;
    }

    public String[] getPregnant(RadioGroup radioGroup, EditText editText) {
        int id = R.id.rgrp_ctz_pregnant_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public boolean isSmoker(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_smoker_y);
    }

    public boolean isAlcohol(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_alcohol_y);
    }

    public boolean isDrugs(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_drugs_y);
    }

    public boolean isHypertension(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_hypertension_y);
    }

    public boolean isDiabetes(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_diabetes_y);
    }

    public boolean isAvc(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_avc_y);
    }

    public boolean isHeartAttack(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_heart_attack_y);
    }

    public boolean isLeprosy(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_leprosy_y);
    }

    public boolean isTuberculosis(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_tuberculosis_y);
    }

    public boolean isCancer(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_cancer_y);
    }

    public boolean isInBed(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_in_bed_y);
    }

    public boolean isDomiciled(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_domiciled_y);
    }

    public boolean isMentalHealth(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_mental_health_y);
    }

    public boolean[] getHeartDisease(RadioGroup radioGroup, CheckBox... checkBoxes) {
        int id = R.id.rgrp_ctz_heart_disease_y;
        return getFields(isYesGroup(radioGroup, id), checkBoxes);
    }

    public boolean[] getKidneyDisease(RadioGroup radioGroup, CheckBox... checkBoxes) {
        int id = R.id.rgrp_ctz_kidney_disease_y;
        return getFields(isYesGroup(radioGroup, id), checkBoxes);
    }

    public boolean[] getRespiratoryDisease(RadioGroup radioGroup, CheckBox... checkBoxes) {
        int id = R.id.rgrp_ctz_respiratory_disease_y;
        return getFields(isYesGroup(radioGroup, id), checkBoxes);
    }

    public String[] getInterment(RadioGroup radioGroup, EditText editText) {
        int id = R.id.rgrp_ctz_interment_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public String[] getPlants(RadioGroup radioGroup, EditText editText) {
        int id = R.id.rgrp_ctz_plants_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public void fillPregnant(EditText editText, String value, RadioGroup radioGroup, boolean checked) {
        int yes = R.id.rgrp_ctz_pregnant_y;
        int no = R.id.rgrp_ctz_pregnant_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillSmoker(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_smoker_y, R.id.rgrp_ctz_smoker_n);
    }

    public void fillAlcohol(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_alcohol_y, R.id.rgrp_ctz_alcohol_n);
    }

    public void fillDrugs(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_drugs_y, R.id.rgrp_ctz_drugs_n);
    }

    public void fillHypertension(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_hypertension_y, R.id.rgrp_ctz_hypertension_n);
    }

    public void fillDiabetes(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_diabetes_y, R.id.rgrp_ctz_diabetes_n);
    }

    public void fillAvc(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_avc_y, R.id.rgrp_ctz_avc_n);
    }

    public void fillHeartAttack(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_heart_attack_y, R.id.rgrp_ctz_heart_attack_n);
    }

    public void fillLeprosy(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_leprosy_y, R.id.rgrp_ctz_leprosy_n);
    }

    public void fillTuberculosis(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_tuberculosis_y, R.id.rgrp_ctz_tuberculosis_n);
    }

    public void fillCancer(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_cancer_y, R.id.rgrp_ctz_cancer_n);
    }

    public void fillInBed(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_in_bed_y, R.id.rgrp_ctz_in_bed_n);
    }

    public void fillDomiciled(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_domiciled_y, R.id.rgrp_ctz_domiciled_n);
    }

    public void fillMentalHealth(RadioGroup radioGroup, boolean checked) {
        fillField(radioGroup, checked, R.id.rgrp_ctz_mental_health_y, R.id.rgrp_ctz_mental_health_n);
    }

    public void fillHeartDisease(RadioGroup radioGroup, boolean checked, boolean[] values, CheckBox... checkBoxes) {
        int yes = R.id.rgrp_ctz_heart_disease_y;
        int no = R.id.rgrp_ctz_heart_disease_n;
        fillField(radioGroup, checked, checkBoxes, values,  yes, no);
    }

    public void fillKidneyDisease(RadioGroup radioGroup, boolean checked, boolean[] values, CheckBox... checkBoxes) {
        int yes = R.id.rgrp_ctz_kidney_disease_y;
        int no = R.id.rgrp_ctz_kidney_disease_n;
        fillField(radioGroup, checked,checkBoxes,  values, yes, no);
    }

    public void fillRespiratoryDisease(RadioGroup radioGroup, boolean checked, boolean[] values, CheckBox... checkBoxes) {
        int yes = R.id.rgrp_ctz_respiratory_disease_y;
        int no = R.id.rgrp_ctz_respiratory_disease_n;
        fillField(radioGroup, checked, checkBoxes, values, yes, no);
    }

    public void fillInterment(EditText editText, String value, RadioGroup radioGroup, boolean checked) {
        int yes = R.id.rgrp_ctz_interment_y;
        int no = R.id.rgrp_ctz_interment_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillPlants( RadioGroup radioGroup, boolean checked, EditText editText, String value) {
        int yes = R.id.rgrp_ctz_plants_y;
        int no = R.id.rgrp_ctz_plants_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    private void enablePregnant(int id) {
        boolean checked = id == R.id.rgrp_ctz_pregnant_y;
        EditText editText = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_pregnant);
        if (!checked) {
            fillField(editText, "");
        }
        enableView(editText, checked);
    }

    private void enableHeartDisease(int id) {
        boolean checked = id == R.id.rgrp_ctz_heart_disease_y;

        CheckBox chbInsufficiency = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_cardiac_insufficiency);
        CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_heart_another);
        CheckBox chbUnknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_heart_dont_know);
        enableView(checked, chbInsufficiency, chbAnother, chbUnknown);
    }

    private void enableKidneyDisease(int id) {
        boolean checked = id == R.id.rgrp_ctz_kidney_disease_y;

        CheckBox chbInsufficiency = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_renal_insufficiency);
        CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_renal_another);
        CheckBox chbUnknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_renal_dont_know);
        enableView(checked, chbInsufficiency, chbAnother, chbUnknown);
    }

    private void enableRespiratoryDisease(int id) {
        boolean checked = id == R.id.rgrp_ctz_respiratory_disease_y;

        CheckBox chbAsthma = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_asthma);
        CheckBox chbEmphysema = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_emphysema);
        CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_respiratory_another);
        CheckBox chbUnknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_respiratory_dont_know);
        enableView(checked, chbAsthma, chbEmphysema, chbAnother, chbUnknown);
    }

    private void enableInterment(int id) {
        boolean checked = id == R.id.rgrp_ctz_interment_y;
        EditText editText = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_interment);
        enableView(editText, checked);
    }

    private void enablePlants(int id) {
        boolean checked = id == R.id.rgrp_ctz_plants_y;
        EditText editText = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_plants);
        enableView(editText, checked);
    }

    private class OnClickListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {

            switch (radioGroup.getId()) {
                case R.id.rgrp_ctz_pregnant:
                    enablePregnant(id);
                    break;
                case R.id.rgrp_ctz_heart_disease:
                    enableHeartDisease(id);
                    break;
                case R.id.rgrp_ctz_kidney_disease:
                    enableKidneyDisease(id);
                    break;
                case R.id.rgrp_ctz_respiratory_disease:
                    enableRespiratoryDisease(id);
                    break;
                case R.id.rgrp_ctz_interment:
                    enableInterment(id);
                    break;
                case R.id.rgrp_ctz_plants:
                    enablePlants(id);
                    break;
            }
        }
    }
}
