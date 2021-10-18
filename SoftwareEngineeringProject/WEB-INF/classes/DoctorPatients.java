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

//from the ajax server ViewDoctorPatientsList.js;

public class DoctorPatients extends HttpServlet {
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
        
        // here the method getDoctorPatients to see the doctor patients- DoctorPatients.java;
        
        Vector<DoctorPatientsData> DoctorPatients = DoctorPatientsData.getDoctorPatients(connection, IDDoctors);
        
        toClient.println("<h2 align='center'> Doctor patient list </h2>");
        toClient.println("<table class='Table1' id='DoctorPatients'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th> Patient ID </th>");
        toClient.println("<th> first </th>");
        toClient.println("<th> last </th>");
        toClient.println("<th> Appointment Date </th>");
        toClient.println("<th> Reason  </th>");
        toClient.println("<th> Status  </th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<DoctorPatients.size();i++) {
            DoctorPatientsData DoctorPat = DoctorPatients.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + DoctorPat.IDPatients + "</td>");
            toClient.println("<td>" + DoctorPat.Name + "</td>");
            toClient.println("<td>" + DoctorPat.LastName + "</td>");
            toClient.println("<td>" + DoctorPat.DateTime + "</td>");
            toClient.println("<td>" + DoctorPat.Reason + "</td>");
            toClient.println("<td>" + DoctorPat.Status + "</td>");
            
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}