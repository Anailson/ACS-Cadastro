package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.subModels.EvaluatedExams;
import tcc.acs_cadastro_mobile.subModels.RequestExams;

public class ExamsModel extends RealmObject implements Serializable{

    private String pic;
    private RequestExams request;
    private EvaluatedExams evaluate;
    private RealmList<RealmInt> anothers;//TODO

    public ExamsModel() {
        this("", new RequestExams(), new EvaluatedExams(), new RealmList<RealmInt>());
    }

    public ExamsModel(String pic, RequestExams request, EvaluatedExams evaluate, RealmList<RealmInt> anothers) {
        this.pic = pic;
        this.request = request;
        this.evaluate = evaluate;
        this.anothers = anothers;
    }

    public boolean[] getEvaluates(){
        return getEvaluate().values();
    }

    public boolean[] getRequests(){
        return getRequest().values();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public RequestExams getRequest() {
        return request;
    }

    public void setRequest(RequestExams request) {
        this.request = request;
    }

    public EvaluatedExams getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(EvaluatedExams evaluate) {
        this.evaluate = evaluate;
    }

    public RealmList<RealmInt> getAnothers() {
        return anothers;
    }

    public void setAnothers(RealmList<RealmInt> anothers) {
        this.anothers = anothers;
    }
}
