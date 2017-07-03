package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.Spinner;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.persistence.ExamsPersistence;
import tcc.acs_cadastro_mobile.subModels.EvaluatedExams;
import tcc.acs_cadastro_mobile.subModels.RequestExams;

public class AccompanyStepThreeController extends StepsController{

    public AccompanyStepThreeController(Fragment fragment) {
        super(fragment.getContext());
    }

    public ExamsModel get(String pic, RequestExams request, EvaluatedExams evaluated) {
        return ExamsPersistence.get(pic, request, evaluated, new RealmList<RealmInt>());
    }

    public RequestExams getRequestExams(CheckBox chbTotalCholesterol, CheckBox chbCreatinine,
                        CheckBox chbEasEqu, CheckBox chbElectrocardiogram, CheckBox chbHemoglobin,
                        CheckBox chbSpirometry, CheckBox chbSputum, CheckBox chbGlycemia, CheckBox chbHdl,
                        CheckBox chbGlycemic, CheckBox chbBloodCount, CheckBox chbLdl, CheckBox chbEyes,
                        CheckBox chbSyphilis, CheckBox chbDengue, CheckBox chbHiv, CheckBox chbHumanAntiglobulin,
                        CheckBox chbEarTest, CheckBox chbTestPregnancy, CheckBox chbEyeTest,
                        CheckBox chbTestFoot, CheckBox chbUltrasonography, CheckBox chbUroculture) {
        boolean [] values = getFields(chbTotalCholesterol, chbCreatinine, chbEasEqu, chbElectrocardiogram,
                chbHemoglobin, chbSpirometry, chbSputum, chbGlycemia, chbHdl, chbGlycemic, chbBloodCount,
                chbLdl, chbEyes, chbSyphilis, chbDengue, chbHiv, chbHumanAntiglobulin, chbEarTest,
                chbTestPregnancy, chbEyeTest, chbTestFoot, chbUltrasonography, chbUroculture);
        return ExamsPersistence.getRequestExams(values);
    }

    public EvaluatedExams getEvaluatedExams(CheckBox chbTotalCholesterol, CheckBox chbCreatinine,
                                            CheckBox chbEasEqu, CheckBox chbElectrocardiogram, CheckBox chbHemoglobin,
                                            CheckBox chbSpirometry, CheckBox chbSputum, CheckBox chbGlycemia, CheckBox chbHdl,
                                            CheckBox chbGlycemic, CheckBox chbBloodCount, CheckBox chbLdl, CheckBox chbEyes,
                                            CheckBox chbSyphilis, CheckBox chbDengue, CheckBox chbHiv, CheckBox chbHumanAntiglobulin,
                                            CheckBox chbEarTest, CheckBox chbTestPregnancy, CheckBox chbEyeTest,
                                            CheckBox chbTestFoot, CheckBox chbUltrasonography, CheckBox chbUroculture) {
        boolean [] values = getFields(chbTotalCholesterol, chbCreatinine, chbEasEqu, chbElectrocardiogram,
                chbHemoglobin, chbSpirometry, chbSputum, chbGlycemia, chbHdl, chbGlycemic, chbBloodCount,
                chbLdl, chbEyes, chbSyphilis, chbDengue, chbHiv, chbHumanAntiglobulin, chbEarTest,
                chbTestPregnancy, chbEyeTest, chbTestFoot, chbUltrasonography, chbUroculture);
        return ExamsPersistence.getEvaluatedExams(values);
    }

    public boolean isRequiredFieldsFilled() {
        return true;
    }

    public void fillPic(Spinner spnPic, String pic) {
        int index = getIndex(pic, R.array.pic);
        fillField(spnPic, index);
    }
}
