/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MyDatabase {
 
	private Connection conn = null;
 
	public MyDatabase(String url, String database, String user_name, String password) {
		try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                         
                  String connectionURL = url+";databaseName="+database+";user="+user_name+";password="+password;
                       
			this.conn = DriverManager.getConnection(connectionURL);
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
         public Connection getCon() {
            return conn;
      }
        
	public Connection getConnection() {
		return this.conn;
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
}
