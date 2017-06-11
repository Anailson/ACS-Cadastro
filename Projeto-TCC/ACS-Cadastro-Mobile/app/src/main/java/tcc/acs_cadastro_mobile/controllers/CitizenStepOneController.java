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
import tcc.acs_cadastro_mobile.persistence.PersonalDataPersistence;
import tcc.acs_cadastro_mobile.required.RequiredEditText;
import tcc.acs_cadastro_mobile.required.RequiredSpinner;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.subModels.Responsible;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CitizenStepOneController extends StepsController {

    private final int AL = 1;
    private final int BA = 2;
    private final int SE = 3;

    private Fragment fragment;
    private View.OnClickListener clickListener;
    private CompoundButton.OnCheckedChangeListener chbCheckedListener;
    private AdapterView.OnItemSelectedListener itemSelectedListener;

    public CitizenStepOneController(Fragment fragment) {
        super(fragment);
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

    public boolean isRequiredFieldsFilled(RequiredEditText edtName, RequiredEditText edtBirthDate,
                                          RequiredEditText edtMotherName, RequiredSpinner spnGender, RequiredSpinner spnRace,
                                          RequiredSpinner spnNationality, RequiredSpinner spnUf, RequiredSpinner spnCity) {
        startErrors();
        applyError(edtName, "", "Este campo é obrigatório");
        applyError(edtBirthDate, "", "Este campo é obrigatório");
        applyError(edtMotherName, "", "Este campo é obrigatório");
        applyError(spnGender, fragment.getResources().getString(R.string.txt_default));
        applyError(spnRace, fragment.getResources().getString(R.string.txt_default));
        applyError(spnNationality, fragment.getResources().getString(R.string.txt_default));
        applyError(spnUf, fragment.getResources().getString(R.string.txt_default));
        applyError(spnCity, fragment.getResources().getString(R.string.txt_default));
        return hasError();
    }

    public int getCityIndex(int ufIndex, String city) {
        switch (ufIndex) {
            case AL: return getIndex(city, R.array.al_cities);
            case BA: return getIndex(city, R.array.ba_cities);
            case SE: return getIndex(city, R.array.se_cities);
            default: return 0;
        }
    }

    public ParticularData getParticularData(EditText edtNumSus, EditText edtName, EditText edtSocialName,
                                            EditText edtNumNis, EditText edtBirth) {

        return PersonalDataPersistence.getParticularData(getLong(edtNumSus), getFields(edtName),
                getFields(edtSocialName), getLong(edtNumNis), getFields(edtBirth));
    }
    public Mother getMother(CheckBox chbMotherUnknown, EditText edtMotherName) {
        return PersonalDataPersistence.getMother(chbMotherUnknown.isChecked(), getFields(edtMotherName));
    }

    public Responsible getResponsible(CheckBox chbResponsible,
                                      EditText edtRespNumSus, EditText edtRespBirth) {
        return PersonalDataPersistence.getResponsible(chbResponsible.isChecked(), getLong(edtRespNumSus),
                getFields(edtRespBirth));
    }

    public GenderAndRace getGenderAndRace(Spinner spnGender, Spinner spnRace) {
        return PersonalDataPersistence.getGenderAndRace(getFields(spnGender), getFields(spnRace));
    }

    public Nationality getNationality(Spinner spnNationality,
                                      EditText edtNationBirth, Spinner spnUf, Spinner spnCity) {
        return PersonalDataPersistence.getNationality(getFields(spnNationality), getFields(edtNationBirth),
                getFields(spnUf), getFields(spnCity));
    }

    public Contact getContact(EditText edtPhone, EditText edtEmail){
        return PersonalDataPersistence.getContact(getFields(edtPhone), getFields(edtEmail));
    }

    public void fillField(EditText edt, long value) {
        String text = value == 0 ? "" : String.valueOf(value);
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

    public void fillNationBirth(CheckBox chbNation, boolean check, EditText edtNationBirth, String nationBirth) {

        //TODO Don't fill when return to this view
        if (check) {
            nationBirth = fragment.getString(R.string.txt_brazil);
        }

        fillField(edtNationBirth, nationBirth);
        fillField(chbNation, check);
        enableView(edtNationBirth, check);
    }

    public void fillCity(int uf, Spinner spinner, int position){
        enableView(spinner, true);
        setCities(uf, spinner);
        spinner.setSelection(position, false);
    }

    public void setBirth(EditText editText, String[] date) {
        if (date.length != CalendarActivity.BIRTH) {
            throw new IllegalArgumentException("The birth date is invalid");
        }
        String text = new Formatter().format("%s/%s/%s", date[CalendarActivity.DAY],
                date[CalendarActivity.MONTH], date[CalendarActivity.YEAR]).toString();
        fillField(editText, text);
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

        if (checked) {
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

    private void setCities(int index) {
        setCities(index, (Spinner) fragment.getActivity().findViewById(R.id.spn_ctz_city));
    }

    private void setCities(int index, Spinner spinner){
        switch (index) {
            case AL: spinner.setAdapter(getSpinnerAdapter(R.array.al_cities)); break;
            case BA: spinner.setAdapter(getSpinnerAdapter(R.array.ba_cities)); break;
            case SE: spinner.setAdapter(getSpinnerAdapter(R.array.se_cities)); break;
            default: fillField(spinner, 0); break;
        }
        enableView(spinner, index != 0);
    }

    private void showCalendar(int id) {

        int request = CalendarActivity.BIRTH;
        if (id == R.id.edt_ctz_respons_birth) {
            request = CalendarActivity.RESP_BIRTH;
        }

        Intent newActivity = new Intent(fragment.getActivity(), CalendarActivity.class);
        fragment.startActivityForResult(newActivity, request);
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
                case R.id.chb_ctz_mother_unknow:
                    fillMotherName(check);
                    break;
                case R.id.chb_ctz_nation_birth:
                    fillNationBirth(check);
                    break;
                case R.id.chb_ctz_responsible:
                    fillResponsible(check);
                    break;
            }
        }
    }

    private class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

            switch (adapterView.getId()) {
                case R.id.spn_ctz_uf:
                    setCities(index);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
