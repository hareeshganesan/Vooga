package SpriteTree;


public class DemoAniProxy extends Animation {

	private DemoAniOne animation;
	private long currTime;
	
	public DemoAniProxy(long currTime) {
		super(currTime);
		this.currTime = currTime;
	}

	@Override
	public void animate(LimbNode node1, LimbNode node2) {
		if(this.animation==null){
			this.animation = new DemoAniOne(currTime);
		}
		this.animation.animate(node1, node2);
	}
	
	@Override
	public void setCurrentTime(long currTime){
		this.animation.setCurrentTime(currTime);
	}
	
	
	@Override
	public boolean getStatus(){
		if(this.animation==null){
			this.animation = new DemoAniOne(currTime);
			return false;
		}
		return this.animation.getStatus();		
	}

	@Override
	public void activateAnimation() {
		this.animation.activateAnimation();
	}
	
}
