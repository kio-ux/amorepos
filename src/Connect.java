import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Connect {
	public Statement st;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	public Connection con;
	public PreparedStatement pst;
	
	
	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amorepos","root","");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect the database,the system is terminated!");
			System.exit(0);
		}
	}
public ResultSet execQuery(String query) {
		
		try {
			rs=st.executeQuery(query);
			rsm= rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
public void insertIntoTransactionDetail(String menuid,String quantity) {
	 Random rand = new Random();
	  String karakter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  String userID = "";

	  for (int i = 0; i < 10; i++) {
	   Integer index = rand.nextInt(karakter.length());
	   char x = karakter.charAt(index);
	   userID += x;
	  }
	  try {
		pst = con.prepareStatement("INSERT INTO transactiondetail VALUES (?,?,?)");
		pst.setString(1, userID);
		pst.setString(2, menuid);
		pst.setString(3, quantity);
		pst.execute();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
	
}
public void insertIntoUsers( String fullname , Object role, String email,String password) {
	  Random rand = new Random();
	  String karakter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  String userID = "";

	  for (int i = 0; i < 10; i++) {
	   Integer index = rand.nextInt(karakter.length());
	   char x = karakter.charAt(index);
	   userID += x;
	  }
	try {
		pst = con.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?)");
		pst.setString(1,userID);
		pst.setString(2, fullname);
		pst.setObject(3, role);
		pst.setString(4, email);
		pst.setString(5, password);
		pst.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void execUpdate(String query) {
	try {
		st.executeUpdate(query);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void insertIntoMenu(String name,String sellprice,String ingredientprice) {

	Random rand = new Random();
	  String karakter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  String menuID = "";

	  for (int i = 0; i < 10; i++) {
	   Integer index = rand.nextInt(karakter.length());
	   char x = karakter.charAt(index);
	   menuID += x;
	  }
	try {
		pst = con.prepareStatement("INSERT INTO menu VALUES (?,?,?,?)");
		pst.setString(1,menuID);
		pst.setString(2, name);
		pst.setObject(3, sellprice);
		pst.setString(4, ingredientprice);
		pst.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

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

	

}
