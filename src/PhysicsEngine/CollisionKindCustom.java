package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;

/**
 * a subclass of CollisionKind that helps develpers to create their own
 * collision kind. After creating this class with its reaction passing in, we
 * also need to set the type which defines what belong this kind of collision
 * 
 * @author Donghe
 * 
 */
public class CollisionKindCustom extends CollisionKind {

	private Class<?> myClassOne;
	private Class<?> myClassTwo;
	private Type myTypeOne;
	private Type myTypeTwo;
	private Relation myRelation;

	/**
	 * the relation we care about, IS: this sprite is just the certain type of
	 * class, SUPER: this sprite needs to be the superclass of the certain type
	 * of class, SUB: this sprite needs to be the subclass of the certain type
	 * of class.
	 * 
	 * @author Donghe
	 * 
	 */
	public enum Type {
		IS, SUPER, SUB
	}

	/**
	 * AND: these two sprites have some requirment at the same time. OR: these
	 * two sprites just need one meets its requirment.
	 * 
	 * @author Donghe
	 * 
	 */
	public enum Relation {
		AND, OR
	}

	public CollisionKindCustom(ArrayList<Reaction> reactions) {
		super(reactions);
	}

	public CollisionKindCustom(Reaction reaction) {
		super(reaction);
	}

	public CollisionKindCustom() {
	}

	@Override
	public boolean isThisKind(SpriteTemplate spriteOne, SpriteTemplate spriteTwo) {
		switch (myRelation) {
		case AND:
			return checkThisClass(spriteOne, myClassOne, myTypeOne)
					&& checkThisClass(spriteTwo, myClassTwo, myTypeTwo);
		case OR:
			return checkThisClass(spriteOne, myClassOne, myTypeOne)
					|| checkThisClass(spriteTwo, myClassTwo, myTypeTwo);
		default:
			return false;
		}

	}

	/**
	 * check the IS, SUPER or SUB requirement
	 */
	private boolean checkThisClass(SpriteTemplate sprite, Class<?> c,
			CollisionKindCustom.Type type) {
		switch (type) {
		case IS:
			return isThisClass(sprite, c);
		case SUPER:
			return isSuperClass(sprite, c);
		case SUB:
			return isSubClass(sprite, c);
		default:
			return false;
		}
	}

	/**
	 * this sprite is just this certain type of class
	 */
	private boolean isThisClass(SpriteTemplate sprite, Class<?> c) {
		return sprite.getClass().equals(c);
	}

	/**
	 * this sprite is the super class of the certain type of class
	 */
	private boolean isSuperClass(SpriteTemplate sprite, Class<?> c) {
		return sprite.getClass().isAssignableFrom(c);
	}

	/**
	 * this sprite is the sub class just this certain type of class
	 */
	private boolean isSubClass(SpriteTemplate sprite, Class<?> c) {
		return c.isAssignableFrom(sprite.getClass());
	}

	/**
	 * set the class type and raltion
	 * 
	 * @param classOne
	 *            the certain class type one
	 * @param typeOne
	 *            the sprite is what type to classOne
	 * @param relation
	 *            the requriment
	 * @param classTwo
	 *            the certain class type two
	 * @param typeTwo
	 *            the sprite is what type to classTwo
	 */
	public void setType(Class<?> classOne, CollisionKindCustom.Type typeOne,
			CollisionKindCustom.Relation relation, Class<?> classTwo,
			CollisionKindCustom.Type typeTwo) {
		this.myClassOne = classOne;
		this.myClassTwo = classTwo;
		this.myTypeOne = typeOne;
		this.myTypeTwo = typeTwo;
		this.myRelation = relation;
	}

}
