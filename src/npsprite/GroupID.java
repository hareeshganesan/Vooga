package npsprite;

/**
 * I don't really know how to use enums
 * @author Wendy
 *
 */
public enum GroupID {
    PLAYER_1, PLAYER_2, PLAYER_AI, PLATFORM, POWER_UP, UNCATEGORIZED;
    
    public static GroupID getIdFromString(String n){
        if (n.equals("PLAYER_1")){
            return PLAYER_1;
        }
        if (n.equals("PLAYER_2")){
            return PLAYER_2;
        }
        if (n.equals("PLAYER_AI")){
            return PLAYER_AI;
        }
        if (n.equals("PLATFORM")){
            return PLATFORM;
        }
        if (n.equals("POWER_UP")){
            return POWER_UP;
        }
        
        return UNCATEGORIZED;
    }
}

