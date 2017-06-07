package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;

public interface IResidenceData {
    void send(AddressDataModel addressData);

    void send(HousingConditionsModel housingConditions);

    void send(HousingHistoricalModel[] housingHistorical);
}
