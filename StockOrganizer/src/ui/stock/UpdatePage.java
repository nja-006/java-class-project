package ui.stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.StockController;
import model.StockModel;

public class UpdatePage extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl,nameLbl,brandLbl,quantityLbl;
	private JTextField nameTxt,brandTxt,quantityTxt;
	private JButton backBtn,updateBtn;
	private int id;
	
	public UpdatePage(int id) {
		this.id = id;
		landing();
		init();
		loadData();
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
		
		subtitleLbl = new JLabel("Update Stock",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		nameLbl = new JLabel("Name                          :");
		nameLbl.setBounds(80, 30, 120, 20);
		nameTxt = new JTextField(20);
		nameTxt.setBounds(200, 30, 100, 20);
		
		brandLbl = new JLabel("Brand                          :");
		brandLbl.setBounds(80, 60, 120, 20);
		brandTxt = new JTextField(20);
		brandTxt.setBounds(200, 60, 100, 20);
		
		quantityLbl = new JLabel("Quantity                      :");
		quantityLbl.setBounds(80, 90, 120, 20);    
		quantityTxt = new JTextField(20);
		quantityTxt.setBounds(200, 90, 100, 20);
		
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);
		centerPanel.add(brandLbl);
		centerPanel.add(brandTxt);
		centerPanel.add(quantityLbl);
		centerPanel.add(quantityTxt);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		
		//south panel
		southPanel = new JPanel();
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		southPanel.add(updateBtn);
		southPanel.add(backBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}
	
	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void loadData(){
		StockModel model = null;
		try {
			model = new StockController().getSpesificStock(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameTxt.setText(model.getStockName());
		brandTxt.setText(model.getStockBrand());
		quantityTxt.setText(Integer.toString(model.getQuantity()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.stock.AllStockPage();
		}else if(e.getSource() == updateBtn) {
			try {
				new StockController().updateStock(id, nameTxt.getText(), brandTxt.getText(), Integer.parseInt(quantityTxt.getText()));
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			
			this.setVisible(false);
			new ui.stock.AllStockPage();
		}
		
	}

}
