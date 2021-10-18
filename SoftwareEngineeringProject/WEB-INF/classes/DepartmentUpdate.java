import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class DepartmentUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		
		HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
		
        String IDDepartments = req.getParameter("IDDepartments");
        
        DepartmentData Department = new DepartmentData(
                    req.getParameter("IDDepartments"),
                    req.getParameter("name"),
                    req.getParameter("Description"));
                
        int n = DepartmentData.DepartmentUpdate1(connection, Department);
        res.sendRedirect("DepartmentView?IDDepartments=" + IDDepartments);
    }
}