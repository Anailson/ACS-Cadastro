package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.httpRequest.CitizenHttpRequest;
import tcc.acs_cadastro_mobile.httpRequest.WebServiceConnection;
import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;

public class UpdateSystemController {

    private Fragment fragment;

    public UpdateSystemController(Fragment fragment) {
        this.fragment = fragment;
    }

    public void updateSystem() {
        new UpdateCitizenModel().update();
    }

    private class UpdateCitizenModel {

        public void update() {
            List<CitizenModel> citizens = CitizenPersistence.getAll();
            List<CitizenModel> sendToBD = new ArrayList<>();

            for (CitizenModel citizen : citizens) {
                if (citizen.getStatus() == AcsRecordPersistence.INSERT
                        || citizen.getStatus() == AcsRecordPersistence.UPDATE) {
                    sendToBD.add(citizen);
                }
            }
            CitizenHttpRequest citizenRequest = new CitizenHttpRequest(fragment.getContext());
            citizenRequest.insertAll(sendToBD, new IAsyncTaskResponse.InsertAll(){

                @Override
                public void insertAll(WebServiceConnection.Request request, int rows) {
                    Log.e("Rows", rows + "");
                }
            });



            citizenRequest.insert(sendToBD.get(sendToBD.size() - 1), new IAsyncTaskResponse.Insert() {
                @Override
                public void insert(WebServiceConnection.Request request, int id) {
                    if (request.getStatus() == WebServiceConnection.Status.OK) {
                        //CitizenPersistence.updateStatus()
                    }
                }
            });

            //AgentHttpRequests httpRequests = new AgentHttpRequests(fragment.getContext());
            //httpRequests.setInsertResponse(this);
            //httpRequests.makeRequest(WebServiceConnection.Method.GET, );

            AccompanyModel accompany = AccompanyPersistence.getAll().first();
            CitizenModel citizen = CitizenPersistence.get(197970454);
        }
    }
}
