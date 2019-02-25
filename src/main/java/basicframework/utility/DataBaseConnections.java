package basicframework.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnections {
	
	public static void dbConnection() throws ClassNotFoundException, SQLException{
		//dummy approach to connect database and fetch records
		
		// step 1 load the driver class
		// for oracle
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step 2 create connection object
		Connection con= DriverManager.getConnection(
				"jdbc:oracle:thin:@hosturl:portNumber:dataBaseName","dataBaseuserId","dataBasepassword");
				
		// step 3 create statement Object/ create query
		
		PreparedStatement stmt = con.prepareStatement("UPDATE table set column1=?, column2=?, where column3=?");
		stmt.setString(1, "column1Value");
		stmt.setString(2, "column2Value");
		stmt.setString(3, "column3Value");
		
		// step 4 Execute Query and fetch output from database
		stmt.executeQuery();
		
		
		// close connection 
		
		con.close();
		
	}

}
