package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class CitizenStepTwoController extends StepsController{

    private Fragment fragment;
    private RadioGroup.OnCheckedChangeListener rgrpCheckedListener;

    public CitizenStepTwoController(Fragment fragment) {
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public RadioGroup.OnCheckedChangeListener getCheckedChangeListener() {

        if (rgrpCheckedListener == null) {
            rgrpCheckedListener = new OnRadioGroupChangeListener();
        }
        return rgrpCheckedListener;
    }

    public boolean isSchool(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_school_y);
    }

    public boolean isCaregiver(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_caregiver_y);
    }

    public boolean isCommunityGroup(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_community_group_y);
    }

    public boolean isHealthPlan(RadioGroup radioGroup) {
        return isYesGroup(radioGroup, R.id.rgrp_ctz_health_plan_y);
    }

    public String[] getCommunityTraditional(RadioGroup radioGroup, EditText editText) {

        int id = R.id.rgrp_ctz_community_traditional_y;
        return getFields(isYesGroup(radioGroup, id), editText);
    }

    public String[] getSexualOrientation(RadioGroup radioGroup, Spinner spinner) {
        int id = R.id.rgrp_ctz_sexual_orientation_y;
        return getFields(isYesGroup(radioGroup, id), spinner);
    }

    public boolean[] getDeficiency(RadioGroup radioGroup, CheckBox... checkBoxes) {
        int id = R.id.rgrp_ctz_deficiency_y;
        return getFields(isYesGroup(radioGroup, id), checkBoxes);
    }

    public void fillSchool(RadioGroup radioGroup, boolean bool) {
        fillField(radioGroup, bool, R.id.rgrp_ctz_school_y, R.id.rgrp_ctz_school_n);
    }

    public void fillCaregiver(RadioGroup radioGroup, boolean bool) {
        fillField(radioGroup, bool, R.id.rgrp_ctz_caregiver_y, R.id.rgrp_ctz_caregiver_n);
    }

    public void fillCommunityGroup(RadioGroup radioGroup, boolean bool) {
        fillField(radioGroup, bool, R.id.rgrp_ctz_community_group_y, R.id.rgrp_ctz_community_group_n);
    }

    public void fillHealthPlan(RadioGroup radioGroup, boolean bool) {
        fillField(radioGroup, bool, R.id.rgrp_ctz_health_plan_y, R.id.rgrp_ctz_health_plan_n);
    }

    public void fillCommunityTraditional(RadioGroup radioGroup, EditText editText, String[] value, boolean checked) {
        //TODO Don't fill when return to this view
        int yes = R.id.rgrp_ctz_community_traditional_y;
        int no = R.id.rgrp_ctz_plants_n;
        fillField(radioGroup, checked, editText, value[CitizenModel.VALUE], yes, no);
    }

    public void fillSexualOrientation(RadioGroup radioGroup, boolean enable, Spinner spinner, String index) {
        int no = R.id.rgrp_ctz_sexual_orientation_n;
        int yes = R.id.rgrp_ctz_sexual_orientation_y;
        fillField(radioGroup, enable, spinner, index, yes, no);
    }

    public void fillDeficiency(RadioGroup radioGroup, boolean enable, boolean[] deficiency, CheckBox... checkBoxes) {
        int no = R.id.rgrp_ctz_deficiency_n;
        int yes  = R.id.rgrp_ctz_deficiency_y;
        fillField(radioGroup, enable, checkBoxes, deficiency, yes, no);
    }


    private void enableCommunityTraditional(int idRadioButton) {
        boolean enable = idRadioButton == R.id.rgrp_ctz_community_traditional_y;
        EditText editText = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_community_traditional);
        if (!enable) {
            fillField(editText, "");
        }
        enableView(editText, enable);
    }

    private void enableOrientationSexual(int idRadioButton) {
        boolean enable = idRadioButton == R.id.rgrp_ctz_sexual_orientation_y;
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_sexual_orientation);
        if (!enable) {
            fillField(spinner, 0);
        }
        enableView(spinner, enable);
    }

    private void enableDeficiency(int idRadioButton) {

        boolean enable = idRadioButton == R.id.rgrp_ctz_deficiency_y;
        CheckBox chbHearing = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_hearing);
        CheckBox chbVisual = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_visual);
        CheckBox chbIntellectual = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_intellectual);
        CheckBox chbPhysical = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_physical);
        CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_another);
        enableView(enable, chbHearing, chbVisual, chbIntellectual, chbPhysical, chbAnother);
    }

    private class OnRadioGroupChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {

            switch (radioGroup.getId()) {
                case R.id.rgrp_ctz_community_traditional:
                    enableCommunityTraditional(id);
                    break;
                case R.id.rgrp_ctz_sexual_orientation:
                    enableOrientationSexual(id);
                    break;
                case R.id.rgrp_ctz_deficiency:
                    enableDeficiency(id);
                    break;
            }
        }
    }
}
