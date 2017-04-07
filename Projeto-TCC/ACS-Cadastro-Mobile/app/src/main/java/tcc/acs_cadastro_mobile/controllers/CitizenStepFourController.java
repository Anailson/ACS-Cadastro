package tcc.acs_cadastro_mobile.controllers;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;

public class CitizenStepFourController extends StepsController{

    private Fragment fragment;
    private RadioGroup.OnCheckedChangeListener onChangeListener;

    public CitizenStepFourController(Fragment fragment){
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource){
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public RadioGroup.OnCheckedChangeListener getOnChangeListener() {

        if(onChangeListener == null){
            onChangeListener = new OnChangeListener();
        }
        return onChangeListener;
    }

    public boolean isStreetSituation(RadioGroup radioGroup){
        int id = R.id.rgrp_ctz_street_y;
        return isYesGroup(radioGroup, id);
    }

    public boolean isBenefit(RadioGroup radioGroup){
        int id = R.id.rgrp_ctz_any_benefit_y;
        return isYesGroup(radioGroup, id);
    }

    public boolean isFamily(RadioGroup radioGroup){
        int id = R.id.rgrp_ctz_family_y;
        return isYesGroup(radioGroup, id);
    }

    public boolean[] getFoodOrigin(CheckBox... checkBoxes){
        return getFields(true, checkBoxes);
    }

    public String[] getInstitutionAnother(RadioGroup radioGroup, EditText editText){
        int id = R.id.rgrp_ctz_institution_another_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public String[] getFamilyVisit(RadioGroup radioGroup, EditText editText){
        int id = R.id.rgrp_ctz_family_visit_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public boolean[] getHygiene(RadioGroup radioGroup, CheckBox... checkBoxes){
        int id = R.id.rgrp_ctz_hygiene_y;
        return getFields(isYesGroup(radioGroup, id), checkBoxes);
    }

    public void fillStreetSituation(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_street_y, R.id.rgrp_ctz_street_n);
    }

    public void fillStreetTime(Spinner spinner, String position){
        fillField(spinner, position);
    }

    public void fillBenefit(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_any_benefit_y, R.id.rgrp_ctz_any_benefit_n);
    }

    public void fillFamily(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_family_y, R.id.rgrp_ctz_family_n);
    }

    public void fillFoodPerDay(Spinner spinner, String position){
        fillField(spinner, position);
    }

    public void fillFoodOrigin(boolean[] values, CheckBox... checkBoxes){
        fillField(checkBoxes, values);
    }

    public void fillInstitutionAnother(RadioGroup radioGroup, boolean checked, EditText editText, String value){
        int yes = R.id.rgrp_ctz_institution_another_y;
        int no = R.id.rgrp_ctz_institution_another_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillFamilyVisit(RadioGroup radioGroup, boolean checked, EditText editText, String value){
        int yes = R.id.rgrp_ctz_family_visit_y;
        int no = R.id.rgrp_ctz_family_visit_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillHygiene(RadioGroup radioGroup, boolean checked, boolean[] values, CheckBox... checkBoxes){
        int yes = R.id.rgrp_ctz_hygiene_y;
        int no = R.id.rgrp_ctz_hygiene_n;
        fillField(radioGroup, checked, checkBoxes, values, yes, no);
    }








    private void enableInstitutionAnother(int id){
        boolean checked = id == R.id.rgrp_ctz_institution_another_y;
        EditText editText = (EditText)fragment.getActivity().findViewById(R.id.edt_ctz_institution_another);
        enableView(editText, checked);
        if(!checked){
            fillField(editText, "");
        }
    }

    private void enableFamilyVisit(int id){
        boolean checked = id == R.id.rgrp_ctz_family_visit_y;
        EditText editText = (EditText)fragment.getActivity().findViewById(R.id.edt_ctz_family_visit);
        enableView(editText, checked);
        if(!checked){
            fillField(editText, "");
        }
    }

    private void enableHygiene(int id){
        boolean checked = id == R.id.rgrp_ctz_hygiene_y;
        CheckBox chbBath = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_bath);
        CheckBox chbSanitary = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_sanitary);
        CheckBox chbOral = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_oral);
        CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_hygiene_another);
        enableView(checked, chbBath, chbSanitary, chbOral, chbAnother);

        if(!checked){
            //fillField(editText, "");
        }
    }

    private class OnChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            switch (radioGroup.getId()){
                case R.id.rgrp_ctz_institution_another: enableInstitutionAnother(id); break;
                case R.id.rgrp_ctz_family_visit: enableFamilyVisit(id); break;
                case R.id.rgrp_ctz_hygiene: enableHygiene(id); break;
            }
        }
    }
}
