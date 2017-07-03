package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class House extends RealmObject implements Serializable {

    private String type, access, contruction, contructionType;
    private int nResident, nRoom;

    public House() {
        this("", 0, 0, "", "", "");
    }

    public House(String type, int nResident, int nRoom, String access, String contruction, String contructionType) {
        this.type = type;
        this.access = access;
        this.contruction = contruction;
        this.contructionType = contructionType;
        this.nResident = nResident;
        this.nRoom = nRoom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getContruction() {
        return contruction;
    }

    public void setContruction(String contruction) {
        this.contruction = contruction;
    }

    public String getContructionType() {
        return contructionType;
    }

    public void setContructionType(String contructionType) {
        this.contructionType = contructionType;
    }

    public int getnResident() {
        return nResident;
    }

    public void setnResident(int nResident) {
        this.nResident = nResident;
    }

    public int getnRoom() {
        return nRoom;
    }

    public void setnRoom(int nRoom) {
        this.nRoom = nRoom;
    }
}
