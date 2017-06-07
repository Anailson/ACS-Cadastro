package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

public class HousingConditionsModel implements Serializable {

    private HousingSituation housingSituation;
    private House house;
    private boolean electricEnergy;
    private WaterAndSanitation waterAndSanitation;
    private Pet pet;

    public HousingConditionsModel(HousingSituation housingSituation, House house, boolean electricEnergy,
                                  WaterAndSanitation waterAndSanitation, Pet pet) {

        this.housingSituation = housingSituation;
        this.house = house;
        this.electricEnergy = electricEnergy;
        this.waterAndSanitation = waterAndSanitation;
        this.pet = pet;
    }

    public String getHousingSituation() {
        return housingSituation.situation;
    }

    public String getLocation() {
        return housingSituation.location;
    }

    public String getOwnership() {
        return housingSituation.location;
    }

    public String getResidenceType() {
        return house.type;
    }

    public int getTotalResidents() {
        return house.nResident;
    }

    public int getTotalRooms() {
        return house.nRoom;
    }

    public String getResidenceAccess() {
        return house.access;
    }

    public String getConstruction() {
        return house.contruction;
    }

    public String getConstructionType() {
        return house.contructionType;
    }

    public String getWaterSupply(){
        return waterAndSanitation.waterSupply;
    }

    public String getWaterTreatment(){
        return waterAndSanitation.waterTreatment;
    }

    public String getBathroom(){
        return waterAndSanitation.bathroom;
    }












    public boolean isElectricEnergy() {
        return electricEnergy;
    }

    public boolean[] getPets() {
        return new boolean[]{pet.cat, pet.dog, pet.bird, pet.criation, pet.another};
    }

    public boolean isHasPets() {
        return pet.hasPet;
    }

    public int getTotalPets() {
        return pet.nPets;
    }


    public static class HousingSituation{
        private String situation, location, ownership;

        public  HousingSituation(String situation, String location, String ownership){
            this.situation = situation;
            this.location = location;
            this.ownership = ownership;
        }
    }

    public static class House{
        private String type, access, contruction, contructionType;
        private int nResident, nRoom;

        public House(String type, int nResident, int nRoom, String access, String contruction, String contructionType){
            this.type = type;
            this.nResident = nResident;
            this.nRoom = nRoom;
            this.access = access;
            this.contruction = contruction;
            this.contructionType = contructionType;
        }
    }

    public static class WaterAndSanitation{
        private String waterSupply, waterTreatment, bathroom;

        public WaterAndSanitation (String waterApply, String waterTreatment, String bathroom){
            this.waterSupply = waterApply;
            this.waterTreatment = waterTreatment;
            this.bathroom = bathroom;
        }
    }

    public static class Pet {
        private boolean hasPet, cat, dog, bird, criation, another;
        private int nPets;

        public Pet (boolean hasPet, boolean cat, boolean dog, boolean bird, boolean criation,
                    boolean another, int nPets){
            this.hasPet = hasPet;
            this.cat = cat;
            this.dog = dog;
            this.bird = bird;
            this.criation = criation;
            this.another = another;
            this.nPets = nPets;
        }

        public Pet(boolean hasPet, boolean[] pets, int nPets){
            this(hasPet, pets[0], pets[1], pets[2], pets[3], pets[4], nPets);
        }
    }
}