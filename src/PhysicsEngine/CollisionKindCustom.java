package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;

public class CollisionKindCustom extends CollisionKind {

	private Class<?> c1;
	private Class<?> c2;
	private Type type1;
	private Type type2;
	private Relation relation;

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
		switch (relation) {
		case AND:
			return checkThisClass(spriteOne, c1, type1)
					&& checkThisClass(spriteTwo, c2, type2);
		case OR:
			return checkThisClass(spriteOne, c1, type1)
					|| checkThisClass(spriteTwo, c2, type2);
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

	public void setType(Class<?> c1, CollisionKindCustom.Type type1,
			CollisionKindCustom.Relation r, Class<?> c2,
			CollisionKindCustom.Type type2) {
		this.c1 = c1;
		this.c2 = c2;
		this.type1 = type1;
		this.type2 = type2;
		this.relation = r;
	}

}
