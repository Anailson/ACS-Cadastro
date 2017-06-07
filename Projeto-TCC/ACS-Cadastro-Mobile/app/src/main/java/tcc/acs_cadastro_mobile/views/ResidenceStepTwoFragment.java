package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceStepTwoController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;

public class ResidenceStepTwoFragment extends Fragment {

    private static final String HOUSING_CONDITIONS = "HOUSING_CONDITIONS";

    private IResidenceData residenceData;
    private HousingConditionsModel housingConditions;
    private ResidenceStepTwoController controller;

    private Spinner spnHousingSituation, spnLocation, spnOwnership, spnResidenceType, spnResidenceAccess,
            spnResidenceConstruction, spnConstructionType, spnWaterSupply, spnWaterTreatment, spnBathroom;
    private EditText edtResidents, edtRooms, edtPets;
    private CheckBox chbCat, chbDog, chbBird, chbBreeding, chbPetsAnother;
    private RadioGroup rgrpElectricEnergy, rgrpPets;

    public static Fragment newInstance(HousingConditionsModel housingConditions) {
        Fragment fragment = new ResidenceStepTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(HOUSING_CONDITIONS, housingConditions);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        residenceData = (IResidenceData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater layout, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = layout.inflate(R.layout.content_rsd_add_2, container, false);
        housingConditions = (HousingConditionsModel) getArguments().getSerializable(HOUSING_CONDITIONS);
        controller = new ResidenceStepTwoController(this);

        spnHousingSituation = (Spinner) view.findViewById(R.id.spn_rsd_housing_situation);
        spnLocation = (Spinner) view.findViewById(R.id.spn_rsd_location);
        spnOwnership = (Spinner) view.findViewById(R.id.spn_rsd_location_if);
        spnResidenceType = (Spinner) view.findViewById(R.id.spn_rsd_residence_type);
        spnResidenceAccess = (Spinner) view.findViewById(R.id.spn_rsd_residence_access);
        spnResidenceConstruction = (Spinner) view.findViewById(R.id.spn_rsd_residence_construction);
        spnConstructionType = (Spinner) view.findViewById(R.id.spn_rsd_construction_type);
        spnWaterSupply = (Spinner) view.findViewById(R.id.spn_rsd_water_supply);
        spnWaterTreatment = (Spinner) view.findViewById(R.id.spn_rsd_water_treatment);
        spnBathroom = (Spinner) view.findViewById(R.id.spn_rsd_bathroom);
        chbCat = (CheckBox) view.findViewById(R.id.chb_rsd_cat);
        chbDog = (CheckBox) view.findViewById(R.id.chb_rsd_dog);
        chbBird = (CheckBox) view.findViewById(R.id.chb_rsd_bird);
        chbBreeding = (CheckBox) view.findViewById(R.id.chb_rsd_breeding);
        chbPetsAnother = (CheckBox) view.findViewById(R.id.chb_rsd_pet_another);
        edtResidents = (EditText) view.findViewById(R.id.edt_rsd_residents);
        edtRooms = (EditText) view.findViewById(R.id.edt_rsd_rooms);
        edtPets = (EditText) view.findViewById(R.id.edt_rsd_pets_total);
        rgrpElectricEnergy = (RadioGroup) view.findViewById(R.id.rgrp_rsd_electric_energy);
        rgrpPets = (RadioGroup) view.findViewById(R.id.rgrp_rsd_pets);

        spnHousingSituation.setAdapter(controller.getSpinnerAdapter(R.array.housing_situation));
        spnLocation.setAdapter(controller.getSpinnerAdapter(R.array.location));
        spnOwnership.setAdapter(controller.getSpinnerAdapter(R.array.ownership));
        spnResidenceType.setAdapter(controller.getSpinnerAdapter(R.array.residence_type));
        spnResidenceAccess.setAdapter(controller.getSpinnerAdapter(R.array.residence_access));
        spnResidenceConstruction.setAdapter(controller.getSpinnerAdapter(R.array.residence_construction));
        spnConstructionType.setAdapter(controller.getSpinnerAdapter(R.array.construction_coating));
        spnWaterSupply.setAdapter(controller.getSpinnerAdapter(R.array.water_supply));
        spnWaterTreatment.setAdapter(controller.getSpinnerAdapter(R.array.water_treatment));
        spnBathroom.setAdapter(controller.getSpinnerAdapter(R.array.bathroom));

        spnLocation.setOnItemSelectedListener(controller.getItemSelectedListener());
        spnResidenceConstruction.setOnItemSelectedListener(controller.getItemSelectedListener());
        rgrpPets.setOnCheckedChangeListener(controller.getOnChangeListener());
        rgrpPets.setTag(rgrpPets.getId(), controller.getOnChangeListener());

        controller.enableView(spnOwnership, false);
        controller.enableView(spnConstructionType, false);

        if (housingConditions != null) {
            fillFields();
        }
        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    private void getFields() {

        HousingConditionsModel.HousingSituation housingSituation = controller.getHousingSituation(
                spnHousingSituation, spnLocation, spnOwnership);
        HousingConditionsModel.House house = controller.getHouse(spnResidenceType, edtResidents,
                edtRooms, spnResidenceAccess, spnResidenceConstruction, spnConstructionType);
        boolean electricEnergy = controller.getElectricEnergy(rgrpElectricEnergy);
        HousingConditionsModel.WaterAndSanitation waterAndSanitation = controller.getWaterAndSanitation(
                spnWaterSupply, spnWaterTreatment, spnBathroom);
        HousingConditionsModel.Pet pet = controller.getPets(rgrpPets, edtPets, chbCat, chbDog, chbBird,
                chbBreeding, chbPetsAnother);

        housingConditions = new HousingConditionsModel(housingSituation, house, electricEnergy,
                waterAndSanitation, pet);
        residenceData.send(housingConditions);
    }

    private void fillFields() {

        int locationIndex = controller.getIndex(housingConditions.getLocation(), R.array.location);
        int constructionIndex = controller.getIndex(housingConditions.getConstruction(), R.array.residence_construction);

        controller.fillField(spnHousingSituation,
                controller.getIndex(housingConditions.getHousingSituation(), R.array.housing_situation));
        controller.fillField(spnLocation, locationIndex);
        controller.fillField(spnOwnership, controller.getOwnershipIndex(locationIndex, housingConditions.getOwnership()));
        controller.fillField(spnResidenceType,
                controller.getIndex(housingConditions.getResidenceType(), R.array.residence_type));
        controller.fillField(edtResidents, String.valueOf(housingConditions.getTotalResidents()));
        controller.fillField(edtRooms, String.valueOf(housingConditions.getTotalRooms()));
        controller.fillField(spnResidenceAccess,
                controller.getIndex(housingConditions.getResidenceAccess(), R.array.residence_access));
        controller.fillField(spnResidenceConstruction, constructionIndex);
        controller.fillField(spnConstructionType,
                controller.getConstructionTypeIndex(constructionIndex, housingConditions.getConstructionType()));
        controller.fillElectricEnergy(rgrpElectricEnergy, housingConditions.isElectricEnergy());
        controller.fillField(spnWaterSupply, housingConditions.getWaterSupply());
        controller.fillField(spnWaterTreatment, housingConditions.getWaterTreatment());
        controller.fillField(spnBathroom, housingConditions.getBathroom());
        controller.fillPets(rgrpPets, housingConditions.isHasPets(), housingConditions.getPets(),
                chbCat, chbDog, chbBird, chbBreeding, chbPetsAnother);
        controller.fillField(housingConditions.getPets(), chbCat, chbDog, chbBird, chbBreeding, chbPetsAnother);
        controller.fillField(edtPets, housingConditions.getTotalPets() + "");
    }
}