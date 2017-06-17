package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;

import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.BIRTH_DATE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_INCOME;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_RECORD;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.LIVES_SINCE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MEMBERS;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MOVED;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.NUM_SUS;

public class ResidenceNewResponsibleController extends StepsController{

    private AppCompatActivity activity;
    private Listeners listener;

    public ResidenceNewResponsibleController(AppCompatActivity activity){
        super(activity.getBaseContext());
        this.activity = activity;
    }

    public View.OnClickListener getClickListener(){
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public AdapterView.OnItemClickListener getItemClickListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public ArrayAdapter<String> getNumSusAdapter() {
        String[] numSus = CitizenPersistence.getNumSus();
        return new Adapter(activity.getBaseContext()).getSpinnerAdapter(numSus);
    }
    public ArrayAdapter<String> getNumSusAdapter(int resource) {
        return new Adapter(activity.getBaseContext()).getSpinnerAdapter(resource);
    }

    private void save(){

        long numSus = getLong((EditText) activity.findViewById(R.id.edt_rsd_resp_num_sus));
        long familyRecord = getLong((EditText) activity.findViewById(R.id.edt_rsd_family_record));
        String birthDate = getFields((EditText) activity.findViewById(R.id.edt_rsd_resp_birth_date));
        String familyIncome = getFields((Spinner) activity.findViewById(R.id.spn_rsd_famlily_income));
        int nMembers = getInt((EditText) activity.findViewById(R.id.edt_rsd_n_members));
        String livesSince = getFields((EditText) activity.findViewById(R.id.edt_rsd_lives_since));
        boolean moved = getFields((CheckBox) activity.findViewById(R.id.chb_rsd_moved));

        Intent intent = new Intent();
        intent.putExtra(NUM_SUS, numSus);
        intent.putExtra(FAMILY_RECORD, familyRecord);
        intent.putExtra(BIRTH_DATE, birthDate);
        intent.putExtra(FAMILY_INCOME, familyIncome);
        intent.putExtra(MEMBERS, nMembers);
        intent.putExtra(LIVES_SINCE, livesSince);
        intent.putExtra(MOVED, moved);
        activity.setResult(Activity.RESULT_OK, intent);
        cancel();
    }

    private void cancel(){
        activity.finish();
    }

    private void setBirthDate(EditText editText){

        String text = editText.getText().toString();
        //TODO: if numSus is changed after to be selected, data base will return a citizen unexpected.
        // be careful to get a correct citizen
        CitizenModel citizen = CitizenPersistence.get(text.equals("") ? 0 : Long.parseLong(text));
        if (citizen == null) {

            DefaultAlert alert = new DefaultAlert(activity);
            alert.setTitle(R.string.err_num_sus_not_found_title);
            alert.setMessage(R.string.err_num_sus_not_found_message);
            alert.setListeners(listener);
            alert.show();

        } else {
            EditText birthDate = (EditText) activity.findViewById(R.id.edt_rsd_resp_birth_date);
            birthDate.setText(citizen.getBirthDate());
        }
    }

    private class Listeners implements View.OnClickListener, AdapterView.OnItemClickListener, DefaultAlert.DefaultClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_new_resp_save: save(); break;
                case R.id.btn_new_resp_cancel: cancel(); break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setBirthDate((EditText) activity.findViewById(R.id.edt_rsd_resp_num_sus));
        }

        @Override
        public void positive(DialogInterface dialog, int which) {
            enableView(activity.findViewById(R.id.edt_rsd_resp_birth_date), true);
        }

        @Override
        public void negative(DialogInterface dialog, int which) {
            activity.finish();
        }
    }
}
