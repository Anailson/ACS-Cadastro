package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import java.io.Serializable;

public class Feeding extends RealmObject implements Serializable{

    private String foodPerDay;
    private boolean restaurant, restaurantDonation, religiousGroup, popular, another;

    public Feeding() {
        this("", new boolean[5]);
    }

    public Feeding(String foodPerDay, boolean [] feedings) {
        this(foodPerDay, feedings[0], feedings[1], feedings[2], feedings[3], feedings[4]);
    }

    public Feeding(String foodPerDay, boolean restaurant, boolean restaurantDonation, boolean religiousGroup,
                   boolean popular, boolean another) {
        this.foodPerDay = foodPerDay;
        this.restaurant = restaurant;
        this.restaurantDonation = restaurantDonation;
        this.religiousGroup = religiousGroup;
        this.popular = popular;
        this.another = another;
    }

    public boolean[] getFeedings() {
        return new boolean[]{restaurant, restaurantDonation, religiousGroup, popular, another};
    }

    public void setFeedings(boolean feedings[]){
        if(feedings.length != 5){
            throw new IllegalArgumentException("Array length must be equals at 5. Length = " + feedings.length + ".");
        }
        setRestaurant(feedings[0]);
        setRestaurantDonation(feedings[1]);
        setReligiousGroup(feedings[2]);
        setPopular(feedings[3]);
        setAnother(feedings[4]);
    }

    public String getFoodPerDay() {
        return foodPerDay;
    }

    public void setFoodPerDay(String foodPerDay) {
        this.foodPerDay = foodPerDay;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isRestaurantDonation() {
        return restaurantDonation;
    }

    public void setRestaurantDonation(boolean restaurantDonation) {
        this.restaurantDonation = restaurantDonation;
    }

    public boolean isReligiousGroup() {
        return religiousGroup;
    }

    public void setReligiousGroup(boolean religiousGroup) {
        this.religiousGroup = religiousGroup;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.FOOD_PER_DAY.name(), foodPerDay);
        json.put(Constants.Citizen.RESTAURANT.name(), restaurant);
        json.put(Constants.Citizen.RESTAURANT_DONATION.name(), restaurantDonation);
        json.put(Constants.Citizen.RELIGIOUS_GROUP.name(), religiousGroup);
        json.put(Constants.Citizen.POPULAR.name(), popular);
        json.put(Constants.Citizen.ANOTHER.name(), another);
        return json;
    }
}
