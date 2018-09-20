/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FlightBooked {
    
    String mailis;
    String flightno;
    MyDatabase mydatabase;

    public String getMailis() {
        return mailis;
    }

    public void setMailis(String mailis) {
        this.mailis = mailis;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }
    
       public int Booked() throws SQLException{

        String sql = "select id from Register where email='"+mailis+"'";
               int userid=0;
		ResultSet rs;
		
			try {
			rs = mydatabase.runSql(sql);
 
			while(rs.next()){
                    userid = rs.getInt("id");
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
                   String query = " insert into booked (flightno, regid)"
        + " values (?, ?)";

                   Connection conn = mydatabase.getCon();
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getFlightno());
                    preparedStmt.setInt(2, userid);
                    preparedStmt.execute();

       
                    String sql2 = "select bookingid from booked where regid='"+userid+"'";
              
		ResultSet rs2;
		
			try {
			rs2 = mydatabase.runSql(sql2);
 
			while(rs2.next()){
                    userid = rs2.getInt("bookingid");
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                        
                        int seat=0;
                        
                String sql3 = "select economyseat from flight where flightno='"+this.flightno+"'";
              
		ResultSet rs3;
		
			try {
			rs3 = mydatabase.runSql(sql3);
 
			while(rs3.next()){
                    seat = rs3.getInt("economyseat");
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                       seat--;
                        
                        String query2 = " update flight set economyseat =(?) where flightno='"+this.flightno+"'";

                    conn = mydatabase.getCon();
      
                    preparedStmt = conn.prepareStatement(query2);
                    preparedStmt.setInt(1, seat);
                    preparedStmt.execute();
                        
                    
    
      return userid;
       }
       
       
       public int NoOfSeatsWebservice() throws SQLException, ClassNotFoundException
     {
         
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=ARS;user=testing;password=1234";

        Connection con = DriverManager.getConnection(connectionURL);
         
        String sql = "select economyseat from flight where approval='Yes' AND flightno='"+flightno+"' AND date='"+mailis+"'";
       Statement stm = con.createStatement();

       stm.executeQuery(sql);
        
     ResultSet rs3   = stm.getResultSet();
        int seatprice=0;
		
                try {
			
 
			while(rs3.next()){
                    seatprice = rs3.getInt("economyseat");
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                return seatprice; 
         
     }
      
}
