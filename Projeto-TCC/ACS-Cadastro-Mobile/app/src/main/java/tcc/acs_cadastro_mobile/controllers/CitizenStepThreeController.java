package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.persistence.HealthConditionsPersistence;
import tcc.acs_cadastro_mobile.subModels.Diseases;
import tcc.acs_cadastro_mobile.subModels.HeartDisease;
import tcc.acs_cadastro_mobile.subModels.Interment;
import tcc.acs_cadastro_mobile.subModels.KidneyDisease;
import tcc.acs_cadastro_mobile.subModels.Plant;
import tcc.acs_cadastro_mobile.subModels.Pregnant;
import tcc.acs_cadastro_mobile.subModels.RespiratoryDisease;

public class CitizenStepThreeController extends StepsController {

    private Fragment fragment;
    private RadioGroup.OnCheckedChangeListener listener;

    public CitizenStepThreeController(Fragment fragment) {
        super(fragment.getContext());
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

    public Pregnant getPregnant(RadioGroup radioGroup, EditText editText) {
        boolean isPregnant = isYesGroup(radioGroup, R.id.rgrp_ctz_pregnant_y);
        return HealthConditionsPersistence.getPregnant(isPregnant, getFields(editText));
    }

    public Diseases getDiseases(RadioGroup rgrpSmoker, RadioGroup rgrpAlcohol, RadioGroup rgrpDrugs,
                RadioGroup rgrpHypertension, RadioGroup rgrpDiabetes, RadioGroup rgrpAvc,
                RadioGroup rgrpHeartAttack, RadioGroup rgrpLeprosy, RadioGroup rgrpTuberculosis,
                RadioGroup rgrpCancer, RadioGroup rgrpInBed, RadioGroup rgrpDomiciled,
                RadioGroup rgrpOtherPractices, RadioGroup rgrpMentalHealth) {

        boolean smoker = isYesGroup(rgrpSmoker, R.id.rgrp_ctz_smoker_y);
        boolean alcohol = isYesGroup(rgrpAlcohol, R.id.rgrp_ctz_alcohol_y);
        boolean drugs = isYesGroup(rgrpDrugs, R.id.rgrp_ctz_drugs_y);
        boolean hypertension = isYesGroup(rgrpHypertension, R.id.rgrp_ctz_hypertension_y);
        boolean diabetis = isYesGroup(rgrpDiabetes, R.id.rgrp_ctz_diabetes_y);
        boolean avc = isYesGroup(rgrpAvc, R.id.rgrp_ctz_avc_y);
        boolean heartAttack = isYesGroup(rgrpHeartAttack, R.id.rgrp_ctz_heart_attack_y);
        boolean leprosy = isYesGroup(rgrpLeprosy, R.id.rgrp_ctz_leprosy_y);
        boolean tuberculosis = isYesGroup(rgrpTuberculosis, R.id.rgrp_ctz_tuberculosis_y);
        boolean cancer = isYesGroup(rgrpCancer, R.id.rgrp_ctz_cancer_y);
        boolean inBed = isYesGroup(rgrpInBed, R.id.rgrp_ctz_in_bed_y);
        boolean domiciled = isYesGroup(rgrpDomiciled, R.id.rgrp_ctz_domiciled_y);
        boolean otherPractices = isYesGroup(rgrpOtherPractices, R.id.rgrp_ctz_other_practices_y);
        boolean mentalHealth = isYesGroup(rgrpMentalHealth, R.id.rgrp_ctz_mental_health_y);

        return HealthConditionsPersistence.getDiseases(smoker, alcohol, drugs, hypertension, diabetis, avc,
                heartAttack, leprosy, tuberculosis, cancer, inBed, domiciled, otherPractices, mentalHealth);
    }

    public HeartDisease getHeartDisease(RadioGroup radioGroup, CheckBox... checkboxes) {
        boolean isHeartDisease = isYesGroup(radioGroup, R.id.rgrp_ctz_heart_disease_y);
        return HealthConditionsPersistence.getHeartDisease(isHeartDisease, getFields(checkboxes));
    }

    public KidneyDisease getKidneyDisease(RadioGroup radioGroup, CheckBox... checkboxes) {
        boolean kidneyDisease = isYesGroup(radioGroup, R.id.rgrp_ctz_kidney_disease_y);
        return HealthConditionsPersistence.getKidneyDisease(kidneyDisease, getFields(checkboxes));
    }

    public RespiratoryDisease getRespiratoryDisease(RadioGroup radioGroup, CheckBox... checkboxes) {
        boolean isRespiratoryDisease = isYesGroup(radioGroup, R.id.rgrp_ctz_respiratory_disease_y);
        return HealthConditionsPersistence.getRespiratoryDisease(isRespiratoryDisease, getFields(checkboxes));
    }

    public Interment getInterment(RadioGroup radioGroup, EditText editText) {
        boolean isInterment = isYesGroup(radioGroup, R.id.rgrp_ctz_interment_y);
        return HealthConditionsPersistence.getInterment(isInterment, getFields(editText));
    }

    public Plant getPlant(RadioGroup radioGroup, EditText editText) {
        boolean isPlant = isYesGroup(radioGroup, R.id.rgrp_ctz_plants_y);
        return HealthConditionsPersistence.getPlant(isPlant, getFields(editText));
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

    public void fillOtherPractices(RadioGroup rgrpOtherPractices, boolean otherPractices) {
        fillField(rgrpOtherPractices, otherPractices, R.id.rgrp_ctz_other_practices_y, R.id.rgrp_ctz_other_practices_n);
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
