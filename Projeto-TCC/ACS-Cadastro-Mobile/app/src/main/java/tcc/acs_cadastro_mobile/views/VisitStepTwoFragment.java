package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.VisitStepTwoController;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.interfaces.IVisit;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.persistence.ReasonsVisitPersistence;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;

public class VisitStepTwoFragment extends Fragment implements IRequiredFields {

    private static final String REASONS_VISIT = "REASONS_VISIT";

    private IVisit visit;
    private ReasonsVisitModel reasons;
    private VisitStepTwoController controller;

    private CheckBox chbAppointment, chbExam, chbVaccine, chbConditions,
            chbPregnant, chbPuerpera, chbNewborn, chbChild, chbMalnutrition, chbRehabilitationDeficiency,
            chbHypertension, chbDiabetes, chbAsthma, chbCopdEmphysema, chbCancer, chbChronicDiseases,
            chbLeprosy, chbTuberculosis, chbRespiratory, chbSmoker, chbHomeBedding, chbVulnerability,
            chbBolsaFamília, chbMentalHealth, chbAlcohol, chbDrugs,
            chbRecordUpdate, chbPeriodicVisit, chbInternment, chbControlEnvironments, chbCollectiveActivities,
            chbGuidance, chbOthers;
			
    private RequiredSpinner spnResult;

    public static Fragment newInstance(ReasonsVisitModel reasons) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(REASONS_VISIT, reasons);
        Fragment fragment = new VisitStepTwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        visit = (IVisit) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_vst_add_2, container, false);
        controller = new VisitStepTwoController(this);
        reasons = (ReasonsVisitModel) getArguments().getSerializable(REASONS_VISIT);

        chbRecordUpdate = (CheckBox) view.findViewById(R.id.chb_vst_record_update);
        chbPeriodicVisit = (CheckBox) view.findViewById(R.id.chb_vst_periodic_visit);
        chbInternment = (CheckBox) view.findViewById(R.id.chb_vst_internment);
        chbControlEnvironments = (CheckBox) view.findViewById(R.id.chb_vst_control_environments);
        chbCollectiveActivities = (CheckBox) view.findViewById(R.id.chb_vst_collective_activities);
        chbGuidance = (CheckBox) view.findViewById(R.id.chb_vst_guidance);
        chbOthers = (CheckBox) view.findViewById(R.id.chb_vst_another);
        chbAppointment = (CheckBox) view.findViewById(R.id.chb_vst_appointment);
        chbExam = (CheckBox) view.findViewById(R.id.chb_vst_exam);
        chbVaccine = (CheckBox) view.findViewById(R.id.chb_vst_vaccine);
        chbConditions = (CheckBox) view.findViewById(R.id.chb_vst_conditions);
        chbPregnant = (CheckBox) view.findViewById(R.id.chb_vst_pregnant);
        chbPuerpera = (CheckBox) view.findViewById(R.id.chb_vst_puerpera);
        chbNewborn = (CheckBox) view.findViewById(R.id.chb_vst_newborn);
        chbChild = (CheckBox) view.findViewById(R.id.chb_vst_child);
        chbMalnutrition = (CheckBox) view.findViewById(R.id.chb_vst_malnutrition);
        chbRehabilitationDeficiency = (CheckBox) view.findViewById(R.id.chb_vst_rehabilitation_deficiency);
        chbHypertension = (CheckBox) view.findViewById(R.id.chb_vst_hypertension);
        chbDiabetes = (CheckBox) view.findViewById(R.id.chb_vst_diabetes);
        chbAsthma = (CheckBox) view.findViewById(R.id.chb_vst_asthma);
        chbCopdEmphysema = (CheckBox) view.findViewById(R.id.chb_vst_copd_emphysema);
        chbCancer = (CheckBox) view.findViewById(R.id.chb_vst_cancer);
        chbChronicDiseases = (CheckBox) view.findViewById(R.id.chb_vst_chronic_diseases);
        chbLeprosy = (CheckBox) view.findViewById(R.id.chb_vst_leprosy);
        chbTuberculosis = (CheckBox) view.findViewById(R.id.chb_vst_tuberculosis);
        chbRespiratory = (CheckBox) view.findViewById(R.id.chb_vst_respiratory);
        chbSmoker = (CheckBox) view.findViewById(R.id.chb_vst_smoker);
        chbHomeBedding = (CheckBox) view.findViewById(R.id.chb_vst_home_bedding);
        chbVulnerability = (CheckBox) view.findViewById(R.id.chb_vst_vulnerabity);
        chbBolsaFamília = (CheckBox) view.findViewById(R.id.chb_vst_bolsa_família);
        chbMentalHealth = (CheckBox) view.findViewById(R.id.chb_vst_mental_health);
        chbAlcohol = (CheckBox) view.findViewById(R.id.chb_vst_alcohol);
        chbDrugs = (CheckBox) view.findViewById(R.id.chb_vst_drugs);
        spnResult = (RequiredSpinner) view.findViewById(R.id.spn_vst_result);

        spnResult.setAdapter(controller.getAdapter(R.array.visit_result));


        if (reasons != null) {
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

        return controller.isRequiredFieldsFilled(spnResult, chbAppointment, chbExam, chbVaccine, chbConditions,
                chbPregnant, chbPuerpera, chbNewborn, chbChild, chbMalnutrition, chbRehabilitationDeficiency,
                chbHypertension, chbDiabetes, chbAsthma, chbCopdEmphysema, chbCancer, chbChronicDiseases,
                chbLeprosy, chbTuberculosis, chbRespiratory, chbSmoker, chbHomeBedding, chbVulnerability,
                chbBolsaFamília, chbMentalHealth, chbAlcohol, chbDrugs, chbRecordUpdate, chbPeriodicVisit,
                chbInternment, chbControlEnvironments, chbCollectiveActivities, chbGuidance, chbOthers);
    }

    private void getFields() {
        ActiveSearch activeSearch = controller.getActiveSearchs(chbAppointment, chbExam, chbVaccine, chbConditions);
        Following following = controller.getFollowing(chbPregnant, chbPuerpera, chbNewborn, chbChild,
                chbMalnutrition, chbRehabilitationDeficiency, chbHypertension, chbDiabetes, chbAsthma,
                chbCopdEmphysema, chbCancer, chbChronicDiseases, chbLeprosy, chbTuberculosis,
                chbRespiratory, chbSmoker, chbHomeBedding, chbVulnerability, chbBolsaFamília,
                chbMentalHealth, chbAlcohol, chbDrugs);
        AnotherReasons anotherReasons = controller.getAnotherReasons(chbRecordUpdate, chbPeriodicVisit, chbInternment,
                chbControlEnvironments, chbCollectiveActivities, chbGuidance, chbOthers);
        String result = controller.getFields(spnResult);

        reasons = controller.get(activeSearch, following, anotherReasons, result);
        visit.send(reasons);
    }

    private void fillFields() {
        controller.fillField(reasons.getActiveSearchs(), chbAppointment, chbExam, chbVaccine, chbConditions);
        controller.fillField(reasons.getAnotherReasons(), chbRecordUpdate, chbPeriodicVisit, chbInternment,
                chbControlEnvironments, chbCollectiveActivities, chbGuidance, chbOthers);
        controller.fillField(reasons.getFollowings(), chbPregnant, chbPuerpera, chbNewborn, chbChild,
                chbMalnutrition, chbRehabilitationDeficiency, chbHypertension, chbDiabetes, chbAsthma,
                chbCopdEmphysema, chbCancer, chbChronicDiseases, chbLeprosy, chbTuberculosis,
                chbRespiratory, chbSmoker, chbHomeBedding, chbVulnerability, chbBolsaFamília,
                chbMentalHealth, chbAlcohol, chbDrugs);
        controller.fillResult(spnResult, reasons.getResult());
    }
}
