
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


public class AdminAddController extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
           // int adults = Integer.parseInt(request.getParameter("adults"));
            // int children = Integer.parseInt(request.getParameter("children"));
             String flightno = request.getParameter("flightno");
             String arrival = request.getParameter("arrival");
             String departure = request.getParameter("departure");
             String dates = request.getParameter("dates");
             String time = request.getParameter("time");
             int economyseat = Integer.parseInt(request.getParameter("economyseat"));
             int economyprice = Integer.parseInt(request.getParameter("economyprice"));
             int businessseat = Integer.parseInt(request.getParameter("businessseat"));
             int businessprice = Integer.parseInt(request.getParameter("businessprice"));
             int firstseat = Integer.parseInt(request.getParameter("firstseat"));
             int firstprice = Integer.parseInt(request.getParameter("firstprice"));
             String finaldate = "";
            PrintWriter out = response.getWriter();
           
             
           
            
            
            
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
                
                if(adminadd.flightalready(flightno))
                  {
                                    try {
                       adminadd.AddFlights();
                   } catch (SQLException ex) {
                       Logger.getLogger(AdminAddController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   }
                else
                {
                            String perror="'This Flight No. Already Exits !'";
                            request.setAttribute("perror", perror);
                            RequestDispatcher d = request.getRequestDispatcher("adminadd.jsp");
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
