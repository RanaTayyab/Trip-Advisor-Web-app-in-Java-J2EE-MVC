/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LoginUser;
import Model.MyDatabase;
import Model.ProfileUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ProfileController extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
             
             int adults = Integer.parseInt(request.getParameter("adults"));
             int children = Integer.parseInt(request.getParameter("children"));
             String departure = request.getParameter("departure");
             String arrival = request.getParameter("arrival");
             String selectclass = request.getParameter("selectclass");
             String dates = request.getParameter("dates");
             String finaldate = "";
            PrintWriter out = response.getWriter();
            out.println(adults);
            out.println(children);
            out.println(departure);
            out.println(arrival);
            out.println(selectclass);
            
            
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date selectdate = (Date) dateFormat.parse(dates);
                     finaldate = new SimpleDateFormat("dd-MM-yyyy").format(selectdate);
                    out.println(finaldate);
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
                
                ProfileUser profile = new ProfileUser();
                profile.setAdults(adults);
                profile.setChildren(children);
                profile.setDeparture(departure);
                profile.setArrival(arrival);
                profile.setSelectclass(selectclass);
                profile.setFinaldate(finaldate);
                profile.setMydatabase(db);
                
                            ResultSet rs = null;
        try {
            rs = profile.SearchFlight();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        HttpSession session = request.getSession();
                        session.setAttribute("rs",rs);
                        response.sendRedirect("DisplayFlightsClient.jsp");
                            
            
    }

 
}
