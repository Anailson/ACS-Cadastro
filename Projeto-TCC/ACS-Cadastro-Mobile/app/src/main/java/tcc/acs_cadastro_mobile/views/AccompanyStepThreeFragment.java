package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyStepThreeController;
import tcc.acs_cadastro_mobile.interfaces.IAccompany;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.persistence.ExamsPersistence;
import tcc.acs_cadastro_mobile.subModels.EvaluatedExams;
import tcc.acs_cadastro_mobile.subModels.RequestExams;

public class AccompanyStepThreeFragment extends Fragment implements IRequiredFields {

    private static final String EXAMS_MODEL = "EXAMS_MODEL";

    private AccompanyStepThreeController controller;
    private IAccompany accompany;
    private ExamsModel exams;

    private Spinner spnPic;
    private CheckBox chbTotalCholesterolE, chbCreatinineE, chbEasEquE, chbElectrocardiogramE, chbHemoglobinE,
            chbSpirometryE, chbSputumE, chbGlycemiaE, chbHdlE, chbGlycemicE, chbBloodCountE, chbLdlE,
            chbEyesE, chbSyphilisE, chbDengueE, chbHivE, chbHumanAntiglobulinE, chbEarTestE, chbTestPregnancyE,
            chbEyeTestE, chbTestFootE, chbUltrasonographyE, chbUrocultureE;
    private CheckBox chbTotalCholesterolR, chbCreatinineR, chbEasEquR, chbElectrocardiogramR, chbHemoglobinR,
            chbSpirometryR, chbSputumR, chbGlycemiaR, chbHdlR, chbGlycemicR, chbBloodCountR, chbLdlR,
            chbEyesR, chbSyphilisR, chbDengueR, chbHivR, chbHumanAntiglobulinR, chbEarTestR, chbTestPregnancyR,
            chbEyeTestR, chbTestFootR, chbUltrasonographyR, chbUrocultureR;


    public static Fragment newInstance(ExamsModel exams) {
        Fragment fragment = new AccompanyStepThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXAMS_MODEL, exams);
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
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.content_acc_add_3, container, false);
        controller = new AccompanyStepThreeController(this);
        exams = (ExamsModel) getArguments().getSerializable(EXAMS_MODEL);

        spnPic = (Spinner) view.findViewById(R.id.spn_acc_pic);
        chbTotalCholesterolE = (CheckBox) view.findViewById(R.id.chb_acc_total_cholesterol_e);
        chbCreatinineE = (CheckBox) view.findViewById(R.id.chb_acc_creatinine_e);
        chbEasEquE = (CheckBox) view.findViewById(R.id.chb_acc_eas_equ_e);
        chbElectrocardiogramE = (CheckBox) view.findViewById(R.id.chb_acc_electrocardiogram_e);
        chbHemoglobinE = (CheckBox) view.findViewById(R.id.chb_acc_hemoglobin_e);
        chbSpirometryE = (CheckBox) view.findViewById(R.id.chb_acc_spirometry_e);
        chbSputumE = (CheckBox) view.findViewById(R.id.chb_acc_spucutum_e);
        chbGlycemiaE = (CheckBox) view.findViewById(R.id.chb_acc_glycemia_e);
        chbHdlE = (CheckBox) view.findViewById(R.id.chb_acc_hdl_e);
        chbGlycemicE = (CheckBox) view.findViewById(R.id.chb_acc_hemoglobin_glycemic_e);
        chbBloodCountE = (CheckBox) view.findViewById(R.id.chb_acc_blood_count_e);
        chbLdlE = (CheckBox) view.findViewById(R.id.chb_acc_ldl_e);
        chbEyesE = (CheckBox) view.findViewById(R.id.chb_acc_eyes_e);
        chbSyphilisE = (CheckBox) view.findViewById(R.id.chb_acc_syphilis_e);
        chbDengueE = (CheckBox) view.findViewById(R.id.chb_acc_dengue_sorology_e);
        chbHivE = (CheckBox) view.findViewById(R.id.chb_acc_hiv_e);
        chbHumanAntiglobulinE = (CheckBox) view.findViewById(R.id.chb_acc_human_antiglobulin_e);
        chbEarTestE = (CheckBox) view.findViewById(R.id.chb_acc_ear_test_e);
        chbTestPregnancyE = (CheckBox) view.findViewById(R.id.chb_acc_pregnancy_test_e);
        chbEyeTestE = (CheckBox) view.findViewById(R.id.chb_acc_eye_test_e);
        chbTestFootE = (CheckBox) view.findViewById(R.id.chb_acc_foot_test_e);
        chbUltrasonographyE = (CheckBox) view.findViewById(R.id.chb_acc_ultrasonography_e);
        chbUrocultureE = (CheckBox) view.findViewById(R.id.chb_acc_uroculture_e);

        chbTotalCholesterolR = (CheckBox) view.findViewById(R.id.chb_acc_total_cholesterol_r);
        chbCreatinineR = (CheckBox) view.findViewById(R.id.chb_acc_creatinine_r);
        chbEasEquR = (CheckBox) view.findViewById(R.id.chb_acc_eas_equ_r);
        chbElectrocardiogramR = (CheckBox) view.findViewById(R.id.chb_acc_electrocardiogram_r);
        chbHemoglobinR = (CheckBox) view.findViewById(R.id.chb_acc_hemoglobin_r);
        chbSpirometryR = (CheckBox) view.findViewById(R.id.chb_acc_spirometry_r);
        chbSputumR = (CheckBox) view.findViewById(R.id.chb_acc_spucutum_r);
        chbGlycemiaR = (CheckBox) view.findViewById(R.id.chb_acc_glycemia_r);
        chbHdlR = (CheckBox) view.findViewById(R.id.chb_acc_hdl_r);
        chbGlycemicR = (CheckBox) view.findViewById(R.id.chb_acc_hemoglobin_glycemic_r);
        chbBloodCountR = (CheckBox) view.findViewById(R.id.chb_acc_blood_count_r);
        chbLdlR = (CheckBox) view.findViewById(R.id.chb_acc_ldl_r);
        chbEyesR = (CheckBox) view.findViewById(R.id.chb_acc_eyes_r);
        chbSyphilisR = (CheckBox) view.findViewById(R.id.chb_acc_syphilis_r);
        chbDengueR = (CheckBox) view.findViewById(R.id.chb_acc_dengue_sorology_r);
        chbHivR = (CheckBox) view.findViewById(R.id.chb_acc_hiv_r);
        chbHumanAntiglobulinR = (CheckBox) view.findViewById(R.id.chb_acc_human_antiglobulin_r);
        chbEarTestR = (CheckBox) view.findViewById(R.id.chb_acc_ear_test_r);
        chbTestPregnancyR = (CheckBox) view.findViewById(R.id.chb_acc_pregnancy_test_r);
        chbEyeTestR = (CheckBox) view.findViewById(R.id.chb_acc_eye_test_r);
        chbTestFootR = (CheckBox) view.findViewById(R.id.chb_acc_foot_test_r);
        chbUltrasonographyR = (CheckBox) view.findViewById(R.id.chb_acc_ultrasonography_r);
        chbUrocultureR = (CheckBox) view.findViewById(R.id.chb_acc_uroculture_r);

        spnPic.setAdapter(controller.getAdapter(R.array.pic));

        if(exams != null){
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
        return controller.isRequiredFieldsFilled();
    }

    private void getFields() {

        String pic = controller.getFields(spnPic);
        boolean[] requests = controller.getFields(chbTotalCholesterolR, chbCreatinineR, chbEasEquR,
                chbElectrocardiogramR, chbHemoglobinR, chbSpirometryR, chbSputumR, chbGlycemiaR, chbHdlR,
                chbGlycemicR, chbBloodCountR, chbLdlR, chbEyesR, chbSyphilisR, chbDengueR, chbHivR,
                chbHumanAntiglobulinR, chbEarTestR, chbTestPregnancyR, chbEyeTestR, chbTestFootR,
                chbUltrasonographyR, chbUrocultureR);
        RequestExams request = ExamsPersistence.getRequestExams(requests);

        boolean[] evaluateds = controller.getFields(chbTotalCholesterolE, chbCreatinineE, chbEasEquE,
                chbElectrocardiogramE, chbHemoglobinE, chbSpirometryE, chbSputumE, chbGlycemiaE, chbHdlE,
                chbGlycemicE, chbBloodCountE, chbLdlE, chbEyesE, chbSyphilisE, chbDengueE, chbHivE,
                chbHumanAntiglobulinE, chbEarTestE, chbTestPregnancyE, chbEyeTestE, chbTestFootE,
                chbUltrasonographyE, chbUrocultureE);
        EvaluatedExams evaluated = ExamsPersistence.getEvaluatedExams(evaluateds);

        exams = ExamsPersistence.get(pic, request, evaluated, null);
        accompany.send(exams);
    }

    private void fillFields(){
        controller.fillPic(spnPic, exams.getPic());
        controller.fillField(exams.getEvaluates(), chbTotalCholesterolE, chbCreatinineE, chbEasEquE,
                chbElectrocardiogramE, chbHemoglobinE, chbSpirometryE, chbSputumE, chbGlycemiaE, chbHdlE,
                chbGlycemicE, chbBloodCountE, chbLdlE, chbEyesE, chbSyphilisE, chbDengueE, chbHivE,
                chbHumanAntiglobulinE, chbEarTestE, chbTestPregnancyE, chbEyeTestE, chbTestFootE,
                chbUltrasonographyE, chbUrocultureE);
        controller.fillField(exams.getRequests(), chbTotalCholesterolR, chbCreatinineR, chbEasEquR,
                chbElectrocardiogramR, chbHemoglobinR, chbSpirometryR, chbSputumR, chbGlycemiaR, chbHdlR,
                chbGlycemicR, chbBloodCountR, chbLdlR, chbEyesR, chbSyphilisR, chbDengueR, chbHivR,
                chbHumanAntiglobulinR, chbEarTestR, chbTestPregnancyR, chbEyeTestR, chbTestFootR,
                chbUltrasonographyR, chbUrocultureR);
    }
}
