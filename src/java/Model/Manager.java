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


public class Manager {
    
    String flightno;
    String selection;
    MyDatabase mydatabase;

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public MyDatabase getMydatabase() {
        return mydatabase;
    }

    public void setMydatabase(MyDatabase mydatabase) {
        this.mydatabase = mydatabase;
    }
    
    public boolean ManagerHasChecked() throws SQLException
    {
         String Checksql = "select approval from flight where flightno='"+this.getFlightno()+"'";
         String approving ="";
         String checking="";
		ResultSet rs;
		try {
			rs = mydatabase.runSql(Checksql);
 
			while(rs.next()){
                    checking =rs.getString("approval");
                    if (checking.equals("No")) {
                        approving="No";
            }
                    else if (checking.equals("Del")) {
                        approving="Del";
            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
        String sql="";
        if(approving.equals("No"))
        {
        
       if(this.selection.equals("approve"))
       {
         sql = " update flight set approval ='Yes' where flightno='"+this.getFlightno()+"'";
       }
       else
       {
           sql= "delete from flight where flightno='"+this.getFlightno()+"'";
       }
       }
        else if(approving.equals("Del"))
        {
            if(this.selection.equals("reject"))
       {
         sql = " update flight set approval ='Yes' where flightno='"+this.getFlightno()+"'";
       }
       else
       {
           sql= "delete from flight where flightno='"+this.getFlightno()+"'";
       }
        }
        mydatabase.runSql(sql);
         
        
        return true;
    }
    
   
    
    
         public ResultSet ManagerApproval() throws SQLException
     {
        String sql = "select * from flight where approval in('No', 'Del')";
        
		ResultSet rs = mydatabase.runSql(sql);
                
                return rs; 
         
     }
    
}
