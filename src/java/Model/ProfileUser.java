
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProfileUser {
    
      MyDatabase mydatabase;
             int adults;
             int children;
             String departure;
             String arrival;
             String selectclass;
             String finaldate;

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getSelectclass() {
        return selectclass;
    }

    public void setSelectclass(String selectclass) {
        this.selectclass = selectclass;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

     
     public ResultSet SearchFlight() throws SQLException
     {
        String sql = "select flightno,departure,arrival,date,time,economyprice from flight where approval='Yes' AND departure='"+departure+"' AND arrival='"+arrival+"' AND date='"+finaldate+"'";
        
		ResultSet rs = mydatabase.runSql(sql);
                
                return rs; 
         
     }
     
      public int SearchFlightWebservice() throws SQLException, ClassNotFoundException
     {
         
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=ARS;user=testing;password=1234";

        Connection con = DriverManager.getConnection(connectionURL);
         
        String sql = "select economyprice from flight where approval='Yes' AND departure='"+departure+"' AND arrival='"+arrival+"'";
       Statement stm = con.createStatement();

       stm.executeQuery(sql);
        
     ResultSet rs3   = stm.getResultSet();
        int seatprice=0;
		
                try {
			
 
			while(rs3.next()){
                    seatprice = rs3.getInt("economyprice");
                  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                return seatprice; 
         
     }
      
      
    
    
}
