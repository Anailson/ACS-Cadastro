package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.EditText;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.persistence.ConditionPersistence;
import tcc.acs_cadastro_mobile.subModels.CommunicableDisease;
import tcc.acs_cadastro_mobile.subModels.ConditionDiseases;
import tcc.acs_cadastro_mobile.subModels.TrackingDiseases;

public class AccompanyStepTwoController extends StepsController {

    private Fragment fragment;

    public AccompanyStepTwoController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public ConditionsModel get(ConditionDiseases condition, CommunicableDisease communicable,
                               TrackingDiseases tracking, RealmList<RealmInt> anothers) {
        return ConditionPersistence.get(condition, communicable, tracking, anothers);
    }

    public ConditionDiseases getConditionDiseases(CheckBox chbAsthma, CheckBox chbMalnutrition,
                  CheckBox chbDiabetes, CheckBox chbDpoc, CheckBox chbHypertension, CheckBox chbObesity,
                  CheckBox chbPrenatal, CheckBox chbChildcare, CheckBox chbPuerperium, CheckBox chbSexualHealth,
                  CheckBox chbSmoking, CheckBox chbAlcohol, CheckBox chbDrugs, CheckBox chbMentalHealth,
                  CheckBox chbRehabilitation) {
        boolean[] values = getFields(chbAsthma, chbMalnutrition, chbDiabetes, chbDpoc, chbHypertension,
                chbObesity, chbPrenatal, chbChildcare, chbPuerperium, chbSexualHealth, chbSmoking,
                chbAlcohol, chbDrugs, chbMentalHealth, chbRehabilitation);
        return ConditionPersistence.getConditionDiseases(values);
    }

    public CommunicableDisease getCommunicableDisease(CheckBox chbTuberculosis, CheckBox chbLeprosy,
                                                      CheckBox chbDengue, CheckBox chbDst) {
        boolean[]values = getFields(chbTuberculosis, chbLeprosy, chbDengue, chbDst);
        return ConditionPersistence.getCommunicableDisease(values);
    }

    public TrackingDiseases getTrackingDiseases(CheckBox chbCervicalCancer, CheckBox chbBreastCancer,
                                                CheckBox chbCardiovascular) {
        boolean[] values = getFields(chbCervicalCancer, chbBreastCancer, chbCardiovascular);
        return ConditionPersistence.getTrackingDiseases(values);
    }

    public RealmList<RealmInt> getAnotherCids(EditText editText){
        String [] lines = editText.getText().toString().split(",");
        if(lines.length == 0) return new RealmList<>();

        RealmList<RealmInt> cids = new RealmList<>();
        for (String line : lines) {
            String cid = line.replaceAll(" ", "");
            if (cid.matches("[0-9]+")) {
                cids.add(new RealmInt(Integer.parseInt(cid)));
            }
        }
        return cids;
    }

    public void fillCids(RealmList<RealmInt> anothers, EditText edtAnothers) {
        if(anothers.size() > 0) {

            String text = "";
            for (RealmInt cid : anothers) {
                text += cid.getCode() + ", ";
            }
            text = text.substring(0, text.length() - 2);
            fillField(edtAnothers, text);
        }
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
        alert.setTitle(R.string.err_condition_required_title);
        alert.setMessage(R.string.err_condition_required_message);
        alert.setPositiveListener(R.string.btn_ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
