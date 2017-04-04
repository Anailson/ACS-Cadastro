package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;
import tcc.acs_cadastro_mobile.views.CitizenStepFourFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepOneFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepThreeFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepTwoFragment;

public class CitizenAddController  {

    private final int FIRST_STEP = 1;
    private final int SECOND_STEP = 2;
    private final int THIRD_STEP = 3;
    private final int FOURTH_STEP = 4;

    private int actualMenu;
    private AppCompatActivity parent;
    private CtzOnClickListener listener;
    private PersonalDataModel personalData;
    private SocialDemographicModel socialDemographicData;
    private HealthConditionsModel healthConditions;
    private StreetSituationModel streetSituation;

    public CitizenAddController (AppCompatActivity view){
        this.actualMenu = FIRST_STEP;
        this.parent = view;
    }

    public View.OnClickListener getClickListener(){

        if(listener == null){
            listener = new CtzOnClickListener();
        }
        return listener;
    }

    public void send(PersonalDataModel personalData){
        this.personalData = personalData;
    }

    public void send(SocialDemographicModel socialDemographicData){
        this.socialDemographicData = socialDemographicData;
    }

    public void send(HealthConditionsModel healthConditions){
        this.healthConditions = healthConditions;
    }

    public void send(StreetSituationModel streetSituation){
        this.streetSituation = streetSituation;
    }

    public void setStepOne(){
        shiftToStepOne();
    }

    private void nextMenu(){

        switch (actualMenu){
            case FIRST_STEP: shiftToStepTwo(); break;
            case SECOND_STEP: shiftToStepThree(); break;
            case THIRD_STEP: shiftToStepFour(); break;
            case FOURTH_STEP: save(); break;
        }
    }

    private void previousMenu(){
        switch (actualMenu){
            case SECOND_STEP: shiftToStepOne(); break;
            case THIRD_STEP: shiftToStepTwo(); break;
            case FOURTH_STEP: shiftToStepThree(); break;
        }
    }

    private void save(){
        //RealmObject
        CitizenModel citizen = new CitizenModel(personalData, socialDemographicData, healthConditions, streetSituation);
        citizen.save();
    }

    private void shiftToStepOne(){
        Button btnBack = (Button) parent.findViewById(R.id.btn_ctz_add_back);
        btnBack.setEnabled(false);

        updateProgressBar(FIRST_STEP);
        replaceTitle(R.string.txt_ctz_data_1);
        replaceStep(CitizenStepOneFragment.newInstance(personalData));
        actualMenu = FIRST_STEP;
    }

    private void shiftToStepTwo(){

        Button btnBack = (Button) parent.findViewById(R.id.btn_ctz_add_back);
        btnBack.setEnabled(true);

        updateProgressBar(SECOND_STEP);
        replaceTitle(R.string.txt_ctz_data_2);
        replaceStep(CitizenStepTwoFragment.newInstance(socialDemographicData));
        actualMenu = SECOND_STEP;
    }

    private void shiftToStepThree(){

        Button btnProgress = (Button) parent.findViewById(R.id.btn_ctz_add_progress);
        btnProgress.setText(R.string.btn_progress);

        updateProgressBar(THIRD_STEP);
        replaceTitle(R.string.txt_ctz_data_3);
        replaceStep(CitizenStepThreeFragment.newInstance(healthConditions));
        actualMenu = THIRD_STEP;
    }

    private void shiftToStepFour(){

        Button btnProgress = (Button) parent.findViewById(R.id.btn_ctz_add_progress);
        btnProgress.setText(R.string.btn_save);

        updateProgressBar(FOURTH_STEP);
        replaceTitle(R.string.txt_ctz_data_4);
        replaceStep(CitizenStepFourFragment.newInstance(streetSituation));
        actualMenu = FOURTH_STEP;
    }

    private void updateProgressBar(final int progress){
        final ProgressBar pBar = (ProgressBar) parent.findViewById(R.id.pbar_citizen);
        new Thread(){
            @Override
            public void run() {
                super.run();
                pBar.setProgress(progress);
            }
        }.start();
    }

    private void replaceTitle(final int id){
        TextView txtTitle = (TextView) parent.findViewById(R.id.txt_ctz_add);
        txtTitle.setText(id);
    }

    private void replaceStep(final Fragment fragment){
        FragmentManager manager = parent.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_ctz_add, fragment).commit();
    }

    private class CtzOnClickListener implements View.OnClickListener {

        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_ctz_add_back: previousMenu(); break;
                case R.id.btn_ctz_add_progress: nextMenu(); break;
            }
        }
    }
}
