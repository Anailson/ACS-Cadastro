package tcc.acs_cadastro_mobile.interfaces;

import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;

public interface IResidenceData {
    void send(AddressDataModel addressData);

    void send(HousingConditionsModel housingConditions);
}
