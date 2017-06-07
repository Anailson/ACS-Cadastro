package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class Deficiency extends RealmObject implements Serializable {

    private boolean isDeficiency, hearing, visual, intellectual, phisical, another;


    public Deficiency() {}

    public Deficiency(boolean isDeficiency, boolean[] values) {
        this.isDeficiency = isDeficiency;
        setDeficiencys(values);
    }

    public boolean[] getDeficiencys(){
        return new boolean[]{hearing, visual, intellectual, phisical, another};
    }

    public void setDeficiencys(boolean[] deficiency){
        if(deficiency.length < 5){
            throw new IllegalArgumentException();
        }
        setHearing(deficiency[0]);
        setVisual(deficiency[1]);
        setIntellectual(deficiency[2]);
        setPhisical(deficiency[3]);
        setAnother(deficiency[4]);
    }

    public boolean isDeficiency() {
        return isDeficiency;
    }

    public void setDeficiency(boolean deficiency) {
        isDeficiency = deficiency;
    }

    public boolean isHearing() {
        return hearing;
    }

    public void setHearing(boolean hearing) {
        this.hearing = hearing;
    }

    public boolean isVisual() {
        return visual;
    }

    public void setVisual(boolean visual) {
        this.visual = visual;
    }

    public boolean isIntellectual() {
        return intellectual;
    }

    public void setIntellectual(boolean intellectual) {
        this.intellectual = intellectual;
    }

    public boolean isPhisical() {
        return phisical;
    }

    public void setPhisical(boolean phisical) {
        this.phisical = phisical;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }
}
