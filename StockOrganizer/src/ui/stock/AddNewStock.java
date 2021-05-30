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

public class AddNewStock extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl,nameLbl,brandLbl,quantityLbl;
	private JTextField nameTxt,brandTxt,quantityTxt;
	private JButton backBtn,addNewBtn;
	
	public AddNewStock() {
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
		
		subtitleLbl = new JLabel("Add New Stock",SwingConstants.CENTER);
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
		addNewBtn = new JButton("Add New");
		addNewBtn.addActionListener(this);
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		southPanel.add(addNewBtn);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.home.HomePage();
		}else if(e.getSource() == addNewBtn) {
			try {
				new StockController().AddNewStock(nameTxt.getText(), Integer.parseInt(quantityTxt.getText()), brandTxt.getText());
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			JOptionPane.showMessageDialog(this, "Input Item Succeed");
			nameTxt.setText("");
			brandTxt.setText("");
			quantityTxt.setText("");
		}
		
	}

}
