import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class transactionForm extends JInternalFrame implements ActionListener,MouseListener{
 private JLabel idLbl,nameLbl,menuNameLbl,quantityLbl,menuidLbl,totalLbl,totalPrice,menuLbl,cartLbl;
 private JTextField nameText, spText,ipText;
 private JButton addBtn,UpdateBtn,removeBtn,cancelBtn,finishBtn;
 private JSpinner quantitySpin;
 private JTable menuTable,cartTable;
 private JPanel south, north,mainPanel,center;
 private DefaultTableModel dtm,dtm2;
 private Connect con = new Connect();
   private Vector<Object> tableContent,tableHeader,quantbox,tablecontent2,price1;
   private JScrollPane sctbl,cartscroll;
   private TableModel model;
   private int quan,tblsellprice,totalAmount;
   private String menuid,tblname,tblingPrice;
   private Font big_font = new Font("Tahoma", Font.BOLD, 20);
  public transactionForm() {
   //Panel
   mainPanel = new JPanel(new BorderLayout());
   south = new JPanel(new GridLayout(2,2));
   south.setBorder(new EmptyBorder(50, 30, 50, 30));
   center = new JPanel(new GridLayout(2,2));
   center.setBorder(new EmptyBorder(50, 30, 50, 30));
   north = new JPanel(new GridLayout(3,3));
   north.setBorder(new EmptyBorder(50, 30, 50, 30));
   //Label
   idLbl = new JLabel("ID: ");
   menuidLbl = new JLabel();
   nameLbl = new JLabel("Name:");
   menuNameLbl = new JLabel();
   quantityLbl = new JLabel("Quantity");
   totalLbl = new JLabel("Total");
   totalPrice = new JLabel("0");
   menuLbl = new JLabel("Menu:");
   cartLbl = new JLabel("Cart:");
   //Button
   addBtn = new JButton("Add");
   UpdateBtn = new JButton("Update");
   removeBtn = new JButton("Remove");
   cancelBtn = new JButton("Cancel");
   finishBtn = new JButton("Finish");
   //Table
   String [] headerMenu= {"ID","Name","Sell Price"};
   dtm = new DefaultTableModel(headerMenu,0);
   menuTable = new JTable(dtm);
   menuTable.setPreferredSize(new Dimension(1200,500));
   menuTable.getColumn(headerMenu[0]).setPreferredWidth(200);
   menuTable.getColumn(headerMenu[1]).setPreferredWidth(200);
   menuTable.getColumn(headerMenu[2]).setPreferredWidth(200);
 //menuTable.getColumn(headerMenu[3]).setPreferredWidth(200);
   menuTable.addMouseListener(this);
    con.rs = con.execQuery("SELECT * FROM menu");
   
   try {
    while (con.rs.next()) {
     tableContent = new Vector<>();
//     for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
//      tableContent.add(con.rs.getObject(i)+"");
//     }
     tableContent.add(con.rs.getString(1));
     tableContent.add(con.rs.getString(2));
     tableContent.add(con.rs.getString(3));
     dtm.addRow(tableContent);
    }
   menuTable.setModel(dtm);
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   
   }
   
   String [] headerCart = {"ID","Name","Price","Quantity"};
   dtm2 = new DefaultTableModel(headerCart,0);
   cartTable= new JTable(dtm2);
   cartTable.setPreferredSize(new Dimension(1200,500));
   cartTable.getColumn(headerCart[0]).setPreferredWidth(200);
   cartTable.getColumn(headerCart[1]).setPreferredWidth(200);
   cartTable.getColumn(headerCart[2]).setPreferredWidth(200);
   cartTable.getColumn(headerCart[3]).setPreferredWidth(200);
   cartTable.addMouseListener(this);
   cartscroll = new JScrollPane(cartTable);
  
 
   
   //Spinner
   quantitySpin = new JSpinner();
   //Font
   menuLbl.setFont(big_font);
   cartLbl.setFont(big_font);
   
   
   //Component
   //North
   north.add(idLbl);
   north.add(menuidLbl);
   north.add(addBtn);
   north.add(nameLbl);
   north.add(menuNameLbl);
   north.add(UpdateBtn);
   north.add(quantityLbl);
   north.add(quantitySpin);
   north.add(removeBtn);
   //Center
   center.add(menuLbl);
   center.add(cartLbl);
   center.add(new JScrollPane(menuTable));
   center.add(new JScrollPane(cartTable));
   //South
   south.add(totalLbl);
   south.add(totalPrice);
   south.add(cancelBtn);
   south.add(finishBtn);
   
   mainPanel.add(north,BorderLayout.NORTH);
   mainPanel.add(center,BorderLayout.CENTER);
   mainPanel.add(south,BorderLayout.SOUTH);
   

   finishBtn.addActionListener(this);
   cancelBtn.addActionListener(this);
   addBtn.addActionListener(this);
   removeBtn.addActionListener(this);
   UpdateBtn.addActionListener(this);
   
   add(mainPanel);
   setEnabled(true);
   setTitle("Manage Restaurant Menu");
   setResizable(true);
   setClosable(true);
   setVisible(true);
   setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
}
  public void getSum() {
	  int sum = 0;
			  for(int i=0;i<cartTable.getRowCount();i++) {
				  sum = sum+Integer.parseInt(cartTable.getValueAt(i, 2).toString());
			  }
			  totalPrice.setText(Integer.toString(sum));
  }
  public void tableUpdate() {
	  dtm2.fireTableDataChanged();
  }

   @Override
  public void actionPerformed(ActionEvent e) {

   if (e.getSource()==addBtn) {
     
    String menuid =(String) menuTable.getValueAt(menuTable.getSelectedRow(), 0).toString();
    String tblname =(String) menuTable.getValueAt(menuTable.getSelectedRow(), 1).toString();
    int tblsellprice = Integer.parseInt (menuTable.getValueAt(menuTable.getSelectedRow(), 2).toString());
    int qty = (int) quantitySpin.getValue();
    int price = qty * tblsellprice;
     
  price1 = new Vector<>();
      if (menuTable.getSelectedRow()==-1) {
      JOptionPane.showMessageDialog(null, "Choose One Menu");
     }else if (qty<=0) {
      JOptionPane.showMessageDialog(null, "Quantity must be more than 0");
     }else {
      price1.add(menuid);
      price1.add(tblname);
      price1.add(price);
      price1.add(qty);
      dtm2.addRow(price1);
      JOptionPane.showMessageDialog(null, "Success Add Item");
//      totalPrice.setText(String.valueOf(price));
      getSum();
      tableUpdate();
      getSum();
      menuTable.getSelectionModel().clearSelection();
      cartTable.getSelectionModel().clearSelection();
     
	
    }
 }else if(e.getSource()==UpdateBtn) {
  
  
  int tblsellprice = Integer.parseInt (menuTable.getValueAt(menuTable.getSelectedRow(), 2).toString());
  int qty = (int) quantitySpin.getValue();
  int price = qty * tblsellprice;
   
     price1 = new Vector<>();
      if (cartTable.getSelectedRow()==-1) {
      JOptionPane.showMessageDialog(null, "Select Item from chart table to be updated");
      }else if (qty<=0) {
      JOptionPane.showMessageDialog(null, "Quantity must be more than 0");
      }else {
      dtm2.setValueAt(price, cartTable.getSelectedRow(), 2);
      dtm2.setValueAt(qty, cartTable.getSelectedRow(), 3);
      getSum();
      tableUpdate();
      getSum();
      JOptionPane.showMessageDialog(null, "item Updated");
      menuTable.getSelectionModel().clearSelection();
      cartTable.getSelectionModel().clearSelection();
     
   }
  
  }else if(e.getSource()==removeBtn) {
   DefaultTableModel dtm = (DefaultTableModel)cartTable.getModel();

   int selRow = cartTable.getSelectedRow();
            if(selRow != -1) {
                dtm.removeRow(selRow);
                menuTable.getSelectionModel().clearSelection();
                cartTable.getSelectionModel().clearSelection();
   getSum();
   tableUpdate();
   getSum();
   JOptionPane.showMessageDialog(null, "item Removed!");
   }else if (cartTable.getSelectedRow()==-1) {
       JOptionPane.showMessageDialog(null, "Select Item from Chart Table to be removed ");
       cartTable.getSelectionModel().clearSelection();
   } 
    
  }else if(e.getSource()==finishBtn) {
	  for(int i=0;i<cartTable.getRowCount();i++) {
		  String menuid = dtm2.getValueAt(i, 0).toString();
		  String quantity = dtm2.getValueAt(i, 3).toString();
		  con.insertIntoTransactionDetail(menuid, quantity);
	  }
	  JOptionPane.showMessageDialog(null, "Data Saved");
	  this.dispose();
   
  }else if (e.getSource()==cancelBtn){
	  this.dispose();
   }
}
  
 

  @Override
  public void mouseClicked(MouseEvent e) {
   
   if (menuTable.getSelectedRow()!=-1) {
    
     String menuid =(String) menuTable.getValueAt(menuTable.getSelectedRow(), 0);
     String tblname =(String) menuTable.getValueAt(menuTable.getSelectedRow(), 1);
     
     menuidLbl.setText(menuid);
     menuNameLbl.setText(tblname);
   
   }
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