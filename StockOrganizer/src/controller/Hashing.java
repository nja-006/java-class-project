package controller;

public class Hashing {

	public Hashing() {
		// TODO Auto-generated constructor stub
	}
	
	public String hash(char[] password) {
		String hashPass = "";
		for (int i = 0; i < password.length; i++) {
			char buff = (char)((int)password[i] + 2);
			hashPass += buff;
		}
		return hashPass;
	}

}
