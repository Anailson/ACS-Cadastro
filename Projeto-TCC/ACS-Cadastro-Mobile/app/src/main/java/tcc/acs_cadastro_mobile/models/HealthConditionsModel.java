package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.Diseases;
import tcc.acs_cadastro_mobile.subModels.HeartDisease;
import tcc.acs_cadastro_mobile.subModels.Interment;
import tcc.acs_cadastro_mobile.subModels.KidneyDisease;
import tcc.acs_cadastro_mobile.subModels.Plant;
import tcc.acs_cadastro_mobile.subModels.Pregnant;
import tcc.acs_cadastro_mobile.subModels.RespiratoryDisease;

import static tcc.acs_cadastro_mobile.Constants.Citizen.DISEASES;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HEART_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.INTERMENT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.KIDNEY_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PLANT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PREGNANT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.RESPIRATORY_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.WEIGHT;


public class HealthConditionsModel extends RealmObject implements Serializable {

    private Pregnant pregnant;
    private String weight;
    private Diseases diseases;
    private HeartDisease heartDisease;
    private KidneyDisease kidneyDisease;
    private RespiratoryDisease respiratoryDisease;
    private Interment interment;
    private Plant plant;

    public HealthConditionsModel() {
        this(new Pregnant(), "", new Diseases(), new HeartDisease(), new KidneyDisease(), new RespiratoryDisease(),
                new Interment(), new Plant());
    }

    public HealthConditionsModel(Pregnant pregnant, String weight, Diseases diseases, HeartDisease heartDisease,
                     KidneyDisease kidneyDisease, RespiratoryDisease respiratoryDisease, Interment interment, Plant plant) {
        this.pregnant = pregnant;
        this.weight = weight;
        this.diseases = diseases;
        this.heartDisease = heartDisease;
        this.kidneyDisease = kidneyDisease;
        this.respiratoryDisease = respiratoryDisease;
        this.interment = interment;
        this.plant = plant;
    }

    public Pregnant getPregnant() {
        return pregnant;
    }

    public void setPregnant(Pregnant pregnant) {
        this.pregnant = pregnant;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Diseases getDiseases() {
        return diseases;
    }

    public void setDiseases(Diseases diseases) {
        this.diseases = diseases;
    }

    public HeartDisease getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(HeartDisease heartDisease) {
        this.heartDisease = heartDisease;
    }

    public KidneyDisease getKidneyDisease() {
        return kidneyDisease;
    }

    public void setKidneyDisease(KidneyDisease kidneyDisease) {
        this.kidneyDisease = kidneyDisease;
    }

    public RespiratoryDisease getRespiratoryDisease() {
        return respiratoryDisease;
    }

    public void setRespiratoryDisease(RespiratoryDisease respiratoryDisease) {
        this.respiratoryDisease = respiratoryDisease;
    }

    public Interment getInterment() {
        return interment;
    }

    public void setInterment(Interment interment) {
        this.interment = interment;
    }

    public Plant getPlant(){
        return this.plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public boolean isPregnant() {
        return getPregnant().isPregnant();
    }

    public String getMaternity() {
        return getPregnant().getMaternity();
    }

    public boolean isSmoker() {
        return getDiseases().isSmoker();
    }

    public boolean isAlcohol() {
        return getDiseases().isAlcohol();
    }

    public boolean isDrugs() {
        return getDiseases().isDrugs();
    }

    public boolean isHypertension() {
        return getDiseases().isHypertension();
    }

    public boolean isDiabetes() {
        return getDiseases().isDiabetes();
    }

    public boolean isAvc() {
        return getDiseases().isAvc();
    }

    public boolean isHeartAttack() {
        return getDiseases().isHeartAttack();
    }

    public boolean isLeprosy() {
        return getDiseases().isLeprosy();
    }

    public boolean isTuberculosis() {
        return getDiseases().isTuberculosis();
    }

    public boolean isCancer() {
        return getDiseases().isCancer();
    }

    public boolean isInBend() {
        return getDiseases().isInBend();
    }

    public boolean isDomiciled() {
        return getDiseases().isDomiciled();
    }

    public boolean isMentalHealth() {
        return getDiseases().isMentalHealth();
    }

    public boolean isHeartDisease() {
        return getHeartDisease().isHeartDisease();
    }

    public boolean[] getHeartDiseases() {
        return getHeartDisease().getHeartDiseases();
    }

    public boolean isKidneyDisease() {
        return getKidneyDisease().isKidneyDisease();
    }

    public boolean[] getKidneyDiseases() {
        return getKidneyDisease().getKidneyDiseases();
    }

    public boolean isRespiratoryDisease() {
        return getRespiratoryDisease().isRespiratoryDisease();
    }

    public boolean[] getRespiratoryDiseases() {
        return getRespiratoryDisease().getRespiratoryDiseases();
    }

    public boolean isInterment() {
        return getInterment().isInterment();
    }

    public String getIntermentValue() {
        return getInterment().getValue();
    }

    public boolean isPlant() {
        return getPlant().isPlants();
    }

    public String getPlantValue() {
        return getPlant().getValue();
    }

    public boolean isOtherPractices() {
        return getDiseases().isOtherPractices();
    }

    public JSONObject asJson() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(PREGNANT.name(), pregnant.getAsJson());
        json.put(WEIGHT.name(), weight);
        json.put(DISEASES.name(), diseases.getAsJson());
        json.put(HEART_DISEASE.name(), heartDisease.getAsJson());
        json.put(KIDNEY_DISEASE.name(), kidneyDisease.getAsJson());
        json.put(RESPIRATORY_DISEASE.name(), respiratoryDisease.getAsJson());
        json.put(INTERMENT.name(), interment.getAsJson());
        json.put(PLANT.name(), plant.getAsJson());
        return json;
    }
}

