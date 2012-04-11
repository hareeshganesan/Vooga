package sprite;

import java.util.ArrayList;

/**
 * Better than SpriteGroup given by GoldenT in our engine
 * 
 * @author Donghe
 * 
 */
public class SpriteGroupTemplate {
	private ArrayList<SpriteTemplate> mySpriteGourp = new ArrayList<SpriteTemplate>();
	private String myName;

	public SpriteGroupTemplate(String groupName) {
		myName = groupName;
	}

	public void addSpriteTemplate(SpriteTemplate s) {
		mySpriteGourp.add(s);
	}

	public SpriteTemplate getSprite(int index) {
		return mySpriteGourp.get(index);
	}

	public void removeSpriteTemplate(int index) {
		mySpriteGourp.remove(index);
	}

	public int getSize() {
		return mySpriteGourp.size();
	}

	public String getGroupName() {
		return myName;
	}

	public ArrayList<SpriteTemplate> getSpriteArray() {
		return mySpriteGourp;
	}

	public void addSpriteArray(ArrayList<SpriteTemplate> group) {
		for (SpriteTemplate s : group) {
			mySpriteGourp.add(s);
		}
	}
	
	public void addFighterSpriteArray(ArrayList<FighterSprite> group) {
		for (FighterSprite s : group) {
			mySpriteGourp.add(s);
		}
	}
	
	public void addPlatformBlockArray(ArrayList<PlatformBlock> group) {
		for (PlatformBlock s : group) {
			mySpriteGourp.add(s);
		}
	}
}
