package tcc.acs_cadastro_mobile.controllers;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;

public class NewResponsibleController {

    private AppCompatActivity activity;
    private Listener listener;

    public  NewResponsibleController(AppCompatActivity activity){
        this.activity = activity;
    }

    public View.OnClickListener getClickListener(){
        if(listener == null){
            listener = new Listener();
        }
        return listener;
    }

    public TextWatcher getTextWatcher(){
        if(listener == null){
            listener = new Listener();
        }
        return listener;
    }

    private void save(){
        cancel();
    }

    private void cancel(){
        activity.finish();
    }

    private class Listener implements View.OnClickListener, TextWatcher {

        private EditText edtBirthDate;

        public Listener() {
            this.edtBirthDate = (EditText) activity.findViewById(R.id.edt_rsd_resp_birth_date);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_new_resp_save: save(); break;
                case R.id.btn_new_resp_cancel: cancel(); break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            edtBirthDate.setText("");
        }

        @Override
        public void onTextChanged(final CharSequence s, int start, int before, int count) {
            new Thread(){
                @Override
                public void run() {
                    CitizenModel citizen = CitizenPersistence.get(Long.valueOf(s.toString()));
                    if (citizen != null){
                        edtBirthDate.setText(citizen.getBirthDate());
                    }
                    super.run();
                }
            }.start();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
