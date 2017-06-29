package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;

public interface IAccompany {
    void send(RecordDataModel recordData);

    void send(ConditionsModel conditions);

    void send(ExamsModel exams);

    void send(NasfConductModel nasfConduct);
}