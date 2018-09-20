/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Manager;
import Model.MyDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ManagerApproved extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
             
             String selection = "";
             
         String flightno = request.getParameter("flightno");
         
            if (request.getParameter("approve")==null) { 
                 selection = "reject";
            }
            else
            {
                selection = "approve";
            }
            
                MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
                
                Manager manager = new Manager();
                
                manager.setFlightno(flightno);
                manager.setSelection(selection);
                manager.setMydatabase(db);
        try {
            manager.ManagerHasChecked();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerApproved.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         ResultSet resultmanager = null;
                     try {
                         resultmanager = manager.ManagerApproval();
                     } catch (SQLException ex) {
                         Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     HttpSession session = request.getSession();
                      
                        session.setAttribute("resultmanager",resultmanager);
                     
                        response.sendRedirect("managerindex.jsp");
        
    }


}
