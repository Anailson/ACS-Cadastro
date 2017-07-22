package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;

import static tcc.acs_cadastro_mobile.Constants.Citizen.ANOTHER_INSTITUTION;
import static tcc.acs_cadastro_mobile.Constants.Citizen.BENEFIT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FAMILY;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FAMILY_VISIT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FEEDING;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HYGIENE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.STREET;
import static tcc.acs_cadastro_mobile.Constants.Citizen.STREET_SITUATION;

public class StreetSituationModel extends RealmObject implements Serializable {

    private StreetSituation streetSituation;
    private boolean benefit, family;
    private Feeding feeding;
    private AnotherInstitution anotherInstitution;
    private FamilyVisit familyVisit;
    private Hygiene hygiene;

    public StreetSituationModel() {
        this(new StreetSituation(), false, false, new Feeding(), new AnotherInstitution(), new FamilyVisit(),
                new Hygiene());
    }

    public StreetSituationModel(StreetSituation streetSituation, boolean benefit, boolean family, Feeding feeding,
                                AnotherInstitution anotherInstitution, FamilyVisit familyVisit, Hygiene hygiene) {
        this.streetSituation = streetSituation;
        this.benefit = benefit;
        this.family = family;
        this.feeding = feeding;
        this.anotherInstitution = anotherInstitution;
        this.familyVisit = familyVisit;
        this.hygiene = hygiene;
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


    public JSONObject asJson() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(STREET.name(), streetSituation.getAsJson());
        json.put(BENEFIT.name(), benefit);
        json.put(FAMILY.name(), family);
        json.put(FEEDING.name(), feeding.getAsJson());
        json.put(ANOTHER_INSTITUTION.name(), anotherInstitution.getAsJson());
        json.put(FAMILY_VISIT.name(), familyVisit.getAsJson());
        json.put(HYGIENE.name(), hygiene.getAsJson());
        return json;
    }
}