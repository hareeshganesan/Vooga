package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;

public class CollisionKindCustom extends CollisionKind {

	private Class<?> myClassOne;
	private Class<?> myClassTwo;
	private Type myTypeOne;
	private Type myTypeTwo;
	private Relation myRelation;

	public enum Type {
		IS, SUPER, SUB
	}

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

	private boolean isThisClass(SpriteTemplate sprite, Class<?> c) {
		return sprite.getClass().equals(c);
	}

	private boolean isSuperClass(SpriteTemplate sprite, Class<?> c) {
		return sprite.getClass().isAssignableFrom(c);
	}

	private boolean isSubClass(SpriteTemplate sprite, Class<?> c) {
		return c.isAssignableFrom(sprite.getClass());
	}

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
