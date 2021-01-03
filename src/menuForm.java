import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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

public class menuForm extends JInternalFrame implements ActionListener,MouseListener{
	 private JLabel idLbl,nameLbl,sellLbl,priceLbl,menuidLbl;
	 private JTextField nameText, spText,ipText;
	 private JButton insertBtn,UpdateBtn,DeleteBtn;
	 private JTable mainTable;
	 private JPanel south, north,mainPanel,center;
	 private DefaultTableModel dtm;
	 private Connect con = new Connect();
	 private Vector<Object> tableContent,tableHeader,rolebox;
	 private JScrollPane sctbl;
	 private TableModel model;
	public menuForm() {
		 mainPanel = new JPanel(new BorderLayout());
		 south = new JPanel(new FlowLayout());
		 north = new JPanel(new GridLayout(4, 3));
		 north.setBorder(new EmptyBorder(50, 30, 50, 30));
//		 pnlkosong2 = new JPanel();
		 menuidLbl = new JLabel();
		 idLbl = new JLabel("ID : ");
		 nameLbl = new JLabel("Name : ");
		 sellLbl = new JLabel("Sell Price : ");
		 priceLbl = new JLabel("Ingredient Price : ");
		 insertBtn = new JButton("Insert");
		 UpdateBtn = new JButton("Update");
		 DeleteBtn = new JButton("Delete");
		 nameText = new JTextField();
		 spText = new JTextField();
		 ipText = new JTextField();
		
		 
		 String [] header = {"ID","Name","Sell Price","Ingredient Price"};
		 dtm = new DefaultTableModel(header,0);
		 mainTable = new JTable(dtm);
		 mainTable.setPreferredSize(new Dimension(1200,500));
		 mainTable.getColumn(header[0]).setPreferredWidth(200);
		 mainTable.getColumn(header[1]).setPreferredWidth(200);
		 mainTable.getColumn(header[2]).setPreferredWidth(200);
		 mainTable.getColumn(header[3]).setPreferredWidth(200);
		 mainTable.addMouseListener(this);
		 
		 con.rs = con.execQuery("SELECT * FROM menu");
		 
		try {
			while(con.rs.next()){
				 tableContent = new Vector<Object>();
				 for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContent.add(con.rs.getObject(i)+"");
				}
				 dtm.addRow(tableContent);
			 }
			mainTable.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 north.add(idLbl);
		 north.add(menuidLbl);
		 north.add(new JPanel());
		 north.add(nameLbl);
		 north.add(nameText);
		 north.add(insertBtn);
		 north.add(sellLbl);
		 north.add(spText);
		 north.add(UpdateBtn);
		 north.add(priceLbl);
		 north.add(ipText);
		 north.add(DeleteBtn);

		 south = new JPanel(new FlowLayout());
		 south.add(new JScrollPane(mainTable));
		
		 
		  mainPanel.add(south,BorderLayout.SOUTH);
		  mainPanel.add(north, BorderLayout.NORTH);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 insertBtn.addActionListener(this);
		 DeleteBtn.addActionListener(this);
		 UpdateBtn.addActionListener(this);
		
		  add(mainPanel);
		  setEnabled(true);
		  setTitle("Manage Restaurant Menu");
		  setResizable(true);
		  setClosable(true);
		  setVisible(true);
		  setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	public boolean validateAll(String valName, String valPrice, String valIngPrice) {

		if (valName.equals("") || valPrice.equals("") || valIngPrice.equals("")) {

			return false;
		}

		return true;

	}
	public boolean validatePrice(String valPrice,String ValIngPrice) {
		
		try {
			Integer.parseInt(valPrice);
			Integer.parseInt(ValIngPrice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	public void tableUpdate() {
		  dtm.fireTableDataChanged();
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		String sName,sPrice,sIng;
		sName = nameText.getText();
		sPrice = spText.getText();
		sIng = ipText.getText();
		//INSERT
		if (e.getSource()==insertBtn) {
			if (validateAll(sName, sPrice, sIng) == false) {
				JOptionPane.showMessageDialog(null, "Fields must be filled!");
			}else if (validatePrice(sPrice, sIng)==false) {
				JOptionPane.showMessageDialog(null, "Price must be a number!");
			}else {
				con.insertIntoMenu(sName, sPrice, sIng);
				JOptionPane.showMessageDialog(null, "Insert Success!");
				tableUpdate();
			}
		}else if (e.getSource() == UpdateBtn) {
			if (validateAll(sName, sPrice, sIng) == false) {
				JOptionPane.showMessageDialog(null, "Fields must be filled!");
			}else if (validatePrice(sPrice, sIng)==false) {
				JOptionPane.showMessageDialog(null, "Price must be a number!");
			}else {

				
				//single row is selected than update
				DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
				if (mainTable.getSelectedRowCount()==1) {
				String menuid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();	
				String name = nameText.getText();
				String sellPrice = spText.getText();
				String ingPrice = ipText.getText();
				menuidLbl.setText(menuid);
				dtm.setValueAt(name, mainTable.getSelectedRow(), 1);
				dtm.setValueAt(sellPrice, mainTable.getSelectedRow(), 2);
				dtm.setValueAt(ingPrice, mainTable.getSelectedRow(), 3);
				
				con.execUpdate("UPDATE menu SET name= '"+name+"',sellprice= '"+sellPrice+"',ingredientprice= '"+ingPrice+"' WHERE menuid= '"+menuid+"'");
				tableUpdate();
				JOptionPane.showMessageDialog(null, "Update Success!");
				mainTable.getSelectionModel().clearSelection();
				}else if(mainTable.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Please select data to be updated first");
				}
			}
				
				
			
		}else {
			//DELETE DATA


			
			//single row is selected than update
			DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
			if (mainTable.getSelectedRowCount()==1) {
			String menuid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();	
			String name = nameText.getText();
			String sellPrice = spText.getText();
			String ingPrice = ipText.getText();
			menuidLbl.setText(menuid);
			dtm.setValueAt(name, mainTable.getSelectedRow(), 1);
			dtm.setValueAt(sellPrice, mainTable.getSelectedRow(), 2);
			dtm.setValueAt(ingPrice, mainTable.getSelectedRow(), 3);
			
			con.execUpdate("DELETE FROM users WHERE userid= '"+menuid+"' ");
			JOptionPane.showMessageDialog(null, "Update Success!");
			mainTable.getSelectionModel().clearSelection();
			tableUpdate();
			}else if(mainTable.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Please select data to be deleted first");
			}
		
		}
		
		}
		
		
	

	@Override
	public void mouseClicked(MouseEvent e) {

		DefaultTableModel dtm = (DefaultTableModel)mainTable.getModel();
		String menuid = dtm.getValueAt(mainTable.getSelectedRow(), 0).toString();
		String tblname = dtm.getValueAt(mainTable.getSelectedRow(), 1).toString();
		String tblsellprice = dtm.getValueAt(mainTable.getSelectedRow(), 2).toString();
		String tblingPrice = dtm.getValueAt(mainTable.getSelectedRow(), 3).toString();
		menuidLbl.setText(menuid);
		nameText.setText(tblname);;
		spText.setText(tblsellprice);
		ipText.setText(tblingPrice);
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
		// TODO Auto-generated method stub
		
	}

}
