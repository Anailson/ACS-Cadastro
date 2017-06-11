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
import tcc.acs_cadastro_mobile.controllers.CitizenStepThreeController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.persistence.HealthConditionsPersistence;
import tcc.acs_cadastro_mobile.subModels.Diseases;
import tcc.acs_cadastro_mobile.subModels.HeartDisease;
import tcc.acs_cadastro_mobile.subModels.Interment;
import tcc.acs_cadastro_mobile.subModels.KidneyDisease;
import tcc.acs_cadastro_mobile.subModels.Plant;
import tcc.acs_cadastro_mobile.subModels.Pregnant;
import tcc.acs_cadastro_mobile.subModels.RespiratoryDisease;

public class CitizenStepThreeFragment extends Fragment implements IRequiredFields{

    private static final String HEALTH_CONDITIONS_DATA = "HEALTH_CONDITIONS_DATA";

    private CitizenStepThreeController controller;
    private ICitizenData citizenData;
    private HealthConditionsModel healthConditions;

    private RadioGroup rgrpPregnant, rgrpSmoker, rgrpAlcohol, rgrpDrugs, rgrpHypertension, rgrpDiabetes,
            rgrpAvc, rgrpHeartAttack, rgrpLeprosy, rgrpTuberculosis, rgrpCancer, rgrpInBed, rgrpDomiciled,
            rgrpOtherPractices, rgrpMentalHealth, rgrpHeartDisease, rgrpKidneyDisease, rgrpRespiratoryDisease,
            rgrpInterment, rgrpPlants;
    private EditText edtPregnant, edtInterment, edtPlants;
    private Spinner spnWeight;
    private CheckBox chbCardiacInsufficiency, chbHeartAnother, chbHeartDontKnow, chbRenalInsufficiency,
            chbKidneyAnother, chbKidneyDontKnow, chbAsthma, chbEmphysema, chbRespiratoryAnother, chbRespiratoryDontKnow;

    public static Fragment newInstance(HealthConditionsModel healthConditions) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(HEALTH_CONDITIONS_DATA, healthConditions);
        Fragment fragment = new CitizenStepThreeFragment();
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

        View view = inflater.inflate(R.layout.content_ctz_add_3, container, false);
        controller = new CitizenStepThreeController(this);
        healthConditions = (HealthConditionsModel) getArguments().getSerializable(HEALTH_CONDITIONS_DATA);

        rgrpPregnant = (RadioGroup) view.findViewById(R.id.rgrp_ctz_pregnant);
        rgrpSmoker = (RadioGroup) view.findViewById(R.id.rgrp_ctz_smoker);
        rgrpAlcohol = (RadioGroup) view.findViewById(R.id.rgrp_ctz_alcohol);
        rgrpDrugs = (RadioGroup) view.findViewById(R.id.rgrp_ctz_drugs);
        rgrpHypertension = (RadioGroup) view.findViewById(R.id.rgrp_ctz_hypertension);
        rgrpDiabetes = (RadioGroup) view.findViewById(R.id.rgrp_ctz_diabetes);
        rgrpAvc = (RadioGroup) view.findViewById(R.id.rgrp_ctz_avc);
        rgrpHeartAttack = (RadioGroup) view.findViewById(R.id.rgrp_ctz_heart_attack);
        rgrpLeprosy = (RadioGroup) view.findViewById(R.id.rgrp_ctz_leprosy);
        rgrpTuberculosis = (RadioGroup) view.findViewById(R.id.rgrp_ctz_tuberculosis);
        rgrpCancer = (RadioGroup) view.findViewById(R.id.rgrp_ctz_cancer);
        rgrpInBed = (RadioGroup) view.findViewById(R.id.rgrp_ctz_in_bed);
        rgrpDomiciled = (RadioGroup) view.findViewById(R.id.rgrp_ctz_domiciled);
        rgrpOtherPractices = (RadioGroup) view.findViewById(R.id.rgrp_ctz_other_practices);
        rgrpMentalHealth = (RadioGroup) view.findViewById(R.id.rgrp_ctz_mental_health);
        rgrpHeartDisease = (RadioGroup) view.findViewById(R.id.rgrp_ctz_heart_disease);
        rgrpKidneyDisease = (RadioGroup) view.findViewById(R.id.rgrp_ctz_kidney_disease);
        rgrpRespiratoryDisease = (RadioGroup) view.findViewById(R.id.rgrp_ctz_respiratory_disease);
        rgrpInterment = (RadioGroup) view.findViewById(R.id.rgrp_ctz_interment);
        rgrpPlants = (RadioGroup) view.findViewById(R.id.rgrp_ctz_plants);
        chbCardiacInsufficiency = (CheckBox) view.findViewById(R.id.chb_ctz_cardiac_insufficiency);
        chbHeartAnother = (CheckBox) view.findViewById(R.id.chb_ctz_heart_another);
        chbHeartDontKnow = (CheckBox) view.findViewById(R.id.chb_ctz_heart_dont_know);
        chbRenalInsufficiency = (CheckBox) view.findViewById(R.id.chb_ctz_renal_insufficiency);
        chbKidneyAnother = (CheckBox) view.findViewById(R.id.chb_ctz_renal_another);
        chbKidneyDontKnow = (CheckBox) view.findViewById(R.id.chb_ctz_renal_dont_know);
        chbAsthma = (CheckBox) view.findViewById(R.id.chb_ctz_asthma);
        chbEmphysema = (CheckBox) view.findViewById(R.id.chb_ctz_emphysema);
        chbRespiratoryAnother = (CheckBox) view.findViewById(R.id.chb_ctz_respiratory_another);
        chbRespiratoryDontKnow = (CheckBox) view.findViewById(R.id.chb_ctz_respiratory_dont_know);
        edtPregnant = (EditText) view.findViewById(R.id.edt_ctz_pregnant);
        edtInterment = (EditText) view.findViewById(R.id.edt_ctz_interment);
        edtPlants = (EditText) view.findViewById(R.id.edt_ctz_plants);
        spnWeight = (Spinner) view.findViewById(R.id.spn_ctz_weight);

        spnWeight.setAdapter(controller.getSpinnerAdapter(R.array.weight));

        rgrpPregnant.setOnCheckedChangeListener(controller.getRadioChangeListener());
        rgrpHeartDisease.setOnCheckedChangeListener(controller.getRadioChangeListener());
        rgrpKidneyDisease.setOnCheckedChangeListener(controller.getRadioChangeListener());
        rgrpRespiratoryDisease.setOnCheckedChangeListener(controller.getRadioChangeListener());
        rgrpInterment.setOnCheckedChangeListener(controller.getRadioChangeListener());
        rgrpPlants.setOnCheckedChangeListener(controller.getRadioChangeListener());

        rgrpPregnant.setTag(rgrpPregnant.getId(), controller.getRadioChangeListener());
        rgrpHeartDisease.setTag(rgrpHeartDisease.getId(), controller.getRadioChangeListener());
        rgrpKidneyDisease.setTag(rgrpKidneyDisease.getId(), controller.getRadioChangeListener());
        rgrpRespiratoryDisease.setTag(rgrpRespiratoryDisease.getId(), controller.getRadioChangeListener());
        rgrpInterment.setTag(rgrpInterment.getId(), controller.getRadioChangeListener());
        rgrpPlants.setTag(rgrpPlants.getId(), controller.getRadioChangeListener());

        if (healthConditions != null) {
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
        return true;
    }

    private void getFields() {

        Pregnant pregnant = controller.getPregnant(rgrpPregnant, edtPregnant);
        String weight = controller.getFields(spnWeight);
        Diseases diseases = controller.getDiseases(rgrpSmoker, rgrpAlcohol, rgrpDrugs,
                rgrpHypertension, rgrpDiabetes, rgrpAvc, rgrpHeartAttack, rgrpLeprosy, rgrpTuberculosis,
                rgrpCancer, rgrpInBed, rgrpDomiciled, rgrpOtherPractices, rgrpMentalHealth);
        HeartDisease heartDisease = controller.getHeartDisease(rgrpHeartDisease,
                chbCardiacInsufficiency, chbHeartAnother, chbHeartDontKnow);
        KidneyDisease kidneyDisease = controller.getKidneyDisease(rgrpKidneyDisease,
                chbRenalInsufficiency, chbKidneyAnother, chbKidneyDontKnow);
        RespiratoryDisease respiratoryDisease = controller.getRespiratoryDisease(
                rgrpRespiratoryDisease, chbAsthma, chbEmphysema, chbRespiratoryAnother, chbRespiratoryDontKnow);
        Interment interment = controller.getInterment(rgrpInterment, edtInterment);
        Plant plants = controller.getPlant(rgrpPlants, edtPlants);


        healthConditions = HealthConditionsPersistence.getInstance(pregnant, weight, diseases, heartDisease,
                kidneyDisease, respiratoryDisease, interment, plants);

        citizenData.send(healthConditions);
    }

    private void fillFields() {

        controller.fillPregnant(edtPregnant, healthConditions.getMaternity(),
                rgrpPregnant, healthConditions.isPregnant());
        controller.fillField(spnWeight, controller.getIndex(healthConditions.getWeight(), R.array.weight));
        controller.fillSmoker(rgrpSmoker, healthConditions.isSmoker());
        controller.fillAlcohol(rgrpAlcohol, healthConditions.isAlcohol());
        controller.fillDrugs(rgrpDrugs, healthConditions.isDrugs());
        controller.fillHypertension(rgrpHypertension, healthConditions.isHypertension());
        controller.fillDiabetes(rgrpDiabetes, healthConditions.isDiabetes());
        controller.fillAvc(rgrpAvc, healthConditions.isAvc());
        controller.fillHeartAttack(rgrpHeartAttack, healthConditions.isHeartAttack());
        controller.fillLeprosy(rgrpLeprosy, healthConditions.isLeprosy());
        controller.fillTuberculosis(rgrpTuberculosis, healthConditions.isTuberculosis());
        controller.fillCancer(rgrpCancer, healthConditions.isCancer());
        controller.fillInBed(rgrpInBed, healthConditions.isInBend());
        controller.fillDomiciled(rgrpDomiciled, healthConditions.isDomiciled());
        controller.fillOtherPractices(rgrpOtherPractices, healthConditions.isOtherPractices());
        controller.fillMentalHealth(rgrpMentalHealth, healthConditions.isMentalHealth());
        controller.fillHeartDisease(rgrpHeartDisease, healthConditions.isHeartDisease(),
                healthConditions.getHeartDiseases(), chbCardiacInsufficiency, chbHeartAnother, chbHeartDontKnow);
        controller.fillKidneyDisease(rgrpKidneyDisease, healthConditions.isKidneyDisease(),
                healthConditions.getKidneyDiseases(), chbRenalInsufficiency, chbKidneyAnother, chbKidneyDontKnow);
        controller.fillRespiratoryDisease(rgrpRespiratoryDisease, healthConditions.isRespiratoryDisease(),
                healthConditions.getRespiratoryDiseases(), chbAsthma, chbEmphysema, chbRespiratoryAnother,
                chbRespiratoryDontKnow);
        controller.fillInterment(edtInterment, healthConditions.getIntermentValue(),
                rgrpInterment, healthConditions.isInterment());
        controller.fillPlants(rgrpPlants, healthConditions.isPlant(), edtPlants,
                healthConditions.getPlantValue());
    }
}
