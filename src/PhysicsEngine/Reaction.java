package PhysicsEngine;

import npsprite.SpriteTemplate;


/**
 * the super class of reaction
 * 
 * @author Donghe
 */
public abstract class Reaction
{

    protected double dx1;
    protected double dy1;
    protected double dx2;
    protected double dy2;


    /**
     * do the reaction between these two sprites
     * 
     * @param spriteOne sprite one
     * @param spriteTwo sprite two
     * @param physicsEngine the physics Engine we use to set new location
     *            increment
     */
    public abstract void act (SpriteTemplate spriteOne,
                              SpriteTemplate spriteTwo,
                              PhysicsEngine physicsEngine);


    /**
     * set location increment
     */
    protected void setNextLocationIncrement (SpriteTemplate spriteOne,
                                             SpriteTemplate spriteTwo,
                                             PhysicsEngine physicsEngine)
    {
        physicsEngine.setNextLocationIncrement(spriteOne, dx1, dy1);
        physicsEngine.setNextLocationIncrement(spriteTwo, dx2, dy2);
    }
}
