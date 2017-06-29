package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.views.AccompanyStepFourFragment;
import tcc.acs_cadastro_mobile.views.AccompanyStepOneFragment;
import tcc.acs_cadastro_mobile.views.AccompanyStepThreeFragment;
import tcc.acs_cadastro_mobile.views.AccompanyStepTwoFragment;

public class AccompanyAddController {

    private static final int FIRST_STEP = 1;
    private static final int SECOND_STEP = 2;
    private static final int THIRD_STEP = 3;
    private static final int FOURTH_STEP = 4;
    private static final int N_STEPS = FOURTH_STEP;

    private RecordDataModel recordData;
    private ConditionsModel conditions;
    private ExamsModel exams;
    private NasfConductModel nasfConduct;
    private AppCompatActivity activity;
    private Listeners listener;
    private Fragment actualStep;
    private int actualMenu;

    public AccompanyAddController(AppCompatActivity activity) {
        this.activity = activity;
    }

    public View.OnClickListener getClickListener() {

        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public void send(RecordDataModel recordData) {
        this.recordData = recordData;
    }

    public void send(ConditionsModel conditions) {
        this.conditions = conditions;
    }

    public void send(ExamsModel exams) {
        this.exams = exams;
    }

    public void send(NasfConductModel nasfConduct) {
        this.nasfConduct = nasfConduct;
    }

    public void setStepOne(){
        shiftToStepOne();
    }

    private void previousMenu(){

        switch (actualMenu){
            case SECOND_STEP: shiftToStepOne(); break;
            case THIRD_STEP:  shiftToStepTwo(); break;
            case FOURTH_STEP: shiftToStepThree(); break;
        }
    }

    private void nextMenu(){
        switch (actualMenu){
            case FIRST_STEP:
                if(((IRequiredFields) actualStep).isRequiredFieldsFilled()){
                    shiftToStepTwo();
                }
                break;
            case SECOND_STEP:
                if(((IRequiredFields) actualStep).isRequiredFieldsFilled()){
                    shiftToStepThree();
                }
                break;
            case THIRD_STEP:
                if(((IRequiredFields) actualStep).isRequiredFieldsFilled()){
                    shiftToStepFour();
                }
                break;
            case FOURTH_STEP:
                if(((IRequiredFields) actualStep).isRequiredFieldsFilled()){
                    save();
                }
                break;
        }
    }

    private void shiftToStepOne(){
        activity.findViewById(R.id.btn_acc_add_back).setEnabled(false);

        actualMenu = FIRST_STEP;
        replaceStep(actualStep = AccompanyStepOneFragment.newInstance(recordData));
        updateProgressBar(actualMenu);
        replaceTitle(R.string.txt_acc_data_1);
    }

    private void shiftToStepTwo(){
        activity.findViewById(R.id.btn_acc_add_back).setEnabled(true);

        actualMenu = SECOND_STEP;
        replaceStep(actualStep = AccompanyStepTwoFragment.newInstance(conditions));
        updateProgressBar(actualMenu);
        replaceTitle(R.string.txt_acc_data_2);
    }

    private void shiftToStepThree(){
        ((Button) activity.findViewById(R.id.btn_acc_add_progress)).setText(activity.getString(R.string.btn_progress));
        actualMenu = THIRD_STEP;
        replaceStep(actualStep = AccompanyStepThreeFragment.newInstance(exams));
        updateProgressBar(actualMenu);
        replaceTitle(R.string.txt_acc_data_3);
    }

    private void shiftToStepFour(){

        ((Button) activity.findViewById(R.id.btn_acc_add_progress)).setText(activity.getString(R.string.btn_save));

        actualMenu = FOURTH_STEP;
        replaceStep(actualStep = AccompanyStepFourFragment.newInstance(nasfConduct));
        updateProgressBar(actualMenu);
        replaceTitle(R.string.txt_acc_data_4);
    }

    private void save() {
        actualStep.onDetach();
        AccompanyModel accompany = AccompanyPersistence.save(recordData, conditions, exams, nasfConduct);
        if (accompany != null) {
            showConfirmDialog(recordData.getRecord(), recordData.getNumSus(), recordData.getName());
        }
    }

    private void replaceStep(Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_acc_add, fragment).commit();
    }

    private void updateProgressBar(final int progress) {
        final ProgressBar pBar = (ProgressBar) activity.findViewById(R.id.pbar_accompany);
        new Thread(){
            @Override
            public void run() {
                super.run();
                pBar.setProgress(progress * pBar.getMax() / N_STEPS);
            }
        }.start();
    }

    private void replaceTitle(int id) {
        TextView txtTitle = (TextView) activity.findViewById(R.id.txt_acc_add);
        txtTitle.setText(id);
    }

    private void showConfirmDialog(long record, long numSus, String name){
        DefaultAlert alert = new DefaultAlert(activity);
        alert.setTitle(R.string.msg_save_success);
        alert.setMessage("O acompanhamento, com prontuário " + record + " de " + name + ", Nº do SUS "
                + numSus + ", foi salvo com sucesso!");
        alert.setPositiveListener(new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                activity.finish();
            }
        });
        alert.show();
    }

    private class Listeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_acc_add_back: previousMenu(); break;
                case R.id.btn_acc_add_progress: nextMenu(); break;
            }
        }
    }
}
