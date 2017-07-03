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
import tcc.acs_cadastro_mobile.controllers.CitizenStepTwoController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.persistence.SocialDemographicPersistence;
import tcc.acs_cadastro_mobile.customViews.RequiredRadioGroup;
import tcc.acs_cadastro_mobile.subModels.CommunityTraditional;
import tcc.acs_cadastro_mobile.subModels.Deficiency;
import tcc.acs_cadastro_mobile.subModels.EducationEmployment;
import tcc.acs_cadastro_mobile.subModels.HealthAndGroup;
import tcc.acs_cadastro_mobile.subModels.SexualOrientation;

public class CitizenStepTwoFragment extends Fragment implements IRequiredFields{

    private static final String SOCIAL_DEMOGRAPHIC_DATA = "SOCIAL_DEMOGRAPHIC_DATA";

    private ICitizenData sendCitizenData;
    private SocialDemographicModel socialDemographicData;
    private CitizenStepTwoController controller;

    private RequiredRadioGroup rgrpSchool, rgrpDeficiency;

    private EditText edtOccupation, edtCommunityTraditional;
    private Spinner spnKinship, spnEducation, spnEmployment, spnKids09, spnSexualOrientation;
    private RadioGroup rgrpCaregiver, rgrpCommunityGroup, rgrpHealthPlan,
            rgrpCommunityTraditional, rgrpSexualOrientation;
    private CheckBox chbHearing, chbVisual,chbIntellectual, chbPhysical, chbAnother;

    public static Fragment newInstance(SocialDemographicModel socialDemographicData) {
        Fragment fragment = new CitizenStepTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SOCIAL_DEMOGRAPHIC_DATA, socialDemographicData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendCitizenData = (ICitizenData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_ctz_add_2, container, false);
        controller = new CitizenStepTwoController(this);
        socialDemographicData = (SocialDemographicModel) getArguments().getSerializable(SOCIAL_DEMOGRAPHIC_DATA);

        //TODO what if screen to rotation to horizontal/vertical, how to getGenderAndRace values???

        edtOccupation = (EditText) view.findViewById(R.id.edt_ctz_occupation);
        edtCommunityTraditional = (EditText) view.findViewById(R.id.edt_ctz_community_traditional);
        spnKinship = (Spinner) view.findViewById(R.id.spn_ctz_kinship);
        spnEducation = (Spinner) view.findViewById(R.id.spn_ctz_education);
        spnEmployment = (Spinner) view.findViewById(R.id.spn_ctz_employment);
        spnKids09 = (Spinner) view.findViewById(R.id.spn_ctz_kids_0_9);
        spnSexualOrientation = (Spinner) view.findViewById(R.id.spn_ctz_sexual_orientation);
        rgrpSchool = (RequiredRadioGroup) view.findViewById(R.id.rgrp_ctz_school);
        rgrpCaregiver = (RadioGroup) view.findViewById(R.id.rgrp_ctz_caregiver);
        rgrpCommunityGroup = (RadioGroup) view.findViewById(R.id.rgrp_ctz_community_group);
        rgrpHealthPlan = (RadioGroup) view.findViewById(R.id.rgrp_ctz_health_plan);
        rgrpCommunityTraditional = (RadioGroup) view.findViewById(R.id.rgrp_ctz_community_traditional);
        rgrpSexualOrientation = (RadioGroup) view.findViewById(R.id.rgrp_ctz_sexual_orientation);
        rgrpDeficiency = (RequiredRadioGroup) view.findViewById(R.id.rgrp_ctz_deficiency);
        chbHearing = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_hearing);
        chbVisual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_visual);
        chbIntellectual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_intellectual);
        chbPhysical = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_physical);
        chbAnother = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_another);

        spnKinship.setAdapter(controller.getAdapter(R.array.kinship));
        spnEducation.setAdapter(controller.getAdapter(R.array.education));
        spnEmployment.setAdapter(controller.getAdapter(R.array.employment));
        spnKids09.setAdapter(controller.getAdapter(R.array.kids_0_9));
        spnSexualOrientation.setAdapter(controller.getAdapter(R.array.orientation_sexual));

        if (socialDemographicData != null) {
            fillFields();
        }

        rgrpCommunityTraditional.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        rgrpSexualOrientation.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        rgrpDeficiency.setOnCheckedChangeListener(controller.getCheckedChangeListener());

        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }
    @Override
    public boolean isRequiredFieldsFilled() {
        return controller.isRequiredFieldsFilled(rgrpSchool, rgrpDeficiency);
    }

    private void getFields(){
        String kinship = controller.getFields(spnKinship);
        EducationEmployment educationEmployment = controller.getEducationEmployment(edtOccupation,
                        rgrpSchool, spnEducation, spnEmployment);
        HealthAndGroup healthAndGroup = controller.getHealthAndGroup(rgrpCaregiver, rgrpCommunityGroup,
                        rgrpHealthPlan);
        String kids09 = controller.getFields(spnKids09);
        CommunityTraditional communityTraditional = controller.getCommunityTraditional(rgrpCommunityTraditional,
                        edtCommunityTraditional);
        SexualOrientation sexualOrientation = controller.getSexualOrientation(rgrpSexualOrientation,
                        spnSexualOrientation);
        Deficiency deficiency = controller.getDeficiency(rgrpDeficiency,chbHearing, chbVisual,
                        chbIntellectual, chbPhysical, chbAnother);

        socialDemographicData = controller.get(kinship, educationEmployment, healthAndGroup, kids09,
                communityTraditional, sexualOrientation, deficiency);
        sendCitizenData.send(socialDemographicData);
    }

    private void fillFields() {
        controller.fillField(spnKinship, controller.getIndex(socialDemographicData.getKinship(), R.array.kinship));
        controller.fillField(edtOccupation, socialDemographicData.getOccupation());
        controller.fillSchool(rgrpSchool, socialDemographicData.isSchool());
        controller.fillField(spnEducation, controller.getIndex(socialDemographicData.getEducation(), R.array.education));
        controller.fillField(spnEmployment, controller.getIndex(socialDemographicData.getEmployment(), R.array.employment));
        controller.fillField(spnKids09, controller.getIndex(socialDemographicData.getKids(), R.array.kids_0_9));
        controller.fillCaregiver(rgrpCaregiver, socialDemographicData.isCaregiver());
        controller.fillCommunityGroup(rgrpCommunityGroup, socialDemographicData.isCommunityGroup());
        controller.fillHealthPlan(rgrpHealthPlan, socialDemographicData.isHealthPlan());

        controller.fillCommunityTraditional(rgrpCommunityTraditional, edtCommunityTraditional,
                socialDemographicData.getCommunityName(), socialDemographicData.isCommunityTraditional());

        controller.fillSexualOrientation(rgrpSexualOrientation, socialDemographicData.isSexualOrientation(),
                spnSexualOrientation, controller.getIndex(socialDemographicData.getOrientation(), R.array.orientation_sexual));

        controller.fillDeficiency(rgrpDeficiency, socialDemographicData.isDeficiency(),
                socialDemographicData.getDeficiencys(), chbHearing, chbVisual, chbIntellectual, chbPhysical, chbAnother);
    }
}