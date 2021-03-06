package npsprite;

import java.util.ArrayList;


/**
 * Better than SpriteGroup given by GoldenT in our engine this whole group has
 * several teams collision only checks between sprites from different teams
 * 
 * @author Donghe
 */
public class SpriteGroupTemplate
{
    private ArrayList<ArrayList<SpriteTemplate>> mySpriteGroup =
        new ArrayList<ArrayList<SpriteTemplate>>();


    public SpriteGroupTemplate ()
    {}


    /**
     * add this sprite in a new team
     */
    public void addSpriteInNewTeam (SpriteTemplate s)
    {
        ArrayList<SpriteTemplate> newTeam = new ArrayList<SpriteTemplate>();
        newTeam.add(s);
        mySpriteGroup.add(newTeam);
    }


    /**
     * add this sprite in an old specific team according to the team index
     */
    public void addSpriteInOldTeam (SpriteTemplate s, int teamIndex)
    {
        if (teamIndex >= mySpriteGroup.size())
        {
            addSpriteInNewTeam(s);
        }
        mySpriteGroup.get(teamIndex).add(s);
    }


    /**
     * get the specific team
     */
    public ArrayList<SpriteTemplate> getTeam (int teamIndex)
    {
        return mySpriteGroup.get(teamIndex);
    }


    /**
     * get the specific sprite
     */
    public SpriteTemplate getSprite (int teamIndex, int spriteIndex)
    {
        return mySpriteGroup.get(teamIndex).get(spriteIndex);
    }


    /**
     * remove the team from the whole group
     */
    public void removeSpriteTeam (int teamIndex)
    {
        mySpriteGroup.remove(teamIndex);
    }


    /**
     * remove the sprite from the whole group
     */
    public void removeSpriteTemplate (int teamIndex, int spriteIndex)
    {
        mySpriteGroup.get(teamIndex).remove(spriteIndex);
    }


    /**
     * gives the group with this groupID
     */
    public int getTeam (GroupID g)
    {
        for (int i = 0; i < mySpriteGroup.size(); i++)
        {
            ArrayList<SpriteTemplate> a = mySpriteGroup.get(i);
            if (a.get(0).getGroupID() == g)
            {
                return 0;
            }

        }
        return mySpriteGroup.size();
    }


    /**
     * the total number of teams in this group
     */
    public int getTeamNum ()
    {
        return mySpriteGroup.size();
    }


    /**
     * the size of a certain team
     */
    public int getTeamSize (int teamIndex)
    {
        return mySpriteGroup.get(teamIndex).size();
    }


    /**
     * get the index in its team
     */
    public int getSpriteIndexInTeam (SpriteTemplate s)
    {
        for (ArrayList<SpriteTemplate> team : mySpriteGroup)
        {
            if (team.contains(s)) return team.indexOf(s);
        }
        return -1;
    }


    /**
     * get the team index
     */
    public int getTeamIndex (SpriteTemplate s)
    {
        for (ArrayList<SpriteTemplate> team : mySpriteGroup)
        {
            if (team.contains(s)) return mySpriteGroup.indexOf(team);
        }
        return -1;
    }


    /**
     * get the whole group
     */
    public ArrayList<ArrayList<SpriteTemplate>> getGroup ()
    {
        return mySpriteGroup;
    }

    public void addSpriteArray (ArrayList<SpriteTemplate> team)
    {
        mySpriteGroup.add(team);
    }


    /**
     * Add all limbs to collision detection
     */
    public void addFighterSpriteArray (ArrayList<FighterBody> group)
    {
        for (FighterBody s : group)
        {
            // only add body as a whole, check all limb collisions in the collision class
            addSpriteInNewTeam(s);
        }
    }


    public void addPlatformBlockArray (ArrayList<PlatformBlock> team)
    {
        ArrayList<SpriteTemplate> newTeam = new ArrayList<SpriteTemplate>();
        for (PlatformBlock s : team)
        {
            newTeam.add(s);
        }
        mySpriteGroup.add(newTeam);
    }

}
