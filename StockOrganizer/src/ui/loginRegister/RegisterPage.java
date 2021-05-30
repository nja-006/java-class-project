package ui.loginRegister;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UserController;

public class RegisterPage extends JFrame implements ActionListener{
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl,usernameLbl,passwordLbl,dobLbl,emailLbl,confPassLbl,error;
	private JTextField usernameTxt,emailTxt,dayTxt,monthTxt,yearTxt;
	private JPasswordField passwordTxt,confPassTxt;
	private JButton registerBtn, backBtn;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterPage() {
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
		
		subtitleLbl = new JLabel("Register",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		error = new JLabel();
		error.setBounds(180, 5, 100, 10);
		
		usernameLbl = new JLabel("Username                 :");
		usernameLbl.setBounds(80, 30, 120, 20);
		usernameTxt = new JTextField(20);
		usernameTxt.setBounds(200, 30, 100, 20);
		
		emailLbl = new JLabel("Email                          :");
		emailLbl.setBounds(80, 60, 120, 20);
		emailTxt = new JTextField(20);
		emailTxt.setBounds(200, 60, 100, 20);
		
		passwordLbl = new JLabel("Password                 :");
		passwordLbl.setBounds(80, 90, 120, 20);
		passwordTxt = new JPasswordField(20);
		passwordTxt.setBounds(200, 90, 100, 20);
		
		confPassLbl = new JLabel("Confirm Password :");
		confPassLbl.setBounds(80, 120, 120, 20);
		confPassTxt = new JPasswordField(20);
		confPassTxt.setBounds(200, 120, 100, 20);
		
		dobLbl = new JLabel("DOB                            :");
		dobLbl.setBounds(80, 150, 120, 20);
		
		dayTxt = new JTextField(2);
		dayTxt.setBounds(200, 150, 100, 20);
		dayTxt.setSize(20, 20);
		JLabel strip1 = new JLabel("-");
		strip1.setBounds(222, 152, 10, 10);
		
		monthTxt = new JTextField(2);
		monthTxt.setBounds(230, 150, 100, 20);
		monthTxt.setSize(20, 20);
		JLabel strip2 = new JLabel("-");
		strip2.setBounds(252, 152, 10, 10);
		
		yearTxt = new JTextField(4);
		yearTxt.setBounds(260, 150, 100, 20);
		yearTxt.setSize(40, 20);
		
		centerPanel.add(usernameLbl);
		centerPanel.add(usernameTxt);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordTxt);
		centerPanel.add(emailLbl);
		centerPanel.add(emailTxt);
		centerPanel.add(confPassLbl);
		centerPanel.add(confPassTxt);
		centerPanel.add(dobLbl);
		centerPanel.add(dayTxt);
		centerPanel.add(strip1);
		centerPanel.add(monthTxt);
		centerPanel.add(strip2);
		centerPanel.add(yearTxt);
		centerPanel.add(error);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		//south panel
		southPanel = new JPanel();
		
		registerBtn = new JButton("Register");
		registerBtn.addActionListener(this);
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		
		southPanel.add(registerBtn);
		southPanel.add(backBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}

	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,320));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void setError(String error) {
		this.error.setText(error);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.loginRegister.LoginPage();
		}else if(e.getSource() == registerBtn) {
			try {
				new UserController().register(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getPassword(), confPassTxt.getPassword(), dayTxt.getText(), monthTxt.getText(), yearTxt.getText());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			this.setVisible(false);
			new ui.loginRegister.LoginPage();
		}
	}
}
