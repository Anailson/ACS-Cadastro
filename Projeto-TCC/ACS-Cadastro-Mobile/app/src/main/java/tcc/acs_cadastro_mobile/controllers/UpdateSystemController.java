package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.util.Log;

import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;

public class UpdateSystemController {

    private Fragment fragment;

    public UpdateSystemController(Fragment fragment) {
        this.fragment = fragment;
    }

    public void updateSystem() {
        AccompanyModel accompany = AccompanyPersistence.getAll().first();
        CitizenModel citizen = CitizenPersistence.get(197970454);

        Log.e("CITIZEN", citizen.asJson().toString());
    }
}
