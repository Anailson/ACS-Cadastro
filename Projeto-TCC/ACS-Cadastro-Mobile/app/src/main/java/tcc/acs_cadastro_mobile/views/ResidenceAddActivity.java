package tcc.acs_cadastro_mobile.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceAddController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;

public class ResidenceAddActivity extends AppCompatActivity implements IResidenceData{

    private ResidenceAddController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_residence_add);
        setSupportActionBar((Toolbar) findViewById(R.id.bar_rsd_add));

        Button btnBack = (Button) findViewById(R.id.btn_rsd_add_back);
        Button btnProgress = (Button) findViewById(R.id.btn_rsd_add_progress);
        controller = new ResidenceAddController(this);

        btnBack.setOnClickListener(controller.getClickListener());
        btnProgress.setOnClickListener(controller.getClickListener());

        controller.setStepOne();
    }

    @Override
    public void send(AddressDataModel addressData) {controller.send(addressData);}

    @Override
    public void send(HousingConditionsModel housingConditions) {controller.send(housingConditions);}

    @Override
    public void send(RealmList<HousingHistoricalModel> housingHistorical) {
        controller.send(housingHistorical);
    }
}