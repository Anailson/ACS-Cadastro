package tcc.acs_cadastro_mobile.controllers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import tcc.acs_cadastro_mobile.R;

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

    public View.OnFocusChangeListener getFocusChangeListener(){
        if(listener == null){
            listener = new Listener();
        }else{
            Log.e("getFocusChangeListener", String.valueOf(true));
        }
        return listener;
    }

    private void save(){
        cancel();
    }

    private void cancel(){
        activity.finish();
    }

    private void searchCitizen(boolean hasFocus){
        if(!hasFocus) {
            Log.e("searchCitizen", String.valueOf(hasFocus));
        }
    }

    private class Listener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_new_resp_save: save(); break;
                case R.id.btn_new_resp_cancel: cancel(); break;
            }
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()){
                case R.id.edt_rsd_resp_num_sus: searchCitizen(hasFocus);break;
            }
        }
    }
}
