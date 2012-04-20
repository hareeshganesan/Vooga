package npsprite;

import java.util.ArrayList;

/**
 * Better than SpriteGroup given by GoldenT in our engine
 * 
 * @author Donghe
 * 
 */
public class SpriteGroupTemplate {
	private ArrayList<SpriteTemplate> mySpriteGroup = new ArrayList<SpriteTemplate>();
	
	private String myName;

	public SpriteGroupTemplate(String groupName) {
		myName = groupName;
	}

	public void addSpriteTemplate(SpriteTemplate s) {
		mySpriteGroup.add(s);
	}

	public SpriteTemplate getSprite(int index) {
		return mySpriteGroup.get(index);
	}

	public void removeSpriteTemplate(int index) {
		mySpriteGroup.remove(index);
	}

	public int getSize() {
		return mySpriteGroup.size();
	}

	public String getGroupName() {
		return myName;
	}

	public ArrayList<SpriteTemplate> getSpriteArray() {
		return mySpriteGroup;
	}

	//TODO: figure out how to make more defaulted for nodesprites too
	public void addSpriteArray(ArrayList<SpriteTemplate> group) {
		for (SpriteTemplate s : group) {
			mySpriteGroup.add(s);
		}
	}
	
	/**
	 * Add all limbs to collision detection
	 */
	public void addFighterSpriteArray(ArrayList<FighterBody> group) {
		for (FighterBody s : group) {
		    for (NodeSprite n:s.getBodyParts()){
	            mySpriteGroup.add(n);
		    }
		}
	}
	
	public void addPlatformBlockArray(ArrayList<PlatformBlock> group) {
		for (PlatformBlock s : group) {
			mySpriteGroup.add(s);
		}
	}
	
	public void addSpriteGroup(SpriteGroupTemplate group){
		ArrayList<SpriteTemplate> spriteGroup = group.getSpriteArray();
		mySpriteGroup.addAll(spriteGroup);
	}
}
