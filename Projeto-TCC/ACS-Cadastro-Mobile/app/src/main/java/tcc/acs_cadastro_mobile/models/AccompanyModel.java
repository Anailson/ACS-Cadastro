package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class AccompanyModel extends RealmObject implements Serializable, ISearcher {

    public static final String RECORD = "record";
    public static final String NUM_SUS = "numSus";
    public static final String STATUS = "status";

    @PrimaryKey
    private long record;
    private long numSus;
    private RecordDataModel recordData;
    private ConditionsModel conditions;
    private ExamsModel exams;
    private NasfConductModel nasfConduct;
    private int status;

    public AccompanyModel() {
        this.record = this.numSus = AcsRecordPersistence.DEFAULT_INT;
        this.recordData = new RecordDataModel();
        this.conditions = new ConditionsModel();
        this.exams = new ExamsModel();
        this.nasfConduct = new NasfConductModel();
        this.status = AcsRecordPersistence.INSERT;
    }

    public AccompanyModel(RecordDataModel recordData, ConditionsModel conditions, ExamsModel exams,
                          NasfConductModel nasfConduct) {
        this.record = recordData.getRecordDetails().getRecord();
        this.numSus = recordData.getRecordDetails().getNumSus();
        this.recordData = recordData;
        this.conditions = conditions;
        this.exams = exams;
        this.nasfConduct = nasfConduct;
        this.status = AcsRecordPersistence.INSERT;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
