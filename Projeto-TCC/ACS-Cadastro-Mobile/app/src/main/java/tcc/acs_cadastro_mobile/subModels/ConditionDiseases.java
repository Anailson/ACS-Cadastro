package tcc.acs_cadastro_mobile.subModels;

import com.google.api.client.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class ConditionDiseases extends RealmObject implements Serializable {

    private boolean asthma, malnutrition, diabetes, dpoc, hypertension, obesity, prenatal,
            childcare, puerperium, sexualHealth, smoking, alcohol, drugs, mentalHealth,
            rehabilitation;

    public ConditionDiseases() {
        this(new boolean[15]);
    }

    public ConditionDiseases(boolean[] conditions) {
        this(conditions[0], conditions[1], conditions[2], conditions[3], conditions[4], conditions[5],
                conditions[6], conditions[7], conditions[8], conditions[9], conditions[10], conditions[11],
                conditions[12], conditions[13], conditions[14]);
        if(conditions.length != 15){
            throw new IllegalArgumentException("Length of conditions array must be 15 instead " + conditions.length);
        }
    }

    public ConditionDiseases(boolean asthma, boolean malnutrition, boolean diabetes, boolean dpoc,
                             boolean hypertension, boolean obesity, boolean prenatal, boolean childcare,
                             boolean puerperium, boolean sexualHealth, boolean smoking, boolean alcohol,
                             boolean drugs, boolean mentalHealth, boolean rehabilitation) {
        this.asthma = asthma;
        this.malnutrition = malnutrition;
        this.diabetes = diabetes;
        this.dpoc = dpoc;
        this.hypertension = hypertension;
        this.obesity = obesity;
        this.prenatal = prenatal;
        this.childcare = childcare;
        this.puerperium = puerperium;
        this.sexualHealth = sexualHealth;
        this.smoking = smoking;
        this.alcohol = alcohol;
        this.drugs = drugs;
        this.mentalHealth = mentalHealth;
        this.rehabilitation = rehabilitation;
    }

    public boolean[] getValues(){
        return new boolean[]{asthma, malnutrition, diabetes, dpoc, hypertension, obesity, prenatal,
                childcare, puerperium, sexualHealth, smoking, alcohol, drugs, mentalHealth,
                rehabilitation};
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isMalnutrition() {
        return malnutrition;
    }

    public void setMalnutrition(boolean malnutrition) {
        this.malnutrition = malnutrition;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isDpoc() {
        return dpoc;
    }

    public void setDpoc(boolean dpoc) {
        this.dpoc = dpoc;
    }

    public boolean isHypertension() {
        return hypertension;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean isObesity() {
        return obesity;
    }

    public void setObesity(boolean obesity) {
        this.obesity = obesity;
    }

    public boolean isPrenatal() {
        return prenatal;
    }

    public void setPrenatal(boolean prenatal) {
        this.prenatal = prenatal;
    }

    public boolean isChildcare() {
        return childcare;
    }

    public void setChildcare(boolean childcare) {
        this.childcare = childcare;
    }

    public boolean isPuerperium() {
        return puerperium;
    }

    public void setPuerperium(boolean puerperium) {
        this.puerperium = puerperium;
    }

    public boolean isSexualHealth() {
        return sexualHealth;
    }

    public void setSexualHealth(boolean sexualHealth) {
        this.sexualHealth = sexualHealth;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isDrugs() {
        return drugs;
    }

    public void setDrugs(boolean drugs) {
        this.drugs = drugs;
    }

    public boolean isMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(boolean mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public boolean isRehabilitation() {
        return rehabilitation;
    }

    public void setRehabilitation(boolean rehabilitation) {
        this.rehabilitation = rehabilitation;
    }

    public JSONObject asJson() {

        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Accompany.ASTHMA.name(), asthma);
            json.put(Constants.Accompany.MALNUTRITION.name(), malnutrition);
            json.put(Constants.Accompany.DIABETES.name(), diabetes);
            json.put(Constants.Accompany.DPOC.name(), dpoc);
            json.put(Constants.Accompany.HYPERTENSION.name(), hypertension);
            json.put(Constants.Accompany.OBESITY.name(), obesity);
            json.put(Constants.Accompany.PRENATAL.name(), prenatal);
            json.put(Constants.Accompany.CHILD_CARE.name(), childcare);
            json.put(Constants.Accompany.PUERPERIUM.name(), puerperium);
            json.put(Constants.Accompany.SEXUAL_HEALTH.name(), sexualHealth);
            json.put(Constants.Accompany.SMOKING.name(), smoking);
            json.put(Constants.Accompany.ALCOHOL.name(), alcohol);
            json.put(Constants.Accompany.DRUGS.name(), drugs);
            json.put(Constants.Accompany.MENTAL_HEALTH.name(), mentalHealth);
            json.put(Constants.Accompany.REHABILITATION.name(), rehabilitation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
