package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyStepFourController;
import tcc.acs_cadastro_mobile.interfaces.IAccompany;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.persistence.NasfConductPersistence;
import tcc.acs_cadastro_mobile.subModels.Conduct;
import tcc.acs_cadastro_mobile.subModels.Forwarding;
import tcc.acs_cadastro_mobile.subModels.NASF;

public class AccompanyStepFourFragment extends Fragment implements IRequiredFields {

    private static final String NASF_CONDUCT_MODEL = "NASF_CONDUCT_MODEL";

    private NasfConductModel nasfConduct;
    private IAccompany accompany;
    private AccompanyStepFourController controller;

    private RadioGroup rgrpObservation;
    private CheckBox chbEvaluation, chbProcedures, chbPrescription, chbScheduledAppointment, chbScheduledCare,
            chbGroupScheduling, chbNasfScheduling, chbRrelsease, chbInTheDay, chbSpecializedService,
            chbCaps, chbHospitalization, chbUrgency, chbHomeCareService, chbIntersectoral;

    public static Fragment newInstance(NasfConductModel nasfConduct) {
        Fragment fragment = new AccompanyStepFourFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NASF_CONDUCT_MODEL, nasfConduct);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accompany = (IAccompany) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_acc_add_4, container, false);
        nasfConduct = (NasfConductModel) getArguments().getSerializable(NASF_CONDUCT_MODEL);
        controller = new AccompanyStepFourController(this);

        rgrpObservation = (RadioGroup) view.findViewById(R.id.rgrp_acc_observation);
        chbEvaluation = (CheckBox) view.findViewById(R.id.chb_acc_evaluation);
        chbProcedures = (CheckBox) view.findViewById(R.id.chb_acc_procedures);
        chbPrescription = (CheckBox) view.findViewById(R.id.chb_acc_prescription);
        chbScheduledAppointment = (CheckBox) view.findViewById(R.id.chb_acc_scheduled_appointment);
        chbScheduledCare = (CheckBox) view.findViewById(R.id.chb_acc_scheduled_care);
        chbGroupScheduling = (CheckBox) view.findViewById(R.id.chb_acc_scheduled_group);
        chbNasfScheduling = (CheckBox) view.findViewById(R.id.chb_acc_scheduled_nasf);
        chbRrelsease = (CheckBox) view.findViewById(R.id.chb_acc_release);
        chbInTheDay = (CheckBox) view.findViewById(R.id.chb_acc_in_day);
        chbSpecializedService = (CheckBox) view.findViewById(R.id.chb_acc_specialized);
        chbCaps = (CheckBox) view.findViewById(R.id.chb_acc_caps);
        chbHospitalization = (CheckBox) view.findViewById(R.id.chb_acc_hospitalization);
        chbUrgency = (CheckBox) view.findViewById(R.id.chb_acc_urgency);
        chbHomeCareService = (CheckBox) view.findViewById(R.id.chb_acc_home_care_service);
        chbIntersectoral = (CheckBox) view.findViewById(R.id.chb_acc_intersectoral);

        if (nasfConduct != null) {
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
        return controller.isRequiredFieldsFilled(chbScheduledAppointment, chbScheduledCare,
                chbGroupScheduling, chbNasfScheduling, chbRrelsease, chbInTheDay, chbSpecializedService,
                chbCaps, chbHospitalization, chbUrgency, chbHomeCareService, chbIntersectoral);
    }

    private void getFields() {

        boolean observation = controller.getObservation(rgrpObservation);
        boolean[] nasfs = controller.getFields(chbEvaluation, chbProcedures, chbPrescription);
        NASF nasf = NasfConductPersistence.getNasf(nasfs);

        boolean[] conducts = controller.getFields(chbScheduledAppointment, chbScheduledCare,
                chbGroupScheduling, chbNasfScheduling, chbRrelsease);
        Conduct conduct = NasfConductPersistence.getConduct(conducts);

        boolean[] forwardings = controller.getFields(chbInTheDay, chbSpecializedService,
                chbCaps, chbHospitalization, chbUrgency, chbHomeCareService, chbIntersectoral);
        Forwarding forwarding = NasfConductPersistence.getForwarding(forwardings);

        nasfConduct = NasfConductPersistence.get(observation, nasf, conduct, forwarding);
        accompany.send(nasfConduct);
    }

    private void fillFields() {
        controller.fillObservation(nasfConduct.isObservation(), rgrpObservation);
        controller.fillField(nasfConduct.getNasfs(), chbEvaluation, chbProcedures, chbPrescription);
        controller.fillField(nasfConduct.getConducts(), chbScheduledAppointment, chbScheduledCare,
                chbGroupScheduling, chbNasfScheduling, chbRrelsease);
        controller.fillField(nasfConduct.getForwardings(), chbInTheDay, chbSpecializedService,
                chbCaps, chbHospitalization, chbUrgency, chbHomeCareService, chbIntersectoral);
    }
}
