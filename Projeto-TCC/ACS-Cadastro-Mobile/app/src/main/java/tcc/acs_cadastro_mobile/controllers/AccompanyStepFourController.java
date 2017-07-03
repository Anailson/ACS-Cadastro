package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.persistence.NasfConductPersistence;
import tcc.acs_cadastro_mobile.subModels.Conduct;
import tcc.acs_cadastro_mobile.subModels.Forwarding;
import tcc.acs_cadastro_mobile.subModels.NASF;


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

    public NasfConductModel get(boolean observation, NASF nasf, Conduct conduct, Forwarding forwarding) {
        return NasfConductPersistence.get(observation, nasf, conduct, forwarding);
    }

    public NASF getNasf(CheckBox chbEvaluation, CheckBox chbProcedures, CheckBox chbPrescription) {
        boolean [] values = getFields(chbEvaluation, chbProcedures, chbPrescription);
        return NasfConductPersistence.getNasf(values);
    }

    public Conduct getConduct(CheckBox chbScheduledAppointment, CheckBox chbScheduledCare, CheckBox chbGroupScheduling,
                              CheckBox chbNasfScheduling, CheckBox chbRrelsease) {
        boolean [] values = getFields(chbScheduledAppointment, chbScheduledCare, chbGroupScheduling,
                chbNasfScheduling, chbRrelsease);
        return NasfConductPersistence.getConduct(values);
    }

    public Forwarding getForwarding(CheckBox chbInTheDay, CheckBox chbSpecializedService, CheckBox chbCaps,
                            CheckBox chbHospitalization, CheckBox chbUrgency, CheckBox chbHomeCareService,
                            CheckBox chbIntersectoral) {
        boolean[] values = getFields(chbInTheDay, chbSpecializedService, chbCaps, chbHospitalization,
                chbUrgency, chbHomeCareService, chbIntersectoral);
        return NasfConductPersistence.getForwarding(values);
    }
}
