
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminAdd;
import Model.MyDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminUpdateController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
             String flightno ="";
             String arrival ="";
             String departure="";
             String dates="";
             String time="";
             int economyseat=-1;
             int economyprice=-1;
             int businessseat=-1;
             int businessprice=-1;
             int firstseat=-1;
             int firstprice=-1;
            
             flightno = request.getParameter("flightno");
            if (!(request.getParameter("arrival").equals(""))) { 
                 arrival = request.getParameter("arrival");
            }
            if (!(request.getParameter("departure").equals(""))) { 
                departure = request.getParameter("departure");
            }
            if (!(request.getParameter("dates").equals(""))) { 
                dates = request.getParameter("dates");
                
            }
            if (!(request.getParameter("time").equals(""))) { 
                time = request.getParameter("time");
                
            }
            if (!(request.getParameter("economyseat").equals(""))) { 
                economyseat = Integer.parseInt(request.getParameter("economyseat"));
            }
            if (!(request.getParameter("economyprice").equals(""))) { 
                economyprice = Integer.parseInt(request.getParameter("economyprice"));
            }
            if (!(request.getParameter("businessseat").equals(""))) { 
                    businessseat = Integer.parseInt(request.getParameter("businessseat"));
            }
            if (!(request.getParameter("businessprice").equals(""))) { 
                businessprice = Integer.parseInt(request.getParameter("businessprice"));
            }
            if (!(request.getParameter("firstseat").equals(""))) { 
                firstseat = Integer.parseInt(request.getParameter("firstseat"));
            }
            if (!(request.getParameter("firstprice").equals(""))) { 
                  firstprice = Integer.parseInt(request.getParameter("firstprice"));
                
            }
            
             String finaldate = "";
            PrintWriter out = response.getWriter();
           
             
            out.println(flightno);
            out.println(departure);
            out.println(arrival);
             out.println(dates);
            out.println(time);
            out.println(economyseat);
            out.println(economyprice);
            out.println(businessseat);
             out.println(businessprice);
            out.println(firstseat);
            out.println(firstprice);
            
            
            
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date selectdate = (Date) dateFormat.parse(dates);
                     finaldate = new SimpleDateFormat("dd-MM-yyyy").format(selectdate);
                    out.println(finaldate);
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
                
                AdminAdd adminadd = new AdminAdd();
                
                adminadd.setFlightno(flightno);
                adminadd.setArrival(arrival);
                adminadd.setDeparture(departure);
                adminadd.setFinaldate(finaldate);
                adminadd.setTime(time);
                adminadd.setEconomyseat(economyseat);
                adminadd.setEconomyprice(economyprice);
                adminadd.setBusinessseat(businessseat);
                adminadd.setBusinessprice(businessprice);
                adminadd.setFirstseat(firstseat);
                adminadd.setFirstprice(firstprice);
                adminadd.setMydatabase(db);
                
                ResultSet totalresult = null;
                
                 if(!(adminadd.flightalready(flightno)))
                  {
                                    try {
                       adminadd.UpdateFlights();
                   } catch (SQLException ex) {
                       Logger.getLogger(AdminAddController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   }
                else
                {
                            String perror="'This Flight No. does not Exit !'";
                            request.setAttribute("perror", perror);
                            RequestDispatcher d = request.getRequestDispatcher("adminupdate.jsp");
                            d.forward(request, response);
                }
        
         try {
           totalresult = adminadd.DisplayFlights();
        } catch (SQLException ex) {
            Logger.getLogger(AdminAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                        HttpSession session = request.getSession();
                        session.setAttribute("totalresult",totalresult);
                        response.sendRedirect("admindisplay.jsp");
        
    }

}
