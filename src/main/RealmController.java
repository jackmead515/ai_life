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
		if(realmIndex[1]+1 > realms[0].length) {
			return;
		}
		realmIndex[1]+=1;
		createNewRealm();
	}
	
	public void downRealm() {
		if(realmIndex[1]-1 < realms[0].length) {
			return;
		}
		realmIndex[1]-=1;
		createNewRealm();
	}
	
	public void leftRealm() {
		if(realmIndex[0]-1 < realms[0].length) {
			return;
		}
		realmIndex[0]-=1;
		createNewRealm();
	}
	
	public void rightRealm() {
		if(realmIndex[0]+1 > realms[0].length) {
			return;
		}
		realmIndex[0]+=1;
		createNewRealm();
	}

}
