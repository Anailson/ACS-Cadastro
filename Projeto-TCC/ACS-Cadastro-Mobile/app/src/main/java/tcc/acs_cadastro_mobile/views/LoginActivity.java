package tcc.acs_cadastro_mobile.views;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.LoginController;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class LoginActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AcsRecordPersistence.startDatabase(this);

        final LoginController controller = new LoginController(this);
        Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign_in);
        final EditText edtNumSus = (EditText) findViewById(R.id.edt_agt_sus);


        edtNumSus.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                controller.validateData(edtNumSus.getText().toString());
            }
        });
    }
}

