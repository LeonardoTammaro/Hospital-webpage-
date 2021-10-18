
//this is mentioned on DoctorView.java;
function viewDoctorPatients() {
    
    //create de request and the variables we need to send. 
	var IDDoctors = document.getElementById("DoctorId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button2").innerHTML == "View Doctor Patient's List") {
                document.getElementById("DoctorPatients").innerHTML = this.responseText;
                document.getElementById("button2").innerHTML = "Hide Doctor Patient's List";           
            } else {
            document.getElementById("DoctorPatients").innerHTML = " ";
            document.getElementById("button2").innerHTML = "View Doctor Patient's List";    
            }
        }
    };
    
    //here i open what am sending- DoctorPatients.java;
    xhttp.open("GET", "DoctorPatients?IDDoctors="+IDDoctors+"", true);
    xhttp.send(); 
}