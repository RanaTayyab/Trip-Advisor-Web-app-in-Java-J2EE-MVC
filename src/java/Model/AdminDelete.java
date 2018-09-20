/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDelete {
    String flightno;
    String finaldate;
    MyDatabase mydatabase;

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }
    
      public boolean DeleteFlights() throws SQLException{
          
          String sql = "update flight set approval ='Del' where flightno='"+this.getFlightno()+"'";
          mydatabase.runSql(sql);
          
               // String query = " delete from flight where flightno=(?) AND date=(?)";

                 //  Connection conn = mydatabase.getCon();
      
                   // PreparedStatement preparedStmt = conn.prepareStatement(query);
                  //  preparedStmt.setString (1, this.getFlightno());
                   
                    //preparedStmt.setString (2, this.getFinaldate());
                    
                   // preparedStmt.setInt(2, userid);
                    //preparedStmt.execute();
    return true;
     }
      
          public ResultSet DisplayFlights() throws SQLException
     {
         String sql = "select * from flight where approval ='Yes'";
        
		ResultSet rs = mydatabase.runSql(sql);
                
                return rs; 
         
     }
    
}
