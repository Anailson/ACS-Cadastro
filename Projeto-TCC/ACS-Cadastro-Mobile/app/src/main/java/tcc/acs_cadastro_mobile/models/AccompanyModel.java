package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

import static tcc.acs_cadastro_mobile.Constants.Accompany.CONDITIONS;
import static tcc.acs_cadastro_mobile.Constants.Accompany.EXAMS;
import static tcc.acs_cadastro_mobile.Constants.Accompany.NASF_CONDUCT;
import static tcc.acs_cadastro_mobile.Constants.Accompany.RECORD_DATA;

public class AccompanyModel extends RealmObject implements Serializable, ISearcher {

    public static final String RECORD = "record";
    public static final String NUM_SUS = "numSus";

    @PrimaryKey
    private long record;
    private long numSus;
    private RecordDataModel recordData;
    private ConditionsModel conditions;
    private ExamsModel exams;
    private NasfConductModel nasfConduct;
    public AccompanyModel() {
        this.record = this.numSus = AcsRecordPersistence.DEFAULT_INT;
        this.recordData = new RecordDataModel();
        this.conditions = new ConditionsModel();
        this.exams = new ExamsModel();
        this.nasfConduct = new NasfConductModel();
    }

    public AccompanyModel(RecordDataModel recordData, ConditionsModel conditions, ExamsModel exams,
                          NasfConductModel nasfConduct) {
        this.record = recordData.getRecordDetails().getRecord();
        this.numSus = recordData.getRecordDetails().getNumSus();
        this.recordData = recordData;
        this.conditions = conditions;
        this.exams = exams;
        this.nasfConduct = nasfConduct;
    }

    public JSONObject asJson(){
        JSONObject json = new JSONObject();
        try {
            json.put(RECORD_DATA.name(), recordData.asJson());
            json.put(CONDITIONS.name(), conditions.asJson());
            json.put(EXAMS.name(), exams.asJson());
            json.put(NASF_CONDUCT.name(), nasfConduct.asJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getName() {
        return getRecordData().getName();
    }

    public long getNumSus() {
        return numSus;
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public RecordDataModel getRecordData() {
        return recordData;
    }

    public void setRecordData(RecordDataModel recordData) {
        this.recordData = recordData;
    }

    public ConditionsModel getConditions() {
        return conditions;
    }

    public void setConditions(ConditionsModel conditions) {
        this.conditions = conditions;
    }

    public ExamsModel getExams() {
        return exams;
    }

    public void setExams(ExamsModel exams) {
        this.exams = exams;
    }

    public NasfConductModel getNasfConduct() {
        return nasfConduct;
    }

    public void setNasfConduct(NasfConductModel nasfConduct) {
        this.nasfConduct = nasfConduct;
    }

    public boolean recordContainsKey(int search) {
        return getRecord() > AcsRecordPersistence.DEFAULT_INT
                && containsKey(String.valueOf(getRecord()), String.valueOf(search));
    }

    public boolean numSusContainsKey(int search) {
        return getNumSus() > AcsRecordPersistence.DEFAULT_INT
                && containsKey(String.valueOf(getNumSus()), String.valueOf(search));
    }

    public boolean nameContainsKey(String search) {
        return containsKey(getName(), search);
    }

    private boolean containsKey(String value, String search) {
        return value.trim().toUpperCase().contains(search.toUpperCase());
    }
}
