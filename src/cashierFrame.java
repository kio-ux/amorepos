import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class cashierFrame extends JFrame implements ActionListener,MenuListener {
	private JDesktopPane dPane;
	private JMenuBar mainBar;
	private JMenu menuAccount,menuTrans;
	private JMenuItem createTrans,logout;
	public cashierFrame() {
		initComponents();
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		
		mainBar = new JMenuBar();
		menuAccount = new JMenu("Account");
		menuTrans =  new JMenu("Transaction");
		createTrans = new JMenuItem("Create Transaction");
		logout = new JMenuItem("Logout");
		dPane = new JDesktopPane();
		
		menuAccount.add(logout);
		menuTrans.add(createTrans);
		
		menuAccount.addMenuListener(this);
		menuTrans.addMenuListener(this);
		createTrans.addActionListener(this);
		logout.addActionListener(this);
		
		mainBar.add(menuAccount);
		mainBar.add(menuTrans);
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
		if (e.getSource()==createTrans) {
			transactionForm tf = new transactionForm();
			tf.setVisible(true);
			add(tf);
			try {
				tf.setMaximum(true);
				tf.setSelected(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

}
