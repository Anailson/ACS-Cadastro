package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;


public class HealthConditionsModel implements Serializable {

    private String[] pregnant, weight, interment, plants;
    private boolean smoker, alcohol, drugs, hypertension, diabetes, avc, heartAttack, leprosy,
            tuberculosis, cancer, inBend, domiciled, mentalHealth;
    private boolean [] heartDisease, kidneyDisease, respiratoryDisease;

    public HealthConditionsModel(){
        pregnant = weight = interment = plants = new String[]{"" + CitizenModel.INT_DEFAULT_VALUE,
                        CitizenModel.STRING_DEFAULT_VALUE};
        smoker = alcohol = drugs = hypertension = diabetes = avc = heartAttack = leprosy
                = tuberculosis = cancer = inBend = domiciled = mentalHealth = false;
        heartDisease = kidneyDisease = new boolean[4];
        respiratoryDisease = new boolean[5];
    }

    public HealthConditionsModel(String[] pregnant, String[] weight, boolean smoker, boolean alcohol,
                                 boolean drugs, boolean hypertension, boolean diabetes, boolean avc,
                                 boolean heartAttack, boolean leprosy, boolean tuberculosis, boolean cancer,
                                 boolean inBend, boolean domiciled, boolean mentalHealth,
                                 boolean[] heartDisease, boolean[] kidneyDisease, boolean[] respiratoryDisease,
                                 String [] interment, String [] plants){
        this.pregnant = pregnant;
        this.weight = weight;
        this.smoker = smoker;
        this.alcohol = alcohol;
        this.drugs = drugs;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.avc = avc;
        this.heartAttack = heartAttack;
        this.leprosy = leprosy;
        this.tuberculosis = tuberculosis;
        this.cancer = cancer;
        this.inBend = inBend;
        this.domiciled = domiciled;
        this.mentalHealth = mentalHealth;
        this.heartDisease = heartDisease;
        this.kidneyDisease = kidneyDisease;
        this.respiratoryDisease = respiratoryDisease;
        this.interment = interment;
        this.plants = plants;
    }

    public boolean isPregnant(){
        return !pregnant[CitizenModel.INDEX].equals(""+CitizenModel.INT_DEFAULT_VALUE);
    }

    public String[] getPregnant() {
        return pregnant;
    }

    public String[] getWeight() {
        return weight;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public boolean isDrugs() {
        return drugs;
    }

    public boolean isHypertension() {
        return hypertension;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public boolean isAvc() {
        return avc;
    }

    public boolean isHeartAttack() {
        return heartAttack;
    }

    public boolean isLeprosy() {
        return leprosy;
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public boolean isCancer() {
        return cancer;
    }

    public boolean isInBend() {
        return inBend;
    }

    public boolean isDomiciled() {
        return domiciled;
    }

    public boolean isMentalHealth() {
        return mentalHealth;
    }

    public boolean isHeartDisease(){
        return heartDisease[0];
    }

    public boolean[] getHeartDisease() {
        return heartDisease;
    }

    public boolean isKidneyDisease() {
        return kidneyDisease[0];
    }

    public boolean[] getKidneyDisease() {
        return kidneyDisease;
    }

    public boolean isRespiratoryDisease() {
        return respiratoryDisease[0];
    }

    public boolean[] getRespiratoryDisease() {
        return respiratoryDisease;
    }

    public boolean isInterment(){
        return !interment[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getInterment() {
        return interment;
    }

    public boolean isPlants() {
        return !plants[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getPlants() {
        return plants;
    }
}
