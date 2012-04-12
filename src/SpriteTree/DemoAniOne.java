package SpriteTree;

public class DemoAniOne extends Animation {

	public DemoAniOne(long currTime) {
		super(currTime);
	}
	public void animate(LimbNode leg, LimbNode lowerLeg){
		if(this.myTime<=40){
			leg.rotate(-1);
			lowerLeg.rotate(2);
		}
		if(this.myTime>40 && this.myTime <=80){
			lowerLeg.rotate(-4);
		}
		if(this.myTime>80 && this.myTime<=120){
			lowerLeg.rotate(4);

		}
		if(this.myTime >120 && this.myTime<=160){
			leg.rotate(1);
			lowerLeg.rotate(-2);
		}
	}
	
}
