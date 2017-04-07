package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

public class StreetSituationModel implements Serializable {

    private boolean streetSituation, benefit, family;
    private String[] streetTime, foodPerDay, institutionAnother, familyVisit;
    private boolean[] foodOrigin, hygiene;

    public StreetSituationModel() {
        streetSituation = benefit = family = false;
        streetTime = foodPerDay = institutionAnother = familyVisit
                = new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
        foodOrigin = hygiene = new boolean[]{};
    }

    public StreetSituationModel(boolean streetSituation, String[] streetTime, boolean benefit, boolean family,
                                String[] foodPerDay, boolean[] foodOrigin, String[] institutionAnother,
                                String[] familyVisit, boolean[] hygiene) {
        this.streetSituation = streetSituation;
        this.streetTime = streetTime;
        this.benefit = benefit;
        this.family = family;
        this.foodPerDay = foodPerDay;
        this.foodOrigin = foodOrigin;
        this.institutionAnother = institutionAnother;
        this.familyVisit = familyVisit;
        this.hygiene = hygiene;
    }

    public boolean isStreetSituation() {
        return streetSituation;
    }

    public String[] getStreetTime() {
        return streetTime;
    }

    public boolean isBenefit() {
        return benefit;
    }

    public boolean isFamily() {
        return family;
    }

    public String[] getFoodPerDay() {
        return foodPerDay;
    }

    public boolean[] getFoodOrigin() {
        return foodOrigin;
    }

    public boolean isInstitutionAnother(){
        return !institutionAnother[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getInstitutionAnother() {
        return institutionAnother;
    }

    public boolean isFamilyVisit() {
        return !familyVisit[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getFamilyVisit() {
        return familyVisit;
    }

    public boolean isHygiene() {
        return hygiene[0];
    }

    public boolean[] getHygiene() {
        return hygiene;
    }
}
