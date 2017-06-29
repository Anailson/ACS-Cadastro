package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class Following extends RealmObject implements Serializable {
    private boolean pregnant, puerpera, newborn, child, malnutrition, rehabilitationDeficiency,
            hypertension, diabetes, asthma, copdEmphysema, cancer, chronicDiseases, leprosy, tuberculosis,
            respiratory, smoker, homeBedding, vulnerability, bolsaFamília, mentalHealth, alcohol, drugs;

    public Following() {
        this(new boolean[22]);
    }

    public Following(boolean[] followings) {
        this(followings[0], followings[1], followings[2], followings[3], followings[4], followings[5],
                followings[6], followings[7], followings[8], followings[9], followings[10], followings[11],
                followings[12], followings[13], followings[14], followings[15], followings[16], followings[17],
                followings[18], followings[19], followings[20], followings[21]);
    }

    public Following(boolean pregnant, boolean puerpera, boolean newborn, boolean child, boolean malnutrition,
                     boolean rehabilitationDeficiency, boolean hypertension, boolean diabetes, boolean asthma,
                     boolean copdEmphysema, boolean cancer, boolean chronicDiseases, boolean leprosy,
                     boolean tuberculosis, boolean respiratory, boolean smoker, boolean homeBedding,
                     boolean vulnerability, boolean bolsaFamília, boolean mentalHealth, boolean alcohol,
                     boolean drugs) {
        this.pregnant = pregnant;
        this.puerpera = puerpera;
        this.newborn = newborn;
        this.child = child;
        this.malnutrition = malnutrition;
        this.rehabilitationDeficiency = rehabilitationDeficiency;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.asthma = asthma;
        this.copdEmphysema = copdEmphysema;
        this.cancer = cancer;
        this.chronicDiseases = chronicDiseases;
        this.leprosy = leprosy;
        this.tuberculosis = tuberculosis;
        this.respiratory = respiratory;
        this.smoker = smoker;
        this.homeBedding = homeBedding;
        this.vulnerability = vulnerability;
        this.bolsaFamília = bolsaFamília;
        this.mentalHealth = mentalHealth;
        this.alcohol = alcohol;
        this.drugs = drugs;
    }

    public boolean[] getValues(){
        return new boolean[]{pregnant, puerpera, newborn, child, malnutrition, rehabilitationDeficiency,
                hypertension, diabetes, asthma, copdEmphysema, cancer, chronicDiseases, leprosy, tuberculosis,
                respiratory, smoker, homeBedding, vulnerability, bolsaFamília, mentalHealth, alcohol, drugs};
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isPuerpera() {
        return puerpera;
    }

    public void setPuerpera(boolean puerpera) {
        this.puerpera = puerpera;
    }

    public boolean isNewborn() {
        return newborn;
    }

    public void setNewborn(boolean newborn) {
        this.newborn = newborn;
    }

    public boolean isChild() {
        return child;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public boolean isMalnutrition() {
        return malnutrition;
    }

    public void setMalnutrition(boolean malnutrition) {
        this.malnutrition = malnutrition;
    }

    public boolean isRehabilitationDeficiency() {
        return rehabilitationDeficiency;
    }

    public void setRehabilitationDeficiency(boolean rehabilitationDeficiency) {
        this.rehabilitationDeficiency = rehabilitationDeficiency;
    }

    public boolean isHypertension() {
        return hypertension;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isCopdEmphysema() {
        return copdEmphysema;
    }

    public void setCopdEmphysema(boolean copdEmphysema) {
        this.copdEmphysema = copdEmphysema;
    }

    public boolean isCancer() {
        return cancer;
    }

    public void setCancer(boolean cancer) {
        this.cancer = cancer;
    }

    public boolean isChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(boolean chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public boolean isLeprosy() {
        return leprosy;
    }

    public void setLeprosy(boolean leprosy) {
        this.leprosy = leprosy;
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public boolean isRespiratory() {
        return respiratory;
    }

    public void setRespiratory(boolean respiratory) {
        this.respiratory = respiratory;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isHomeBedding() {
        return homeBedding;
    }

    public void setHomeBedding(boolean homeBedding) {
        this.homeBedding = homeBedding;
    }

    public boolean isVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(boolean vulnerability) {
        this.vulnerability = vulnerability;
    }

    public boolean isBolsaFamília() {
        return bolsaFamília;
    }

    public void setBolsaFamília(boolean bolsaFamília) {
        this.bolsaFamília = bolsaFamília;
    }

    public boolean isMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(boolean mentalHealth) {
        this.mentalHealth = mentalHealth;
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
}
