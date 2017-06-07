package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

public class HousingHistoricalModel implements Serializable{

    private long familyRecord;
    private CitizenModel responsible;
    private float familyIncome;
    private int nMembers;
    private String livesSince;
    private boolean moved;

    public HousingHistoricalModel(long familyRecord, CitizenModel responsible, float familyIncome,
                                  int nMembers, String livesSince, boolean moved){
        this.familyRecord = familyRecord;
        this.responsible = responsible;
        this.familyIncome = familyIncome;
        this.nMembers = nMembers;
        this.livesSince = livesSince;
        this.moved = moved;
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
