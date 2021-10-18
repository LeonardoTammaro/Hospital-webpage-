import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

//it is from the utils.java;

public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
        // function down returns the current session if session exists and if there's no valid session, a new session will not be created, it will return nul
       HttpSession session = req.getSession(false);
        
        // then if the session is still active i want to invalidate to;
        //go back to Login form;
        
        if (session != null) {
            session.invalidate();
            res.sendRedirect("CheckLogin");
        }
        
    }
    
}