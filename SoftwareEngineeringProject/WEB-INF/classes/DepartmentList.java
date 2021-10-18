import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

//i mentioned from MainPrincipal.java;
public class DepartmentList extends HttpServlet {
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
        
		toClient.println(Utils.header("Departments", login));
        
        toClient.println("<center>");
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th> Department ID </th>");
		toClient.println("<th>  Name </th>");
		toClient.println("</thead>");
		
        Vector<DepartmentData> DepartmentList;
        //the constructor is in DepartmentData.java;
        DepartmentList= DepartmentData.getDepartmentList(connection);
        for(int i=0; i< DepartmentList.size(); i++){
                DepartmentData Departments =DepartmentList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='DepartmentView?IDDepartments=" + Departments.IDDepartments + "'>"+ Departments.IDDepartments +"</a></td>");
                toClient.println("<td>" + Departments.name + " </td>");
                toClient.println("</tr>");
        }
		
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
        toClient.println("</center><br><br>");
    
        
             toClient.println("<Center>");	
        //obtengo departamentos y numero de doctores 
        toClient.print("<div id='list1'></div>");
        toClient.print("<script>rawData=[");
     
        Vector<DepartmentData> DepartmentsList = 
            //the constructor is in DepartmentData.java;
        DepartmentData.getNumOfDoctorsPerDepartment(connection);
        
        for(int i=0; i< DepartmentsList.size(); i++){
            
                DepartmentData DepartmentDoctor= DepartmentsList.elementAt(i);
                			
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"IDDepartments\":\"" + DepartmentDoctor.IDDepartments + "\"");
                toClient.print(",\"NumDoctors\":" + DepartmentDoctor.NumDoctors);
                toClient.print("}");
        }
        toClient.println("]</script>");
        
    
		toClient.println("<script src='TotalDoctorDepartment.js'></script>");	
        toClient.println("</Center><br>");	
		toClient.println("<a href='MainPrincipal'>Back to Principal Main </a>");
		toClient.println("</body>");
        toClient.close();
    }
}