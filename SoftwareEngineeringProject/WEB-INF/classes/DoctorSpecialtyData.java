import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


//i mentioned from DoctorView.java;

public class DoctorSpecialtyData{
    

    
    String Name;
    String IDSpecialty; 
	String IDDoctors;
	Date SpecialtyDate;
    
    // constructor for specialties
    
    DoctorSpecialtyData(String IDSpecialty, String IDDoctors , String Name, Date SpecialtyDate){
        this.IDSpecialty = IDSpecialty;
        this.IDDoctors=IDDoctors;
        this.Name = Name;
		this.SpecialtyDate = SpecialtyDate;
    }
    
        
    public static Vector<DoctorSpecialtyData> getDoctorSpecialty(Connection connection, String IDDoctors) {
        Vector<DoctorSpecialtyData> vec = new Vector<DoctorSpecialtyData>();
        String sql = "Select Specialties.IDSpecialty, Name, SpecialtyDate FROM Specialties, DoctorsSpecialities";
        sql += " WHERE Specialties.IDSpecialty = DoctorsSpecialities.IDSpecialty AND IDDoctors = ?";
        System.out.println("getDoctorSpecialty: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, IDDoctors);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                DoctorSpecialtyData DoctorSpecialty = new DoctorSpecialtyData(
                    IDDoctors,
                    result.getString("IDSpecialty"),
                    result.getString("Name"),
                    result.getDate("SpecialtyDate")
                );
                vec.addElement(DoctorSpecialty);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getDoctorSpecialty: " + sql + " Exception: " + e);
        }
        return vec;
    }
	


}