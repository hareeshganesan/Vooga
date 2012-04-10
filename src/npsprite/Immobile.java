package npsprite;

import java.awt.geom.Point2D.Double;

/**
 * More for syntax and organization than anything else
 * @author Wendy
 *
 */
public interface Immobile {
    
    /**
     * Set to nothing, immobile can't move.
     */
    public abstract void move (double dx, double dy);
    public void setNextLocation(Double double1); //TODO: figure what the hell is going on
    
}
