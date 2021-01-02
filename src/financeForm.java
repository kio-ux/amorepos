import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class financeForm extends JInternalFrame implements ActionListener, MenuListener {
 
 private JLabel monthLbl, yrLbl;
 private JTextField monthTxt, yrTxt;
 private JButton viewBtn;
 private JTable table;
 private JPanel south, north,mainPanel;

 public financeForm() {
	 
//	 add(new Panel());
//     pack();
  mainPanel = new JPanel(new BorderLayout());
	 south = new JPanel(new FlowLayout());
  monthLbl = new JLabel("Month");
  yrLbl = new JLabel("Year");
  viewBtn= new JButton("View");
  monthTxt=new JTextField();
  yrTxt=new JTextField();
  
  
  //Center
  JPanel panelCenter = new JPanel();
	add(panelCenter, "Center");
	panelCenter.setLayout(new GridLayout(2 , 2));
	
	//ROW1
	 panelCenter.add(new JPanel());
	 table = new JTable();
	 panelCenter.add(table);
//	 JTextField txtName = new JTextField();
//	 panelCenter.add(txtName);
//	 panelCenter.add(new JPanel());
//	 //ROW2
//	 panelCenter.add(new JPanel());
//	 JLabel lblUserName = new JLabel("User Name");
//	 panelCenter.add(lblUserName);
//	 JTextField txtUserName = new JTextField();
//	 panelCenter.add(txtUserName);
//	 panelCenter.add(new JPanel());
//	 //ROW3
//	 panelCenter.add(new JPanel());
//	 JLabel lblPass = new JLabel("Password");
//	 panelCenter.add(lblPass);
//	 JPasswordField txtPass = new JPasswordField();
//	 panelCenter.add(txtPass);
//	 panelCenter.add(new JPanel());
	 
  //NORTH
  north = new JPanel(new GridLayout(3, 2));
  north.add(monthLbl);
  north.add(monthTxt);
  north.add(yrLbl);
  north.add(yrTxt);
  
  //SOUTH
  south.add(viewBtn);
//  add(north,"North");
  
//  south.add(table);
//  add(south,"South");
  
  mainPanel.add(north, "North");
  mainPanel.add(panelCenter, "Center");
  mainPanel.add(south, "South");
  
  add(mainPanel);
  setTitle("Finance Report");
  setSize(1000,1000);
  setResizable(false);
  setMaximizable(true);
  setClosable(true);
//  setVisible(true);
  setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
//  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }


 @Override
 public void menuCanceled(MenuEvent arg0) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void menuDeselected(MenuEvent arg0) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void menuSelected(MenuEvent arg0) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void actionPerformed(ActionEvent arg0) {
  // TODO Auto-generated method stub
  
 }

}