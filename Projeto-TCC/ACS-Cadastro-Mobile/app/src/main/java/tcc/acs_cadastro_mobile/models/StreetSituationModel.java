package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;

public class StreetSituationModel extends RealmObject implements Serializable {

    private StreetSituation streetSituation;
    private boolean benefit, family;
    private Feeding feeding;
    private AnotherInstitution anotherInstitution;
    private FamilyVisit familyVisit;
    private Hygiene hygiene;

    public static StreetSituationModel newInstance(Realm realm, StreetSituation streetSituation,
                       boolean benefit, boolean family, Feeding feeding, AnotherInstitution anotherInstitution,
                       FamilyVisit familyVisit, Hygiene hygiene) {
        realm.beginTransaction();
        StreetSituationModel object = realm.createObject(StreetSituationModel.class);
        object.setStreetSituation(streetSituation);
        object.setBenefit(benefit);
        object.setFamily(family);
        object.setFeeding(feeding);
        object.setAnotherInstitution(anotherInstitution);
        object.setFamilyVisit(familyVisit);
        object.setHygiene(hygiene);
        realm.commitTransaction();
        return object;
    }

    public static StreetSituation getStreetSituation(Realm realm, boolean isStreet, String value){
        realm.beginTransaction();
        StreetSituation object = realm.createObject(StreetSituation.class);
        object.setStreetSituation(isStreet);
        object.setValue(value);
        realm.commitTransaction();
        return object;
    }

    public static Feeding getFeeding(Realm realm, String foodPerDay, boolean feedings[]){
        realm.beginTransaction();
        Feeding object = realm.createObject(Feeding.class);
        object.setFoodPerDay(foodPerDay);
        object.setFeedings(feedings);
        realm.commitTransaction();
        return object;
    }

    public static AnotherInstitution getAnotherInstitution(Realm realm, boolean isAnother, String value){
        realm.beginTransaction();
        AnotherInstitution object = realm.createObject(AnotherInstitution.class);
        object.setAnotherInstitution(isAnother);
        object.setValue(value);
        realm.commitTransaction();
        return object;
    }
    public static FamilyVisit getFamilyVisit (Realm realm, boolean isFamily, String value){
        realm.beginTransaction();
        FamilyVisit object = realm.createObject(FamilyVisit.class);
        object.setFamilyVisit(isFamily);
        object.setValue(value);
        realm.commitTransaction();
        return object;
    }
    public static Hygiene getHygiene (Realm realm, boolean isHygiene, boolean hygienes[]){
        realm.beginTransaction();
        Hygiene object = realm.createObject(Hygiene.class);
        object.setHygiene(isHygiene);
        object.setHygienes(hygienes);
        realm.commitTransaction();
        return object;
    }

    public StreetSituation getStreetSituation() {return streetSituation;}

    public void setStreetSituation(StreetSituation streetSituation) {this.streetSituation = streetSituation;}

    public boolean isBenefit() {return benefit;}

    public void setBenefit(boolean benefit) {this.benefit = benefit;}

    public boolean isFamily() {return family;}

    public void setFamily(boolean family) {this.family = family;}

    public Feeding getFeeding() {return feeding;}

    public void setFeeding(Feeding feeding) {this.feeding = feeding;}

    public AnotherInstitution getAnotherInstitution() {return anotherInstitution;}

    public void setAnotherInstitution(AnotherInstitution anotherInstitution) {
        this.anotherInstitution = anotherInstitution;
    }

    public FamilyVisit getFamilyVisit() {return familyVisit;}

    public void setFamilyVisit(FamilyVisit familyVisit) {this.familyVisit = familyVisit;}

    public Hygiene getHygiene() {return hygiene;}

    public void setHygiene(Hygiene hygiene) {this.hygiene = hygiene;}

    public boolean isStreetSituation() {return getStreetSituation().isStreetSituation();}

    public String getStreetTime() {return getStreetSituation().getValue();}

    public String getFoodPerDay() {return getFeeding().getFoodPerDay();}

    public boolean[] getFoodOrigin() {return getFeeding().getFeedings();}

    public boolean isInstitutionAnother() {
        return getAnotherInstitution().isAnotherInstitution();
    }

    public String getInstitutionAnother() {
        return getAnotherInstitution().getValue();
    }

    public boolean isFamilyVisit() {
        return getFamilyVisit().isFamilyVisit();
    }

    public String getFamilyVisitValue() {return getFamilyVisit().getValue();}

    public boolean isHygiene() {
        return getHygiene().isHygiene();
    }

    public boolean[] getHygienes() {return getHygiene().getHygienes();}
}