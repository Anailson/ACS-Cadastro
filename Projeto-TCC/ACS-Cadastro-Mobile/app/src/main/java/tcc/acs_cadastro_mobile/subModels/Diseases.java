package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class Diseases extends RealmObject implements Serializable {

    private boolean isSmoker, isAlcohol, isDrugs, isHypertension, isDiabetes, isAvc, isHeartAttack,
            isLeprosy, isTuberculosis, isCancer, isInBend, isDomiciled, otherPractices, isMentalHealth;

    public Diseases() {
        this(new boolean[14]);
    }

    public Diseases(boolean [] diseases) {
        this(diseases[0], diseases[1], diseases[2], diseases[3], diseases[4], diseases[5], diseases[6],
                diseases[7], diseases[8], diseases[9], diseases[10], diseases[11], diseases[12], diseases[13]);

    }

    public Diseases(boolean isSmoker, boolean isAlcohol, boolean isDrugs, boolean isHypertension,
                    boolean isDiabetes, boolean isAvc, boolean isHeartAttack, boolean isLeprosy,
                    boolean isTuberculosis, boolean isCancer, boolean isInBend, boolean isDomiciled,
                    boolean otherPractices, boolean isMentalHealth) {
        this.isSmoker = isSmoker;
        this.isAlcohol = isAlcohol;
        this.isDrugs = isDrugs;
        this.isHypertension = isHypertension;
        this.isDiabetes = isDiabetes;
        this.isAvc = isAvc;
        this.isHeartAttack = isHeartAttack;
        this.isLeprosy = isLeprosy;
        this.isTuberculosis = isTuberculosis;
        this.isCancer = isCancer;
        this.isInBend = isInBend;
        this.isDomiciled = isDomiciled;
        this.otherPractices = otherPractices;
        this.isMentalHealth = isMentalHealth;
    }

    public boolean[] getDiseases(){
        return new boolean[]{isSmoker, isAlcohol, isDrugs, isHypertension, isDiabetes, isAvc, isHeartAttack,
                isLeprosy, isTuberculosis, isCancer, isInBend, isDomiciled, otherPractices, isMentalHealth};
    }

    public void setDiseases(boolean[] diseases) {

        if(diseases.length != 14){
            throw new IllegalArgumentException("Array length must be equals at 14. Length = " + diseases.length + ".");
        }
        setSmoker(diseases[0]);
        setAlcohol(diseases[1]);
        setDrugs(diseases[2]);
        setHypertension(diseases[3]);
        setDiabetes(diseases[4]);
        setAvc(diseases[5]);
        setHeartAttack(diseases[6]);
        setLeprosy(diseases[7]);
        setTuberculosis(diseases[8]);
        setCancer(diseases[9]);
        setInBend(diseases[10]);
        setDomiciled(diseases[11]);
        setOtherPractices(diseases[12]);
        setMentalHealth(diseases[13]);
    }


    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    public boolean isAlcohol() {
        return isAlcohol;
    }

    public void setAlcohol(boolean alcohol) {
        isAlcohol = alcohol;
    }

    public boolean isDrugs() {
        return isDrugs;
    }

    public void setDrugs(boolean drugs) {
        isDrugs = drugs;
    }

    public boolean isHypertension() {
        return isHypertension;
    }

    public void setHypertension(boolean hypertension) {
        isHypertension = hypertension;
    }

    public boolean isDiabetes() {
        return isDiabetes;
    }

    public void setDiabetes(boolean diabetes) {
        isDiabetes = diabetes;
    }

    public boolean isAvc() {
        return isAvc;
    }

    public void setAvc(boolean avc) {
        isAvc = avc;
    }

    public boolean isHeartAttack() {
        return isHeartAttack;
    }

    public void setHeartAttack(boolean heartAttack) {
        isHeartAttack = heartAttack;
    }

    public boolean isLeprosy() {
        return isLeprosy;
    }

    public void setLeprosy(boolean leprosy) {
        isLeprosy = leprosy;
    }

    public boolean isTuberculosis() {
        return isTuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        isTuberculosis = tuberculosis;
    }

    public boolean isCancer() {
        return isCancer;
    }

    public void setCancer(boolean cancer) {
        isCancer = cancer;
    }

    public boolean isInBend() {
        return isInBend;
    }

    public void setInBend(boolean inBend) {
        isInBend = inBend;
    }

    public boolean isDomiciled() {
        return isDomiciled;
    }

    public void setDomiciled(boolean domiciled) {
        isDomiciled = domiciled;
    }

    public boolean isOtherPractices() {
        return otherPractices;
    }

    public void setOtherPractices(boolean otherPractices) {
        this.otherPractices = otherPractices;
    }

    public boolean isMentalHealth() {
        return isMentalHealth;
    }

    public void setMentalHealth(boolean mentalHealth) {
        isMentalHealth = mentalHealth;
    }
}
