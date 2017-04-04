package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.views.ResidenceStepOneFragment;

public class ResidenceAddController {

    private final int FIRST_STEP = 1;
    private final int SECOND_STEP = 2;

    private int actualMenu;
    private AppCompatActivity parent;
    private AddressDataModel addressData;
    private HousingConditionsModel housingConditions;

    public ResidenceAddController(AppCompatActivity view){
        this.actualMenu = FIRST_STEP;
        this.parent = view;
    }

    public void setStepOne(){
        shiftToStepOne();
    }

    private void shiftToStepOne(){
        Button btnBack = (Button) parent.findViewById(R.id.btn_rsd_add_back);
        btnBack.setEnabled(false);

        updateProgressBar(FIRST_STEP);
        replaceTitle(R.string.txt_rsd_data_1);
        replaceStep(ResidenceStepOneFragment.newInstance(addressData));
        actualMenu = FIRST_STEP;
    }

    private void updateProgressBar(final int progress){
        final ProgressBar pBar = (ProgressBar) parent.findViewById(R.id.pbar_residence);
        new Thread(){
            @Override
            public void run() {
                super.run();
                pBar.setProgress(progress);
            }
        }.start();
    }

    private void replaceTitle(final int id){
        TextView txtTitle = (TextView) parent.findViewById(R.id.txt_rsd_add);
        txtTitle.setText(id);
    }

    private void replaceStep(final Fragment fragment){
        FragmentManager manager = parent.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_rsd_add, fragment).commit();
    }

}
