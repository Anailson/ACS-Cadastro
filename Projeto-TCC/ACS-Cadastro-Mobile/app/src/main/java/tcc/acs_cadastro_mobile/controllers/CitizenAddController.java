package tcc.acs_cadastro_mobile.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import tcc.acs_cadastro_mobile.views.CitizenAddActivity;
import tcc.acs_cadastro_mobile.views.CitizenStepFourFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepOneFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepThreeFragment;
import tcc.acs_cadastro_mobile.views.CitizenStepTwoFragment;
import tcc.acs_cadastro_mobile.views.ConfirmSaveCitizenActivity;

public class CitizenAddController  {

    private final int FIRST_STEP = 1;
    private final int SECOND_STEP = 2;
    private final int THIRD_STEP = 3;
    private final int FOURTH_STEP = 4;

    private int actualMenu;
    private Fragment actualStep;
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

        if(actualMenu == FOURTH_STEP) {

            actualStep.onDetach();
            CitizenModel citizen = new CitizenModel(personalData, socialDemographicData, healthConditions, streetSituation);
            new SaveCitizen(actualStep.getContext()).execute(citizen);
        }
    }

    private void showConfirmDialog(String name){

        Intent intent = new Intent(parent, ConfirmSaveCitizenActivity.class);
        intent.putExtra(ConfirmSaveCitizenActivity.SAVE_CITIZEN, name);
        parent.startActivity(intent);
        parent.finish();
    }

    private void shiftToStepOne(){
        Button btnBack = (Button) parent.findViewById(R.id.btn_ctz_add_back);
        btnBack.setEnabled(false);

        actualMenu = FIRST_STEP;
        actualStep = CitizenStepOneFragment.newInstance(personalData);
        replaceStep(actualStep);
        updateProgressBar(FIRST_STEP);
        replaceTitle(R.string.txt_ctz_data_1);
    }

    private void shiftToStepTwo(){

        Button btnBack = (Button) parent.findViewById(R.id.btn_ctz_add_back);
        btnBack.setEnabled(true);

        actualMenu = SECOND_STEP;
        actualStep = CitizenStepTwoFragment.newInstance(socialDemographicData);
        replaceStep(actualStep);
        updateProgressBar(SECOND_STEP);
        replaceTitle(R.string.txt_ctz_data_2);
    }

    private void shiftToStepThree(){

        Button btnProgress = (Button) parent.findViewById(R.id.btn_ctz_add_progress);
        btnProgress.setText(R.string.btn_progress);

        actualMenu = THIRD_STEP;
        actualStep = CitizenStepThreeFragment.newInstance(healthConditions);
        replaceStep(actualStep);
        updateProgressBar(THIRD_STEP);
        replaceTitle(R.string.txt_ctz_data_3);
    }

    private void shiftToStepFour(){

        Button btnProgress = (Button) parent.findViewById(R.id.btn_ctz_add_progress);
        btnProgress.setText(R.string.btn_save);

        actualMenu = FOURTH_STEP;
        actualStep = CitizenStepFourFragment.newInstance(streetSituation);
        replaceStep(actualStep);
        updateProgressBar(FOURTH_STEP);
        replaceTitle(R.string.txt_ctz_data_4);
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
    private class SaveCitizen extends AsyncTask<CitizenModel, Void, String[]>{

        private ProgressDialog progressDialog;

        public SaveCitizen(Context context){
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setCancelable(true);
            progressDialog.setTitle("Salvando os dados...");
            progressDialog.show();
        }

        @Override
        protected String[] doInBackground(CitizenModel... citizens) {
            CitizenModel citizen = citizens[0];
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new String[]{String.valueOf(citizen.save()), citizen.getName()};
        }

        @Override
        protected void onPostExecute(String[] values) {
            super.onPostExecute(values);
            if(Boolean.parseBoolean(values[0])){
                progressDialog.cancel();
                showConfirmDialog(values[1]);
            }
        }
    }
}
