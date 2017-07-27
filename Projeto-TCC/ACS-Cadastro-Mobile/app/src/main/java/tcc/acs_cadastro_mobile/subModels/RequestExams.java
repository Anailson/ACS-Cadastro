package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;

import static tcc.acs_cadastro_mobile.Constants.Accompany.BLOOD_COUNT;
import static tcc.acs_cadastro_mobile.Constants.Accompany.CREATINE;
import static tcc.acs_cadastro_mobile.Constants.Accompany.DENGUE;
import static tcc.acs_cadastro_mobile.Constants.Accompany.EAR_TEST;
import static tcc.acs_cadastro_mobile.Constants.Accompany.EAS_EQU;
import static tcc.acs_cadastro_mobile.Constants.Accompany.ELECTROCARDIOGRAM;
import static tcc.acs_cadastro_mobile.Constants.Accompany.EYES;
import static tcc.acs_cadastro_mobile.Constants.Accompany.EYE_TEST;
import static tcc.acs_cadastro_mobile.Constants.Accompany.FOOT_TEST;
import static tcc.acs_cadastro_mobile.Constants.Accompany.GLYCEMIA;
import static tcc.acs_cadastro_mobile.Constants.Accompany.GLYCEMIC_HEMOGLIBIN;
import static tcc.acs_cadastro_mobile.Constants.Accompany.HDL;
import static tcc.acs_cadastro_mobile.Constants.Accompany.HEMOGLOBIN;
import static tcc.acs_cadastro_mobile.Constants.Accompany.HIV;
import static tcc.acs_cadastro_mobile.Constants.Accompany.HUMAN_ANTIGLOBULIN;
import static tcc.acs_cadastro_mobile.Constants.Accompany.LDL;
import static tcc.acs_cadastro_mobile.Constants.Accompany.PREGNANCY_TEST;
import static tcc.acs_cadastro_mobile.Constants.Accompany.SPIROMETRY;
import static tcc.acs_cadastro_mobile.Constants.Accompany.SPUTUM;
import static tcc.acs_cadastro_mobile.Constants.Accompany.SYPHILIS;
import static tcc.acs_cadastro_mobile.Constants.Accompany.TOTAL_CHOLESTEROL;
import static tcc.acs_cadastro_mobile.Constants.Accompany.ULTRASONOGRAPHY;
import static tcc.acs_cadastro_mobile.Constants.Accompany.UROCULTURE;

public class RequestExams extends RealmObject implements Serializable {

    private boolean totalCholesterol, creatinine, easEqu, electrocardiogram, hemoglobin, spirometry,
            sputum, glycemia, hdl, glycemic, bloodCount, ldl, eyes, syphilis, dengue, hiv,
            humanAntiglobulin, earTest, testPregnancy, eyeTest, testFoot, ultrasonography, uroculture;

    public RequestExams() {
        this(new boolean[23]);
    }

    public RequestExams(boolean[] requests) {
        this(requests[0], requests[1], requests[2], requests[3], requests[4], requests[5],
                requests[6], requests[7], requests[8], requests[9], requests[10], requests[11],
                requests[12], requests[13], requests[14], requests[15], requests[16], requests[17],
                requests[18], requests[19], requests[20], requests[21], requests[22]);
        if(requests.length != 23){
            throw new IllegalArgumentException("Length of conditions array must be 23 instead " + requests.length);
        }
    }

    public RequestExams(boolean totalCholesterol, boolean creatinine, boolean easEqu, boolean electrocardiogram,
                        boolean hemoglobin, boolean spirometry, boolean sputum, boolean glycemia, boolean hdl,
                        boolean glycemic, boolean bloodCount, boolean ldl, boolean eyes, boolean syphilis,
                        boolean dengue, boolean hiv, boolean humanAntiglobulin, boolean earTest, boolean testPregnancy,
                        boolean eyeTest, boolean testFoot, boolean ultrasonography, boolean uroculture) {
        this.totalCholesterol = totalCholesterol;
        this.creatinine = creatinine;
        this.easEqu = easEqu;
        this.electrocardiogram = electrocardiogram;
        this.hemoglobin = hemoglobin;
        this.spirometry = spirometry;
        this.sputum = sputum;
        this.glycemia = glycemia;
        this.hdl = hdl;
        this.glycemic = glycemic;
        this.bloodCount = bloodCount;
        this.ldl = ldl;
        this.eyes = eyes;
        this.syphilis = syphilis;
        this.dengue = dengue;
        this.hiv = hiv;
        this.humanAntiglobulin = humanAntiglobulin;
        this.earTest = earTest;
        this.testPregnancy = testPregnancy;
        this.eyeTest = eyeTest;
        this.testFoot = testFoot;
        this.ultrasonography = ultrasonography;
        this.uroculture = uroculture;
    }

    public boolean[] values() {
        return new boolean[]{totalCholesterol, creatinine, easEqu, electrocardiogram, hemoglobin,
                spirometry,sputum, glycemia, hdl, glycemic, bloodCount, ldl, eyes, syphilis, dengue, hiv,
                humanAntiglobulin, earTest, testPregnancy, eyeTest, testFoot, ultrasonography, uroculture};
    }

    public boolean isTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(boolean totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public boolean isCreatinine() {
        return creatinine;
    }

    public void setCreatinine(boolean creatinine) {
        this.creatinine = creatinine;
    }

    public boolean isEasEqu() {
        return easEqu;
    }

    public void setEasEqu(boolean easEqu) {
        this.easEqu = easEqu;
    }

    public boolean isElectrocardiogram() {
        return electrocardiogram;
    }

    public void setElectrocardiogram(boolean electrocardiogram) {
        this.electrocardiogram = electrocardiogram;
    }

    public boolean isHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(boolean hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public boolean isSpirometry() {
        return spirometry;
    }

    public void setSpirometry(boolean spirometry) {
        this.spirometry = spirometry;
    }

    public boolean isSputum() {
        return sputum;
    }

    public void setSputum(boolean sputum) {
        this.sputum = sputum;
    }

    public boolean isGlycemia() {
        return glycemia;
    }

    public void setGlycemia(boolean glycemia) {
        this.glycemia = glycemia;
    }

    public boolean isHdl() {
        return hdl;
    }

    public void setHdl(boolean hdl) {
        this.hdl = hdl;
    }

    public boolean isGlycemic() {
        return glycemic;
    }

    public void setGlycemic(boolean glycemic) {
        this.glycemic = glycemic;
    }

    public boolean isBloodCount() {
        return bloodCount;
    }

    public void setBloodCount(boolean bloodCount) {
        this.bloodCount = bloodCount;
    }

    public boolean isLdl() {
        return ldl;
    }

    public void setLdl(boolean ldl) {
        this.ldl = ldl;
    }

    public boolean isEyes() {
        return eyes;
    }

    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }

    public boolean isSyphilis() {
        return syphilis;
    }

    public void setSyphilis(boolean syphilis) {
        this.syphilis = syphilis;
    }

    public boolean isDengue() {
        return dengue;
    }

    public void setDengue(boolean dengue) {
        this.dengue = dengue;
    }

    public boolean isHiv() {
        return hiv;
    }

    public void setHiv(boolean hiv) {
        this.hiv = hiv;
    }

    public boolean isHumanAntiglobulin() {
        return humanAntiglobulin;
    }

    public void setHumanAntiglobulin(boolean humanAntiglobulin) {
        this.humanAntiglobulin = humanAntiglobulin;
    }

    public boolean isEarTest() {
        return earTest;
    }

    public void setEarTest(boolean earTest) {
        this.earTest = earTest;
    }

    public boolean isTestPregnancy() {
        return testPregnancy;
    }

    public void setTestPregnancy(boolean testPregnancy) {
        this.testPregnancy = testPregnancy;
    }

    public boolean isEyeTest() {
        return eyeTest;
    }

    public void setEyeTest(boolean eyeTest) {
        this.eyeTest = eyeTest;
    }

    public boolean isTestFoot() {
        return testFoot;
    }

    public void setTestFoot(boolean testFoot) {
        this.testFoot = testFoot;
    }

    public boolean isUltrasonography() {
        return ultrasonography;
    }

    public void setUltrasonography(boolean ultrasonography) {
        this.ultrasonography = ultrasonography;
    }

    public boolean isUroculture() {
        return uroculture;
    }

    public void setUroculture(boolean uroculture) {
        this.uroculture = uroculture;
    }

    public JSONObject asJson() {

        JSONObject json = new JSONObject();
        try {
            json.put(TOTAL_CHOLESTEROL.name(), totalCholesterol);
            json.put(CREATINE.name(), creatinine);
            json.put(EAS_EQU.name(), easEqu);
            json.put(ELECTROCARDIOGRAM.name(), electrocardiogram);
            json.put(HEMOGLOBIN.name(), hemoglobin);
            json.put(SPIROMETRY.name(), spirometry);
            json.put(SPUTUM.name(), sputum);
            json.put(GLYCEMIA.name(), glycemia);
            json.put(HDL.name(), hdl);
            json.put(GLYCEMIC_HEMOGLIBIN.name(), glycemic);
            json.put(BLOOD_COUNT.name(), bloodCount);
            json.put(LDL.name(), ldl);
            json.put(EYES.name(), eyes);
            json.put(SYPHILIS.name(), syphilis);
            json.put(DENGUE.name(), dengue);
            json.put(HIV.name(), hiv);
            json.put(HUMAN_ANTIGLOBULIN.name(), humanAntiglobulin);
            json.put(EAR_TEST.name(), earTest);
            json.put(PREGNANCY_TEST.name(), testPregnancy);
            json.put(EYE_TEST.name(), eyeTest);
            json.put(FOOT_TEST.name(), testFoot);
            json.put(ULTRASONOGRAPHY.name(), ultrasonography);
            json.put(UROCULTURE.name(), uroculture);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
