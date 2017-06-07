package tcc.acs_cadastro_mobile.views;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.NewResponsibleController;

public class NewResponsibleActivity extends AppCompatActivity {

    private EditText edtFamilyRecord, edtNumSus, edtBirthDate, edtLivesSince, edtNMembers;
    private Spinner spnFamilyIncome;
    private CheckBox chbMoved;
    private Button btnCancel, btnSave;
    private NewResponsibleController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_new_responsible);

        controller = new NewResponsibleController(this);
        edtFamilyRecord = (EditText) findViewById(R.id.edt_rsd_family_record);
        edtNumSus = (EditText) findViewById(R.id.edt_rsd_resp_num_sus);
        edtBirthDate = (EditText) findViewById(R.id.edt_rsd_resp_birth_date);
        edtLivesSince = (EditText) findViewById(R.id.edt_rsd_lives_since);
        edtNMembers = (EditText) findViewById(R.id.edt_rsd_n_members);
        spnFamilyIncome = (Spinner) findViewById(R.id.spn_rsd_famlily_income);
        chbMoved = (CheckBox) findViewById(R.id.chb_rsd_moved);

        btnSave = (Button) findViewById(R.id.btn_new_resp_save);
        btnCancel = (Button) findViewById(R.id.btn_new_resp_cancel);

        edtNumSus.setOnFocusChangeListener(controller.getFocusChangeListener());
        btnSave.setOnClickListener(controller.getClickListener());
        btnCancel.setOnClickListener(controller.getClickListener());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * .9);
        int height = (int)(dm.heightPixels * .9);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            width = (int) (dm.widthPixels * .6);
            height = (int)(dm.heightPixels * .9);
        }
        getWindow().setLayout(width, height);
    }
}
