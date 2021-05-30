package ui.stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.StockController;

public class addStockPage extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl,quantityLbl;
	private JTextField quantityTxt;
	private JButton backBtn,addBtn;
	private int id;
	public addStockPage(int id) {
		this.id = id;
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
		
		subtitleLbl = new JLabel("Add Stock",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		quantityLbl = new JLabel("Quantity                      :");
		quantityLbl.setBounds(80, 30, 120, 20);    
		quantityTxt = new JTextField(20);
		quantityTxt.setBounds(200, 30, 100, 20);
		
		centerPanel.add(quantityLbl);
		centerPanel.add(quantityTxt);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		
		//south panel
		southPanel = new JPanel();
		addBtn = new JButton("Add");
		addBtn.addActionListener(this);
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		southPanel.add(addBtn);
		southPanel.add(backBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}
	
	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.stock.AllStockPage();
		}else if(e.getSource() == addBtn) {
			boolean isconfirmed = false;
			try {
				isconfirmed = new StockController().addStock(id, Integer.parseInt(quantityTxt.getText()));
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			if (isconfirmed) {
				this.setVisible(false);
				new ui.stock.AllStockPage();
			}else {
				JOptionPane.showMessageDialog(this, "Please input the quantity more than 0");
			}
		}
		
	}

}
