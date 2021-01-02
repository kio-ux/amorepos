import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class accountantFrame extends JFrame implements ActionListener, MenuListener{
 

 private JDesktopPane dPane;
 private JMenuBar mainBar;
 private JMenu menuAccount,menuFinance;
 private JMenuItem monthlyReport,logout; 



 public accountantFrame() {
	 dPane = new JDesktopPane();
	 mainBar = new JMenuBar();
	 menuAccount = new JMenu("Account");
	 menuFinance =  new JMenu("Finance");
	 monthlyReport = new JMenuItem("Monthly Report");
	 logout = new JMenuItem("Logout");
	 
	 menuAccount.add(logout);
	 menuFinance.add(monthlyReport);
	 
	 menuAccount.addMenuListener(this);
	 menuFinance.addMenuListener(this);
	 monthlyReport.addActionListener(this);
	 logout.addActionListener(this);
	 
	 mainBar.add(menuAccount);
	 mainBar.add(menuFinance);
	 
	 dPane.add(mainBar);
	 
	 setTitle("Amore Pos");
	 setJMenuBar(mainBar);
	 setVisible(true);
	 setExtendedState(MAXIMIZED_BOTH);
	 setLocationRelativeTo(null);
	 setResizable(false);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }


public void financeFormulir() {
	JInternalFrame ff = new JInternalFrame("Finance Form");
	JDesktopPane Pane= new JDesktopPane();
	ff.setSize(500, 500);
	ff.setVisible(true);
	ff.setMaximizable(true);
	ff.setIconifiable(true);
	ff.setClosable(true);
	ff.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	
	Pane.add(ff);
	add(Pane);
	

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
 public void actionPerformed(ActionEvent arg0) {
  

  if (arg0.getSource()== logout) {
   this.dispose();
   new LoginForm();
  }
  else if (arg0.getSource()== monthlyReport) {
   financeForm ff = new financeForm();
   ff.setVisible(true);
   add(ff);
   try {
       ff.setSelected(true);
       ff.setMaximum(true);
   } catch (java.beans.PropertyVetoException e) {
   }
	  
  }
  
  // TODO Auto-generated method stub
  
 }

}