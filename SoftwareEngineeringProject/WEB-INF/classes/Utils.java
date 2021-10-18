//useful class if i want a specific info to appear many times;

public class Utils {
    
     public static String header(String title) {
        return header(title, null);
    }
    
   
  public static String header(String title, String user) {
        StringBuilder str = new StringBuilder();
      
        str.append("<!DOCTYPE HTML>");
        str.append("<html>");
        str.append("<head><title>" + title + "</title>");
        str.append("<div class='menu'>");
        str.append("<meta charset='UTF-8'>");
        str.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        str.append("<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>");
        str.append("<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway'>");
        str.append("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        str.append("<style>");
        str.append("body,h1 {font-family: 'Raleway', Arial, sans-serif,  background-color:#dadcdc}");
        str.append("h1 {letter-spacing: 6px}");
        str.append(".w3-row-padding img {margin-bottom: 12px}");
        str.append("</style>");
        str.append("<div class='w3-top'>");
        str.append("<div class='w3-bar w3-gray w3-card'>");
        str.append("<a class='w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right' href='javascript:void(0)' onclick='myFunction()' title='Toggle Navigation Menu'><i class='fa fa-bars'></i></a>");
        str.append("<a href='index.html' class='w3-bar-item w3-button w3-padding-large'>Home</a>");
        str.append("<a href='PatientsInicio.html' class='w3-bar-item w3-button w3-padding-large w3-hide-small'>Patients</a>");
        str.append("<a href='RoomsInicio.html' class='w3-bar-item w3-button w3-padding-large w3-hide-small'>Patient Rooms</a>");
        str.append("<a href='CheckLogin' class='w3-bar-item w3-button w3-padding-large w3-hide-small'>Doctors and Departments</a>");
        str.append("<a href='Investigaciones.html' class='w3-bar-item w3-button w3-padding-large w3-hide-small'>Investigations</a>");
        str.append("<a href='DonationsInicio.html' class='w3-bar-item w3-button w3-padding-large w3-hide-small'>Donations</a>"); 
      
        str.append("<div style='text-align: right;'>");
      
      
      //if my user is still in session it shows a log out option;
      //then go to the login page;
       if (user != null) {
           str.append("<a id='login' href='Logout' class='w3-bar-item w3-button w3-padding-large w3-hide-small' >Logout (" + user + ")</a>");
        } else {
            str.append("<a id='login' href='CheckLogin' class='w3-bar-item w3-button w3-padding-large w3-hide-small' > Login </a>");
        }
      
        str.append("</div>");
        str.append("</div>");
        str.append("<a href='javascript:void(0)' class='w3-padding-large w3-hover-red w3-hide-small w3-right'><i class='fa fa-search'></i></a>");
        str.append("</div>");
        str.append("<header class='w3-panel w3-center w3-opacity' style='padding:128px 16px'>");
        str.append("<img src='https://www.uupfarm.org/images/Medical.png' alt='Logo' width='100' height='100' align='right'>");
        str.append("<center>");
        str.append("<h1 class='w3-xlarge'> Clinic Pro </h1>"); 
      
      //set up a clock so user could see time and the actual date;
      //onload because i want it to appear as a page is open;
        str.append("<body onload='mueveReloj()'>"); 
      str.append("<form name='form_reloj'>");
      str.append("<table>");
      str.append("<tbody>");
      str.append("<tr>"); 

      //set the name for my input type//;
      str.append("<td> Date / Time :    <input type='text' name='Date' size='15'>              </td>");
      str.append("<td>          <input type='text' name='reloj' size='15'>                     </td>");
      str.append("</tr>");
      str.append("</tbody></table>");
      str.append("</form>");
      
      //put the function where am getting hour and date;
      str.append("<script src=TimeDate.js></script>");
      
      str.append("</center>");
      str.append("  </body>");
        str.append("<H2 align=\"center\">" + title + "</H2>");
        return str.toString();
    }

    public static String footer(String title) {
        StringBuilder str = new StringBuilder();
        str.append("</body>");
        str.append("</html>");
        return str.toString();
    }
}

      