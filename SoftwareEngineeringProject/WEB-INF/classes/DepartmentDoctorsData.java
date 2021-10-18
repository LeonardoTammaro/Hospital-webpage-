import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//mentioned in DepartmentDoctors.java
public class DepartmentDoctorsData{
    
    String IDDoctors;
    String IDDepartments;
    String first;
    String Last;

    //contructor;
    
    DepartmentDoctorsData(String IDDepartments, String IDDoctors, String first,String Last ) {
        this.IDDepartments    = IDDepartments;
        this.IDDoctors  = IDDoctors;
        this.first = first;
        this.Last = Last;
    }
        
    //method;
    public static Vector<DepartmentDoctorsData> getDepartmentDoctors(Connection connection, String IDDepartments) {
        Vector<DepartmentDoctorsData> vec = new Vector<DepartmentDoctorsData>();
        String sql = "SELECT DepartmentsOrganization.IDDoctors, first, Last FROM DepartmentsOrganization, Doctors";
        sql += " WHERE DepartmentsOrganization.IDDoctors = Doctors.IDDoctors AND IDDepartments = ?";
        System.out.println("getDepartmentDoctors: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, IDDepartments);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DepartmentDoctorsData DepartmentDoctors = new DepartmentDoctorsData(
                    IDDepartments,
                    result.getString("IDDoctors"),
                    result.getString("first"),
                    result.getString("Last"));
                
                vec.addElement(DepartmentDoctors);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDepartmentDoctors : " + sql + " Exception: " + e);
        }
        return vec;
    }    

    
    
        
}