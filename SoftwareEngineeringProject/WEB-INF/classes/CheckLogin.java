
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// it comes here when we click the doctor and departments section in Main page-index.html;

@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //this makes to get the connectioon ;
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
        //I get the values the user enter;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        // I check the information is correct with the database;
        String logged = check(connection, login, password);
        System.out.println("check Login logged: " + logged);
        System.out.println("check Login login, password: " + login + " " + password);
        
		//i put an if to see if the login is different than null;
        if (logged != null) {
            HttpSession session = req.getSession(true);
            // if the session is true then i login, and i set the atrrbute i want;
            session.setAttribute("login", logged);
            //I go to the main page//
            res.sendRedirect("MainPrincipal");
        } else {
                        PrintWriter toClient = res.getWriter();
            //in case the login information isn't correct;
            toClient.println(Utils.header("<h3>Welcome to the doctors and departments section, before any changes please login: </h3>"));
            
            //as the login different than null it informs the user the user and password are wrong;

            if (login != null) {
              toClient.println("<h1>Login incorrect</h1><br>");
            }
            
            //I establish  the login form  i created;
            toClient.println(loginForm());
            toClient.close();
        }
    }
    
    //  I wanna get the Info about the user and the password of the database to see if it matches with the user one;
    // the constructor in DoctorData.java;
    String check(Connection connection, String login, String password) {
        String sql = "SELECT first, Last FROM Doctors";
        sql += " WHERE IDDoctors=? AND Extension=?";
        System.out.println("check Login: " + sql);
        String name = null;
        try {
            
            // here i get the info and connect with database;
            // i send the SQL parameterizedstatetement to the database;
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            
            
            // i get what i want from the database;
            ResultSet result = pstmt.executeQuery();
            
            // to complete the request;
            if(result.next()) {
                name = result.getString("first") + " " + result.getString("Last");
           }
            //then i close it;
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check login. Exception: " + e);
        }
        return name;
    }

    // i create the form where the user must introduce its user and password; 
    
    String loginForm() {
        String out = "";
        
        out += "<form action='CheckLogin'";
        out += " method='GET' name='form'>";
        out += "<Center>";
        out += "<pre>";
        out += " Doctor ID: <input NAME='login' size=' 20'>\n";
        out += "  Password: <input TYPE='password' NAME='password' size='20'>\n";
        out += " <input TYPE='SUBMIT' VALUE='Login'>";
        out += "</pre>";
        out += "</Center>";
        out += "</form>";
        
        return out;
    }
}





