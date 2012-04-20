package npsprite;

/**
 * For keeping track of a sprite's additional characteristics Just a data class
 * for now, subclass if you want to add more stuff
 * 
 * @author Wendy
 * @deprecated only used for interfaces, interfaces are deprecated now
 */
@Deprecated
public class SpriteID {
    public enum GroupID {
        PLAYER_1, PLAYER_2, PLAYER_AI, PLATFORM, POWER_UP, UNCATEGORIZED
    }

    private GroupID groupID;
    private boolean hasHealth;
    private boolean doesDamage;
    private boolean spawns;
    private boolean attachable;


    /**
     * @param group - group ID (sprites in same group will not be checked for
     *            collisions by physics engine?)
     * @param health - true if sprite has health
     * @param damages - true if sprite can damage/heal other sprites (damages
     *            will be - values, heals + values)
     * @param spawns - true if sprite can spawn other sprites
     * @param attaches - true if sprite can attach as child to other sprites
     */
    public SpriteID (GroupID group,
                     boolean health,
                     boolean damages,
                     boolean spawns,
                     boolean attaches)
    {
        groupID = group;
        hasHealth = health;
        doesDamage = damages;
        this.spawns = spawns;
        attachable = attaches;
    }


    public GroupID getGroupID ()
    {
        return groupID;
    }


    public boolean hasHealth ()
    {
        return hasHealth;
    }


    public boolean doesDamage ()
    {
        return doesDamage;
    }


    public boolean spawns ()
    {
        return spawns;
    }


    public boolean attaches ()
    {
        return attachable;
    }

}
