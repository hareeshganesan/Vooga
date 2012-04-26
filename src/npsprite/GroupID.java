package npsprite;

import java.util.HashMap;

/**
 * I don't really know how to use enums
 * @author Wendy
 *
 */
public enum GroupID {
    PLAYER_1, PLAYER_2, PLAYER_AI, PLATFORM, POWER_UP, UNCATEGORIZED;
    private static HashMap<String,GroupID> xmlcodes;
    static {
        xmlcodes=new HashMap<String,GroupID>();
        xmlcodes.put("PLAYER_1",PLAYER_1);
        xmlcodes.put("PLAYER_2",PLAYER_2);
        xmlcodes.put("PLAYER_AI",PLAYER_AI);
        xmlcodes.put("PLATFORM",PLATFORM);
        xmlcodes.put("POWER_UP",POWER_UP);
        xmlcodes.put("UNCATEGORIZED",UNCATEGORIZED);
    }
    
    //for level objects factory
    public static GroupID getIdFromString(String n){
        GroupID name=xmlcodes.get(n);
        if (name==null){
            return UNCATEGORIZED;
        }
        return name;
    }
    
    //if the collisions want to use this instead of reflection
    public static boolean isFighter(GroupID g){
        return g.name().contains("PLAYER");
    }
}

