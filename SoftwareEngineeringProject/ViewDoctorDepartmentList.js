

// a function i created- DoctorView.java;

function viewDoctorDepartment() {
    
    //create de request and the variables we need to send. 
	var IDDoctors = document.getElementById("DoctorId").value;
    var xhttp=new XMLHttpRequest();
    
    //Then i define a function to be executed when the readyState changes.
    xhttp.onreadystatechange = function() {
        
        //readyState 4 because in when every step is check: request not initialized,server connection established,request received,processing request,request finished and response is ready;
        //status is when is OK, just in case to know 403 is forbidden and 404 is page not found;
        
        if (this.readyState == 4 && this.status == 200) {
            
            //i create an if to be able to show a button that can show and hide a specific info depending on the user; 
            //the element is the same in order to do both and the id that is mentioned is button1;
           //it innerHTML function:When set, the element's HTML syntax is replaced by the new one;
            
            if (document.getElementById("button1").innerHTML == "View Doctor Department's") {
                
                //this.responseText means: is the response of your XML HTTP Request ("ajax" return);
                
                document.getElementById("DoctorDepartment").innerHTML = this.responseText;
               //show the same button to allows to a hide button;
                document.getElementById("button1").innerHTML = "Hide Doctor Department's";           
            } else {
                
            document.getElementById("DoctorDepartment").innerHTML = " ";
                
            document.getElementById("button1").innerHTML = "View Doctor Department's";    
            }
        }
    };
    
    //i open what i want to send DoctorDepartment- DoctorDepartment.java;
    xhttp.open("GET", "DoctorDepartment?IDDoctors="+IDDoctors+"", true);
    xhttp.send(); 
}