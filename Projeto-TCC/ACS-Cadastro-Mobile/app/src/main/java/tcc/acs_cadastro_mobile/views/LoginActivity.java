package tcc.acs_cadastro_mobile.views;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.LoginController;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginController controller = new LoginController(this);

        if(controller.needsLogin()){

            Button btnSignIn = (Button) findViewById(R.id.btn_sign_in);
            EditText edtNumSus = (EditText) findViewById(R.id.edt_agt_sus);

            edtNumSus.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
            btnSignIn.setOnClickListener(controller.getOnClickListener());

        } else {
            controller.startApp();
        }
    }
}