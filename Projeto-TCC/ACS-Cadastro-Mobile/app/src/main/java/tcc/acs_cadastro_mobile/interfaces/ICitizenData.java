package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.HealthConditionsDataModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicDataModel;
import tcc.acs_cadastro_mobile.models.StreetSituationDataModel;


public interface ICitizenData {
    void send(PersonalDataModel personalData);

    void send(SocialDemographicDataModel socialDemographicData);

    void send(HealthConditionsDataModel healthConditions);

    void send(StreetSituationDataModel streetSituation);
}
