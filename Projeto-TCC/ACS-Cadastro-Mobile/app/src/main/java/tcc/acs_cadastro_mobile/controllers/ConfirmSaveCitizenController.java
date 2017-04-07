package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.view.View;

public class ConfirmSaveCitizenController {

    private Activity activity;

    public ConfirmSaveCitizenController(Activity activity){
        this.activity = activity;
    }

    public View.OnClickListener getOnClickListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        };
    }
}
