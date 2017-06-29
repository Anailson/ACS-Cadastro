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
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.persistence.HousingConditionsPersistence;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.subModels.House;
import tcc.acs_cadastro_mobile.subModels.HousingSituation;
import tcc.acs_cadastro_mobile.subModels.Pet;
import tcc.acs_cadastro_mobile.subModels.WaterAndSanitation;


public class ResidenceStepTwoFragment extends Fragment implements IRequiredFields{

    private static final String HOUSING_CONDITIONS = "HOUSING_CONDITIONS";

    private IResidenceData residenceData;
    private HousingConditionsModel housingConditions;
    private ResidenceStepTwoController controller;

    private RequiredSpinner spnHousingSituation, spnLocation;

    private Spinner spnOwnership, spnResidenceType, spnResidenceAccess,
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

        spnHousingSituation = (RequiredSpinner) view.findViewById(R.id.spn_rsd_housing_situation);
        spnLocation = (RequiredSpinner) view.findViewById(R.id.spn_rsd_location);
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

        spnHousingSituation.setAdapter(controller.getAdapter(R.array.housing_situation));
        spnLocation.setAdapter(controller.getAdapter(R.array.location));
        spnOwnership.setAdapter(controller.getAdapter(R.array.ownership));
        spnResidenceType.setAdapter(controller.getAdapter(R.array.residence_type));
        spnResidenceAccess.setAdapter(controller.getAdapter(R.array.residence_access));
        spnResidenceConstruction.setAdapter(controller.getAdapter(R.array.residence_construction));
        spnConstructionType.setAdapter(controller.getAdapter(R.array.construction_coating));
        spnWaterSupply.setAdapter(controller.getAdapter(R.array.water_supply));
        spnWaterTreatment.setAdapter(controller.getAdapter(R.array.water_treatment));
        spnBathroom.setAdapter(controller.getAdapter(R.array.bathroom));

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

    @Override
    public boolean isRequiredFieldsFilled() {
        return controller.isRequiredFieldsFilled(spnHousingSituation, spnLocation);
    }

    private void getFields() {
        HousingSituation housingSituation = controller.getHousingSituation(
                spnHousingSituation, spnLocation, spnOwnership);
        House house = controller.getHouse(spnResidenceType, edtResidents,
                edtRooms, spnResidenceAccess, spnResidenceConstruction, spnConstructionType);
        boolean electricEnergy = controller.getElectricEnergy(rgrpElectricEnergy);
        WaterAndSanitation waterAndSanitation = controller.getWaterAndSanitation(
                spnWaterSupply, spnWaterTreatment, spnBathroom);
        Pet pet = controller.getPets(rgrpPets, edtPets, chbCat, chbDog, chbBird,
                chbBreeding, chbPetsAnother);

        housingConditions = HousingConditionsPersistence.getHousingConditionsModel(housingSituation,
                house, electricEnergy, waterAndSanitation, pet);
        residenceData.send(housingConditions);
    }

    private void fillFields() {

        int locationIndex = controller.getIndex(housingConditions.getLocation(), R.array.location);
        int constructionIndex = controller.getIndex(housingConditions.getConstruction(), R.array.residence_construction);

        controller.fillField(spnHousingSituation,
                controller.getIndex(housingConditions.getSituation(), R.array.housing_situation));
        controller.fillField(spnLocation, locationIndex);
        controller.fillField(spnOwnership, controller.getOwnershipIndex(locationIndex, housingConditions.getOwnership()));
        controller.fillField(spnResidenceType,
                controller.getIndex(housingConditions.getResidenceType(), R.array.residence_type));
        controller.fillField(edtResidents, StepsController.getEmptyOrValue(housingConditions.getTotalResidents()));
        controller.fillField(edtRooms, StepsController.getEmptyOrValue(housingConditions.getTotalRooms()));
        controller.fillField(spnResidenceAccess,
                controller.getIndex(housingConditions.getResidenceAccess(), R.array.residence_access));
        controller.fillField(spnResidenceConstruction, constructionIndex);
        controller.fillField(spnConstructionType,
                controller.getConstructionTypeIndex(constructionIndex, housingConditions.getConstructionType()));
        controller.fillElectricEnergy(rgrpElectricEnergy, housingConditions.isElectricEnergy());
        controller.fillWaterSupply(spnWaterSupply, housingConditions.getWaterSupply());
        controller.fillWaterConditions(spnWaterTreatment, housingConditions.getWaterTreatment());
        controller.fillBathroom(spnBathroom, housingConditions.getBathroom());
        controller.fillPets(rgrpPets, housingConditions.isHasPets(), housingConditions.getPets(),
                chbCat, chbDog, chbBird, chbBreeding, chbPetsAnother);
        controller.fillField(housingConditions.getPets(), chbCat, chbDog, chbBird, chbBreeding, chbPetsAnother);
        controller.fillField(edtPets, housingConditions.getTotalPets() + "");
    }
}