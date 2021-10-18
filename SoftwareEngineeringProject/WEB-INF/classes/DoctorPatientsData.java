import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorPatientsData{
    
    String IDDoctors;
    String IDPatients;
    String Name;
    String LastName; 
    Date DateTime;
    String Reason;
    String Status;
    
    DoctorPatientsData(String IDDoctors, String IDPatients, String Name, String LastName, Date DateTime,String Reason,String Status) {
        this.IDDoctors    = IDDoctors;
        this.IDPatients  = IDPatients;
        this.Name = Name;
        this.LastName = LastName;
        this.DateTime = DateTime;
        this.Reason= Reason;
        this.Status = Status;
        
    }
        
    public static Vector<DoctorPatientsData> getDoctorPatients(Connection connection, String IDDoctors) {
        Vector<DoctorPatientsData> vec = new Vector<DoctorPatientsData>();
        String sql = "SELECT MedicalConsultations.IDPatients, Name, LastName, DateTime, Reason, Status FROM MedicalConsultations, Patients";
        sql += " WHERE MedicalConsultations.IDPatients = Patients.IDPatients AND IDDoctors = ?";
        
        System.out.println("getDoctorPatients: " + sql);
        try {
            
            
            
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, IDDoctors);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DoctorPatientsData DoctorPatients = new DoctorPatientsData(
                    IDDoctors,
                    result.getString("IDPatients"),
                    result.getString("Name"),
                    result.getString("LastName"),
                    result.getDate("DateTime"),
                    result.getString("Reason"),
                    result.getString("Status"));
                
                vec.addElement(DoctorPatients);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDoctorPatients : " + sql + " Exception: " + e);
        }
        return vec;
    }    

    
    
        
}