package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import io.realm.RealmList;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.subModels.EvaluatedExams;
import tcc.acs_cadastro_mobile.subModels.RequestExams;

public class ExamsPersistence {

    private ExamsPersistence() {}

    public static ExamsModel get(String pic, RequestExams request, EvaluatedExams evaluated, RealmList<RealmInt> sids){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ExamsModel object = realm.copyToRealm(new ExamsModel(pic, request, evaluated, sids));
        realm.commitTransaction();
        return object;
    }

    public static RequestExams getRequestExams(boolean[] request){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RequestExams object = realm.copyToRealm(new RequestExams(request));
        realm.commitTransaction();
        return object;
    }

    public static EvaluatedExams getEvaluatedExams(boolean[] evaluated){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        EvaluatedExams object = realm.copyToRealm(new EvaluatedExams(evaluated));
        realm.commitTransaction();
        return object;
    }
}
