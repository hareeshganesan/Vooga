package PhysicsEngine;

import npsprite.SpriteTemplate;


/**
 * this reaction works in the case that each sprite has a power to the other
 * 
 * @author Donghe
 */
public class ReactionForce extends Reaction
{

    private double forceX1 = 3000.0;
    private double forceY1 = 3000.0;
    private double forceX2 = -3000.0;
    private double forceY2 = -3000.0;


    public ReactionForce ()
    {

    }


    public ReactionForce (double horizontalForceOne,
                          double verticalForceOne,
                          double horizontalForceTwo,
                          double verticalForceTwo)
    {
        forceX1 = horizontalForceOne;
        forceY1 = verticalForceOne;
        forceX2 = horizontalForceTwo;
        forceY2 = verticalForceTwo;
    }


    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        double massOne = spriteOne.getMass();
        double massTwo = spriteTwo.getMass();

        dx1 = forceX2 / massOne;
        dy1 = forceY2 / massOne;
        dx2 = forceX1 / massTwo;
        dy2 = forceY1 / massTwo;

        setNextLocationIncrement(spriteOne, spriteTwo, physicsEngine);
    }


    /**
     * set the power for these two sprites on X and Y direction
     * 
     * @param horizontalForceOne sprite one x direction power
     * @param verticalForceOne sprite one y direction power
     * @param horizontalForceTwo sprite two x direction power
     * @param verticalForceTwo sprite two y direction power
     */
    public void setPower (double horizontalForceOne,
                          double verticalForceOne,
                          double horizontalForceTwo,
                          double verticalForceTwo)
    {
        forceX1 = horizontalForceOne;
        forceY1 = verticalForceOne;
        forceX2 = horizontalForceTwo;
        forceY2 = verticalForceTwo;
    }

}
