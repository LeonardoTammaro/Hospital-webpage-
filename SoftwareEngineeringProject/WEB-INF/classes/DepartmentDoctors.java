import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@SuppressWarnings("serial")
//mentioned in ViewDepartmentDoctorsList.java;
public class DepartmentDoctors extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String IDDepartments = req.getParameter("IDDepartments");
        PrintWriter toClient = res.getWriter();
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        
        Vector<DepartmentDoctorsData> DepartmentDoctors = 
            
        //method getDepartmentDoctors mentioned DepartmentDoctorsData.java;
            
        DepartmentDoctorsData.getDepartmentDoctors(connection, IDDepartments);
        
        toClient.println("<h2 align='center'> Department Doctors List </h2>");
        toClient.println("<table class='Table1' id='DepartmentDoctors'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Doctor ID</th>");
        toClient.println("<th> First Name </th>");
        toClient.println("<th> Last Name </th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<DepartmentDoctors.size();i++) {
            DepartmentDoctorsData DepartmentDoc = DepartmentDoctors.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + DepartmentDoc.IDDoctors+ "</td>");
            toClient.println("<td>" + DepartmentDoc.first + "</td>");
            toClient.println("<td>" + DepartmentDoc.Last + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}