import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class adminFrame extends JFrame implements ActionListener,MenuListener{
	
	private JDesktopPane dPane;
	private JMenuBar mainBar;
	private JMenu menuAccount,menuManage;
	private JMenuItem accounts,restaurantMenu,logout;

	public adminFrame() {
		initComponents();
		
		
	}
	
private void initComponents() {
	// TODO Auto-generated method stub
	
	mainBar = new JMenuBar();
	menuAccount = new JMenu("Account");
	menuManage =  new JMenu("Manage");
	accounts = new JMenuItem("Account");
	restaurantMenu = new JMenuItem("Restaurant Menu");
	logout = new JMenuItem("Logout");
	dPane = new JDesktopPane();
	
	menuAccount.add(logout);
	menuManage.add(accounts);
	menuManage.add(restaurantMenu);
	
	menuAccount.addMenuListener(this);
	menuManage.addMenuListener(this);
	accounts.addActionListener(this);
	restaurantMenu.addActionListener(this);
	logout.addActionListener(this);
	
	mainBar.add(menuAccount);
	mainBar.add(menuManage);
	add(mainBar);
	
	
	setJMenuBar(mainBar);
	setExtendedState(MAXIMIZED_BOTH);
	setLocationRelativeTo(null);
	setVisible(true);
	setResizable(false);
	setTitle("Amore Pos");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()== logout) {
			this.dispose();
			new LoginForm();
		}
		if (e.getSource()== accounts) {
			 accountsForm af = new accountsForm();
//			 mf.setSize(500,500);
			   af.setVisible(true);
			   add(af);
			   try {
				   af.setMaximum(true);
			       af.setSelected(true);
			   } catch (java.beans.PropertyVetoException ex) {
			   }
			   
			
			   
		}else if(e.getSource()==restaurantMenu) {
			menuForm mf = new menuForm();
			mf.setVisible(true);
			add(mf);
			try {
				mf.setMaximum(true);
				mf.setSelected(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

}
