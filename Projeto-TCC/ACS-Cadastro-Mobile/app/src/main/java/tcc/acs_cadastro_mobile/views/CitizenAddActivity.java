package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenAddController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;

public class CitizenAddActivity extends AppCompatActivity
        implements ICitizenData {

    private CitizenAddController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bar_ctz_add);
        setSupportActionBar(toolbar);
        controller = new CitizenAddController(this);

        Button btnBack = (Button) findViewById(R.id.btn_ctz_add_back);
        Button btnProgress = (Button) findViewById(R.id.btn_ctz_add_progress);

        btnBack.setOnClickListener(controller.getClickListener());
        btnProgress.setOnClickListener(controller.getClickListener());

        controller.setStepOne();
    }

    @Override
    public void send(PersonalDataModel personalData) {
        controller.send(personalData);
    }

    @Override
    public void send(SocialDemographicModel socialDemographicData) {
        controller.send(socialDemographicData);
    }

    @Override
    public void send(HealthConditionsModel healthConditions) {
        controller.send(healthConditions);
    }

    @Override
    public void send(StreetSituationModel streetSituation) {
        controller.send(streetSituation);
    }
}
