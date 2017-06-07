package tcc.acs_cadastro_mobile.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepOneController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.subModels.Responsible;
import tcc.acs_cadastro_mobile.persistence.PersonalDataPersistence;
import tcc.acs_cadastro_mobile.required.RequiredEditText;
import tcc.acs_cadastro_mobile.required.RequiredSpinner;

public class CitizenStepOneFragment extends Fragment {

    private static final String PERSONAL_DATA = "PERSONAL_DATA";

    private CitizenStepOneController controller;
    private ICitizenData citizenData;
    private PersonalDataModel personalData;

    private RequiredEditText edtName, edtMotherName, edtBirth;
    private RequiredSpinner spnGender, spnRace, spnNationality, spnUf, spnCity;
    private EditText edtNumSus, edtSocialName, edtNumNis, edtRespNumSus, edtRespBirth, edtNationBirth,
            edtPhone, edtEmail;
    private CheckBox chbMotherUnknown, chbResponsible, chbNationBirth;

    public static CitizenStepOneFragment newInstance(PersonalDataModel personalData) {
        CitizenStepOneFragment fragment = new CitizenStepOneFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PERSONAL_DATA, personalData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        citizenData = (ICitizenData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater layout, ViewGroup container, Bundle savedInstanceState) {

        View view = layout.inflate(R.layout.content_ctz_add_1, container, false);

        controller = new CitizenStepOneController(this);
        personalData = (PersonalDataModel) getArguments().getSerializable(PERSONAL_DATA);

        edtNumSus = (EditText) view.findViewById(R.id.edt_ctz_num_sus);
        edtName = (RequiredEditText) view.findViewById(R.id.edt_ctz_name);
        edtSocialName = (EditText) view.findViewById(R.id.edt_ctz_social_name);
        edtMotherName = (RequiredEditText) view.findViewById(R.id.edt_ctz_mother_name);
        edtNumNis = (EditText) view.findViewById(R.id.edt_ctz_num_nis);
        edtBirth = (RequiredEditText) view.findViewById(R.id.edt_ctz_birth);
        edtRespNumSus = (EditText) view.findViewById(R.id.edt_ctz_respon_num_sus);
        edtRespBirth = (RequiredEditText) view.findViewById(R.id.edt_ctz_respons_birth);
        edtNationBirth = (EditText) view.findViewById(R.id.edt_ctz_nation_birth);
        edtPhone = (EditText) view.findViewById(R.id.edt_ctz_phone);
        edtEmail = (EditText) view.findViewById(R.id.edt_ctz_email);
        spnGender = (RequiredSpinner) view.findViewById(R.id.spn_ctz_gender);
        spnRace = (RequiredSpinner) view.findViewById(R.id.spn_ctz_race);
        spnNationality = (RequiredSpinner) view.findViewById(R.id.spn_ctz_nationality);
        spnUf = (RequiredSpinner) view.findViewById(R.id.spn_ctz_uf);
        spnCity = (RequiredSpinner) view.findViewById(R.id.spn_ctz_city);
        chbMotherUnknown = (CheckBox) view.findViewById(R.id.chb_ctz_mother_unknow);
        chbResponsible = (CheckBox) view.findViewById(R.id.chb_ctz_responsible);
        chbNationBirth = (CheckBox) view.findViewById(R.id.chb_ctz_nation_birth);

        spnGender.setAdapter(controller.getSpinnerAdapter(R.array.gender));
        spnRace.setAdapter(controller.getSpinnerAdapter(R.array.race));
        spnNationality.setAdapter(controller.getSpinnerAdapter(R.array.nationality));
        spnUf.setAdapter(controller.getSpinnerAdapter(R.array.uf));
        spnCity.setAdapter(controller.getSpinnerAdapter(R.array.se_cities));
        spnCity.setEnabled(false);


        if (personalData != null) {
            fillFields(personalData);
        }

        edtBirth.setOnClickListener(controller.getClickListener());
        edtRespBirth.setOnClickListener(controller.getClickListener());
        spnUf.setOnItemSelectedListener(controller.getItemSelectedListener());
        chbMotherUnknown.setOnCheckedChangeListener(controller.getCheckBoxChangeListener());
        chbNationBirth.setOnCheckedChangeListener(controller.getCheckBoxChangeListener());
        chbResponsible.setOnCheckedChangeListener(controller.getCheckBoxChangeListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO fill fields when fragment is on resume
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String[] dateBirth = data.getStringArrayExtra(CalendarActivity.VALUE);
            switch (requestCode){
                case CalendarActivity.BIRTH: controller.setBirth(edtBirth, dateBirth); break;
                case CalendarActivity.RESP_BIRTH: controller.setBirth(edtRespBirth, dateBirth); break;
            }
        }
    }

    @Override
    public void onDetach() {

        ParticularData particular = controller.getParticularData(edtNumSus, edtName, edtSocialName, edtNumNis, edtBirth);
        Mother mother = controller.getMother(chbMotherUnknown, edtMotherName);
        Responsible responsible = controller.getResponsible(chbResponsible, edtRespNumSus, edtRespBirth);
        GenderAndRace genderAndRace = controller.getGenderAndRace(spnGender, spnRace);
        Nationality nationality = controller.getNationality(spnNationality, edtNationBirth, spnUf, spnCity);
        Contact contact = controller.getContact(edtPhone, edtEmail);

        personalData = PersonalDataPersistence.getInstance(particular, mother, responsible,genderAndRace, nationality, contact);
        citizenData.send(personalData);

        super.onDetach();
    }

    public boolean isRequiredFieldsFilled(){
        return controller.isRequiredFieldsFilled(edtName, edtMotherName,
                edtBirth, spnGender, spnRace, spnNationality, spnUf, spnCity);
    }

    private void fillFields(PersonalDataModel personalData) {
        int ufIndex = controller.getIndex(personalData.getUf(), R.array.uf);

        //TODO: Set spinner city according personalData value (whats wrong?)
        controller.fillField(edtNumSus, personalData.getNumSus());
        controller.fillField(edtName, personalData.getName());
        controller.fillField(edtSocialName, personalData.getSocialName());
        controller.fillMotherName(chbMotherUnknown, personalData.isMotherUnknown(), edtMotherName,
                personalData.getMotherName());
        controller.fillField(edtNumNis, personalData.getNumNis());
        controller.fillField(edtBirth, personalData.getBirth());
        controller.fillResponsible(chbResponsible, personalData.isResponsible(), edtRespNumSus,
                personalData.getRespNumSus() + "", edtRespBirth, personalData.getRespBirth());
        controller.fillField(spnGender, controller.getIndex(personalData.getGender(), R.array.gender));
        controller.fillField(spnRace, controller.getIndex(personalData.getRace(), R.array.race));
        controller.fillField(spnNationality, controller.getIndex(personalData.getNation(), R.array.nationality));
        controller.fillNationBirth(chbNationBirth, personalData.isNationBirth(), edtNationBirth,
                personalData.getNationBirth());
        controller.fillField(spnUf, ufIndex);
        controller.fillCity(ufIndex, spnCity, controller.getCityIndex(ufIndex, personalData.getCity()));
        controller.fillField(edtPhone, personalData.getPhone());
        controller.fillField(edtEmail, personalData.getEmail());
    }
}