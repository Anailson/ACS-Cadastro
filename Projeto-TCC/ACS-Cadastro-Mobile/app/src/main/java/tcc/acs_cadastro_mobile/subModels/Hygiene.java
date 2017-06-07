package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;


public class Hygiene extends RealmObject implements Serializable {

    private boolean isHygiene, bath, sanitary, oral, another;

    public boolean[] getHygienes(){
        return new boolean[]{bath, sanitary, oral, another};
    }

    public void setHygienes(boolean[] hygienes){
        if(hygienes.length != 4){
            throw new IllegalArgumentException("Array length must be equals at 5. Length = " + hygienes.length + ".");
        }
        setBath(hygienes[0]);
        setSanitary(hygienes[1]);
        setOral(hygienes[2]);
        setAnother(hygienes[3]);
    }

    public boolean isHygiene() {
        return isHygiene;
    }

    public void setHygiene(boolean hygiene) {
        isHygiene = hygiene;
    }

    public boolean isBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public boolean isSanitary() {
        return sanitary;
    }

    public void setSanitary(boolean sanitary) {
        this.sanitary = sanitary;
    }

    public boolean isOral() {
        return oral;
    }

    public void setOral(boolean oral) {
        this.oral = oral;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }
}
