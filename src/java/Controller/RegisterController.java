
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Email;
import Model.MyDatabase;
import Model.RegisterUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterController extends HttpServlet {

   

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
                
             String fullname = request.getParameter("fullname");
             String email = request.getParameter("email");
             String password = request.getParameter("password");
             String confrimpassword = request.getParameter("confirmpassword");
             String usertype = "customer";
             Email sendemail = new Email();
             
             MyDatabase db = (MyDatabase) getServletContext().getAttribute("db");
             
             if(password.equals(confrimpassword))
             {
             RegisterUser register = new RegisterUser();
             register.setEmail(email);
             register.setFullname(fullname);
             register.setMydatabase(db);
             register.setPassword(password);
             register.setUsertype(usertype);
             
              Random rand=new Random();
             int n = rand.nextInt(9999) + 1000;
         
            String code = Integer.toString(n);
                 try {
                     if(register.already(email))
                     {
                         register.RegisterUser();
                         sendemail.send_email(email, code, fullname);
                         
                         response.sendRedirect("login.jsp");
                         
                     }
                 
                     else
                     {
                            String perror="User Already Exits !";
                            request.setAttribute("perror", perror);
                            RequestDispatcher d = request.getRequestDispatcher("signup.jsp");
                            d.forward(request, response);
                     }
                 
                 
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             
             }
             else
             {
                 String perror="Type Password Again Carefully !";
                 request.setAttribute("perror", perror);
                 RequestDispatcher d = request.getRequestDispatcher("signup.jsp");
                 d.forward(request, response);
             }
            
             
             
           }  
}
