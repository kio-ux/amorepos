import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;

import javax.naming.NameParser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame implements ActionListener {
	
	private JPanel mainPanel,northPanel,centerPanel,southPanel,emailPanel1,emailPanel2,passwordPanel1,passwordPanel2;
	private JLabel amoreLabel,emailLabel,passLabel;
	private JTextField emailTf;
	private JPasswordField passF;
	private JButton loginBtn;
	private Font big_font = new Font("Tahoma", Font.BOLD, 20);
	private Font medium_font = new Font("Tahoma", Font.BOLD, 12);
	private Connect con =  new Connect();
	private String roledb;
	
	
	public LoginForm() {
		
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(2,2,0,10));
		centerPanel.setBorder(new EmptyBorder(50, 30, 50, 30));
		southPanel = new JPanel();
		emailPanel1 = new JPanel();
		emailPanel2 = new JPanel();

		
		//North
		amoreLabel = new JLabel("Amore POS");
		northPanel.add(amoreLabel);
		
		//Center
		emailLabel = new JLabel("Email : ");
		emailTf = new JTextField();
		emailTf.setPreferredSize(new Dimension(200,30));
		emailPanel1.add(emailTf);
		emailPanel2.add(emailLabel);
		
		
		passLabel = new JLabel("Password : ");
		passF = new JPasswordField();
		
//		centerPanel.add(emailPanel2);
//		centerPanel.add(emailPanel1);
		
		centerPanel.add(emailLabel);
		centerPanel.add(emailTf);
		centerPanel.add(passLabel);
		centerPanel.add(passF);
		
		
		
		
		//South
		loginBtn = new JButton("Login");
		southPanel.add(loginBtn);
		loginBtn.addActionListener(this);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		
		
		
		add(mainPanel);
		
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("Amore POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		String username,password;
//		con.rs = con.execQuery("SELECT * FROM `users` WHERE email= 'admin@gmail.com' AND password = 'admin123'");
		
		
	}
	
	private void roleadmin() {
		try {
			con.rs = con.execQuery("SELECT * FROM users WHERE role = 'Admin'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void cekLogin() {
		
		try {
			if (emailTf.getText().equals("") || passF.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null, "Email and Password cannot be Empty");
				
			}else {
			String sql = "SELECT * FROM users WHERE email ='"+emailTf.getText()+"'AND password = '"+String.valueOf(passF.getPassword())
					+"'";
			con.rs = con.execQuery(sql);
//			roledb = con.rs.getString("role");
			if (con.rs.next()) {
				
				String s1 = con.rs.getString("role");
				String fn = con.rs.getString("fullname");
				if (s1.equalsIgnoreCase("Admin")) {
					this.dispose();
					JOptionPane.showMessageDialog(null, "Welcome, "+fn);
					new adminFrame();
				}
				if (s1.equalsIgnoreCase("Accountant")) {
					this.dispose();
					JOptionPane.showMessageDialog(null, "Welcome, "+fn);
					new accountantFrame();
				}
				if (s1.equalsIgnoreCase("Cashier")) {
					this.dispose();
					JOptionPane.showMessageDialog(null, "Welcome, "+fn);
					new cashierFrame();
				}
				
//				this.dispose();
//				new accountantFrame();
				
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect Email or Password");
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(loginBtn)) {
			cekLogin();
			}
		}
		
		
	}


