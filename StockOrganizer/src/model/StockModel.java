package model;

public class StockModel {
	private String stockName,stockBrand;
	private Integer stokID,quantity;
	public StockModel(String stockName, String stockBrand, Integer stokID, Integer quantity) {
		super();
		this.stockName = stockName;
		this.stockBrand = stockBrand;
		this.stokID = stokID;
		this.quantity = quantity;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockBrand() {
		return stockBrand;
	}
	public void setStockBrand(String stockBrand) {
		this.stockBrand = stockBrand;
	}
	public Integer getStokID() {
		return stokID;
	}
	public void setStokID(Integer stokID) {
		this.stokID = stokID;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
