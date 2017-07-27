package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class NASF extends RealmObject implements Serializable {

    private boolean evaluation, procedures, prescription;

    public NASF() {
        this(new boolean[3]);
    }

    public NASF(boolean[] nasf) {
        this(nasf[0], nasf[1], nasf[2]);
        if(nasf.length != 3){
            throw new IllegalArgumentException("Length of conditions array must be 3 instead " + nasf.length);
        }
    }

    public NASF(boolean evaluation, boolean procedures, boolean prescription) {
        this.evaluation = evaluation;
        this.procedures = procedures;
        this.prescription = prescription;
    }

    public boolean[] getValues() {
        return new boolean[]{evaluation, procedures, prescription};
    }

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }

    public boolean isProcedures() {
        return procedures;
    }

    public void setProcedures(boolean procedures) {
        this.procedures = procedures;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public JSONObject asJson() {

        JSONObject json = new JSONObject();
        try{
            json.put(Constants.Accompany.EVALUATION.name(), evaluation);
            json.put(Constants.Accompany.PROCEDURES.name(), procedures);
            json.put(Constants.Accompany.PRESCRIPTION.name(), prescription);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
