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
	
	public void upRealm() {
		realmIndex[1]+=1;
		createNewRealm();
	}
	
	public void downRealm() {
		realmIndex[1]-=1;
		createNewRealm();
	}
	
	public void leftRealm() {
		realmIndex[0]-=1;
		createNewRealm();
	}
	
	public void rightRealm() {
		realmIndex[0]+=1;
		createNewRealm();
	}

}
