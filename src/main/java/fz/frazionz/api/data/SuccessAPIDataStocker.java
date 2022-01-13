package fz.frazionz.api.data;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.gsonObj.*;
import java.util.List;

public class SuccessAPIDataStocker {

    public static List<SuccessType> successTypes;

    public static void loadAPIData() {
        successTypes = HTTPFunctions.getAllSucessTypes();
        
        successTypes.add(new SuccessType(-1, "minecarft", "Minecraft"));
        
        for(SuccessType type : successTypes) {
            type.setItems(HTTPFunctions.getAllSuccessItemsPlayer(String.valueOf(type.getId())));
        }
    }
}
