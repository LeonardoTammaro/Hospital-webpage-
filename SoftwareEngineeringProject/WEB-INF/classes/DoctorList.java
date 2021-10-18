import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

// this i get when the view section is selected by the user- MainPrincipal.java;
public class DoctorList extends HttpServlet {
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
        
		toClient.println(Utils.header("Doctors", login));
        
        toClient.println("<center>");
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th> Doctor ID </th>");
		toClient.println("<th> First Name </th>");
		toClient.println("<th> Last Name </th>");
		toClient.println("</thead>");
		
        Vector<DoctorData> DoctorList;
        
        // am able to show the method i defined geDoctorList-i get the info of the database- DoctorData.java; 
        DoctorList= DoctorData.getDoctorList(connection);
        for(int i=0; i< DoctorList.size(); i++){
                DoctorData Doctors =DoctorList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='DoctorView?IDDoctors=" + Doctors.IDDoctors + "'>"+ Doctors.IDDoctors +"</a></td>");
                toClient.println("<td>" + Doctors.first + " </td>");
				toClient.println("<td>" + Doctors.Last + " </td>");
                toClient.println("</tr>");
        }
		
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
        toClient.println("</center><br><br>");
     
        
        
        toClient.println("<Center>");	
        
        
        // am doing this with js, then i put script ;
        //am getting each doctor had see;
        
        toClient.print("<div id='list'></div>");
        toClient.print("<script>rawData=[");
     // this method getNumOfPatientsPerDoctor i defined it in DoctorData;
        
        Vector<DoctorData> DoctorsList = DoctorData.getNumOfPatientsPerDoctor(connection);
        
        for(int i=0; i< DoctorsList.size(); i++){
            
                DoctorData DoctorPatients = DoctorsList.elementAt(i);
                			
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"IDDoctors\":\"" + DoctorPatients.IDDoctors + "\"");
                toClient.print(",\"numPatients\":" + DoctorPatients.numPatients);
                toClient.print("}");
        }
        toClient.println("]</script>");
        
        //here I insert the document where am defining the method to get the table-javascript;
        // in the js am able to get the rawData we defined early;
		toClient.println("<script src='DoctorsTop4.js'></script>");	
        toClient.println("</Center><br>");
    
        toClient.println("<a href='MainPrincipal'>Back to Principal Main </a>");
		toClient.println("</body>");
        toClient.close();
    }
}