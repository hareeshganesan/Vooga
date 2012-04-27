package npsprite.properties;

public class DamageProperty extends PropertyObject{
    public static final String NAME="damage";
    private double damage;
    
    public DamageProperty(double damage){
        this.damage=damage;
    }
    
    public void setDamage(double damage){
        this.damage=damage;
    }
    
    @Override
    public double getValue() {
        return (int) damage;
    }
    
    @Override
    public PropertyObject clone() {
        return new DamageProperty(damage);
    }
}
