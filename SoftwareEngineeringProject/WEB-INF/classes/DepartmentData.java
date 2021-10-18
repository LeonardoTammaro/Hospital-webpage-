import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//it is mentioned in DepartmentList.java;

public class DepartmentData{
    
    
    String IDDepartments;
    String name;
    String Description;
    int NumDoctors;

	//Constructor for department list; 
    DepartmentData(String IDDepartments, String name) {
        this.IDDepartments = IDDepartments;
        this.name = name;
    }
    
    //Constructor for doctor per departments; 
    DepartmentData(String IDDepartments, int NumDoctors) {
        this.IDDepartments = IDDepartments;
        this.NumDoctors = NumDoctors;
    }
    
    //constructor if i wanna edit it;
  DepartmentData(String IDDepartments, String name,String Description) {
        this.IDDepartments = IDDepartments;
        this.name = name;
        this.Description = Description;
    }
    
	//my method getDepartmentList- DepartmentList.java;
    public static Vector<DepartmentData> getDepartmentList(Connection connection){
        
        Vector<DepartmentData> vec = new Vector<DepartmentData>();
        
        
        String sql = "SELECT IDDepartments, name FROM Departments";
        System.out.println("getDepartmentList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {

                DepartmentData Department = new DepartmentData(
                    result.getString("IDDepartments"),
                    result.getString("name"));
                 
                vec.addElement(Department);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDepartmentList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    //this method is in DepartmentList.java;
    	public static Vector<DepartmentData> getNumOfDoctorsPerDepartment(Connection connection){
        
        Vector<DepartmentData> DepartmentVec = new Vector<DepartmentData>();
        
        
        String sql = "SELECT IDDepartments, count(*) as NumDoctors FROM DepartmentsOrganization GROUP BY IDDepartments";
        System.out.println("getNumOfPatientsPerDoctor: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {

                DepartmentData Departments = new DepartmentData(
                    result.getString("IDDepartments"),
                    Integer.parseInt(result.getString("NumDoctors"))
                );
                
                DepartmentVec.addElement(Departments);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getNumOfPatientsPerDoctor: " + sql + " Exception: " + e);
        }
        return DepartmentVec;
    }
    
    
     public static DepartmentData getDepartmentInfo(Connection connection, String IDDepartments){
        
        String sql = "SELECT name, Description FROM Departments WHERE IDDepartments=?";
        System.out.println("getDepartmentInfo: " + sql);
        
        DepartmentData Department = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, IDDepartments);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                Department = new DepartmentData(
                    IDDepartments,
                    result.getString("name"),
                    result.getString("Description") );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDepartmentInfo: " + sql + " Exception: " + e);
        }
        
        return Department;
		
        
    }
    
    
    public static int DepartmentUpdate1(Connection connection, DepartmentData Department){
       
        String sql="UPDATE Departments";
        sql += " SET name=?, Description=? ";
        sql += " WHERE IDDepartments=?";
        System.out.println("DepartmentUpdate1: " + sql);
            
            
        int n = 0;
        
        try {
                
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
        
            stmtUpdate.setString(1,Department.name);
            stmtUpdate.setString(2,Department.Description);
            stmtUpdate.setString(3,Department.IDDepartments);
       
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in DepartmentUpdate1: " + sql + " Exception: " + e);   
        }  
        
        return n;
    }
    
    
}
         
    
 
    