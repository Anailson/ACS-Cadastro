package tcc.acs_cadastro_mobile.views;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ConfirmSaveCitizenController;

public class ConfirmSaveCitizenActivity extends AppCompatActivity {


    public static final String SAVE_CITIZEN = "SAVE_CITIZEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_confirm_save_citizen);

        ConfirmSaveCitizenController controller = new ConfirmSaveCitizenController(this);
        Button btnOk = (Button) findViewById(R.id.btn_ctz_save_confirm);
        TextView txtCitizen = (TextView) findViewById(R.id.txt_ctz_save_citizen);

        txtCitizen.setText(getIntent().getCharSequenceExtra(SAVE_CITIZEN));
        btnOk.setOnClickListener(controller.getOnClickListener());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = (int) (dm.widthPixels * .9);
        int height = (int)(dm.heightPixels * .4);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            width = (int) (dm.widthPixels * .8);
            height = (int)(dm.heightPixels * .8);
        }
        getWindow().setLayout(width, height);
    }
}
