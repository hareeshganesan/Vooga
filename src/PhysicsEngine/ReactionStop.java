package PhysicsEngine;

import npsprite.SpriteTemplate;


/**
 * this class works for the case that two sprites collide and then we want them
 * both stop with a certain distance between them
 * 
 * @author Donghe
 */
public class ReactionStop extends Reaction
{

    private final double FACTOR = 0.5;
    private double myDistance = 0.2;
    private double myStopPositionXOne = 0;
    private double myStopPositionYOne = 0;
    private double myStopPositionXTwo = 0;
    private double myStopPositionYTwo = 0;


    public ReactionStop ()
    {

    }


    public ReactionStop (double dis)
    {
        myDistance = dis;
    }


    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        CollisionStatus status1 = spriteOne.getCollisionStatus();
        CollisionStatus status2 = spriteTwo.getCollisionStatus();
        initializeIncrement();
        setHorizontalIncrement(status1, status2);
        setVerticalIncrement(status1, status2);
        setNextLocationIncrement(spriteOne, spriteTwo, physicsEngine);
    }


    private void initializeIncrement ()
    {
        dx1 = myStopPositionXOne;
        dy1 = myStopPositionYOne;
        dx2 = myStopPositionXTwo;
        dy2 = myStopPositionYTwo;
    }


    private void setHorizontalIncrement (CollisionStatus status1,
                                         CollisionStatus status2)
    {
        if (status1.getLeft() && status2.getRight())
        {
            dx1 = myStopPositionXOne + myDistance * FACTOR;
            dx2 = myStopPositionXTwo - myDistance * FACTOR;
        }
        else if (status1.getRight() && status2.getLeft())
        {
            dx1 = myStopPositionXOne - myDistance * FACTOR;
            dx2 = myStopPositionXTwo + myDistance * FACTOR;
        }
    }


    private void setVerticalIncrement (CollisionStatus status1,
                                       CollisionStatus status2)
    {
        if (status1.getUp() && status2.getDown())
        {
            dy1 = myStopPositionYOne + myDistance * FACTOR;
            dy2 = myStopPositionYTwo - myDistance * FACTOR;
        }
        else if (status1.getDown() && status2.getUp())
        {
            dy1 = myStopPositionYOne - myDistance * FACTOR;
            dy2 = myStopPositionYTwo + myDistance * FACTOR;
        }
    }


    /**
     * the stop position for these two sprites
     */
    public void setStopPosition (double x1, double y1, double x2, double y2)
    {
        this.myStopPositionXOne = x1;
        this.myStopPositionXTwo = x2;
        this.myStopPositionYOne = y1;
        this.myStopPositionYTwo = y2;
    }


    /**
     * the distance we want them to keep
     */
    public void setDistance (double dis)
    {
        myDistance = dis;
    }

}
