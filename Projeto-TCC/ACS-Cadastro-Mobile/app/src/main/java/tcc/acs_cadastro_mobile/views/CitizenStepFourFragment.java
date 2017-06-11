package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepFourController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;
import tcc.acs_cadastro_mobile.persistence.StreetSituationPersistence;
import tcc.acs_cadastro_mobile.required.RequiredRadioGroup;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;

public class CitizenStepFourFragment extends Fragment implements IRequiredFields {

    private static final String STREET_SITUATION_DATA = "STREET_SITUATION_DATA";

    private CitizenStepFourController controller;
    private StreetSituationModel streetSituation;
    private ICitizenData citizenData;
    private RequiredRadioGroup rgrpStreet;
    private RadioGroup rgrpBenefit, rgrpFamily, rgrpInstitutionAnother, rgrpFamilyVisit,
            rgrpHygiene;
    private CheckBox chbRestaurant, chbRestaurantDonation, chbReligiousDonation, chbPeopleDonation,
            chbFoodAnother, chbBath, chbSanitary, chbOral, chbHygieneAnother;
    private Spinner spnStreetTime, spnFoodPerDay;
    private EditText edtInstitutionAnother, edtFamilyVisit;


    public static Fragment newInstance(StreetSituationModel streetSituation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(STREET_SITUATION_DATA, streetSituation);
        Fragment fragment = new CitizenStepFourFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        citizenData = (ICitizenData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_ctz_add_4, container, false);
        controller = new CitizenStepFourController(this);
        streetSituation = (StreetSituationModel) getArguments().getSerializable(STREET_SITUATION_DATA);

        rgrpStreet = (RequiredRadioGroup) view.findViewById(R.id.rgrp_ctz_street);
        rgrpBenefit = (RadioGroup) view.findViewById(R.id.rgrp_ctz_any_benefit);
        rgrpFamily = (RadioGroup) view.findViewById(R.id.rgrp_ctz_family);
        rgrpInstitutionAnother = (RadioGroup) view.findViewById(R.id.rgrp_ctz_institution_another);
        rgrpFamilyVisit = (RadioGroup) view.findViewById(R.id.rgrp_ctz_family_visit);
        rgrpHygiene = (RadioGroup) view.findViewById(R.id.rgrp_ctz_hygiene);
        chbRestaurant = (CheckBox) view.findViewById(R.id.chb_ctz_restaurant);
        chbRestaurantDonation = (CheckBox) view.findViewById(R.id.chb_ctz_restaurant_donation);
        chbReligiousDonation = (CheckBox) view.findViewById(R.id.chb_ctz_religious_donation);
        chbPeopleDonation = (CheckBox) view.findViewById(R.id.chb_ctz_people_donation);
        chbFoodAnother = (CheckBox) view.findViewById(R.id.chb_ctz_food_another);
        chbBath = (CheckBox) view.findViewById(R.id.chb_ctz_bath);
        chbSanitary = (CheckBox) view.findViewById(R.id.chb_ctz_sanitary);
        chbOral = (CheckBox) view.findViewById(R.id.chb_ctz_oral);
        chbHygieneAnother = (CheckBox) view.findViewById(R.id.chb_ctz_hygiene_another);
        spnStreetTime = (Spinner) view.findViewById(R.id.spn_ctz_street_time);
        spnFoodPerDay = (Spinner) view.findViewById(R.id.spn_ctz_food_per_day);
        edtInstitutionAnother = (EditText) view.findViewById(R.id.edt_ctz_institution_another);
        edtFamilyVisit = (EditText) view.findViewById(R.id.edt_ctz_family_visit);

        spnStreetTime.setAdapter(controller.getSpinnerAdapter(R.array.street_time));
        spnFoodPerDay.setAdapter(controller.getSpinnerAdapter(R.array.food_per_day));

        rgrpInstitutionAnother.setOnCheckedChangeListener(controller.getOnChangeListener());
        rgrpFamilyVisit.setOnCheckedChangeListener(controller.getOnChangeListener());
        rgrpHygiene.setOnCheckedChangeListener(controller.getOnChangeListener());

        rgrpInstitutionAnother.setTag(rgrpInstitutionAnother.getId(), controller.getOnChangeListener());
        rgrpFamilyVisit.setTag(rgrpFamilyVisit.getId(), controller.getOnChangeListener());
        rgrpHygiene.setTag(rgrpHygiene.getId(), controller.getOnChangeListener());

        if (streetSituation != null) {
            fillFields();
        }
        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    @Override
    public boolean isRequiredFieldsFilled(){
        return controller.isRequiredFieldsFilled(rgrpStreet);
    }

    private void getFields() {

        StreetSituation street = controller.getStreetSituation(rgrpStreet, spnStreetTime);
        boolean benefit = controller.isBenefit(rgrpBenefit);
        boolean family = controller.isFamily(rgrpFamily);
        Feeding feeding = controller.getFoodOrigin(spnFoodPerDay, chbRestaurant,
                chbRestaurantDonation, chbReligiousDonation, chbPeopleDonation, chbFoodAnother);
        AnotherInstitution anotherInstitution = controller.getInstitutionAnother(
                rgrpInstitutionAnother, edtInstitutionAnother);
        FamilyVisit familyVisit = controller.getFamilyVisit(rgrpFamilyVisit,
                edtFamilyVisit);
        Hygiene hygiene = controller.getHygiene(rgrpHygiene, chbBath,
                chbSanitary, chbOral, chbHygieneAnother);

        streetSituation = StreetSituationPersistence.getInstance(street, benefit, family, feeding,
                anotherInstitution, familyVisit, hygiene);
        citizenData.send(streetSituation);
    }

    private void fillFields() {
        controller.fillStreetSituation(rgrpStreet, streetSituation.isStreetSituation());
        controller.fillStreetTime(spnStreetTime, controller.getIndex(streetSituation.getStreetTime(), R.array.street_time));
        controller.fillBenefit(rgrpBenefit, streetSituation.isBenefit());
        controller.fillFamily(rgrpFamily, streetSituation.isFamily());
        controller.fillFoodPerDay(spnFoodPerDay, controller.getIndex(streetSituation.getFoodPerDay(), R.array.food_per_day));
        controller.fillFoodOrigin(streetSituation.getFoodOrigin(), chbRestaurant, chbRestaurantDonation,
                chbReligiousDonation, chbPeopleDonation, chbFoodAnother);
        controller.fillInstitutionAnother(rgrpInstitutionAnother, streetSituation.isInstitutionAnother(),
                edtInstitutionAnother, streetSituation.getInstitutionAnother());
        controller.fillFamilyVisit(rgrpFamilyVisit, streetSituation.isFamilyVisit(),
                edtFamilyVisit, streetSituation.getFamilyVisitValue());
        controller.fillHygiene(rgrpHygiene, streetSituation.isHygiene(), streetSituation.getHygienes(),
                chbBath, chbSanitary, chbOral, chbHygieneAnother);
    }
}

