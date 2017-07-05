package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.persistence.ReasonsVisitPersistence;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;

public class VisitStepTwoController extends StepsController{

    private Fragment fragment;
    public VisitStepTwoController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public boolean isRequiredFieldsFilled(IRequiredView spnResult, CheckBox... checkBoxes) {
        return isRequiredFieldsFilled(checkBoxes) && isRequiredFieldsFilled(spnResult);
    }

    private boolean isRequiredFieldsFilled(IRequiredView spnResult){
        startErrors();
        applyError(spnResult);
        return hasError();
    }

    private boolean isRequiredFieldsFilled(CheckBox[] checkBoxes){

        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()) return true;
        }
        showAlert();
        return false;
    }

    public void fillResult(RequiredSpinner spnResult, String result) {
        fillField(spnResult, getIndex(result, R.array.visit_result));
    }

    private void showAlert(){
        DefaultAlert alert = new DefaultAlert(fragment.getContext());
        alert.setTitle(R.string.err_visit_required_title);
        alert.setMessage(R.string.err_visit_required_message);
        alert.setPositiveListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();

    }

    public ReasonsVisitModel get(ActiveSearch activeSearch, Following following, AnotherReasons anotherReasons, String result) {
        return ReasonsVisitPersistence.get(activeSearch, following, anotherReasons, result);
    }

    public ActiveSearch getActiveSearchs(CheckBox chbAppointment, CheckBox chbExam, CheckBox chbVaccine, CheckBox chbConditions) {
        return ReasonsVisitPersistence.getActiveSearchs(getFields(chbAppointment, chbExam, chbVaccine, chbConditions));
    }

    public Following getFollowing(CheckBox chbPregnant, CheckBox chbPuerpera, CheckBox chbNewborn,
                      CheckBox chbChild, CheckBox chbMalnutrition, CheckBox chbRehabilitationDeficiency,
                      CheckBox chbHypertension, CheckBox chbDiabetes, CheckBox chbAsthma, CheckBox chbCopdEmphysema,
                      CheckBox chbCancer, CheckBox chbChronicDiseases, CheckBox chbLeprosy, CheckBox chbTuberculosis,
                      CheckBox chbRespiratory, CheckBox chbSmoker, CheckBox chbHomeBedding, CheckBox chbVulnerability,
                      CheckBox chbBolsaFamília, CheckBox chbMentalHealth, CheckBox chbAlcohol, CheckBox chbDrugs) {
        boolean[] values = getFields(chbPregnant, chbPuerpera, chbNewborn, chbChild, chbMalnutrition,
                chbRehabilitationDeficiency, chbHypertension, chbDiabetes, chbAsthma, chbCopdEmphysema,
                chbCancer, chbChronicDiseases, chbLeprosy, chbTuberculosis, chbRespiratory, chbSmoker,
                chbHomeBedding, chbVulnerability, chbBolsaFamília, chbMentalHealth, chbAlcohol, chbDrugs);
        return ReasonsVisitPersistence.getFollowing(values);
    }

    public AnotherReasons getAnotherReasons(CheckBox chbRecordUpdate, CheckBox chbPeriodicVisit,
                        CheckBox chbInternment, CheckBox chbControlEnvironments, CheckBox chbCollectiveActivities,
                        CheckBox chbGuidance, CheckBox chbOthers) {
        boolean[] values = getFields(chbRecordUpdate, chbPeriodicVisit, chbInternment, chbControlEnvironments,
                chbCollectiveActivities, chbGuidance, chbOthers);
        return ReasonsVisitPersistence.getAnotherReasons(values);
    }
}
