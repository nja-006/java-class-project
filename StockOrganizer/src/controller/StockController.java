package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connection.Connect;
import model.StockModel;
import session.UserSession;

public class StockController {
	private Connect con = Connect.getInstance();
	public StockController() {
		// TODO Auto-generated constructor stub
	}
	
	public void AddNewStock(String stockName, Integer qty, String brand) throws SQLException, Exception {
		con.rs = con.getData("SELECT * FROM stock where stock_Name = '"+stockName+"' AND stock_brand = '"+brand+"'");
		if(con.rs.next()) {
			con.updateData("UPDATE stock set status = 'active', quantity = '"+qty+"' WHERE stock_ID = '"+con.rs.getInt(1)+"'");
			return;
		}
		
		con.insertData("INSERT INTO stock(stock_Name,quantity,stock_brand) values ('"+stockName+"','"+qty+"','"+brand+"')");
		return;
	}
	
	public ArrayList<StockModel> getStock() {
		con.rs = con.getData("SELECT * FROM stock WHERE status = 'active'");
		ArrayList<StockModel> stock = new ArrayList<StockModel>();
		try {
			while(con.rs.next() == true) {
				stock.add(new StockModel(con.rs.getString(2), con.rs.getString(4), con.rs.getInt(1), con.rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stock;
	}
	
	public StockModel getSpesificStock(Integer id) throws SQLException {
		con.rs = con.getData("SELECT * FROM stock WHERE stock_ID = '"+id+"'");
		con.rs.next();
		StockModel model = new StockModel(con.rs.getString(2), con.rs.getString(4), con.rs.getInt(1), con.rs.getInt(3));
		return model;
	}
	
	public void updateStock(int id, String name, String brand, int quantity) throws Exception {
		con.updateData("UPDATE stock set stock_Name = '"+name+"', stock_brand = '"+brand+"', quantity = '"+quantity+"' WHERE stock_ID = '"+id+"'");
	}
	
	public void deleteStock(int id) {
		con.updateData("UPDATE stock set status = 'inactive' WHERE stock_ID = '"+id+"'");
	}
	
	public boolean addStock(int id, int quantity) throws SQLException {
		if(quantity < 1) return false;
		con.rs = con.getData("SELECT * FROM stock WHERE stock_ID = '"+id+"'");
		con.rs.next();
		StockModel model = new StockModel(con.rs.getString(2), con.rs.getString(4), con.rs.getInt(1), con.rs.getInt(3));
		int total = quantity + model.getQuantity();
		model.setQuantity(total);
		
		con.updateData("UPDATE stock set quantity = '"+model.getQuantity()+"' WHERE stock_ID = '"+id+"'");
		
		Date rawDate = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String date= formatter.format(rawDate);  
		con.insertData("INSERT INTO stockinput(admin_ID,date,stock_id,quantity) VALUES('"+UserSession.getAdmin().getAdminID()+"','"+date+"','"+id+"','"+quantity+"')");
		return true;
	}
	
	public void sell(ArrayList<StockModel> cart) throws Exception {
		Date rawDate = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String date= formatter.format(rawDate);
		int id = con.insertData("INSERT INTO stockoutput(admin_ID,date) values('"+UserSession.getAdmin().getAdminID()+"','"+date+"')");
		
		for (int i = 0; i < cart.size(); i++) {
			con.insertData("INSERT INTO stockoutput_detail(stockOutput_ID,stock_ID,quantity) VALUES ('"+id+"','"+cart.get(i).getStokID()+"','"+cart.get(i).getQuantity()+"')");
			stockOut(cart.get(i).getStokID(), cart.get(i).getQuantity());
		}
	}
	
	public void stockOut(int id, int quantity) throws Exception {
		con.rs = con.getData("SELECT * FROM stock WHERE stock_ID = '"+id+"'");
		con.rs.next();
		StockModel model = new StockModel(con.rs.getString(2), con.rs.getString(4), con.rs.getInt(1), con.rs.getInt(3));
		int total = model.getQuantity() - quantity;
		model.setQuantity(total);
		
		con.updateData("UPDATE stock set quantity = '"+model.getQuantity()+"' WHERE stock_ID = '"+id+"'");
	}
	
	public StockModel addToCart(Integer id, Integer qty) throws SQLException {
		con.rs = con.getData("SELECT * FROM stock WHERE stock_ID = '"+id+"'");
		con.rs.next();
		StockModel model = new StockModel(con.rs.getString(2), con.rs.getString(4), con.rs.getInt(1), qty);
		return model;
	}
}
