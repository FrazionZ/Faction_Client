package fz.frazionz.api.gsonObj;

import java.util.UUID;

public class ProfilItem {

     private final int id;
     private final String uuid;
     private final String token;
     private final String username;

     public ProfilItem(int id, String uuid, String token, String username){
         this.id = id;
         this.uuid = uuid;
         this.token = token;
         this.username = username;
     }

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
