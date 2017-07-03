package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class Pet extends RealmObject implements Serializable {

    private boolean hasPet, cat, dog, bird, criation, another;
    private int nPets;

    public Pet() {
        this(false, new boolean[5], 0);
    }

    public Pet(boolean hasPet, boolean[] pets, int nPets) {
        this(hasPet, pets[0], pets[1], pets[2], pets[3], pets[4], nPets);
    }

    public Pet(boolean hasPet, boolean cat, boolean dog, boolean bird, boolean criation, boolean another, int nPets) {
        this.hasPet = hasPet;
        this.cat = cat;
        this.dog = dog;
        this.bird = bird;
        this.criation = criation;
        this.another = another;
        this.nPets = nPets;
    }

    public void setPets(boolean[] pets){
        setCat(pets[0]);
        setDog(pets[1]);
        setBird(pets[2]);
        setCriation(pets[3]);
        setAnother(pets[4]);
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public boolean isCat() {
        return cat;
    }

    public void setCat(boolean cat) {
        this.cat = cat;
    }

    public boolean isDog() {
        return dog;
    }

    public void setDog(boolean dog) {
        this.dog = dog;
    }

    public boolean isBird() {
        return bird;
    }

    public void setBird(boolean bird) {
        this.bird = bird;
    }

    public boolean isCriation() {
        return criation;
    }

    public void setCriation(boolean criation) {
        this.criation = criation;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public int getnPets() {
        return nPets;
    }

    public void setnPets(int nPets) {
        this.nPets = nPets;
    }

    public boolean[] getPets(){
        return new boolean[]{cat, dog, bird, criation, another};
    }
}
