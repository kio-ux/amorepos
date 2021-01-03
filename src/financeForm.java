import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

public class financeForm extends JInternalFrame implements ActionListener, MenuListener {
 
 private JLabel monthLbl, yrLbl;
 private JTextField monthTxt, yrTxt;
 private JButton viewBtn;
 private JTable table;
 private JPanel  north, mainPanel, panelCenter;
 private Vector<Object> tableContent;
 private DefaultTableModel dtm;
 private Connect con= new Connect();;
 String pattern = "yyyy-MM-dd";
 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
 
 

 public financeForm() {
  
  
//  add(new Panel());
//     pack();
  panelCenter= new JPanel(new FlowLayout());
  mainPanel = new JPanel(new BorderLayout());
  monthLbl = new JLabel("Month");
  yrLbl = new JLabel("Year");
  viewBtn= new JButton("View");
  viewBtn.addActionListener(this);
  monthTxt=new JTextField();
  yrTxt=new JTextField();
  
  
  //Center
  
 
 String [] header = {"Transaction ID","Modal","Earn","Gain"};
  dtm = new DefaultTableModel(header,0);
  table = new JTable(dtm);
  table.setPreferredSize(new Dimension(500,500));
  table.getColumn(header[0]).setPreferredWidth(200);
  table.getColumn(header[1]).setPreferredWidth(200);
  table.getColumn(header[2]).setPreferredWidth(200);
  
  con.rs=con.execQuery("SELECT transactionid, quantity * ingredientprice as 'Modal', quantity * sellprice as 'Earn', quantity*sellprice - quantity*ingredientprice  FROM transactiondetail, menu");

  
  try {
  while (con.rs.next()) {
   tableContent = new Vector<Object>();
   for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
    tableContent.add(con.rs.getObject(i)+"");
   }
   dtm.addRow(tableContent);
  }
  table.setModel(dtm);
 } catch (SQLException e) {
  e.printStackTrace();
 }
  panelCenter.add(new JScrollPane(table));
  add(panelCenter, "Center");
  panelCenter.setLayout(new GridLayout(2 , 2));
  panelCenter.setVisible(false);
  
  //NORTH
  north = new JPanel(new GridLayout(2, 3));
  north.setBorder(new EmptyBorder(50, 30, 50, 30));
  
  north.add(monthLbl);
  north.add(monthTxt);
  north.add(viewBtn);
  north.add(yrLbl);
  north.add(yrTxt);
  

  
  mainPanel.add(north, "North");
  mainPanel.add(panelCenter, "Center");
  
  add(mainPanel);
  setTitle("Financial Form");
  setSize(1000,1000);
  setResizable(false);
  setMaximizable(true);
  setClosable(true);
  setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

 }

 private void cekView() {
  String bulan = monthTxt.getText();
  String tahun = yrTxt.getText();


  
  try {
   if (monthTxt.getText().equals("")||yrTxt.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "Month and Year Tidak Boleh Kosong");   
    
    
   }else if (monthTxt.getText().equals("5") && yrTxt.getText().equals("2020")) { 
    panelCenter.setVisible(true);
   

   }else if(monthTxt.getText().equals("1")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini!");
    
   }else if(monthTxt.getText().equals("2")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("3")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("4")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("8")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("6")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("7")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("9")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("10")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("11")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(monthTxt.getText().equals("12")){
    JOptionPane.showMessageDialog(null, "Tidak ada transaksi pada bulan ini");
    
   }else if(validateNumber(bulan, tahun)==false){
    JOptionPane.showMessageDialog(null, "Month and Year must be a numeric!");
   }else {
    JOptionPane.showMessageDialog(null, "Month must be between 1-12!");
   }
   
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 
 public boolean validateNumber(String valMonth,String valYear) {
    
    try {
     Integer.parseInt(valMonth);
     Integer.parseInt(valYear);
     return true;
    } catch (Exception e) {
     // TODO: handle exception
     return false;
    }
    
    
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
 public void actionPerformed(ActionEvent e) {
  Object source = e.getSource();
  
  if (source.equals(viewBtn)) {
   cekView();
   }
  // TODO Auto-generated method stub
  
 }

}