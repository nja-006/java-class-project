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

import connection.Connect;
import controller.UserController;

public class LoginPage extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl,emailLbl,passwordLbl;
	private JTextField emailTxt;
	private JPasswordField passwordTxt;
	private JButton loginBtn, registerBtn;
	Connect con;

	public LoginPage(){
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
		
		subtitleLbl = new JLabel("Login",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		emailLbl = new JLabel("Email :");
		emailLbl.setBounds(120, 30, 100, 20);
		emailTxt = new JTextField(20);
		emailTxt.setBounds(190, 30, 100, 20);
		
		passwordLbl = new JLabel("Password :");
		passwordLbl.setBounds(120, 60, 100, 20);
		passwordTxt = new JPasswordField(20);
		passwordTxt.setBounds(190, 60, 100, 20);
		
		centerPanel.add(emailLbl);
		centerPanel.add(emailTxt);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordTxt);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		//south panel
		southPanel = new JPanel();
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(this);
		registerBtn = new JButton("Register");
		registerBtn.addActionListener(this);
		
		southPanel.add(loginBtn);
		southPanel.add(registerBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);

	}

	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,220));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registerBtn) {
			new ui.loginRegister.RegisterPage();
			this.setVisible(false);
		}else if(e.getSource() == loginBtn) {
			boolean isAuth = false;
			try {
				isAuth = new UserController().login(emailTxt.getText(), passwordTxt.getPassword());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}
			if (isAuth) {
				this.setVisible(false);
				new ui.home.HomePage();				
			}
		}
	}
}
