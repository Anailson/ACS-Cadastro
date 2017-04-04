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
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;

public class CitizenStepTwoFragment extends Fragment {

    private static final String SOCIAL_DEMOGRAPHIC_DATA = "SOCIAL_DEMOGRAPHIC_DATA";

    private ICitizenData sendCitizenData;
    private SocialDemographicModel socialDemographicData;
    private CitizenStepTwoController controller;

    private EditText edtOccupation, edtCommunityTraditional;
    private Spinner spnKinship, spnEducation, spnEmployment, spnKids09, spnSexualOrientation;
    private RadioGroup rgrpSchool, rgrpCaregiver, rgrpCommunityGroup, rgrpHealthPlan,
            rgrpCommunityTraditional, rgrpSexualOrientation, rgrpDeficiency;
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

        //TODO what if screen to rotation to horizontal/vertical, how to get values???

        edtOccupation = (EditText) view.findViewById(R.id.edt_ctz_occupation);
        edtCommunityTraditional = (EditText) view.findViewById(R.id.edt_ctz_community_traditional);
        spnKinship = (Spinner) view.findViewById(R.id.spn_ctz_kinship);
        spnEducation = (Spinner) view.findViewById(R.id.spn_ctz_education);
        spnEmployment = (Spinner) view.findViewById(R.id.spn_ctz_employment);
        spnKids09 = (Spinner) view.findViewById(R.id.spn_ctz_kids_0_9);
        spnSexualOrientation = (Spinner) view.findViewById(R.id.spn_ctz_sexual_orientation);
        rgrpSchool = (RadioGroup) view.findViewById(R.id.rgrp_ctz_school);
        rgrpCaregiver = (RadioGroup) view.findViewById(R.id.rgrp_ctz_caregiver);
        rgrpCommunityGroup = (RadioGroup) view.findViewById(R.id.rgrp_ctz_community_group);
        rgrpHealthPlan = (RadioGroup) view.findViewById(R.id.rgrp_ctz_health_plan);
        rgrpCommunityTraditional = (RadioGroup) view.findViewById(R.id.rgrp_ctz_community_traditional);
        rgrpSexualOrientation = (RadioGroup) view.findViewById(R.id.rgrp_ctz_sexual_orientation);
        rgrpDeficiency = (RadioGroup) view.findViewById(R.id.rgrp_ctz_deficiency);
        chbHearing = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_hearing);
        chbVisual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_visual);
        chbIntellectual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_intellectual);
        chbPhysical = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_physical);
        chbAnother = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_another);

        spnKinship.setAdapter(controller.getSpinnerAdapter(R.array.kinship));
        spnEducation.setAdapter(controller.getSpinnerAdapter(R.array.education));
        spnEmployment.setAdapter(controller.getSpinnerAdapter(R.array.employment));
        spnKids09.setAdapter(controller.getSpinnerAdapter(R.array.kids_0_9));
        spnSexualOrientation.setAdapter(controller.getSpinnerAdapter(R.array.orientation_sexual));

        rgrpCommunityTraditional.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        rgrpSexualOrientation.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        rgrpDeficiency.setOnCheckedChangeListener(controller.getCheckedChangeListener());

        if (socialDemographicData != null) {
            fillFields();
        }

        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    private void getFields(){
        String[] kinship = controller.getIndexAndValue(spnKinship);
        String occupation = edtOccupation.getText().toString();
        boolean school = controller.isSchool(rgrpSchool);
        String[] education = controller.getIndexAndValue(spnEducation);
        String[] employment = controller.getIndexAndValue(spnEmployment);
        String[] kids09 = controller.getIndexAndValue(spnKids09);
        boolean caregiver = controller.isCaregiver(rgrpCaregiver);
        boolean communityGroup = controller.isCommunityGroup(rgrpCommunityGroup);
        boolean healthPlan = controller.isHealthPlan(rgrpHealthPlan);
        String[] communityTraditional = controller.getCommunityTraditional(rgrpCommunityTraditional, edtCommunityTraditional);
        String[] sexualOrientation = controller.getSexualOrientation(rgrpSexualOrientation, spnSexualOrientation);
        boolean[] deficiency = controller.getDeficiency(rgrpDeficiency, chbHearing, chbVisual,
                chbIntellectual, chbPhysical, chbAnother);

        socialDemographicData = new SocialDemographicModel(kinship, occupation, school, education,
                employment, kids09, caregiver, communityGroup, healthPlan, communityTraditional,
                sexualOrientation, deficiency);
        sendCitizenData.send(socialDemographicData);
    }

    private void fillFields() {
        controller.fillField(spnKinship, socialDemographicData.getKinship()[CitizenModel.INDEX]);
        controller.fillField(edtOccupation, socialDemographicData.getOccupation());
        controller.fillSchool(rgrpSchool, socialDemographicData.isSchool());
        controller.fillField(spnEducation, socialDemographicData.getEducation()[CitizenModel.INDEX]);
        controller.fillField(spnEmployment, socialDemographicData.getEmployment()[CitizenModel.INDEX]);
        controller.fillField(spnKids09, socialDemographicData.getKids()[CitizenModel.INDEX]);
        controller.fillCaregiver(rgrpCaregiver, socialDemographicData.isCaregiver());
        controller.fillCommunityGroup(rgrpCommunityGroup, socialDemographicData.isCommunityGroup());
        controller.fillHealthPlan(rgrpHealthPlan, socialDemographicData.isHealthPlan());
        controller.fillCommunityTraditional(rgrpCommunityTraditional, edtCommunityTraditional,
                socialDemographicData.getCommunityTraditional(), socialDemographicData.isCommunityTraditional());
        controller.fillSexualOrientation(rgrpSexualOrientation, spnSexualOrientation,
                socialDemographicData.getSexualOrientation()[CitizenModel.INDEX], socialDemographicData.isSexualOrientation());
        controller.fillDeficiency(rgrpDeficiency, socialDemographicData.isDeficiency(),
                socialDemographicData.getDeficiency(), chbHearing, chbVisual, chbIntellectual, chbPhysical, chbAnother);
    }
}

