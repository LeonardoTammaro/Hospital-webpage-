import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class DepartmentView extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

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
		
        String IDDepartments = req.getParameter("IDDepartments");
		
        toClient.println(Utils.header("Department Identification " + IDDepartments, login));
		
        toClient.println("<body>");
        DepartmentInformation(toClient, connection, IDDepartments);
        DepartmentDoctors(toClient, connection, IDDepartments);
        
        toClient.println("<a href='DepartmentList'>Back to Department Section main page</a>");
        toClient.println("</body>");
        toClient.close();
    }
    
    
    public static void DepartmentInformation(PrintWriter toClient, Connection connection, String IDDepartments) {
        
        //method getDepartmentInfo it is mentioned in DepartmentData.java;
        DepartmentData Department = DepartmentData.getDepartmentInfo(connection, IDDepartments);  
        toClient.println("<center>"); 
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th> Name </th>");
        toClient.println("<th> Description </th>");
        toClient.println("</tr>");
		toClient.println("</thead>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td>" + Department.name + "     " + "</td>");
        toClient.println("<td>" + Department.Description   + "</td>");   
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("</center><br>"); 
        //this goes to DepartmentEdit.java;
        toClient.println("<form method='get' action='DepartmentEdit'>");
        toClient.println("<input type='hidden' name='IDDepartments' value='" + IDDepartments + "'>");
        toClient.println("<div style='text-align:center;'> <button class='button button1' type='submit'> Edit Department information </button></div>");
        toClient.println("</form><br>");
        
    }
    
    public static void DepartmentDoctors(PrintWriter toClient, Connection connection, String IDDepartments) {
		toClient.println("<br><input type='hidden' id='DepartmentId' value='" + IDDepartments + "'> </input>");
        toClient.println("<div align='center' id='DepartmentDoctors'></div>");
        toClient.println("<div style='text-align:center;'>");
        toClient.println("<button class='button button1' id='button3' type='submit' onclick='viewDepartmentDoctors()'>View Department Doctor's list </button>");
        toClient.println("</div>");
		toClient.println("<script src=ViewDepartmentDoctorsList.js></script><br><br>");
    }    

    }