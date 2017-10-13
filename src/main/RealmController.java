package main;

public class RealmController {
	
	public Realm[][] realms;
	private int[] realmIndex;
	
	public RealmController() {
		
		realms = new Realm[20][20];
		realmIndex = new int[] {10, 10};
		
	}
	
	private void createNewRealm() {
		if(realms[realmIndex[0]][realmIndex[1]] == null) {
			realms[realmIndex[0]][realmIndex[1]] = new Realm();
		}
		Main.realm = realms[realmIndex[0]][realmIndex[1]];
	}
	
	public boolean upRealm() {
		if(realmIndex[0]-1 < realms.length-1) {
			return false;
		}
		realmIndex[0]-=1;
		createNewRealm();
		return true;
	}
	
	public boolean downRealm() {
		if(realmIndex[0]+1 > realms.length-1) {
			return false;
		}
		realmIndex[0]+=1;
		createNewRealm();
		return true;
	}
	
	public boolean leftRealm() {
		if(realmIndex[1]-1 < realms.length-1) {
			return false;
		}
		realmIndex[1]-=1;
		createNewRealm();
		return true;
	}
	
	public boolean rightRealm() {
		if(realmIndex[1]+1 > realms.length-1) {
			return false;
		}
		realmIndex[1]+=1;
		createNewRealm();
		return true;
	}

}
