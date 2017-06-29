package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;


public class AccompanyStepFourController extends StepsController {

    private Fragment fragment;

    public AccompanyStepFourController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public boolean getObservation(RadioGroup rgrpObservation) {
        return isYesGroup(rgrpObservation, R.id.rgrp_acc_observation_y);
    }

    public void fillObservation(boolean b, RadioGroup rgrpObservation) {
        int yes = R.id.rgrp_acc_observation_y;
        int no = R.id.rgrp_acc_observation_n;
        fillField(rgrpObservation, b, yes, no);
    }

    public boolean isRequiredFieldsFilled(CheckBox... checkBoxes) {
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                return true;
            }
        }
        showAlert();
        return false;
    }

    private void showAlert(){
        DefaultAlert alert = new DefaultAlert(fragment.getContext());
        alert.setTitle(R.string.err_conduct_required_title);
        alert.setMessage(R.string.err_conduct_required_message);
        alert.setPositiveListener(new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
