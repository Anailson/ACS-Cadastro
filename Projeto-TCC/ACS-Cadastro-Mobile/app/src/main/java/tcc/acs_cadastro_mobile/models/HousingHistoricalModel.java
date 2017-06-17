package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;
import java.util.Formatter;

import io.realm.Realm;
import io.realm.RealmObject;

public class HousingHistoricalModel extends RealmObject implements Serializable {

    private long numSus;
    private long familyRecord;
    private String birthDate;
    private String familyIncome;
    private int nMembers;
    private String livesSince;
    private boolean moved;

    public static HousingHistoricalModel getHousingHistoricalModel(Realm realm, long numSus, long familyRecord,
                       String birthDate, String familyIncome, int nMembers, String livesSince, boolean moved){
        realm.beginTransaction();
        HousingHistoricalModel object = realm.createObject(HousingHistoricalModel.class);
        object.setNumSus(numSus);
        object.setFamilyRecord(familyRecord);
        object.setBirthDate(birthDate);
        object.setFamilyIncome(familyIncome);
        object.setnMembers(nMembers);
        object.setLivesSince(livesSince);
        object.setMoved(moved);
        realm.commitTransaction();
        return object;
    }

    public long getNumSus() {
        return numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public long getFamilyRecord() {
        return familyRecord;
    }

    public void setFamilyRecord(long familyRecord) {
        this.familyRecord = familyRecord;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(String familyIncome) {
        this.familyIncome = familyIncome;
    }

    public int getnMembers() {
        return nMembers;
    }

    public void setnMembers(int nMembers) {
        this.nMembers = nMembers;
    }

    public String getLivesSince() {
        return livesSince;
    }

    public void setLivesSince(String livesSince) {
        this.livesSince = livesSince;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public String toString(){
        return new Formatter().format("NumSus: %s, BirthDate: %s", numSus, birthDate ).toString();
    }
}
