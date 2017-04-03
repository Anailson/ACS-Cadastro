package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;

public class CitizenStepTwoController {

    private Fragment fragment;
    private CompoundButton.OnCheckedChangeListener rgrpCheckedListener;

    public CitizenStepTwoController(Fragment fragment) {
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public CompoundButton.OnCheckedChangeListener getCheckedChangeListener() {

        if (rgrpCheckedListener == null) {
            rgrpCheckedListener = new OnRadioGroupChangeListener();
        }
        return rgrpCheckedListener;
    }

    private void enableCommunityPeople(boolean checked) {
        Activity activity = fragment.getActivity();
        EditText edtCommunityPeople = (EditText) activity.findViewById(R.id.edt_ctz_community_traditional);
        edtCommunityPeople.setEnabled(checked);
    }

    private void enableOrientationSexual(boolean checked) {
        Activity activity = fragment.getActivity();
        Spinner spnCommunityPeople = (Spinner) activity.findViewById(R.id.spn_ctz_orientation_sexual);
        spnCommunityPeople.setEnabled(checked);
    }

    private void enableDeficiency(boolean checked) {
        Activity activity = fragment.getActivity();
        CheckBox hearing = (CheckBox) activity.findViewById(R.id.chb_ctz_deficiency_hearing);
        CheckBox visual = (CheckBox) activity.findViewById(R.id.chb_ctz_deficiency_visual);
        CheckBox intellectual = (CheckBox) activity.findViewById(R.id.chb_ctz_deficiency_intellectual);
        CheckBox physical = (CheckBox) activity.findViewById(R.id.chb_ctz_deficiency_physical);
        CheckBox another = (CheckBox) activity.findViewById(R.id.chb_ctz_deficiency_another);

        hearing.setEnabled(checked);
        visual.setEnabled(checked);
        intellectual.setEnabled(checked);
        physical.setEnabled(checked);
        another.setEnabled(checked);
    }

    private class OnRadioGroupChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            switch (compoundButton.getId()) {
                case R.id.chb_ctz_community_traditional: enableCommunityPeople(checked); break;
                case R.id.chb_ctz_orientation_sexual: enableOrientationSexual(checked); break;
                case R.id.chb_ctz_deficiency: enableDeficiency(checked); break;
            }
        }
    }
}
