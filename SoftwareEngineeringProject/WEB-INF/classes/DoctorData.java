import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorData{
    
    
    String IDDoctors;
    String first;
    String Last;
    String email;
    String StartDay1;
    String Extension;
    String phone; 
    Date StartDay;
	int numPatients;
    
    
    //Constructor to show a specific doctor info. 
   DoctorData(String IDDoctors, String first, String Last, String email, String Extension, Date StartDay, String phone) {
        this.IDDoctors = IDDoctors;
        this.first = first;
        this.Last = Last;
        this.email = email;
        this.Extension=Extension;
        this.StartDay= StartDay;
        this.phone = phone;
    }
    
    
    //Constructor for DoctorUpdate.java ;
    DoctorData(String IDDoctors, String first, String Last, String email, String Extension,String StartDay1, String phone) {
        this.IDDoctors = IDDoctors;
        this.first = first;
        this.Last = Last;
        this.email = email;
        this.Extension=Extension;
        this.StartDay1 = StartDay1;
        this.phone = phone;
    }
    
    
	//Constructor for Doctorlist.java ; 
    DoctorData(String IDDoctors, String first, String Last) {
        this.IDDoctors = IDDoctors;
        this.first = first;
        this.Last = Last;
    }
    
    //constructor for the table- DoctorList.java;
    	DoctorData(String IDDoctors, int numPatients) {
        this.IDDoctors = IDDoctors;
        this.numPatients = numPatients;
    }
	
	//my metodo getDoctorList from the database;
    public static Vector<DoctorData> getDoctorList(Connection connection){
        
        Vector<DoctorData> vec = new Vector<DoctorData>();
        
        
        String sql = "SELECT IDDoctors, first, Last FROM Doctors";
        System.out.println("getEmployeeList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            //am able to display all the record of the database;
            
            while(result.next()) {

                DoctorData Doctors = new DoctorData(
                    result.getString("IDDoctors"),
                    result.getString("first"),
                    result.getString("Last") );
                
                vec.addElement(Doctors);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDoctorList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    //With this method i get the number of patients per doctor-DoctorList.java;
	public static Vector<DoctorData> getNumOfPatientsPerDoctor(Connection connection){
        
        Vector<DoctorData> DoctorVec = new Vector<DoctorData>();
        
        
        String sql = "SELECT top 4 IDDoctors, numPatients FROM (SELECT IDDoctors, Count(*) as numPatients FROM MedicalConsultations GROUP BY IDDoctors) ORDER BY numPatients DESC";
        System.out.println("getNumOfPatientsPerDoctor: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {

                DoctorData Doctors = new DoctorData(
                    result.getString("IDDoctors"),
                    Integer.parseInt(result.getString("numPatients"))
                );
                
                DoctorVec.addElement(Doctors);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNumOfPatientsPerDoctor: " + sql + " Exception: " + e);
        }
        return DoctorVec;
    }
    
    // here i defined the method to get the Doctor info of the databse - DoctorEdit.java;
        public static DoctorData getDoctorInfo(Connection connection, String IDDoctors){
        
        String sql = "SELECT first, last, email, Extension, StartDay, phone FROM Doctors WHERE IDDoctors=?";
        System.out.println("getDoctorInfo: " + sql);
        
        DoctorData Doctor = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, IDDoctors);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                Doctor = new DoctorData(
                    IDDoctors,
                    result.getString("first"),
                    result.getString("Last"),
                    result.getString("email"),
                    result.getString("Extension"),
                    result.getDate("StartDay"),
                    result.getString("phone")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDoctorInfo: " + sql + " Exception: " + e);
        }
        
        return Doctor;
		
        
    }
    
    // in this method am creating the doctor update info in the database- DoctorUpdate.java;
    
        public static int DoctorUpdate1(Connection connection, DoctorData Doctor){
       
        String sql="UPDATE Doctors";
        sql += " SET first=?, Last=?, email=?, Extension=?, StartDay=?, phone=? ";
        sql += " WHERE IDDoctors=?";
        System.out.println("DoctorUpdate: " + sql);
            
        System.out.println(Doctor.StartDay1);
            
            
        int n = 0;
        
        try {
            
            // again this because of the Date format;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilStartDay1 = formatter.parse(Doctor.StartDay1);
			java.sql.Date sqlStartDay1 = new java.sql.Date(utilStartDay1.getTime());
            
            
			
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
        
            stmtUpdate.setString(1,Doctor.first);
            stmtUpdate.setString(2,Doctor.Last);
            stmtUpdate.setString(3,Doctor.email);
            stmtUpdate.setString(4,Doctor.Extension);
            stmtUpdate.setDate(5,sqlStartDay1);
            stmtUpdate.setString(6,Doctor.phone);
            stmtUpdate.setString(7,Doctor.IDDoctors);

            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in DoctorUpdate1: " + sql + " Exception: " + e);   
        }  catch(ParseException p) {
            p.printStackTrace();
            System.out.println("Error in DoctorUpdate1: " + sql + " Exception: " + p);
        }
        return n;
    }
    
        
}