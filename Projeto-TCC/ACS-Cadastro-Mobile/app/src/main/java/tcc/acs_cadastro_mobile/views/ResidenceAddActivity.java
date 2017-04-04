package tcc.acs_cadastro_mobile.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceAddController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;

public class ResidenceAddActivity extends AppCompatActivity implements IResidenceData{

    private ResidenceAddController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bar_rsd_add);
        setSupportActionBar(toolbar);

        Button btnBack = (Button) findViewById(R.id.btn_rsd_add_back);
        Button btnProgress = (Button) findViewById(R.id.btn_rsd_add_progress);
        controller = new ResidenceAddController(this);

        //btnBack.setOnClickListener(controller.getClickListener());
        //btnProgress.setOnClickListener(controller.getClickListener());

        controller.setStepOne();
    }

    @Override
    public void send(AddressDataModel addressData) {
        //TODO anything
    }

    @Override
    public void send(HousingConditionsModel housingConditions) {
        //TODO anything
    }
}
