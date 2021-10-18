import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")

// is where it goes when is click Edit info in DoctorView.java; 
public class DoctorEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    // i check the session;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
		
        String IDDoctors = req.getParameter("IDDoctors");
		
        toClient.println(Utils.header("Doctor " + IDDoctors, login));
		
        toClient.println("<body>");
        
        // i get the method i defined called DoctorInfoEdit;
        DoctorInfoEdit(toClient, connection, IDDoctors);
        toClient.println("</body>");
        toClient.close();
    }
    
    // i created a method to display a form where the user can edit any values of the Doctor info;
    
    public static void DoctorInfoEdit(PrintWriter toClient, Connection connection, String IDDoctors) {
    
        // I defined the method am using to get the doctor info- getDoctorInfo;
        DoctorData Doctor = DoctorData.getDoctorInfo(connection, IDDoctors);  
     
        
        toClient.println("<form method='get' action='DoctorUpdate'>");
        toClient.println("<input type='hidden' name='IDDoctors' value='" + IDDoctors + "'>");
        toClient.println("<table class='Table2'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>First Name</th>");
        toClient.println("<th>Last Name</th>");
        toClient.println("<th>Mail</th>");
        toClient.println("<th>Extension</th>");
        toClient.println("<th>Start Day</th>");
        toClient.println("<th>Phone</th>");
        toClient.println("</tr>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td> <input name='first' value='" + Doctor.first + "'></td>");
        toClient.println("<td> <input name='Last' value='" + Doctor.Last + "'></td>");  
        toClient.println("<td> <input name='email' value='" + Doctor.email + "'></td>");  
        toClient.println("<td> <input name='Extension' value='" + Doctor.Extension + "'></td>");  
        toClient.println("<td> <input name='StartDay' value='" + Doctor.StartDay + "'></td>"); 
        toClient.println("<td> <input name='phone' value='" + Doctor.phone + "'></td>"); 
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("<div style='text-align:center;'> <button class='button button1' type='submit'> Complete Edit </button></div>");
        toClient.println("</form>");
            
    }
    
}