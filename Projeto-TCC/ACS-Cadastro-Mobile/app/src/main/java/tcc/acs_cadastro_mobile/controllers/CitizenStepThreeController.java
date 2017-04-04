package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CitizenStepThreeController {

    private final int AL = 1;
    private final int BA = 2;
    private final int SE = 3;

    private Fragment fragment;
    private View.OnClickListener clickListener;
    private CompoundButton.OnCheckedChangeListener chbCheckedListener;
    private AdapterView.OnItemSelectedListener itemSelectedListener;

    public CitizenStepThreeController(Fragment fragment) {
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

    public String[] getIndexAndValue(Spinner spinner){
        return new String[]{spinner.getSelectedItemPosition() + "", spinner.getSelectedItem().toString()};
    }

    public void fillField(EditText edt, String text){
        edt.setText(text);
    }

    public void fillField(EditText edt, long value){
        String text = value < 0 ? "" : String.valueOf(value);
        fillField(edt, text);
    }

    public void fillField(Spinner spinner, int position){
        spinner.setSelection(position);
    }

    public void fillField(Spinner spinner, String index){
        int position = index.equals(String.valueOf(CitizenModel.INT_DEFAULT_VALUE)) ? 0 : Integer.parseInt(index);
        fillField(spinner, position);
    }

    public void fillMotherName(CheckBox chbMotherUnknown, boolean checked, EditText edtMotherName, String name) {
        if (checked) {
            name = fragment.getString(R.string.txt_unknow);
        }
        edtMotherName.setEnabled(!checked);
        chbMotherUnknown.setChecked(checked);
        fillField(edtMotherName, name);
    }

    public void fillResponsible(CheckBox checkBox, boolean checked, EditText edtRespNumSus,
                                String respNumSus, EditText edtRespBirth, String respBirth) {

        edtRespNumSus.setText(respNumSus);
        edtRespBirth.setText(respBirth);
        checkBox.setChecked(checked);
        edtRespNumSus.setEnabled(!checked);
        edtRespBirth.setEnabled(!checked);
    }

    public void fillNationBirth(CheckBox chbNation, boolean check, EditText edtNationBirth, String nationBirth){

        if(check){
            nationBirth = fragment.getString(R.string.txt_brazil);
        }
        edtNationBirth.setText(nationBirth);
        edtNationBirth.setEnabled(!check);
        chbNation.setChecked(check);
    }


    public void setBirth(int request, String[] date) {
        if (date.length != 3) {
            throw new IllegalArgumentException("The birth date is invalid");
        }

        int id = R.id.edt_ctz_birth;
        if (request == CalendarActivity.RESP_BIRTH) {
            id = R.id.edt_ctz_respons_birth;
        }

        Formatter out = new Formatter();
        out.format("%s/%s/%s", date[CalendarActivity.DAY], date[CalendarActivity.MONTH],
                date[CalendarActivity.YEAR]);
        EditText editText = (EditText) fragment.getActivity().findViewById(id);
        editText.setText(out.toString());
    }

    private void fillMotherName(boolean checked) {
        EditText edtMotherName = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_mother_name);
        CheckBox chbMotherUknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_mother_unknow);
        fillMotherName(chbMotherUknown, checked, edtMotherName, "");
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
        edtNationBirth.setEnabled(!isChecked);
        if (isChecked) {
            edtNationBirth.setText(R.string.txt_brazil);
        } else {
            edtNationBirth.setText("");
        }
    }

    private void setCities(int arrayResource) {
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_city);
        spinner.setEnabled(true);
        spinner.setAdapter(getSpinnerAdapter(arrayResource));
    }

    private void disableSpnCity() {
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_city);
        spinner.setSelection(0);
        spinner.setEnabled(false);
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
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
