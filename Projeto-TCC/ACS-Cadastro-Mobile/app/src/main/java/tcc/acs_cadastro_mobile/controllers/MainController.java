package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.httpRequest.AccompanyHttpRequest;
import tcc.acs_cadastro_mobile.httpRequest.CitizenHttpRequest;
import tcc.acs_cadastro_mobile.httpRequest.ResidenceHttpRequest;
import tcc.acs_cadastro_mobile.httpRequest.VisitHttpRequest;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.models.VisitModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.persistence.ResidencePersistence;
import tcc.acs_cadastro_mobile.persistence.VisitPersistence;
import tcc.acs_cadastro_mobile.views.LoginActivity;

public class MainController {

    private Context context;

    public MainController(Context context) {
        this.context = context;
    }


    public void updateData(AgentModel agent){
        long numSus = agent.getNumSus();
        downloadCitizens(agent);
        downloadAccompanies(numSus);
        downloadVisits(numSus);
        downloadResidences(numSus);
    }

    public void logout(){

        DefaultAlert alert = new DefaultAlert(context);
        alert.setMessage(context.getString(R.string.msg_logout_confirm));
        alert.setPositiveListener(R.string.btn_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AcsRecordPersistence.dropDatabase();
                AcsRecordPersistence.setRememberLogin(context, false);
                ((Activity) context).finish();
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
        alert.show();
    }

    public void dropDatabase() {

        boolean remember = AcsRecordPersistence.isRememberLogin(context);
        if(!remember) {
            AcsRecordPersistence.dropDatabase();
        }
    }

    private void downloadCitizens(final AgentModel agent) {

        final CitizenHttpRequest httpRequest = new CitizenHttpRequest(context);
        httpRequest.getAll(agent.getNumSus(), new IHttpResponse.GetAll<CitizenModel>() {

            @Override
            public void getAll(List<CitizenModel> list) {
                downloadAccompanies(agent.getNumSus());
                //Log.e("Citizens", list.size() + "");
                sendCitizens(httpRequest, agent);
            }
        });
        httpRequest.start();
    }

    private void sendCitizens(CitizenHttpRequest httpRequest, AgentModel agent){
        final List<CitizenModel> citizens = CitizenPersistence.getAllPendents(AcsRecordPersistence.INSERT);
        httpRequest.insertAll(agent, citizens, new IHttpResponse.InsertAll() {
            @Override
            public void insertAll(int[] indexes) {
                Log.e("insertAll", "Size: " + indexes.length);
                //CitizenPersistence.update(citizens);
            }
        });
        httpRequest.start();
    }

    private void updateCitizens(){

    }

    private void downloadAccompanies(final long numSus) {
        AccompanyHttpRequest request = new AccompanyHttpRequest(context);
        request.getAll(numSus, new IHttpResponse.GetAll<AccompanyModel>() {
            @Override
            public void getAll(List<AccompanyModel> list) {
                downloadResidences(numSus);
                //Log.e("Accompanies", list.size() + "");
                //AccompanyPersistence.update(list);
            }
        });
        request.start();
    }

    private void downloadResidences(final long numSus){
        ResidenceHttpRequest request = new ResidenceHttpRequest(context);
        request.getAll(numSus, new IHttpResponse.GetAll<ResidenceModel>(){

            @Override
            public void getAll(List<ResidenceModel> list) {
                downloadVisits(numSus);
                //Log.e("Residences", list.size() + "");
                //ResidencePersistence.update(list);
            }
        });
        request.start();
    }

    private void downloadVisits(final long numSus){
        VisitHttpRequest request = new VisitHttpRequest(context);
        request.getAll(numSus, new IHttpResponse.GetAll< VisitModel>(){

            @Override
            public void getAll(List<VisitModel> list) {
                VisitPersistence.update(list);
                //Log.e("Visits", list.size() + "");
            }
        });
        request.start();
    }
}
