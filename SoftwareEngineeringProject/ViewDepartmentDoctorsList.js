//mentioned in Departmentedit;
function viewDepartmentDoctors() {
    
    //create de request and the variables we need to send; 
	var IDDepartments = document.getElementById("DepartmentId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button3").innerHTML == "View Department Doctor's List") {
                document.getElementById("DepartmentDoctors").innerHTML = this.responseText;
                document.getElementById("button3").innerHTML = "Hide Department Doctor's List";           
            } else {
            document.getElementById("DepartmentDoctors").innerHTML = " ";
            document.getElementById("button3").innerHTML = "View Department Doctor's List";    
            }
        }
    };
    //it is goes to DepartmentDoctors.java;
    xhttp.open("GET", "DepartmentDoctors?IDDepartments="+IDDepartments+"", true);
    xhttp.send(); 
}