import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class MainPrincipal extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String IDDoctors = req.getParameter("id");
        HttpSession session = req.getSession(false);
        String login = null;
        if (session != null) {
            login = (String)session.getAttribute("login");
            System.out.println("logged");
            System.out.println("login: " + login);
        }
        
        toClient.println(Utils.header("<h4>Please, choose the section you will like to access:</h4>", login));
        toClient.println("<Center>");
        toClient.println("<Pre>");
        toClient.println("<form>");
        // created Drop Down list for the section;
        // onchange enable me to make as the user choose;
        
        toClient.println("<select name='Choose' onchange='Section(this.form)'>");
        
        toClient.println("<option selected>Select Section:");
        //i establish also the page where i want to go in each option;
        toClient.println("<option value='DoctorList'>View Doctor section");
        toClient.println("<option value='DepartmentList'>View Department section");
        toClient.println("</select>");
        toClient.println("</form>");
        toClient.println("</div>");
        //i mentioned my js where i created the method;
        toClient.println("<script src=SectorSelection.js></script>");
        toClient.println("</Pre>");
        toClient.println("</Center>");
        toClient.close();
  
        
    }
}