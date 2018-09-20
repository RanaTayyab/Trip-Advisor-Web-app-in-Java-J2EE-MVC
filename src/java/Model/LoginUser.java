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


public class LoginUser {
    
    
    MyDatabase mydatabase;
    String usertype;
    String email;
    String password;

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

      public boolean LoginUser() 
      {

        String sql = "select usertype, password, email from Register";

		ResultSet rs;
		try {
			rs = mydatabase.runSql(sql);
 
			while(rs.next()){
                    String usertype = rs.getString("usertype");
                    String password = rs.getString("password");
                    String email =rs.getString("email");
                    if (this.usertype.equals(usertype) && this.password.equals(password) && this.email.equals(email)) {
                        return true;
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        return false; 

      }
      
    public boolean LoginAdmin() 
      {

        String sql = "select usertype, password, email from Employee";

		ResultSet rs;
		try {
			rs = mydatabase.runSql(sql);
 
			while(rs.next()){
                    String usertype = rs.getString("usertype");
                    String password = rs.getString("password");
                    String email =rs.getString("email");
                    if (this.usertype.equals(usertype) && this.password.equals(password) && this.email.equals(email)) {
                        return true;
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        return false; 

      }
            
      public boolean LoginManager() 
      {

        String sql = "select usertype, password, email from Employee";

		ResultSet rs;
		try {
			rs = mydatabase.runSql(sql);
 
			while(rs.next()){
                    String usertype = rs.getString("usertype");
                    String password = rs.getString("password");
                    String email =rs.getString("email");
                    if (this.usertype.equals(usertype) && this.password.equals(password) && this.email.equals(email)) {
                        return true;
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        return false; 

      }
      
        public ResultSet ManagerApproval() throws SQLException
     {
        String sql = "select * from flight where approval in('No', 'Del')";
        
		ResultSet rs = mydatabase.runSql(sql);
                
                return rs; 
         
     }
    
    
}
