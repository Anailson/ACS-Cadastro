package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.persistence.ResidencePersistence;
import tcc.acs_cadastro_mobile.views.ResidenceStepOneFragment;
import tcc.acs_cadastro_mobile.views.ResidenceStepThreeFragment;
import tcc.acs_cadastro_mobile.views.ResidenceStepTwoFragment;

public class ResidenceAddController  {

    private final int FIRST_STEP = 1;
    private final int SECOND_STEP = 2;
    private final int THIRD_STEP = 3;

    private int actualMenu;
    private Fragment actualStep;
    private AppCompatActivity parent;
    private AddressDataModel addressData;
    private HousingConditionsModel housingConditions;
    private RealmList<HousingHistoricalModel> housingHistorical;
    private Listeners listener;

    public ResidenceAddController(AppCompatActivity view){
        this.actualMenu = FIRST_STEP;
        this.parent = view;
        this.housingHistorical = new RealmList<>();
    }

    public View.OnClickListener getClickListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public void send(AddressDataModel addressData){
        this.addressData = addressData;
    }

    public void send(HousingConditionsModel housingConditions){
        this.housingConditions = housingConditions;
    }

    public void send(RealmList<HousingHistoricalModel> housingHistorical){
        this.housingHistorical = housingHistorical;
    }

    public void setStepOne(){
        shiftToStepOne();
    }

    private void nextMenu(){

        switch (actualMenu){
            case FIRST_STEP:
                if(((ResidenceStepOneFragment) actualStep).isRequiredFieldsFilled()){
                    shiftToStepTwo();
                }
                break;
            case SECOND_STEP:
                if(((ResidenceStepTwoFragment) actualStep).isRequiredFieldsFilled()){
                    shiftToStepThree();
                }
                break;
            case THIRD_STEP: save(); break;
        }
    }

    private void previousMenu(){
        switch (actualMenu){
            case SECOND_STEP: shiftToStepOne(); break;
            case THIRD_STEP: shiftToStepTwo(); break;
        }
    }

    private void save(){

        actualStep.onDetach();
        ResidenceModel saved = ResidencePersistence.save(addressData, housingConditions, housingHistorical);

        if(saved != null){
            showConfirmDialog(saved.getCompleteAddress(), saved.getCep());
        }
    }

    private void shiftToStepOne(){
        parent.findViewById(R.id.btn_rsd_add_back).setEnabled(false);

        actualMenu = FIRST_STEP;
        actualStep = ResidenceStepOneFragment.newInstance(addressData);
        updateView(R.string.txt_rsd_data_1, actualStep, actualMenu);
    }

    private void shiftToStepTwo(){
        parent.findViewById(R.id.btn_rsd_add_back).setEnabled(true);
        ((Button) parent.findViewById(R.id.btn_rsd_add_progress)).setText(R.string.btn_progress);

        actualMenu = SECOND_STEP;
        actualStep = ResidenceStepTwoFragment.newInstance(housingConditions);
        updateView(R.string.txt_rsd_data_2, actualStep, actualMenu);
    }

    private void shiftToStepThree(){

        ((Button) parent.findViewById(R.id.btn_rsd_add_progress)).setText(R.string.btn_save);

        actualMenu = THIRD_STEP;
        actualStep = ResidenceStepThreeFragment.newInstance(housingHistorical);
        updateView(R.string.txt_rsd_data_3, actualStep, actualMenu);
    }

    private void updateView(int title, Fragment fragment, int value){
        replaceStep(fragment);
        replaceTitle(title);
        updateProgressBar(value);
    }

    private void replaceStep(final Fragment fragment){
        new Thread(){
            @Override
            public void run() {
                FragmentManager manager = parent.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frame_rsd_add, fragment).commit();
                super.run();
            }
        }.start();
    }

    private void replaceTitle(final int id){
        ((TextView) parent.findViewById(R.id.txt_rsd_add)).setText(id);
    }

    private void updateProgressBar(final int progress){

        new Thread(){
            @Override
            public void run() {
                ProgressBar pBar = (ProgressBar) parent.findViewById(R.id.pbar_residence);
                pBar.setProgress(progress);
                super.run();
            }
        }.start();
    }

    private void showConfirmDialog(String address, long cep){
        DefaultAlert alerts = new DefaultAlert(parent);
        alerts.setTitle(R.string.msg_save_success);
        alerts.setMessage("Os dados da residência localizada em " + address + ", CEP: " + cep + ", foram salvos com sucesso");
        alerts.setPositiveListener(R.string.btn_ok, listener);
        alerts.show();
    }

    private class Listeners implements View.OnClickListener, DialogInterface.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_rsd_add_back: previousMenu(); break;
                case R.id.btn_rsd_add_progress: nextMenu(); break;
            }
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            parent.finish();
        }
    }
}
