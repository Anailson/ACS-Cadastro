package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class AgentPersistence {

    public static AgentModel save(String name, long numSus, int area, int equip) {
        return save(new AgentModel(name, numSus, area, equip));
    }

    public static AgentModel save(AgentModel agentModel) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(AgentModel.class);
        AgentModel object = realm.copyToRealm(agentModel);
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static AgentModel get() {
        AgentModel agent = Realm.getDefaultInstance()
                .where(AgentModel.class)
                .findAll()
                .first(new AgentModel());
        Realm.getDefaultInstance().close();
        return agent;
    }
}
