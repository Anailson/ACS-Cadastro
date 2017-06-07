package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class KidneyDisease extends RealmObject implements Serializable {

    private boolean isKidneyDisease, insuficiency, another, dontKnow;

    public boolean[] getKidneyDiseases() {
        return new boolean[]{isInsuficiency(), isAnother(), isDontKnow()};
    }


    public void setDisease(boolean diseases[]){
        if(diseases.length != 3){
            throw new IllegalArgumentException("Array length must be equals at 3. Length = " + diseases.length + ".");
        }
        setInsuficiency(diseases[0]);
        setAnother(diseases[1]);
        setDontKnow(diseases[2]);
    }

    public boolean isKidneyDisease() {
        return isKidneyDisease;
    }

    public void setKidneyDisease(boolean kidneyDisease) {
        isKidneyDisease = kidneyDisease;
    }

    public boolean isInsuficiency() {
        return insuficiency;
    }

    public void setInsuficiency(boolean insuficiency) {
        this.insuficiency = insuficiency;
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
