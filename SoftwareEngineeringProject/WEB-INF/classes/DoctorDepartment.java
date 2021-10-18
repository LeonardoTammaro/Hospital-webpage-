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

//I get this from ViewDoctorDepartmentList.js;
public class DoctorDepartment extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String IDDoctors = req.getParameter("IDDoctors");
        PrintWriter toClient = res.getWriter();
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        
        Vector<DoctorDepartmentData> DoctorDepartment = 
            
         //this method getDoctorDepartments make to get the department in which is in-DoctorDepartmentData.java; 
        DoctorDepartmentData.getDoctorDepartments(connection, IDDoctors);
        
        toClient.println("<h2 align='center'> Doctor Department's </h2>");
        toClient.println("<table class='Table1' id='DoctorDepartment'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Department ID</th>");
        toClient.println("<th> Name </th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<DoctorDepartment.size();i++) {
            DoctorDepartmentData DoctorDep = DoctorDepartment.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + DoctorDep.IDDepartments + "</td>");
            toClient.println("<td>" + DoctorDep.name + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}