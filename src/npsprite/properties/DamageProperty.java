package npsprite.properties;

public class DamageProperty extends PropertyObject{
    static final String NAME="damage";
    private double damage;
    public DamageProperty(double damage){
        this.damage=damage;
    }
    public void setDamage(double damage){
        this.damage=damage;
    }
    public double getDamage(){
        return damage;
    }

    public static String getName(){
        return NAME;
    }
    @Override
    public PropertyObject clone() {
        return new DamageProperty(damage);
    }
    
}
