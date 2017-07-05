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
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.models.VisitModel;
import tcc.acs_cadastro_mobile.persistence.VisitPersistence;
import tcc.acs_cadastro_mobile.views.VisitStepOneFragment;
import tcc.acs_cadastro_mobile.views.VisitStepTwoFragment;

public class VisitAddController {

    private static final int FIRST_STEP = 1;
    private static final int SECOND_STEP = 2;
    private static final int N_STEPS = SECOND_STEP;

    private RecordVisitModel details;
    private ReasonsVisitModel reasons;
    private AppCompatActivity activity;

    private Fragment actualStep;
    private int actualMenu;

    private Listeners listener;

    public VisitAddController(AppCompatActivity activity) {
        this.activity = activity;
    }

    private Listeners getListener() {
        if (listener == null) {
            listener = new Listeners();
        }
        return listener;
    }

    public View.OnClickListener getClickListener() {
        return getListener();
    }

    public void setStepOne() {
        shiftToStepOne();
    }

    public void send(RecordVisitModel details) {
        this.details = details;
    }

    public void send(ReasonsVisitModel reasons) {
        this.reasons = reasons;
    }

    private void previousMenu() {

        switch (actualMenu) {
            case SECOND_STEP:shiftToStepOne(); break;
        }
    }

    private void nextMenu() {
        switch (actualMenu) {
            case FIRST_STEP:
                if (((IRequiredFields) actualStep).isRequiredFieldsFilled()) {
                    shiftToStepTwo();
                }
                break;
            case SECOND_STEP:
                if (((IRequiredFields) actualStep).isRequiredFieldsFilled()) {
                    save();
                }
                break;
        }
    }

    private void shiftToStepOne() {

        activity.findViewById(R.id.btn_vst_add_back).setEnabled(false);
        Button btn = (Button) activity.findViewById(R.id.btn_vst_add_progress);
        btn.setText(R.string.btn_progress);

        actualStep = VisitStepOneFragment.newInstance(details);
        actualMenu = FIRST_STEP;
        replaceStep(actualStep);
        updateProgressBar(actualMenu);
        replaceTitle(R.string.txt_vst_data_1);
    }

    private void shiftToStepTwo() {
        activity.findViewById(R.id.btn_vst_add_back).setEnabled(true);
        Button btn = (Button) activity.findViewById(R.id.btn_vst_add_progress);
        btn.setText(R.string.btn_save);

        actualStep = VisitStepTwoFragment.newInstance(reasons);
        replaceStep(actualStep);
        updateProgressBar(actualMenu = SECOND_STEP);
        replaceTitle(R.string.txt_acc_data_2);
    }

    private void save() {
        actualStep.onDetach();
        VisitModel visit = VisitPersistence.save(details, reasons);

        if(visit != null){
            showConfirmSave(visit.getRecord(), visit.getNumSus(), visit.getName());
        }
    }


    private void replaceStep(Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_vst_add, fragment).commit();
    }

    private void updateProgressBar(final int progress) {
        final ProgressBar pBar = (ProgressBar) activity.findViewById(R.id.pbar_citizen);
        new Thread() {
            @Override
            public void run() {
                super.run();
                pBar.setProgress(progress * pBar.getMax() / N_STEPS);
            }
        }.start();
    }

    private void replaceTitle(int id) {
        TextView txtTitle = (TextView) activity.findViewById(R.id.txt_vst_add);
        txtTitle.setText(id);
    }

    private void showConfirmSave(long record, long numSus, String name){

        DefaultAlert alert = new DefaultAlert(activity);
        alert.setTitle(R.string.msg_save_success);
        alert.setMessage("A visita, com prontuário " + record + " de " + name + ", Nº do SUS "
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

    private class Listeners implements View.OnClickListener {
        //View.OnClickListener
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_vst_add_back: previousMenu(); break;
                case R.id.btn_vst_add_progress: nextMenu(); break;
            }
        }
    }
}
