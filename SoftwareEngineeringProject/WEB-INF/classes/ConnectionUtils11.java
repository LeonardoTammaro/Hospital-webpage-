import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class ConnectionUtils11 {
	
	private ConnectionUtils11() {
        
	    throw new IllegalStateException("Utility class");
	  }

	public static Connection getConnection(ServletConfig config) {
		Connection connection = null;
		
		try {
			ServletContext context = config.getServletContext();
            
            //i establish the path of my database;
			System.out.println("realPath: " + context.getRealPath("ClinicPro.mdb"));
            
            //I changed my jdbc:ucanaccess:// and then my database, which with the html;
            
			String dbURL = "jdbc:ucanaccess://" + context.getRealPath("ClinicPro.mdb");
			connection = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		Connection connection = null;
		
		try {
            //I also need to change here to again the driver and databse jdbc:ucanaccess:ClinicPro;
			String url = "jdbc:ucanaccess:ClinicPro";
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection close(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}