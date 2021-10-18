import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")

//mentioned in Departmentview.java;

public class DepartmentEdit extends HttpServlet {
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
		
        toClient.println(Utils.header("Department " + IDDepartments, login));
		
        toClient.println("<body>");
        DepartmentInfoEdit(toClient, connection, IDDepartments);
        toClient.println("</body>");
        toClient.close();
    }
    
    
    public static void DepartmentInfoEdit(PrintWriter toClient, Connection connection, String IDDepartments) {
    
        //method getDepartmentInfo mentioned in DepartmentData.java;
        
        DepartmentData Department = DepartmentData.getDepartmentInfo(connection, IDDepartments);  
        toClient.println("<center>");
        toClient.println("<form method='get' action='DepartmentUpdate'>");
        toClient.println("<input type='hidden' name='IDDepartments' value='" + IDDepartments + "'>");
        toClient.println("<table class='Table2'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Name</th>");
        toClient.println("<th>Description</th>");
        toClient.println("</tr>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td> <input name='name' value='" + Department.name + "'></td>");
        toClient.println("<td> <input name='Description' value='" + Department.Description + "'></td>");  
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("<div style='text-align:center;'> <button class='button button1' type='submit'> Complete Edit </button></div>");
        toClient.println("</form>");
        toClient.println("</center>");
            
    }
    
}