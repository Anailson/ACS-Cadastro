package tcc.acs_cadastro_mobile.views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceNewResponsibleController;
import tcc.acs_cadastro_mobile.customViews.CalendarEditText;
import tcc.acs_cadastro_mobile.customViews.RequiredAutoComplete;

public class ResidenceNewResponsibleActivity extends AppCompatActivity {

    public static final int RESULT = 0;
    public static final String NUM_SUS = "numSus";
    public static final String FAMILY_RECORD = "familyRecord";
    public static final String BIRTH_DATE = "birthDate";
    public static final String FAMILY_INCOME = "familyIncome";
    public static final String MEMBERS = "nMembers";
    public static final String LIVES_SINCE = "livesSince";
    public static final String MOVED = "moved";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.float_new_responsible_activity);

        ResidenceNewResponsibleController controller = new ResidenceNewResponsibleController(this);
        RequiredAutoComplete edtNumSus = (RequiredAutoComplete) findViewById(R.id.edt_rsd_resp_num_sus);
        CalendarEditText edtLivesSince = (CalendarEditText) findViewById(R.id.edt_rsd_lives_since);
        Spinner spnFamilyIncome = (Spinner) findViewById(R.id.spn_rsd_famlily_income);
        Button btnSave = (Button) findViewById(R.id.btn_new_resp_save);
        Button btnCancel = (Button) findViewById(R.id.btn_new_resp_cancel);

        edtNumSus.setAdapter(controller.getNumSusAdapter());
        edtNumSus.setThreshold(1);
        spnFamilyIncome.setAdapter(controller.getNumSusAdapter(R.array.income));

        edtNumSus.setAutoFillListener(controller.getItemClickListener());
        edtLivesSince.setShowCalendarListener(controller.getCalendarListener());
        btnSave.setOnClickListener(controller.getClickListener());
        btnCancel.setOnClickListener(controller.getClickListener());

        defineFloating();
    }

    private void defineFloating(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels * .9);
        int height = (int)(dm.heightPixels * .8);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            width = (int)(dm.widthPixels * .6);
            height = (int)(dm.heightPixels * .9);
        }
        getWindow().setLayout(width, height);
    }
}