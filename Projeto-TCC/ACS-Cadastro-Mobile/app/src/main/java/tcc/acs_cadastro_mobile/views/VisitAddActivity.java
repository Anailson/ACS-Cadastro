package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.VisitAddController;
import tcc.acs_cadastro_mobile.interfaces.IVisit;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;

public class VisitAddActivity extends AppCompatActivity implements IVisit{

    private VisitAddController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.bar_vst_add);
        setSupportActionBar(toolbar);

        controller = new VisitAddController(this);

        Button btnBack = (Button) findViewById(R.id.btn_vst_add_back);
        Button btnProgress = (Button) findViewById(R.id.btn_vst_add_progress);

        btnBack.setOnClickListener(controller.getClickListener());
        btnProgress.setOnClickListener(controller.getClickListener());

        controller.setStepOne();
    }

    @Override
    public void send(RecordVisitModel details) {
        controller.send(details);
    }

    @Override
    public void send(ReasonsVisitModel reasons) {
        controller.send(reasons);
    }
}
