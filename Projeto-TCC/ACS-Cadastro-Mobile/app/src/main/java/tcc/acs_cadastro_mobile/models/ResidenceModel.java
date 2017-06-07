package tcc.acs_cadastro_mobile.models;

import java.util.Random;

public class ResidenceModel {

    private static int count = 1;
    private static final int CEP_NUM = 111111111;

    private String address;
    private int number;
    private long cep, homePhone, referencePhone;

    private AddressDataModel addressData;
    private HousingConditionsModel housingConditions;
    private HousingHistoricalModel[] housingHistorical;

    public ResidenceModel(String address){
        this.address = address;
        this.number = (count++) * 100;
        this.cep = CEP_NUM * count;
        this.homePhone = new Random().nextInt(CEP_NUM);
        this.referencePhone = homePhone + CEP_NUM;
    }

    public ResidenceModel (AddressDataModel addressData, HousingConditionsModel housingConditions,
                            HousingHistoricalModel[] housingHistorical){
        this.addressData = addressData;
        this.housingConditions = housingConditions;
        this.housingHistorical = housingHistorical;
    }


    public boolean addressContainsKey(String key){
        return containsKey(address, key);
    }

    public boolean cepContainsKey(String key){
        return containsKey(String.valueOf(cep), key);
    }

    private boolean containsKey(String value, String key){
        return value.toUpperCase().contains(key.toUpperCase().trim());
    }

    public String getAddress(){
        return "Rua " + address + " " + number + ". Bairro";
    }


    public String getCep() {
        return "CEP: " + cep;
    }

    public String getHomePhone() {
        return "Telefone residencial: " + homePhone;
    }

    public String getReferencePhone() {
        return "Telefone de referencia: " + referencePhone;
    }
}
