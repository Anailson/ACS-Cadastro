package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class RespiratoryDisease extends RealmObject implements Serializable {

    private boolean isRespiratoryDisease, asthma, emphysema, another, dontKnow;

    public boolean[] getRespiratoryDiseases() {
        return new boolean[]{asthma, emphysema, another, dontKnow};
    }

    public void setRespiratoryDisease(boolean [] diseases){

        if(diseases.length != 4){
            throw new IllegalArgumentException("Array length must be equals at 4. Length = " + diseases.length + ".");
        }
        setAsthma(diseases[0]);
        setEmphysema(diseases[1]);
        setAnother(diseases[2]);
        setDontKnow(diseases[3]);
    }

    public boolean isRespiratoryDisease() {
        return isRespiratoryDisease;
    }

    public void setRespiratoryDisease(boolean respiratoryDisease) {
        isRespiratoryDisease = respiratoryDisease;
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isEmphysema() {
        return emphysema;
    }

    public void setEmphysema(boolean emphysema) {
        this.emphysema = emphysema;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public boolean isDontKnow() {
        return dontKnow;
    }

    public void setDontKnow(boolean dontKnow) {
        this.dontKnow = dontKnow;
    }
}
