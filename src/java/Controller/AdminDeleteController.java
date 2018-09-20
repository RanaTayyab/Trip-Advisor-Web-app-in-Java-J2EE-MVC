
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDelete;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String flightno = request.getParameter("flightno");
        String dates = request.getParameter("dates");
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
                
                 ResultSet totalresult = null;
                MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
        
                AdminDelete admindelete = new AdminDelete();
                admindelete.setFlightno(flightno);
                admindelete.setFinaldate(finaldate);
                admindelete.setMydatabase(db);
        try {
            admindelete.DeleteFlights();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
           totalresult = admindelete.DisplayFlights();
        } catch (SQLException ex) {
            Logger.getLogger(AdminAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                        HttpSession session = request.getSession();
                        session.setAttribute("totalresult",totalresult);
                        response.sendRedirect("admindisplay.jsp");
    }

}
