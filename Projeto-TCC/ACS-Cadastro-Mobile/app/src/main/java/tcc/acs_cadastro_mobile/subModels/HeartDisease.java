package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class HeartDisease extends RealmObject implements Serializable {


    private boolean isHeartDisease, insuficiency, another, dontKnow;

    public HeartDisease() {
        this(false, new boolean[3]);
    }

    public HeartDisease(boolean isHearthDisease, boolean [] heartDiseases) {
        this(isHearthDisease, heartDiseases[0], heartDiseases[1], heartDiseases[2]);
    }

    public HeartDisease(boolean isHeartDisease, boolean insuficiency, boolean another, boolean dontKnow) {
        this.isHeartDisease = isHeartDisease;
        this.insuficiency = insuficiency;
        this.another = another;
        this.dontKnow = dontKnow;
    }

    public void setDisease(boolean diseases[]){
        if(diseases.length != 3){
            throw new IllegalArgumentException("Array length must be equals at 3. Length = " + diseases.length + ".");
        }
        setInsuficiency(diseases[0]);
        setAnother(diseases[1]);
        setDontKnow(diseases[2]);
    }

    public boolean[] getHeartDiseases(){
        return new boolean[]{isInsuficiency(), isAnother(), isDontKnow()};
    }

    public boolean isHeartDisease() {
        return isHeartDisease;
    }

    public void setHeartDisease(boolean heartDisease) {
        isHeartDisease = heartDisease;
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
