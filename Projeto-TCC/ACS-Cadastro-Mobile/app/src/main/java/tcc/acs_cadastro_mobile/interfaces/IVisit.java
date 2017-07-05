package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;

public interface IVisit {

    void send(RecordVisitModel details);

    void send(ReasonsVisitModel reasons);
}
