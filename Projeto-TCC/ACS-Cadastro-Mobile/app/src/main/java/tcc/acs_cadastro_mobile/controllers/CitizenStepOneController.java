package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Formatter;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CitizenStepOneController extends StepsController{

    private final int AL = 1;
    private final int BA = 2;
    private final int SE = 3;

    private Fragment fragment;
    private View.OnClickListener clickListener;
    private CompoundButton.OnCheckedChangeListener chbCheckedListener;
    private AdapterView.OnItemSelectedListener itemSelectedListener;

    public CitizenStepOneController(Fragment fragment) {
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public View.OnClickListener getClickListener() {

        if (clickListener == null) {
            clickListener = new OnClickListener();
        }
        return clickListener;
    }
    public CompoundButton.OnCheckedChangeListener getCheckBoxChangeListener() {

        if (chbCheckedListener == null) {
            chbCheckedListener = new OnCheckBoxChangeListener();
        }
        return chbCheckedListener;
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener() {
        if (itemSelectedListener == null) {
            itemSelectedListener = new ItemSelectedListener();
        }
        return itemSelectedListener;
    }

    public void showCalendar(int id) {

        int request = CalendarActivity.BIRTH;
        if (id == R.id.edt_ctz_respons_birth) {
            request = CalendarActivity.RESP_BIRTH;
        }

        Intent newActivity = new Intent(fragment.getActivity(), CalendarActivity.class);
        fragment.startActivityForResult(newActivity, request);
    }

    public long getLong(String s){
        return Long.parseLong(s.equals("") ? "-1" : s);
    }

    public void fillField(EditText edt, long value){
        String text = value < 0 ? "" : String.valueOf(value);
        fillField(edt, text);
    }

    public void fillMotherName(CheckBox chbMotherUnknown, boolean checked, EditText edtMotherName, String name) {
        if (checked) {
            name = fragment.getString(R.string.txt_unknow);
        }
        fillField(chbMotherUnknown, checked);
        fillField(edtMotherName, name);
        enableView(edtMotherName, !checked);
    }

    public void fillResponsible(CheckBox checkBox, boolean checked, EditText edtRespNumSus,
                                String respNumSus, EditText edtRespBirth, String respBirth) {

        fillField(edtRespNumSus, respNumSus);
        fillField(edtRespBirth, respBirth);
        fillField(checkBox, checked);
        enableView(edtRespNumSus, !checked);
        enableView(edtRespBirth, !checked);
    }

    public void fillNationBirth(CheckBox chbNation, boolean check, EditText edtNationBirth, String nationBirth){

        //TODO Don't fill when return to this view
        if(check){
            nationBirth = fragment.getString(R.string.txt_brazil);
        }

        fillField(edtNationBirth, nationBirth);
        fillField(chbNation, check);
        enableView(edtNationBirth, check);
    }


    public void setBirth(EditText editText, String[] date) {
        if (date.length != 3) {
            throw new IllegalArgumentException("The birth date is invalid");
        }

        Formatter out = new Formatter();
        out.format("%s/%s/%s", date[CalendarActivity.DAY], date[CalendarActivity.MONTH],
                date[CalendarActivity.YEAR]);
        fillField(editText, out.toString());
    }

    private void fillMotherName(boolean checked) {
        EditText edtMotherName = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_mother_name);
        CheckBox chbMotherUnknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_mother_unknow);
        fillMotherName(chbMotherUnknown, checked, edtMotherName, "");
    }

    private void fillResponsible(boolean checked) {
        Activity activity = fragment.getActivity();

        String numSus = "";
        String birth = "";
        CheckBox chbResponsible = (CheckBox) activity.findViewById(R.id.chb_ctz_responsible);
        EditText edtRespNumSus = (EditText) activity.findViewById(R.id.edt_ctz_respon_num_sus);
        EditText edtRespBirth = (EditText) activity.findViewById(R.id.edt_ctz_respons_birth);

        if(checked){
            numSus = ((EditText) activity.findViewById(R.id.edt_ctz_num_sus)).getText().toString();
            birth = ((EditText) activity.findViewById(R.id.edt_ctz_birth)).getText().toString();
        }

        fillResponsible(chbResponsible, checked, edtRespNumSus, numSus, edtRespBirth, birth);
    }

    private void fillNationBirth(boolean isChecked) {

        EditText edtNationBirth = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_nation_birth);
        String text = fragment.getString(R.string.txt_brazil);
        if (!isChecked) {
            text = "";
        }
        fillField(edtNationBirth, text);
        enableView(edtNationBirth, !isChecked);
    }

    private void setCities(int arrayResource) {
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_city);
        spinner.setAdapter(getSpinnerAdapter(arrayResource));
        enableView(spinner, true);
    }

    private void disableSpnCity() {
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_city);
        fillField(spinner, 0);
        enableView(spinner, false);
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            showCalendar(view.getId());
        }
    }

    private class OnCheckBoxChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            switch (compoundButton.getId()) {
                case R.id.chb_ctz_mother_unknow: fillMotherName(check); break;
                case R.id.chb_ctz_nation_birth: fillNationBirth(check); break;
                case R.id.chb_ctz_responsible: fillResponsible(check); break;
            }
        }
    }

    private class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

            switch (index) {
                case AL: setCities(R.array.al_cities); break;
                case BA: setCities(R.array.ba_cities); break;
                case SE: setCities(R.array.se_cities);break;
                default: disableSpnCity(); break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    }
}
