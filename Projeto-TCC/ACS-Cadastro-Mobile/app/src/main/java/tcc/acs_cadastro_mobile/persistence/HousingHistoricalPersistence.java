package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;

public class HousingHistoricalPersistence {

    private HousingHistoricalPersistence() {}


    public static HousingHistoricalModel getHousingHistoricalModel(long numSus, long familyRecord,
                       String birthDate, String familyIncome, int nMembers, String livesSince, boolean moved){
        Realm realm = Realm.getDefaultInstance();
        HousingHistoricalModel historicalModel =  HousingHistoricalModel.getHousingHistoricalModel(realm, numSus, familyRecord, birthDate,
                familyIncome, nMembers,livesSince, moved);
        realm.close();
        return historicalModel;
    }
}
