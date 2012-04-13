package SpriteTree;

public abstract class Animation {
	long myTime;
	
	
	public Animation(long currTime) {
		myTime = currTime;
	}
	public abstract void animate(LimbNode node1, LimbNode node2);
	public abstract boolean getStatus();
	public abstract void activateAnimation();
	
	public void setCurrentTime(long currTime){
		this.myTime = currTime;
	}
	public long getCurrentTime(){
		return this.myTime;
	}
}
