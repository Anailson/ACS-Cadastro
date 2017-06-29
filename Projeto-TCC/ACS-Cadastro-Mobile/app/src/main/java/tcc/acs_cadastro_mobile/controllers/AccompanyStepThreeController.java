package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;

public class AccompanyStepThreeController extends StepsController{

    private Fragment fragment;

    public AccompanyStepThreeController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public boolean isRequiredFieldsFilled() {
        return true;
    }

    public void fillPic(Spinner spnPic, String pic) {
        int index = getIndex(pic, R.array.pic);
        fillField(spnPic, index);
    }
}
