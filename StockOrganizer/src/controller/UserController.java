package controller;

import connection.Connect;
import model.UserModel;
import session.UserSession;

public class UserController {
	private Hashing hash = new Hashing();
	private Connect con = Connect.getInstance();
	public UserController() {
	}
	
	public boolean login(String email, char[] password) throws Exception {
		String hashPassword = hash.hash(password);
		
		con.rs = con.getData("SELECT * FROM admin Where admin_Email = '"+email+"' AND admin_Password = '"+hashPassword+"'");
		
		//if there is no data in the database the system will throw an exception
		if (!con.rs.next()) throw new Exception("Admin Not Found");
		UserSession.setAdmin(new UserModel(con.rs.getInt(1), con.rs.getString(2), con.rs.getString(4), con.rs.getString(5), con.rs.getDate(3)));
		return true;
	}
	
	public void register(String username, String email, char[] password, char[] confPassword, String day, String month, String year) throws Exception {
		//Check email is already exist in database
		con.rs = con.getData("Select * FROM admin Where admin_email = '"+email+"'");
		if(con.rs.next()) throw new Exception("Email already Exist");
		if(!email.contains("@") && !email.contains(".com")) throw new Exception("Please Input email correctly");
		
		//Check Password and Confirm Password is same
		boolean isTrue = true;
		for (int i = 0; i < confPassword.length; i++) {
			if(password[i] != confPassword[i]) isTrue = false;
		}
		if (password.length != confPassword.length ) isTrue = false;
		if(isTrue == false) throw new Exception("Password didn't match with confirm password");
		
		// make the string day, month, and year into a date format
		String stringDOB = year + "-" + month + "-" + day;
		
		//hashing the password
		String hashPassword = hash.hash(password);
		
		con.insertData("INSERT INTO admin(admin_name,admin_DOB,admin_Email,admin_Password) VALUES ('"+username+"','"+stringDOB+"','"+email+"','"+hashPassword+"')");
	}
}
