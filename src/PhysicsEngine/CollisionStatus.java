package PhysicsEngine;

/**
 * the collision status for any sprite including the specific collided position
 * 
 * @author Donghe
 * 
 */
public class CollisionStatus {
	private boolean myLeftStatus;
	private boolean myRightStatus;
	private boolean myUpStatus;
	private boolean myDownStatus;
	private boolean myStandOnSth = false;

	public CollisionStatus() {
		setDefault();
	}

	/**
	 * @return whether this sprite is collided with someone
	 */
	public boolean getStatus() {
		return myLeftStatus || myRightStatus || myUpStatus || myDownStatus;
	}

	/**
	 * @return whether this sprite is collided with someone at LEFT
	 */
	public boolean getLeft() {
		return myLeftStatus;
	}

	/**
	 * set the LEFT collision status
	 */
	public void setLeft(boolean b) {
		myLeftStatus = b;
	}

	/**
	 * @return whether this sprite is collided with someone at RIGHT
	 */
	public boolean getRight() {
		return myRightStatus;
	}

	/**
	 * set the RIGHT collision status
	 */
	public void setRight(boolean b) {
		myRightStatus = b;
	}

	/**
	 * @return whether this sprite is collided with someone at UP
	 */
	public boolean getUp() {
		return myUpStatus;
	}

	/**
	 * set the UP collision status
	 */
	public void setUp(boolean b) {
		myUpStatus = b;
	}

	/**
	 * @return whether this sprite is collided with someone at DOWN
	 */
	public boolean getDown() {
		return myDownStatus;
	}

	/**
	 * set the DOWN collision status
	 */
	public void setDown(boolean b) {
		myDownStatus = b;
	}

	/**
	 * whether this sprite is standing on sth. For example, on the ground or on
	 * some other sprite
	 */
	public boolean getStandOnSth() {
		return myStandOnSth || myDownStatus;
	}

	/**
	 * set the standing status
	 */
	public void setStandOnSth(boolean b) {
		myStandOnSth = b;
	}

	/**
	 * set the default collision status, all false
	 */
	public void setDefault() {
		myLeftStatus = false;
		myRightStatus = false;
		myUpStatus = false;
		myDownStatus = false;
	}

}
