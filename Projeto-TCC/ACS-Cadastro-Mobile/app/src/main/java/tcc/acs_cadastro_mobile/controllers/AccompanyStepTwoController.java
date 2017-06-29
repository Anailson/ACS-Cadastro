package tcc.acs_cadastro_mobile.controllers;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.models.RealmInt;

public class AccompanyStepTwoController extends StepsController {

    private Fragment fragment;

    public AccompanyStepTwoController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public RealmInt[] getAnotherCids(EditText edtAnothers) {

        String [] lines = edtAnothers.getText().toString().split(",");
        if(lines.length == 0) return new RealmInt[0];

        ArrayList<RealmInt> cids = new ArrayList<>();
        for (String line : lines) {
            String cid = line.replaceAll(" ", "");
            if (cid.matches("[0-9]+")) {
                cids.add(new RealmInt(Integer.parseInt(cid)));
            }
        }
        return cids.toArray(new RealmInt[cids.size()]);
    }

    public void fillCids(RealmList<RealmInt> anothers, EditText edtAnothers) {
        if(anothers.size() > 0) {

            String text = "";
            for (RealmInt cid : anothers) {
                text += cid.getCode() + ", ";
            }
            text = text.substring(0, text.length() - 2);
            fillField(edtAnothers, text);
        }
    }

    public boolean isRequiredFieldsFilled(CheckBox... checkBoxes) {
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                return true;
            }
        }
        showAlert();
        return false;
    }

    private void showAlert(){

        DefaultAlert alert = new DefaultAlert(fragment.getContext());
        alert.setTitle(R.string.err_condition_required_title);
        alert.setMessage(R.string.err_condition_required_message);
        alert.setPositiveListener(R.string.btn_ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
