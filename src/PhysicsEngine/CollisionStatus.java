package PhysicsEngine;

public class CollisionStatus {
	private boolean myLeftStatus;
	private boolean myRightStatus;
	private boolean myUpStatus;
	private boolean myDownStatus;

	public CollisionStatus() {
		setDefault();
	}

	public boolean getStatus() {
		return myLeftStatus || myRightStatus || myUpStatus || myDownStatus;
	}

	public boolean getLeft() {
		return myLeftStatus;
	}

	public void setLeft(boolean b) {
		myLeftStatus = b;
	}

	public boolean getRight() {
		return myRightStatus;
	}

	public void setRight(boolean b) {
		myRightStatus = b;
	}

	public boolean getUp() {
		return myUpStatus;
	}

	public void setUp(boolean b) {
		myUpStatus = b;
	}

	public boolean getDown() {
		return myDownStatus;
	}

	public void setDown(boolean b) {
		myDownStatus = b;
	}

	public void setDefault() {
		myLeftStatus = false;
		myRightStatus = false;
		myUpStatus = false;
		myDownStatus = false;
	}

}
