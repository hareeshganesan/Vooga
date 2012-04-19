package npsprite.properties;


public class HealthProperty extends PropertyObject{
    static final String NAME="health";
    private double health;
    private double MAX_HEALTH;
    
    public HealthProperty(double health){
        this.health=health;
        MAX_HEALTH=health;
    }
    public void addHealth(double damage){
        health+=damage;
        wrapHealth();
    }
    public double getHealth(){
        return health;
    }
    // also resets health to max health
    public void setMaxHealth(double h){
        MAX_HEALTH=h;
        health=MAX_HEALTH;
    }
    
    private void wrapHealth() {
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
        if (health < 0) {
            health = 0;
        }
    }
    public HealthProperty clone(){
        return new HealthProperty(MAX_HEALTH);
    }
    public static String getName(){
        return NAME;
    }

}
