package session;

import model.UserModel;

public class UserSession {
	private static UserModel admin;

	public UserSession() {
	}

	public static UserModel getAdmin() {
		return admin;
	}

	public static void setAdmin(UserModel adm) {
		admin = adm;
	}

}
