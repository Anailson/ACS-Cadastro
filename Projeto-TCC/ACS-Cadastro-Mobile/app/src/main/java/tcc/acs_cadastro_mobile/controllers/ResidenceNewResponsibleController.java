package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.SpinnerAdapter;
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.interfaces.IAutoFillListener;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.BIRTH_DATE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_INCOME;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_RECORD;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.LIVES_SINCE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MEMBERS;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MOVED;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.NUM_SUS;

public class ResidenceNewResponsibleController extends StepsController{

    private Activity activity;
    private Listeners listener;

    public ResidenceNewResponsibleController(AppCompatActivity activity){
        super(activity.getBaseContext());
        this.activity = activity;
    }

    private Listeners getListener(){
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }


    public View.OnClickListener getClickListener(){
        return getListener();
    }

    public IAutoFillListener getItemClickListener() {
        return getListener();
    }

    public ICalendarListener getCalendarListener() {
        return getListener();
    }

    public ArrayAdapter<Long> getNumSusAdapter() {
        List<Long> numSus = CitizenPersistence.getNumSusAsList();
        return new SpinnerAdapter(activity.getBaseContext()).getAdapter(numSus);
    }
    public ArrayAdapter<String> getNumSusAdapter(int resource) {
        return new SpinnerAdapter(activity.getBaseContext()).getAdapter(resource);
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

    private void fillBirthDate(String text){

        CitizenModel citizen = CitizenPersistence.get(Long.parseLong(text));
        if (citizen != null) {

            EditText birthDate = (EditText) activity.findViewById(R.id.edt_rsd_resp_birth_date);
            birthDate.setText(citizen.getBirthDate());
            enableView(birthDate, false);
        }
    }

    private void clearBirthDate(){
        DefaultAlert alert = new DefaultAlert(activity);
        alert.setTitle(R.string.err_num_sus_not_found_title);
        alert.setMessage(R.string.err_num_sus_not_found_message);
        alert.setListeners(listener);
        alert.show();
    }

    private class Listeners implements View.OnClickListener,
            IAutoFillListener, DefaultAlert.DefaultClickListener, ICalendarListener {

        //View.OnClickListener
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_new_resp_save: save(); break;
                case R.id.btn_new_resp_cancel: cancel(); break;
            }
        }

        // RequiredAutoComplete.IAutoFillListener,
        @Override
        public void searching(EditText editText) {

            switch (editText.getId()){
                case R.id.edt_rsd_resp_num_sus: fillBirthDate(editText.getText().toString());
            }
        }

        @Override
        public void selectItem(EditText editText) {
            searching(editText);
        }

        @Override
        public void changedAfterSelected(EditText editText) {

            switch (editText.getId()){
                case R.id.edt_rsd_resp_num_sus: clearBirthDate();
            }
        }

        //DefaultAlert.DefaultClickListener
        @Override
        public void positive(DialogInterface dialog, int which) {
            enableView(activity.findViewById(R.id.edt_rsd_resp_birth_date), true);
        }

        @Override
        public void negative(DialogInterface dialog, int which) {
            activity.finish();
        }

        // ICalendarListener
        @Override
        public void onOk(int id, CalendarActivity.Date date) {
            switch (id){
                case R.id.edt_rsd_lives_since:
                    fillField((EditText) activity.findViewById(R.id.edt_rsd_lives_since), date.formattedDate(activity));
                    break;
            }
        }

        @Override
        public void onCancel() {}
    }
}
