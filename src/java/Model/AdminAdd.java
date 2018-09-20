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


public class AdminAdd {
             String flightno ;
             String arrival ;
             String departure;
             String finaldate;
             String time;
             int economyseat;
             int economyprice;
             int businessseat;
             int businessprice;
             int firstseat;
             int firstprice;
             MyDatabase mydatabase;

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEconomyseat() {
        return economyseat;
    }

    public void setEconomyseat(int economyseat) {
        this.economyseat = economyseat;
    }

    public int getEconomyprice() {
        return economyprice;
    }

    public void setEconomyprice(int economyprice) {
        this.economyprice = economyprice;
    }

    public int getBusinessseat() {
        return businessseat;
    }

    public void setBusinessseat(int businessseat) {
        this.businessseat = businessseat;
    }

    public int getBusinessprice() {
        return businessprice;
    }

    public void setBusinessprice(int businessprice) {
        this.businessprice = businessprice;
    }

    public int getFirstseat() {
        return firstseat;
    }

    public void setFirstseat(int firstseat) {
        this.firstseat = firstseat;
    }

    public int getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(int firstprice) {
        this.firstprice = firstprice;
    }

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }
    
    
        public boolean flightalready(String temp) {
        

        String sql = "select flightno from flight";
        ResultSet rs;
		try {
			rs = mydatabase.runSql(sql);
 
			while(rs.next()){
                    String flightno = rs.getString("flightno");
                    
                    if (this.flightno.equals(flightno)) {
                        return false;
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        return true; 

    }
    
     public boolean AddFlights() throws SQLException{
                String query = " insert into flight (flightno, arrival, departure, date, time, economyseat, economyprice, businessseat, businessprice, firstseat, firstprice, approval)"
                 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

                    String approval="No";
                   Connection conn = mydatabase.getCon();
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getFlightno());
                    preparedStmt.setString (2, this.getArrival());
                    preparedStmt.setString (3, this.getDeparture());
                    preparedStmt.setString (4, this.getFinaldate());
                    preparedStmt.setString (5, this.getTime());
                    preparedStmt.setInt(6, this.getEconomyseat());
                    preparedStmt.setInt(7, this.getEconomyprice());
                    preparedStmt.setInt(8, this.getBusinessseat());
                    preparedStmt.setInt(9, this.getBusinessprice());
                    preparedStmt.setInt(10, this.getFirstseat());
                    preparedStmt.setInt(11, this.getFirstprice());
                    preparedStmt.setString(12, approval);
                   // preparedStmt.setInt(2, userid);
                    preparedStmt.execute();
    return true;
     }
     
       public ResultSet DisplayFlights() throws SQLException
     {
        String sql = "select * from flight where approval ='Yes'";
        
		ResultSet rs = mydatabase.runSql(sql);
                
                return rs; 
         
     }
       
       
     public boolean UpdateFlights() throws SQLException{
         
         Connection conn = mydatabase.getCon();
         
         String sql = "update flight set approval ='No' where flightno='"+this.getFlightno()+"'";
         mydatabase.runSql(sql);
         
         if(!(this.getDeparture().equals("")))
         {
        String query = " update flight set departure =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getDeparture());
                   
                    preparedStmt.execute();
         }
         
         if(!(this.getArrival().equals("")))
         {
        String query = " update flight set arrival =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getArrival());
                   
                    preparedStmt.execute();
         }
         
         if(!(this.getFinaldate().equals("")))
         {
        String query = " update flight set date =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getFinaldate());
                   
                    preparedStmt.execute();
         }
         
         if(!(this.getTime().equals("")))
         {
        String query = " update flight set time =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, this.getTime());
                   
                    preparedStmt.execute();
         }
         
         if(!(this.getEconomyseat()==-1))
         {
        String query = " update flight set economyseat =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getEconomyseat());
                   
                    preparedStmt.execute();
         }
         
          if(!(this.getEconomyprice()==-1))
         {
        String query = " update flight set economyprice =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getEconomyprice());
                   
                    preparedStmt.execute();
         }
          
          if(!(this.getBusinessseat()==-1))
         {
        String query = " update flight set businessseat =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getBusinessseat());
                   
                    preparedStmt.execute();
         }
          
          if(!(this.getBusinessprice()==-1))
         {
        String query = " update flight set businessprice =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getBusinessprice());
                   
                    preparedStmt.execute();
         }
          
          if(!(this.getFirstseat()==-1))
         {
        String query = " update flight set firstseat =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getFirstseat());
                   
                    preparedStmt.execute();
         }
          
          if(!(this.getFirstprice()==-1))
         {
        String query = " update flight set firstprice =(?) where flightno='"+this.getFlightno()+"'";

                   
      
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt (1, this.getFirstprice());
                   
                    preparedStmt.execute();
         }
         
          
         
         
    return true;
     }  
     
     
}
