package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	Statement stat;
	public Connection con;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	public PreparedStatement ps;
	
	public static Connect instance;

	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kelas", "root", "");
			stat = con.createStatement();
			System.out.println("Connection Succeed");
		} catch (Exception e) {
			System.out.println("Connection Fail");
		}
	}
	
	public synchronized static Connect getInstance() {
		if(instance == null) {
			instance = new Connect();
			return instance;
		}
		
		return instance;
	}
	
	public ResultSet getData(String query) {
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int insertData(String query) {
		try {
			ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.execute();
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void updateData(String query) {
		try {
			stat.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeData(String query){
		try {
			stat.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
