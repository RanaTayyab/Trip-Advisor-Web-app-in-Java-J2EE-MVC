
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Email;
import Model.FlightBooked;
import Model.MyDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomerFlightBooked extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
             String mailis = request.getParameter("mailis");
             String flightno = request.getParameter("flightno");
            
            MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
            //PrintWriter out = response.getWriter();
            //out.println(mailis);
            //out.println(flightno);
            
            FlightBooked flightbooked = new FlightBooked();
                 
                  flightbooked.setMydatabase(db);
                  flightbooked.setFlightno(flightno);
                  flightbooked.setMailis(mailis);
                  int bookingid =-1;
        try {
            bookingid= flightbooked.Booked();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerFlightBooked.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        Email sendemail = new Email();
        try {
            sendemail.sendRecord(mailis, flightno, bookingid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerFlightBooked.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerFlightBooked.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         response.sendRedirect("Payment.jsp");
     
    }

}
