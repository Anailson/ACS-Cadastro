package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class CommunicableDisease extends RealmObject implements Serializable {

    private boolean tuberculosis, leprosy, dengue, dst;

    public CommunicableDisease() {
        this(new boolean[4]);
    }

    public CommunicableDisease(boolean[] communicable) {
        this(communicable[0], communicable[1], communicable[2], communicable[3]);

        if(communicable.length != 4){
            throw new IllegalArgumentException("Length of conditions array must be 4 instead " + communicable.length);
        }
    }

    public CommunicableDisease(boolean tuberculosis, boolean leprosy, boolean dengue, boolean dst) {
        this.tuberculosis = tuberculosis;
        this.leprosy = leprosy;
        this.dengue = dengue;
        this.dst = dst;
    }

    public boolean[] getValues(){
        return new boolean[]{tuberculosis, leprosy, dengue, dst};
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public boolean isLeprosy() {
        return leprosy;
    }

    public void setLeprosy(boolean leprosy) {
        this.leprosy = leprosy;
    }

    public boolean isDengue() {
        return dengue;
    }

    public void setDengue(boolean dengue) {
        this.dengue = dengue;
    }

    public boolean isDst() {
        return dst;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }
}
