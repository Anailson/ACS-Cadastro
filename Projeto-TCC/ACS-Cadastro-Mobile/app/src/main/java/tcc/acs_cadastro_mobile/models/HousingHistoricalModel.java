package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;

public class HousingHistoricalModel extends RealmObject implements Serializable{

    private long familyRecord;
    private CitizenModel responsible;
    private float familyIncome;
    private int nMembers;
    private String livesSince;
    private boolean moved;

    public static HousingHistoricalModel getHousingHistoricalModel(Realm realm, long familyRecord,
                   CitizenModel responsible, float familyIncome, int nMembers, String livesSince, boolean moved){
        realm.beginTransaction();
        HousingHistoricalModel object = realm.createObject(HousingHistoricalModel.class);

        realm.commitTransaction();
        return object;
    }

    public long getFamilyRecord() {
        return familyRecord;
    }

    public long getNumSus() {
        return responsible.getNumSus();
    }

    public String getBirthDate(){
        return  responsible.getBirthDate();
    }

    public float getFamilyIncome() {
        return familyIncome;
    }

    public int getnMembers() {
        return nMembers;
    }

    public String getLivesSince() {
        return livesSince;
    }

    public boolean isMoved() {
        return moved;
    }
}
