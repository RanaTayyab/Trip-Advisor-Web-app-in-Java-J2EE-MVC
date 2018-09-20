/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;

/**
 *
 * @author Tayyab
 */
public class RegisterUser {

    
    String fullname;
    String email;
    String password;
    String usertype;
    MyDatabase mydatabase;
    
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
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
   
  

  
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// SQL

    public boolean RegisterUser() throws ClassNotFoundException, SQLException {

           String query = " insert into Register (fullname, email, password, usertype)"
                 + " values (?, ?, ?, ?)";

                    
                   Connection conn = mydatabase.getCon();
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getFullname());
                    preparedStmt.setString (2, this.getEmail());
                    preparedStmt.setString (3, this.getPassword());
                    preparedStmt.setString (4, this.getUsertype());
                   
                   // preparedStmt.setInt(2, userid);
                    preparedStmt.execute();
    return true; 

        
    }

    public boolean already(String temp) {
        

        String sql = "select email from Register";
        ResultSet rs;
		try {
			rs = mydatabase.runSql(sql);
                   //      out.println("kkk");
			while(rs.next()){
                    String email = rs.getString("email");
                    
                    if (this.email.equals(email)) {
                        return false;
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        return true; 

    }

    public boolean confirm(String code, String users) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1434;databaseName=HRMS;user=testing;password=12345678";

        Connection con = DriverManager.getConnection(connectionURL);

        String sql = "select username,securitycode from Register";

        Statement stm = con.createStatement();

        stm.executeQuery(sql);
        ResultSet rs = stm.getResultSet();

        while (rs.next()) {
            String user = rs.getString("username");
            String sec = rs.getString("securitycode");
            if (user.equals(users) && sec.equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void changeconfirm(String user) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1434;databaseName=HRMS;user=testing;password=12345678";

        Connection con = DriverManager.getConnection(connectionURL);
        String query = "update Register set confrim='" + 1 + "' where username='"
                + user + "'";
        //out.println("tayyab");

        //     pst.executeUpdate();
        //     String query ="UPDATE UserFunctions SET confiirm='"+1+"' where username='"+user+"'";
        Statement stmt = (Statement) con.createStatement();
        stmt.executeUpdate(query);
//"UPDATE users SET confirm='"+1+"' where username='"+user+"'";

    }

   public boolean  LoginUSer(String users, String pass)  throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1434;databaseName=HRMS;user=testing;password=12345678";

        Connection con = DriverManager.getConnection(connectionURL);

        String query = "select username,password,options from Register";

        Statement stm = con.createStatement();

        stm.executeQuery(query);
        ResultSet rs = stm.getResultSet();

        while (rs.next()) {
            String user = rs.getString("username");
            String sec = rs.getString("password");
            String opt=rs.getString("options");
            if (user.equals(users) && sec.equals(pass) && opt.equals("user")) {
                return true;
            }
        }
        return false; 
       
    }

   
//   public void GetUser() throws ClassNotFoundException,SQLException, InstantiationException
//    {
//            try 
//            {      
//      
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String connectionURL = "jdbc:sqlserver://localhost:1434;databaseName=HRMS;user=testing;password=12345678";
//
//                try (Connection con = DriverManager.getConnection(connectionURL)) {
//                    String query = "SELECT * FROM Register WHERE username = '"+this.getUsername()+"'";
//                    
//            try (Statement stm = con.createStatement()) {
//                stm.executeQuery(query);
//                ResultSet rs = stm.getResultSet();
//                while(rs.next())
//                {
//                    name= rs.getString("name");
//                    username = rs.getString("username");
//                    password= rs.getString("password");
//                    email = rs.getString("email");
//                    phoneno = rs.getString("phoneNo");
//                }
//            }
//                }
//                
//            } catch (SQLException ex) {Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);} 
//            
//    }

 
   
   
}
