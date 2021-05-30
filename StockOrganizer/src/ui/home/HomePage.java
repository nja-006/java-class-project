package ui.home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePage extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel;
	private JLabel titleLbl,subtitleLbl;
	private JButton seeStockBtn, addNewStockBtn, sellBtn, logoutBtn;
	public HomePage() {
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
		
		subtitleLbl = new JLabel("Home",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		seeStockBtn = new JButton("All Stock");
		seeStockBtn.setBounds(125, 20, 150, 20);
		seeStockBtn.addActionListener(this);
		
		addNewStockBtn = new JButton("Add New Stock");
		addNewStockBtn.setBounds(125, 50, 150, 20);
		addNewStockBtn.addActionListener(this);
		
		sellBtn = new JButton("Sell");
		sellBtn.setBounds(125, 80, 150, 20);
		sellBtn.addActionListener(this);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(125, 110, 150, 20);
		logoutBtn.addActionListener(this);
		
		centerPanel.add(seeStockBtn);
		centerPanel.add(addNewStockBtn);
		centerPanel.add(sellBtn);
		centerPanel.add(logoutBtn);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

	}
	
	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,270));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == seeStockBtn) {
			this.setVisible(false);
			new ui.stock.AllStockPage();
		}else if(e.getSource() == addNewStockBtn) {
			this.setVisible(false);
			new ui.stock.AddNewStock();
		}else if(e.getSource() == sellBtn) {
			this.setVisible(false);
			new ui.stock.SellPage();
		}else if(e.getSource() == logoutBtn) {
			this.setVisible(false);
			new ui.loginRegister.LoginPage();
		}
	}

}
