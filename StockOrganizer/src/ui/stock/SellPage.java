package ui.stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.StockController;
import model.StockModel;

public class SellPage extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JTable table,cartTable;
	private JLabel titleLbl,subtitleLbl,quantityLbl;
	private JTextField quantityTxt;
	private JButton backBtn, sellBtn, addToCartBtn;
	private DefaultTableModel dtm, cartDtm;
	private int id = -1;
	private ArrayList<StockModel> cart;
	public SellPage() {
		cart = new ArrayList<StockModel>();
		landing();
		init();
	}
	
	public void landing() {
		Font titleFont = new Font("arial", Font.PLAIN, 25);
		Font subTitleFont = new Font("arial", Font.PLAIN, 20);
		
		//main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		setContentPane(mainPanel);
		
		
		//north panel
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		titleLbl = new JLabel("Stock Organizer",SwingConstants.CENTER);
		titleLbl.setFont(titleFont);
		northPanel.add(titleLbl);
		
		subtitleLbl = new JLabel("All Stock",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		//table stock
		table = new JTable();
		loadTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 10, 375, 200);
		centerPanel.add(scrollPane);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				row = table.getSelectedRow();
				id = (int) table.getValueAt(row, 0);
			}
		});
		
		//table cart
		cartTable = new JTable();
		loadCartTable();
		JScrollPane scrollPaneCart = new JScrollPane();
		scrollPaneCart.setViewportView(cartTable);
		scrollPaneCart.setBounds(400, 10, 375, 200);
		centerPanel.add(scrollPaneCart);
		
		quantityLbl = new JLabel("Quantity                      :");
		quantityLbl.setBounds(20, 220, 120, 20);    
		quantityTxt = new JTextField(20);
		quantityTxt.setBounds(150, 220, 100, 20);
		
		centerPanel.add(quantityLbl);
		centerPanel.add(quantityTxt);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		
		//south panel
		southPanel = new JPanel();
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		sellBtn = new JButton("Sell");
		sellBtn.addActionListener(this);
		addToCartBtn = new JButton("Add To Cart");
		addToCartBtn.addActionListener(this);
		southPanel.add(sellBtn);
		southPanel.add(addToCartBtn);
		southPanel.add(backBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}
	
	private void loadTable() {
		String header[] = {"ID","Stock Name", "Stock Brand","Stock Qty"};
		dtm = new DefaultTableModel(header,0);
		
		StockController controller = new StockController();
		try {
			ArrayList<StockModel> stock = controller.getStock();
			for (int i = 0; i < stock.size(); i++) {
				int id = stock.get(i).getStokID();
				String name = stock.get(i).getStockName();
				int qty = stock.get(i).getQuantity();
				String brand = stock.get(i).getStockBrand();
				
				Object[] data = {id,name,brand,qty};
				dtm.addRow(data);
			}
			table.setModel(dtm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadCartTable() {
		String header[] = {"ID","Stock Name", "Stock Brand","Stock Qty"};
		cartDtm = new DefaultTableModel(header,0);
		
		try {
			for (int i = 0; i < cart.size(); i++) {
				int id = cart.get(i).getStokID();
				String name = cart.get(i).getStockName();
				int qty = cart.get(i).getQuantity();
				String brand = cart.get(i).getStockBrand();
				
				Object[] data = {id,name,brand,qty};
				cartDtm.addRow(data);
			}
			 cartTable.setModel(cartDtm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(800,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.home.HomePage();
		}else if(e.getSource() == sellBtn) {
			if (!cart.isEmpty()) {
				try {
					new StockController().sell(cart);
					cart.clear();
					loadCartTable();
					loadTable();
					JOptionPane.showMessageDialog(this, "Succeed");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(this, "Please Add Item to Cart!!!");
			}
		}else if(e.getSource() == addToCartBtn) {
			if(id != -1) {
				boolean istrue = false;
				int index = -1;
				for(int i = 0; i < cart.size(); i++) {
					if (cart.get(i).getStokID() == id) {
						istrue = true;
						index = i;
					}
				}
				StockModel model = null;
				try {
					model = new StockController().getSpesificStock(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (istrue) {
					int total = cart.get(index).getQuantity() + Integer.parseInt(quantityTxt.getText());
					if (model.getQuantity() < total) {
						JOptionPane.showMessageDialog(this, "The total quantity is above the stock quantity");
					}else {
						cart.get(index).setQuantity(total);						
					}
				}else {
					if (model.getQuantity() < Integer.parseInt(quantityTxt.getText())) {
						JOptionPane.showMessageDialog(this, "The total quantity is above the stock quantity");
					}else {
						try {
							cart.add(new StockController().addToCart(id, Integer.parseInt(quantityTxt.getText())));
						} catch (Exception e1) {
							e1.printStackTrace();
						}																
					}
				}
				id = -1;
				quantityTxt.setText("");
				loadCartTable();
			}else {
				JOptionPane.showMessageDialog(this, "Please Pick The Item First!!!");
			}
		}
		
	}

}
