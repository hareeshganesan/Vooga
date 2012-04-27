package SpriteTree;

import npsprite.FighterBody;
import npsprite.NodeSprite;

public class Motion {
	//be able to decrement the time, updat() method, with elapsedtime, subtract from time
	//boolean update(long elapsedTime) time-=elaspedtime; return if time ==0;
	//pass in the currentangle of the limb
	//store dtheta: how much to move per second, constructor needs the source angle
	
	private String name;
	private double dTheta;
	private long time;
	private long defaultTime;
	private boolean active;
	private NodeSprite myLimb;
	private double myExpAngle;


	
	public Motion(String name, double expAngle, FighterBody tree, long time){
		
		this.name = name;
		
		myLimb = tree.getMap().get(name);
		myExpAngle = expAngle;

		this.time = time;
		this.defaultTime = time;
		
		//add rotated angle to current theta, assume already rotated
		//so the next motion performed on the same limb would calculate the rotated dtheta

		
//		System.out.println("name is " + name);
//		System.out.println(tree.getMap().get(name));
//		System.out.println("exp angle is "+ expAngle);
//		
		tree.getMap().get(name).setTheta(expAngle);

		active = true;
	}
	
	public void update(long elapsedTime){

		
		if(this.time >0){
			double currAngle = myLimb.getTheta();

			this.dTheta = (myExpAngle-currAngle)/(double)time;
			System.out.println(this.dTheta);
			this.myLimb.rotate(dTheta*Math.min(elapsedTime, time));			
			this.time -=elapsedTime;
		
		}
		else{
			active = false;
		}
	}
	
	

	                            
	public Boolean isActive(){
		return active;
	}
	
	public void reActivate(){
		this.time = this.defaultTime;
		active = true;
		

	}
	public String getName(){
		return this.name;
	}
	
	public double getDTheta(){
		return this.dTheta;
	}
	
	public long getTime(){
		return this.time;
	}
}

