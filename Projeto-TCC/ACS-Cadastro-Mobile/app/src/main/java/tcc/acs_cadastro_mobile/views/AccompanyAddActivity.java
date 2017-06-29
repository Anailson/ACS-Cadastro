package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyAddController;
import tcc.acs_cadastro_mobile.interfaces.IAccompany;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;

public class AccompanyAddActivity extends AppCompatActivity implements IAccompany{

    private AccompanyAddController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accompany_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bar_acc_add);
        setSupportActionBar(toolbar);
        controller = new AccompanyAddController(this);

        Button btnBack = (Button) findViewById(R.id.btn_acc_add_back);
        Button btnProgress = (Button) findViewById(R.id.btn_acc_add_progress);

        btnBack.setOnClickListener(controller.getClickListener());
        btnProgress.setOnClickListener(controller.getClickListener());

        controller.setStepOne();
    }

    @Override
    public void send(RecordDataModel recordData) {
        controller.send(recordData);
    }

    @Override
    public void send(ConditionsModel conditions) {
        controller.send(conditions);
    }

    @Override
    public void send(ExamsModel exams) {
        controller.send(exams);
    }

    @Override
    public void send(NasfConductModel nasfConduct) {
        controller.send(nasfConduct);
    }
}
