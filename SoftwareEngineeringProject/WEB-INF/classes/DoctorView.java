import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")

//I get from clickng on doctor id DoctorList.java;

public class DoctorView extends HttpServlet {
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
		
        //i want to get the parameter ID Doctor;
        
        String IDDoctors = req.getParameter("IDDoctors");
		
        toClient.println(Utils.header("Doctor Identification " + IDDoctors, login));
		
        toClient.println("<body>");
        //display all the method defined down;
        DoctorInformation(toClient, connection, IDDoctors);
        DoctorSpecialtyData(toClient, connection, IDDoctors);
        DoctorDepartment(toClient, connection, IDDoctors);
        DoctorPatients(toClient, connection, IDDoctors);
        
        toClient.println("<a href='DoctorList'>Back to Doctor Section main page</a>");
        toClient.println("</body>");
        toClient.close();
    }
    
    //this method for creating the Doctor general information;
    public static void DoctorInformation(PrintWriter toClient, Connection connection, String IDDoctors) {
        
        // to get the info i used the method getDoctorInfo- DoctorData.java;
        
        DoctorData Doctor = DoctorData.getDoctorInfo(connection, IDDoctors);  
        toClient.println("<center>"); 
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>First Name</th>");
        toClient.println("<th>Last Name</th>");
        toClient.println("<th>Mail</th>");
        toClient.println("<th>Extension</th>");
        toClient.println("<th>StartDay</th>");
        toClient.println("<th>Phone</th>");
        toClient.println("</tr>");
		toClient.println("</thead>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td>" + Doctor.first + "</td>");
        toClient.println("<td>" + Doctor.Last + "</td>");   
        toClient.println("<td>" + Doctor.email + "</td>");
        toClient.println("<td>" + Doctor.Extension + "</td>");
        toClient.println("<td>" + Doctor.StartDay + "</td>");
         toClient.println("<td>" + Doctor.phone + "</td>");
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("</center><br>"); 
        //here i insert a form- DoctorEdit.java;
        toClient.println("<form method='get' action='DoctorEdit'>");
        //as i dont wanna show the IDDoctors the type is hidden;
        toClient.println("<input type='hidden' name='IDDoctors' value='" + IDDoctors + "'>");
        toClient.println("<div style='text-align:center;'> <button class='button button1' type='submit'> Edit Doctor information </button></div>");
        toClient.println("</form><br>");
        
    }
    
    //this method i get the doctor specialty;
    
    public static void DoctorSpecialtyData(PrintWriter toClient, Connection connection, String IDDoctors) {
        
		Vector<DoctorSpecialtyData> DoctorSpecialty = 
            
        //this method getDoctorSpecialty i get the specialty of the doctor i selected -DoctorSpecialtyData.java;
        DoctorSpecialtyData.getDoctorSpecialty(connection, IDDoctors);
        toClient.println("<Center>");    
        toClient.println("<h2 align='center'> Doctor Specialties </h2>");
        toClient.println("<table id='SpecialtiesTable' class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Qualification</th>");
        toClient.println("<th>Date</th>");
        toClient.println("</tr>");
        toClient.println("</thead>");
        toClient.println("<tbody>");
        for (int i=0; i<DoctorSpecialty.size();i++) {
            DoctorSpecialtyData Specialty = DoctorSpecialty.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + Specialty.Name + "</td>");
            toClient.println("<td>" + Specialty.SpecialtyDate + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("</Center>");
             
}
    
    
    //this method i get the different department in which the doctor is in-DoctorDepartment.data;
        public static void DoctorDepartment(PrintWriter toClient, Connection connection, String IDDoctors) {
		toClient.println("<br><input type='hidden' id='DoctorId' value='" + IDDoctors + "'> </input>");
        toClient.println("<div align='center' id='DoctorDepartment'></div>");
        toClient.println("<div style='text-align:center;'>");
        //here onclick allows me to executed the method that do what i want to show-ViewDoctorDepartmentList.js;
        //the id is button;
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewDoctorDepartment()'>View Doctor Department's</button>");
        toClient.println("</div>");
            //This show a specific info without refreshing page;
            //i included the method where i defined it;
		toClient.println("<script src=ViewDoctorDepartmentList.js></script>");
    }    
    //here i get the method for the patients list the doctor has checked;
    public static void DoctorPatients(PrintWriter toClient, Connection connection, String IDDoctors) {
		toClient.println("<br><input type='hidden' id='DoctorId' value='" + IDDoctors + "'> </input>");
        toClient.println("<div align='center' id='DoctorPatients'></div>");
        toClient.println("<div style='text-align:center;'>");
        //this case the id is button2 as is another;
        //onclick as i want as the user click it;
        
        toClient.println("<button class='button button1' id='button2' type='submit' onclick='viewDoctorPatients()'>View Doctor Patient's List </button>");
        toClient.println("</div>");
        //i insert where i created the method;
		toClient.println("<script src=ViewDoctorPatientsList.js></script><br>");
        
        
    }    
    
    
    }