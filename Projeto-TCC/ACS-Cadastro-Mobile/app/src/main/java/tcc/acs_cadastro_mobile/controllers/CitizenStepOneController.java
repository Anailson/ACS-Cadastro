package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.SpinnerAdapter;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.PersonalDataPersistence;
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
    private Listener listener;

    public CitizenStepOneController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new SpinnerAdapter(fragment.getContext()).getAdapter(arrayResource);
    }

    private Listener getListener(){
        if (listener == null) {
            listener = new Listener();
        }
        return listener;
    }

    public CompoundButton.OnCheckedChangeListener getCheckBoxChangeListener() {
        return getListener();
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener() {
        return getListener();
    }

    public ICalendarListener getCalendarListener() {
        return getListener();
    }

    public boolean isRequiredFieldsFilled(IRequiredView edtName, IRequiredView edtBirthDate,
                      IRequiredView edtMotherName, IRequiredView spnGender, IRequiredView spnRace,
                      IRequiredView spnNationality, IRequiredView spnUf, IRequiredView spnCity) {
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

    public PersonalDataModel get(ParticularData particular, Mother mother, Responsible responsible,
                                 GenderAndRace genderAndRace, Nationality nationality, Contact contact) {
        return PersonalDataPersistence.get(particular, mother, responsible, genderAndRace, nationality, contact);
    }

    public ParticularData getParticularData(EditText edtNumSus, EditText edtName, EditText edtSocialName,
                                            EditText edtNumNis, EditText edtBirth) {

        long numSus = getLong(edtNumSus);
        numSus = AcsRecordPersistence.getMinorValueIfBlank(CitizenModel.class, CitizenModel.NUM_SUS, numSus);
        return PersonalDataPersistence.get(numSus, getFields(edtName),
                getFields(edtSocialName), getLong(edtNumNis), getFields(edtBirth));
    }
    public Mother getMother(CheckBox chbMotherUnknown, EditText edtMotherName) {
        return PersonalDataPersistence.get(chbMotherUnknown.isChecked(), getFields(edtMotherName));
    }

    public Responsible getResponsible(CheckBox chbResponsible,
                                      EditText edtRespNumSus, EditText edtRespBirth) {
        return PersonalDataPersistence.get(chbResponsible.isChecked(), getLong(edtRespNumSus),
                getFields(edtRespBirth));
    }

    public GenderAndRace getGenderAndRace(Spinner spnGender, Spinner spnRace) {
        return PersonalDataPersistence.getGenderAndRace(getFields(spnGender), getFields(spnRace));
    }

    public Nationality getNationality(Spinner spnNationality, EditText edtNationBirth, Spinner spnUf, Spinner spnCity) {
        return PersonalDataPersistence.get(getFields(spnNationality), getFields(edtNationBirth),
                getFields(spnUf), getFields(spnCity));
    }

    public Contact getContact(EditText edtPhone, EditText edtEmail){
        return PersonalDataPersistence.getContact(getFields(edtPhone), getFields(edtEmail));
    }

    public void fillField(EditText edt, long value) {
        String text = value == 0 ? "" : String.valueOf(value);
        fillField(edt, text);
    }

    public void fillResponsible(CheckBox checkBox, boolean checked, EditText edtRespNumSus,
                                long respNumSus, EditText edtRespBirth, String respBirth) {

        fillField(edtRespNumSus, getEmptyOrValue(respNumSus));
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
        spinner.setSelection(position);
    }

    private void fillMotherName(boolean checked) {

        EditText edtMotherName = (EditText) fragment.getActivity().findViewById(R.id.edt_ctz_mother_name);
        CheckBox chbMotherUnknown = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_mother_unknow);
        fillMotherName(chbMotherUnknown, checked, edtMotherName, "");
    }

    public void fillMotherName(CheckBox chbMotherUnknown, boolean checked, EditText edtMotherName, String name) {
        if (checked) {
            name = fragment.getString(R.string.txt_unknow);
        }
        chbMotherUnknown.setOnCheckedChangeListener(null);
        fillField(chbMotherUnknown, checked);
        fillField(edtMotherName, name);
        enableView(edtMotherName, !checked);
        chbMotherUnknown.setOnCheckedChangeListener(getCheckBoxChangeListener());
    }

    private void fillResponsible(boolean checked) {
        Activity activity = fragment.getActivity();

        long numSus = 0;
        String birth = "";
        CheckBox chbResponsible = (CheckBox) activity.findViewById(R.id.chb_ctz_responsible);
        EditText edtRespNumSus = (EditText) activity.findViewById(R.id.edt_ctz_respon_num_sus);
        EditText edtRespBirth = (EditText) activity.findViewById(R.id.edt_ctz_respons_birth);

        if (checked) {
            numSus = getLong((EditText) activity.findViewById(R.id.edt_ctz_num_sus));
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

    private void setBirthDate(int id, String text){
        EditText editText = (EditText) fragment.getActivity().findViewById(id);
        fillField(editText, text);
    }

    private class Listener implements CompoundButton.OnCheckedChangeListener,
                                    AdapterView.OnItemSelectedListener, ICalendarListener {

        //CompoundButton.OnCheckedChangeListener
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            switch (compoundButton.getId()) {
                case R.id.chb_ctz_mother_unknow: fillMotherName(check); break;
                case R.id.chb_ctz_nation_birth: fillNationBirth(check); break;
                case R.id.chb_ctz_responsible: fillResponsible(check);break;
            }
        }

        //AdapterView.OnItemSelectedListener
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

            switch (adapterView.getId()) {
                case R.id.spn_ctz_uf: setCities(index); break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}

        //ICalendarListener
        @Override
        public void onOk(int id, CalendarActivity.Date date) {
            switch (id){
                case R.id.edt_ctz_respons_birth: setBirthDate(id, date.formattedDate(fragment.getContext()));
                case R.id.edt_ctz_birth: setBirthDate(id, date.formattedDate(fragment.getContext()));
            }
        }

        @Override
        public void onCancel() {}
    }
}
