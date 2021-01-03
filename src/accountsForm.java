import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableStringConverter;

public class accountsForm extends JInternalFrame implements ActionListener,MouseListener {
	
	 private JLabel idLbl,fnLbl,roleLbl,emailLbl,passLbl,useridLbl;
	 private JTextField fnText, emailText,passText;
	 private JButton insertBtn,UpdateBtn,DeleteBtn;
	 private JComboBox<String> roleBox;
	 private JTable mainTable;
	 private JPanel south, north,mainPanel,center;
	 private DefaultTableModel dtm;
	 private Connect con = new Connect();
	 private Vector<Object> tableContent,tableHeader,rolebox;
	 private JScrollPane sctbl;
	 private TableModel model;
	 
	public accountsForm() {
		 mainPanel = new JPanel(new BorderLayout());
		 south = new JPanel(new FlowLayout());
		 north = new JPanel(new GridLayout(5, 3));
		 north.setBorder(new EmptyBorder(50, 30, 50, 30));
//		 pnlkosong2 = new JPanel();
		 idLbl = new JLabel("ID : ");
		 fnLbl = new JLabel("Fullname : ");
		 roleLbl = new JLabel("Role : ");
		 emailLbl = new JLabel("Email : ");
		 passLbl = new JLabel("Password : ");
		 useridLbl = new JLabel();
		 insertBtn = new JButton("Insert");
		 UpdateBtn = new JButton("Update");
		 DeleteBtn = new JButton("Delete");
		 fnText = new JTextField();
		 emailText = new JTextField();
		 passText = new JTextField();
		 roleBox = new JComboBox<>();
		 
		 
		 
		 
		 String [] header = {"ID","Fullname","Role","Email","Password"};
		 dtm = new DefaultTableModel(header,0);
		 mainTable = new JTable(dtm);
		 mainTable.setPreferredSize(new Dimension(1200,500));
		 mainTable.getColumn(header[0]).setPreferredWidth(200);
		 mainTable.getColumn(header[1]).setPreferredWidth(200);
		 mainTable.getColumn(header[2]).setPreferredWidth(200);
		 mainTable.getColumn(header[3]).setPreferredWidth(200);
		 mainTable.getColumn(header[4]).setPreferredWidth(200);
		 mainTable.addMouseListener(this);
		 mainTable.setRowSelectionAllowed(true);
		 
		 con.rs = con.execQuery("Select * FROM users");
		 
		 try {
			while (con.rs.next()) {
				roleBox.addItem(con.rs.getString(3));
				tableContent = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContent.add(con.rs.getObject(i)+"");
				}
				dtm.addRow(tableContent);
			}
			mainTable.setModel(dtm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 //North
		 north.add(idLbl);
		 north.add(useridLbl);
		 north.add(new JPanel());
		 north.add(fnLbl);
		 north.add(fnText);
		 north.add(insertBtn);
		 north.add(roleLbl);
		 north.add(roleBox);
		 north.add(UpdateBtn);
		 north.add(emailLbl);
		 north.add(emailText);
		 north.add(DeleteBtn);
		 north.add(passLbl);
		 north.add(passText);
		 north.add(new JPanel());
//		 north.add(mainTable);
		 
		 insertBtn.addActionListener(this);
		 DeleteBtn.addActionListener(this);
		 UpdateBtn.addActionListener(this);
		 
		 
		 
		 
	 
		 //Center
	  center = new JPanel(new FlowLayout());
	  center.add(new JScrollPane(mainTable));
//	  south.add(mainTable);
//	  add(south,"South");
	  
	  mainPanel.add(center,BorderLayout.CENTER);
	  mainPanel.add(north, BorderLayout.NORTH);
//	  mainPanel.add(south, BorderLayout.SOUTH);
	  
	  add(mainPanel);
	  setEnabled(true);
	  setTitle("Manage Accounts");
	  setResizable(true);
	  setClosable(true);
	  setVisible(true);
	  setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	
	public String randomNum() {

		  Random rand = new Random();
		  String karakter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		  String userID = "";

		  for (int i = 0; i < 10; i++) {
		   Integer index = rand.nextInt(karakter.length());
		   char x = karakter.charAt(index);
		   userID += x;
		  }

		  return userID;

		 }
	public boolean validateAll(String valName, String valEmail, String valPass) {

		if (valName.equals("") || valEmail.equals("") || valPass.equals("")) {

			return false;
		}

		return true;

	}

	public boolean validateEmail(String valEmail) {

		int b;
		char a;

		if (valEmail.startsWith("@") || valEmail.endsWith("@") || valEmail.endsWith(".")) {

			return false;

		}

		else {
			b = 0;
			String c = "";
			for (int i = 0; i < valEmail.length(); i++) {
				a = valEmail.charAt(i);

				if (a == '@') {
					b++;
					if (a == valEmail.charAt(i - 1) || a == valEmail.charAt(i + 1)) {
						c = "false";
					}

				}
			}

			if (b != 1 || c.equals("false")) {
				return false;
			}
		}

		return true;

	}

	public boolean validatePass(String valPass) {

		if (!valPass.matches("[a-zA-Z0-9]+")) {
			return false;
		}
		return true;

	}
	public void tableUpdate() {
		  dtm.fireTableDataChanged();
	  }


	@Override
	public void actionPerformed(ActionEvent e) {
			//Insert data
			if (e.getSource() == insertBtn) {
				if (validateAll(fnText.getText(), emailText.getText(), passText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "All Fields Must Be Filled!");
				}

				else if (validateEmail(emailText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Wrong Email Format!");
				}

				else if (validatePass(passText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Password Must Be Alphanumeric");
				}

				else {
//					String id = randomNum();
//					con.insertIntousersID();
					con.insertIntoUsers(fnText.getText(), roleBox.getSelectedItem(), emailText.getText(), passText.getText());
					JOptionPane.showMessageDialog(null, "Insert Success!");
					tableUpdate();

					
				}
			}else if (e.getSource()==UpdateBtn) {
			//Update Data

				if (validateAll(fnText.getText(), emailText.getText(), passText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "All Fields Must Be Filled!");
				}

				else if (validateEmail(emailText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Wrong Email Format!");
				}

				else if (validatePass(passText.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Password Must Be Alphanumeric");
				}

				else {
					
					//single row is selected than update
					DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
					if (mainTable.getSelectedRowCount()==1) {
					String userid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();	
					String fName = fnText.getText();
					Object role = roleBox.getSelectedItem();
					String email = emailText.getText();
					String passw = passText.getText();
					useridLbl.setText(userid);
					dtm.setValueAt(fName, mainTable.getSelectedRow(), 1);
					dtm.setValueAt(role, mainTable.getSelectedRow(), 2);
					dtm.setValueAt(email, mainTable.getSelectedRow(), 3);
					dtm.setValueAt(passw, mainTable.getSelectedRow(), 4);
					
					con.execUpdate("UPDATE users SET fullname= '"+fnText.getText()+"', role= '"+roleBox.getSelectedItem()+"',email= '"+emailText.getText()+"',password= '"+passText.getText()+"' WHERE userid= '"+userid+"' ");
					JOptionPane.showMessageDialog(null, "Update Success!");
					tableUpdate();
					mainTable.getSelectionModel().clearSelection();
					}else if(mainTable.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Please select data to be updated first");
					}
					
					
					
				}
			
				
				
				
				
			
		}else {
			//Delete Data
//			con.execUpdate("DELETE FROM users ");
			//single row is selected than update
			DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
			if (mainTable.getSelectedRowCount()==1) {
			String userid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();	
			String fName = fnText.getText();
			Object role = roleBox.getSelectedItem();
			String email = emailText.getText();
			String passw = passText.getText();
			useridLbl.setText(userid);
			dtm.setValueAt(fName, mainTable.getSelectedRow(), 1);
			dtm.setValueAt(role, mainTable.getSelectedRow(), 2);
			dtm.setValueAt(email, mainTable.getSelectedRow(), 3);
			dtm.setValueAt(passw, mainTable.getSelectedRow(), 4);
			
			con.execUpdate("DELETE FROM users WHERE userid= '"+userid+"' ");
			JOptionPane.showMessageDialog(null, "Update Success!");
			tableUpdate();
			mainTable.getSelectionModel().clearSelection();
			}else if(mainTable.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Please select data to be deleted first");
			}
			
			
			
		}
		}
		
		
	

	@Override
	public void mouseClicked(MouseEvent e) {
//		int selectedrow = mainTable.getSelectedRow();
		DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
		String userid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();
		String tblFn = dtm.getValueAt(mainTable.getSelectedRow(), 1).toString();
		String tblrole = dtm.getValueAt(mainTable.getSelectedRow(), 2).toString();
		String tblEmail = dtm.getValueAt(mainTable.getSelectedRow(), 3).toString();
		String tblPassword = dtm.getValueAt(mainTable.getSelectedRow(), 4).toString();
		
		useridLbl.setText(userid);
		fnText.setText(tblFn);;
		roleBox.setSelectedItem(tblrole);
		emailText.setText(tblEmail);
		passText.setText(tblPassword);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
