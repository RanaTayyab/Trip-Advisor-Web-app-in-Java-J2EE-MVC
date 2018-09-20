
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LoginUser;
import Model.MyDatabase;
import Model.RegisterUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginController extends HttpServlet {


     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
               
             String usertype = request.getParameter("usertype");
             String email = request.getParameter("email");
             String password = request.getParameter("password");
   
             
		MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
		
                  LoginUser login = new LoginUser();
                  login.setEmail(email);
                  login.setPassword(password);
                  login.setUsertype(usertype);
                  login.setMydatabase(db);
                  
                  if(usertype.equals("customer"))
                  {
                  
                 boolean check = login.LoginUser();
                 
                   if(check){
                        HttpSession session = request.getSession();
                        session.setAttribute("mail",email);
                        session.setAttribute("usertype",usertype);
                        response.sendRedirect("profile.jsp");
             }
                   else
             {
                 String error="Wrong Credentials !!!";
                 request.setAttribute("error", error);
                 RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                 d.forward(request, response);
             }
                  }
                  
                  else if(usertype.equals("admin"))
                  {
                  
                 boolean check = login.LoginAdmin();
                 
                   if(check){
                        HttpSession session = request.getSession();
                        session.setAttribute("admin",email);
                        session.setAttribute("usertype",usertype);
                        response.sendRedirect("adminindex.jsp");
             }
                   else
             {
                 String error="Wrong Credentials !!!";
                 request.setAttribute("error", error);
                 RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                 d.forward(request, response);
             }
                  }
                   
                  else if(usertype.equals("manager"))
                  {
                  
                 boolean check = login.LoginManager();
                 
                   if(check){
                       
                       ResultSet resultmanager = null;
                     try {
                         resultmanager = login.ManagerApproval();
                     } catch (SQLException ex) {
                         Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                        HttpSession session = request.getSession();
                        session.setAttribute("manager",email);
                        session.setAttribute("resultmanager",resultmanager);
                        session.setAttribute("usertype",usertype);
                        response.sendRedirect("managerindex.jsp");
             }
                   else
             {
                 String error="Wrong Credentials !!!";
                 request.setAttribute("error", error);
                 RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                 d.forward(request, response);
             }
                  }
                  
             else
             {
                 String error="Wrong Credentials !!!";
                 request.setAttribute("error", error);
                 RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                 d.forward(request, response);
             }
        
                  
             
    }   
    }