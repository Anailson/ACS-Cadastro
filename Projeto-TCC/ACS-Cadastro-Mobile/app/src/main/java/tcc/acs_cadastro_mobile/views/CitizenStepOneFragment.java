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
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepOneController;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;

public class CitizenStepOneFragment extends Fragment {

    private static final String PERSONAL_DATA = "PERSONAL_DATA";
    private CitizenStepOneController controller;
    private ICitizenData citizenData;
    private PersonalDataModel personalData;

    private EditText edtNumSus, edtName, edtSocialName, edtMotherName, edtNumNis, edtBirth,
            edtRespNumSus, edtRespBirth, edtNationBirth, edtPhone, edtEmail;
    private Spinner spnGender, spnRace, spnNationality, spnUf, spnCity;
    private CheckBox chbMotherUnknown, chbResponsible, chbNationBirth;

    public static Fragment newInstance(PersonalDataModel personalData) {
        Fragment fragment = new CitizenStepOneFragment();
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
        edtName = (EditText) view.findViewById(R.id.edt_ctz_name);
        edtSocialName = (EditText) view.findViewById(R.id.edt_ctz_social_name);
        edtMotherName = (EditText) view.findViewById(R.id.edt_ctz_mother_name);
        edtNumNis = (EditText) view.findViewById(R.id.edt_ctz_num_nis);
        edtBirth = (EditText) view.findViewById(R.id.edt_ctz_birth);
        edtRespNumSus = (EditText) view.findViewById(R.id.edt_ctz_respon_num_sus);
        edtRespBirth = (EditText) view.findViewById(R.id.edt_ctz_respons_birth);
        edtNationBirth = (EditText) view.findViewById(R.id.edt_ctz_nation_birth);
        edtPhone = (EditText) view.findViewById(R.id.edt_ctz_phone);
        edtEmail = (EditText) view.findViewById(R.id.edt_ctz_email);
        spnGender = (Spinner) view.findViewById(R.id.spn_ctz_gender);
        spnRace = (Spinner) view.findViewById(R.id.spn_ctz_race);
        spnNationality = (Spinner) view.findViewById(R.id.spn_ctz_nationality);
        spnUf = (Spinner) view.findViewById(R.id.spn_ctz_uf);
        spnCity = (Spinner) view.findViewById(R.id.spn_ctz_city);
        chbMotherUnknown = (CheckBox) view.findViewById(R.id.chb_ctz_mother_unknow);
        chbResponsible = (CheckBox) view.findViewById(R.id.chb_ctz_responsible);
        chbNationBirth = (CheckBox) view.findViewById(R.id.chb_ctz_nation_birth);

        spnGender.setAdapter(controller.getSpinnerAdapter(R.array.gender));
        spnRace.setAdapter(controller.getSpinnerAdapter(R.array.race));
        spnNationality.setAdapter(controller.getSpinnerAdapter(R.array.nation));
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CalendarActivity.BIRTH || requestCode == CalendarActivity.RESP_BIRTH) {
            if (resultCode == Activity.RESULT_OK) {
                String[] dateBirth = data.getStringArrayExtra(CalendarActivity.VALUE);
                controller.setBirth(requestCode, dateBirth);
            }
        }
    }

    @Override
    public void onDetach() {

        long numSus = controller.getLong(edtNumSus.getText().toString());
        String name = edtName.getText().toString();
        String socialName = edtSocialName.getText().toString();
        boolean isMotherUnknown = chbMotherUnknown.isChecked();
        String motherName = edtMotherName.getText().toString();
        long numNis = controller.getLong(edtNumNis.getText().toString());
        String birth = edtBirth.getText().toString();
        boolean responsible = chbResponsible.isChecked();
        long respNumSus = controller.getLong(edtRespNumSus.getText().toString());
        String respBirth = edtRespBirth.getText().toString();
        String[] gender = controller.getIndexAndValue(spnGender);
        String[] race = controller.getIndexAndValue(spnRace);
        String[] nationality = controller.getIndexAndValue(spnNationality);
        String nationBirth = edtNationBirth.getText().toString();
        String[] uf = controller.getIndexAndValue(spnUf);
        String[] city = controller.getIndexAndValue(spnCity);
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        personalData = new PersonalDataModel(numSus, name, socialName, isMotherUnknown, motherName,
                numNis, birth, responsible, respNumSus, respBirth, gender, race, nationality,
                nationBirth, uf, city, phone, email);
        citizenData.send(personalData);

        super.onDetach();
    }

    private boolean isFilled(){
        return true;
    }

    private void fillFields(PersonalDataModel personalData) {

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
        controller.fillField(spnGender, personalData.getGender()[CitizenModel.INDEX]);
        controller.fillField(spnRace, personalData.getRace()[CitizenModel.INDEX]);
        controller.fillField(spnNationality, personalData.getNationality()[CitizenModel.INDEX]);
        controller.fillNationBirth(chbNationBirth, personalData.isNationBirth(), edtNationBirth,
                personalData.getNationBirth());
        controller.fillField(spnUf, personalData.getUf()[CitizenModel.INDEX]);
        controller.fillField(spnCity, personalData.getCity()[CitizenModel.INDEX]);
        controller.fillField(edtPhone, personalData.getPhone());
        controller.fillField(edtEmail, personalData.getEmail());
    }
}