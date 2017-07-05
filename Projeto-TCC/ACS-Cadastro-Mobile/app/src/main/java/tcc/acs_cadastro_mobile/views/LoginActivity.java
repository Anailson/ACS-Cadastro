package tcc.acs_cadastro_mobile.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import tcc.acs_cadastro_mobile.R;

public class LoginActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign_in);
        EditText edtNumSus = (EditText) findViewById(R.id.edt_agt_sus);

        edtNumSus.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

