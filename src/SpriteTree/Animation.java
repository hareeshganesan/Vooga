package SpriteTree;

public abstract class Animation {
	long myTime;
	public Animation(long currTime) {
		myTime = currTime;
	}
	public abstract void animate(LimbNode node1, LimbNode node2);
	
	
}
