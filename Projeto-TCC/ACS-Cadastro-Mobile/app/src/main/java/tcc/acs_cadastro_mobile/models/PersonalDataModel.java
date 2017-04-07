package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;
import java.util.Formatter;

public class PersonalDataModel implements Serializable {

    private long numSus, numNis, respNumSus;
    private String name, socialName, motherName, respBirth, birth, nationBirth, phone, email;
    private String[] gender, race, nationality, uf, city;
    private boolean motherUnknown, responsible;

    public PersonalDataModel() {
        name = socialName = motherName = birth = nationBirth = phone = email = CitizenModel.STRING_DEFAULT_VALUE;
        gender = race = nationality = uf = city
                = new String[]{Integer.toString(CitizenModel.INT_DEFAULT_VALUE), CitizenModel.STRING_DEFAULT_VALUE};
        numSus = numNis = respNumSus = CitizenModel.INT_DEFAULT_VALUE;
    }

    public PersonalDataModel(long numSus, String name, String socialName, boolean motherUnknown,
                             String motherName, long numNis, String birth, boolean responsible,
                             long respNumSus, String resBirth, String[] gender, String[] race, String[] nation,
                             String nationBirth, String[] uf, String[] city, String phone, String email) {

        this.numSus = numSus;
        this.name = name;
        this.socialName = socialName;
        this.motherUnknown = motherUnknown;
        this.motherName = motherName;
        this.numNis = numNis;
        this.birth = birth;
        this.responsible = responsible;
        this.respNumSus = respNumSus;
        this.respBirth = resBirth;
        this.gender = gender;
        this.race = race;
        this.nationality = nation;
        this.nationBirth = nationBirth;
        this.uf = uf;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public long getNumSus() {
        return numSus;
    }

    public String getName() {
        return name;
    }

    public String getSocialName() {
        return socialName;
    }

    public boolean isMotherUnknown() {
        return motherUnknown;
    }

    public String getMotherName() {
        return motherName;
    }

    public long getNumNis() {
        return numNis;
    }

    public String getBirth() {
        return birth;
    }

    public boolean isResponsible() {
        return responsible;
    }

    public long getRespNumSus() {
        return respNumSus;
    }

    public String getRespBirth() {
        return respBirth;
    }

    public String[] getGender() {
        return gender;
    }

    public String[] getRace() {
        return race;
    }

    public String[] getNationality() {
        return nationality;
    }

    public String getNationBirth() {
        return nationBirth;
    }

    public String[] getUf() {
        return uf;
    }

    public String[] getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNationBirth() {
        return nationBirth.equals("Brasil");
    }

    @Override
    public String toString() {

        /*
        this.numSus = numSus;
        this.name = name;
        this.socialName = socialName;
        this.motherUnknown = motherUnknown;
        this.motherName = motherName;
        this.numNis = numNis;
        this.birth = birth;
        this.responsible = responsible;
        this.respNumSus = respNumSus;
        this.respBirth = resBirth;
        this.gender = gender;
        this.race = race;
        this.nationality = nation;
        this.nationBirth = nationBirth;
        this.uf = uf;
        this.city = city;
        this.phone = phone;
        this.email = email;
         */

        Formatter out = new Formatter();
        out.format("numSus: %s, name: %s, socialName: %s, motherUnknown: %s, Nascimento: %s",
                numSus, name, socialName, motherUnknown, birth);
        return out.toString();
    }
}
