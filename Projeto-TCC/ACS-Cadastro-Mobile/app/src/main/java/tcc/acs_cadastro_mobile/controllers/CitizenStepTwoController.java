package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class CitizenStepTwoController {

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

    public String[] getIndexAndValue(Spinner spinner) {
        return new String[]{spinner.getSelectedItemPosition() + "", spinner.getSelectedItem().toString()};
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

        if (isYesGroup(radioGroup, R.id.rgrp_ctz_community_traditional_y)) {
            return new String[]{"1", editText.getText().toString()};
        }

        return new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
    }

    public String[] getSexualOrientation(RadioGroup radioGroup, Spinner spinner) {

        if (isYesGroup(radioGroup, R.id.rgrp_ctz_sexual_orientation_y)) {
            return getIndexAndValue(spinner);
        }
        return new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
    }

    public boolean[] getDeficiency(RadioGroup radioGroup, CheckBox... checkBoxes) {

        if (isYesGroup(radioGroup, R.id.rgrp_ctz_deficiency_y)) {
            boolean[] values = new boolean[checkBoxes.length + 1];
            values[0] = true;
            for (int i = 0; i < checkBoxes.length; i++) {
                values[i + 1] = checkBoxes[i].isChecked();
            }
            return values;
        }

        return new boolean[]{false, false, false, false, false, false};
    }

    public void fillField(Spinner spinner, String index) {
        int position = index.equals(String.valueOf(CitizenModel.INT_DEFAULT_VALUE)) ? 0 : Integer.parseInt(index);
        fillField(spinner, position);
    }

    public void fillField(EditText editText, String text) {
        editText.setText(text);
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

    public void fillCommunityTraditional(RadioGroup radioGroup, EditText editText, String[] value, boolean enable) {

        int id = R.id.rgrp_ctz_community_traditional_n;
        String text = "";

        if (enable) {
            id = R.id.rgrp_ctz_community_traditional_y;
            text = value[CitizenModel.VALUE];
        }

        fillField(editText, text);
        enableView(editText, enable);
        /*TODO What happens when Listener is enabled before RadioGroup check up your child? (See fillSexualOrientation() and fillDeficiency too)*/
        radioGroup.setOnCheckedChangeListener(null);
        radioGroup.check(id);
        radioGroup.setOnCheckedChangeListener(getCheckedChangeListener());
    }

    public void fillSexualOrientation(RadioGroup radioGroup, Spinner spinner, String index, boolean enable) {

        int id = R.id.rgrp_ctz_sexual_orientation_n;
        int position = 0;
        if (enable) {
            id = R.id.rgrp_ctz_sexual_orientation_y;
            position = Integer.parseInt(index);
        }
        fillField(spinner, position);
        enableView(spinner, enable);
        radioGroup.setOnCheckedChangeListener(null);
        radioGroup.check(id);
        radioGroup.setOnCheckedChangeListener(getCheckedChangeListener());
    }

    public void fillDeficiency(RadioGroup radioGroup, boolean enable, boolean[] deficiency, CheckBox... checkBoxes) {
        int id = R.id.rgrp_ctz_deficiency_n;
        if (enable) {
            id = R.id.rgrp_ctz_deficiency_y;
            for (int i = 0; i < checkBoxes.length; i++) {
                fillField(checkBoxes[i], deficiency[i + 1]);
            }
        } else {
            for (CheckBox checkBox : checkBoxes) {
                enableView(checkBox, false);
            }
        }
        radioGroup.setOnCheckedChangeListener(null);
        radioGroup.check(id);
        radioGroup.setOnCheckedChangeListener(getCheckedChangeListener());
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
        CheckBox[] checkBoxes = new CheckBox[5];
        checkBoxes[0] = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_hearing);
        checkBoxes[1] = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_visual);
        checkBoxes[2] = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_intellectual);
        checkBoxes[3] = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_physical);
        checkBoxes[4] = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_deficiency_another);

        for (CheckBox checkBox : checkBoxes) {
            enableView(checkBox, enable);
        }
        if (!enable) {
            for (CheckBox checkBox : checkBoxes) {
                fillField(checkBox, false);
            }
        }
    }

    private boolean isYesGroup(RadioGroup radioGroup, int id) {
        return radioGroup.getCheckedRadioButtonId() == id;
    }

    private void enableView(View view, boolean enable) {
        view.setEnabled(enable);
    }

    private void fillField(Spinner spinner, int position) {
        spinner.setSelection(position);
    }

    private void fillField(CheckBox checkBox, boolean checked) {
        checkBox.setChecked(checked);
    }

    private void fillField(RadioGroup radioGroup, boolean bool, int yes, int no){
        int id = no;
        if (bool) {
            id = yes;
        }
        radioGroup.check(id);
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
