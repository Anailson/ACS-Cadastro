package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepTwoController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicDataModel;

public class CitizenStepTwoFragment extends Fragment {

    private static final String SOCIAL_DEMOGRAPHIC_DATA = "SOCIAL_DEMOGRAPHIC_DATA";

    private ICitizenData sendCitizenData;
    private SocialDemographicDataModel socialDemographicData;
    private CitizenStepTwoController controller;

    private EditText edtOccupation, edtCommunityTraditional;
    private Spinner spnKinship, spnEducation, spnEmployment, spnKids09, spnOrientationSex;
    //private RadioGroup
    private CheckBox chbSchool, chbCaregiver, chbCommunityGroup, chbCommunityTraditional, chbHealthPlan,
            chbOrientationSexual, chbDeficiency, chbHearing, chbVisual, chbIntellectual, chbPhysical, chbAnother;

    public static Fragment newInstance(SocialDemographicDataModel socialDemographicData) {
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
        socialDemographicData = (SocialDemographicDataModel) getArguments().getSerializable(SOCIAL_DEMOGRAPHIC_DATA);

        edtOccupation = (EditText) view.findViewById(R.id.edt_ctz_occupation);
        edtCommunityTraditional = (EditText) view.findViewById(R.id.edt_ctz_community_traditional);
        spnKinship = (Spinner) view.findViewById(R.id.spn_ctz_kinship);
        spnEducation = (Spinner) view.findViewById(R.id.spn_ctz_education);
        spnEmployment = (Spinner) view.findViewById(R.id.spn_ctz_employment);
        spnKids09 = (Spinner) view.findViewById(R.id.spn_ctz_kids_0_9);
        spnOrientationSex = (Spinner) view.findViewById(R.id.spn_ctz_orientation_sexual);
        chbSchool = (CheckBox) view.findViewById(R.id.chb_ctz_school);
        chbCaregiver = (CheckBox) view.findViewById(R.id.chb_ctz_caregiver);
        chbCommunityGroup = (CheckBox) view.findViewById(R.id.chb_ctz_community_group);
        chbHealthPlan = (CheckBox) view.findViewById(R.id.chb_ctz_health_plan);
        chbCommunityTraditional = (CheckBox) view.findViewById(R.id.chb_ctz_community_traditional);
        chbOrientationSexual = (CheckBox) view.findViewById(R.id.chb_ctz_orientation_sexual);
        chbDeficiency = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency);
        chbHearing = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_hearing);
        chbVisual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_visual);
        chbIntellectual = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_intellectual);
        chbPhysical = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_physical);
        chbAnother = (CheckBox) view.findViewById(R.id.chb_ctz_deficiency_another);

        spnKinship.setAdapter(controller.getSpinnerAdapter(R.array.kinship));
        spnEducation.setAdapter(controller.getSpinnerAdapter(R.array.education));
        spnEmployment.setAdapter(controller.getSpinnerAdapter(R.array.employment));
        spnKids09.setAdapter(controller.getSpinnerAdapter(R.array.kids_0_9));
        spnOrientationSex.setAdapter(controller.getSpinnerAdapter(R.array.orientation_sexual));

        chbCommunityTraditional.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        chbOrientationSexual.setOnCheckedChangeListener(controller.getCheckedChangeListener());
        chbDeficiency.setOnCheckedChangeListener(controller.getCheckedChangeListener());

        if (socialDemographicData != null) {
            fillFields();
        }

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
/*
        String[] kinship = controller.getIndexAndValue(spnKinship);
        String occupation = edtOccupation.getText().toString();
        boolean school = controller.isYesGroup(rgrpSchool, R.id.rgrp_ctz_school_y);
        String[] education = controller.getIndexAndValue(spnEducation);
        String[] employment = controller.getIndexAndValue(spnEmployment);
        String[] kids09 = controller.getIndexAndValue(spnKids09);
        boolean caregiver = controller.isYesGroup(rgrpCaregiver, R.id.rgrp_ctz_caregiver_y);
        boolean communityGroup = controller.isYesGroup(rgrpCommunityGroup, R.id.rgrp_ctz_community_group_y);
        boolean healthPlan = controller.isYesGroup(rgrpHealthPlan, R.id.rgrp_ctz_health_plan_y);
        String[] communityTraditional = controller.getCommunityTraditional(rgrpCommunityTraditional, edtCommunityTraditional);
        String[] orientationSexual = controller.getOrientationSexual(rgrpOrientationSexual, spnOrientationSex);
        boolean[] deficiency = controller.getDeficiency(rgrpDeficiency, chbVisual, chbHearing, chbIntellectual, chbPhysical, chbAnother);

        Log.e("TwoFragment.onDetach", "kinship: " + kinship[1] + " occupation:" + occupation
                + " school: " + school + "education: " + education[1] + " employment: " + employment[1]
                + " kids09: " + kids09[1] + " caregiver: " + caregiver + " communityGroup: " + communityGroup
                + " healthPlan: " + healthPlan + " communityTraditional: " + communityTraditional[1]
                + " orientationSexual: " + orientationSexual[1] + " deficiency: " + deficiency[0]
                + " " + deficiency[1] + " " + deficiency[2] + " " + deficiency[3] + " " + deficiency[4]);
        socialDemographicData = new SocialDemographicDataModel(kinship, occupation, school, education,
                    employment, kids09, caregiver, communityGroup, healthPlan, communityTraditional,
                    orientationSexual, deficiency);
        sendCitizenData.send(socialDemographicData);
*/
    }

    private void fillFields() {

    }
}
