import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")

// this is where it goes when we click on complete edit- DoctorEdit.java;
public class DoctorUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    // 
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
		
        String IDDoctors = req.getParameter("IDDoctors");
        
        //i create constructor for this, where i get the parameters the user enter-DoctorData.java;
        DoctorData Doctor = new DoctorData(
                    req.getParameter("IDDoctors"),
                    req.getParameter("first"),
                    req.getParameter("Last"),
                    req.getParameter("email"),
                    req.getParameter("Extension"),
                    req.getParameter("StartDay"),
                    req.getParameter("phone"));
        
        // i defined the method where am getting the info of the database- DoctorUpdate1 DoctorData.java; 
        int n = DoctorData.DoctorUpdate1(connection, Doctor);
        res.sendRedirect("DoctorView?IDDoctors=" + IDDoctors);
    }
}