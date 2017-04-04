package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;


public interface ICitizenData {
    void send(PersonalDataModel personalData);

    void send(SocialDemographicModel socialDemographicData);

    void send(HealthConditionsModel healthConditions);

    void send(StreetSituationModel streetSituation);
}
