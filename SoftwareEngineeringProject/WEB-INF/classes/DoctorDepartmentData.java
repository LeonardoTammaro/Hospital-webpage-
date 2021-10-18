import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//i mentioned from DoctorDepartment.java;

public class DoctorDepartmentData{
    
    String IDDoctors;
    String IDDepartments;
    String name;

    
    DoctorDepartmentData(String IDDoctors, String IDDepartments, String name) {
        this.IDDoctors    = IDDoctors;
        this.IDDepartments  = IDDepartments;
        this.name = name;
    }
        
    public static Vector<DoctorDepartmentData> getDoctorDepartments(Connection connection, String IDDoctors) {
        Vector<DoctorDepartmentData> vec = new Vector<DoctorDepartmentData>();
        String sql = "SELECT DepartmentsOrganization.IDDepartments, name FROM DepartmentsOrganization, Departments";
        sql += " WHERE DepartmentsOrganization.IDDepartments = Departments.IDDepartments AND IDDoctors = ?";
        System.out.println("getDoctorDepartments: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, IDDoctors);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DoctorDepartmentData DoctorDepartment = new DoctorDepartmentData(
                    IDDoctors,
                    result.getString("IDDepartments"),
                    result.getString("name"));
                
                vec.addElement(DoctorDepartment);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDoctorDepartments : " + sql + " Exception: " + e);
        }
        return vec;
    }    

    
    
        
}