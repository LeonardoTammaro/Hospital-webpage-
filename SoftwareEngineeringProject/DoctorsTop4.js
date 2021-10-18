//this method is mentioned in DoctorList.java;

function CreateTable() {
    
    // I get the id;
 parentNode = document.getElementById('list');
    // I create the table i want to show;
var tableEl = document.createElement("TABLE");
    //i set the attributes for the table;
tableEl.setAttribute("id", "table6");
tableEl.setAttribute("border", "1");
parentNode.appendChild(tableEl);
    
    //I am creating the title of the table;
  var tr4 = document.createElement("TR");
  tableEl.appendChild(tr4);
  var tr4 = document.createElement("TR");
  tableEl.appendChild(tr4);
    
    
    var td4 = document.createElement("TD");
        var textNode = document.createTextNode("Most Famous Doctors");
        td4.appendChild(textNode);
        tr4.appendChild(td4);    
    
  var tr = document.createElement("TR");
  tableEl.appendChild(tr);
  var tr = document.createElement("TR");
  tableEl.appendChild(tr);
    
    
    var td = document.createElement("TD");
        var textNode = document.createTextNode("ID Doctors");
        td.appendChild(textNode);
        tr.appendChild(td);
    
     var td2 = document.createElement("TD");
        var textNode = document.createTextNode("Patients that the doctor attended");
        td2.appendChild(textNode);
        tr.appendChild(td2);
    
    
    //at this point i want to insert into the table the info I saved in my rawdata-DoctorList.java;
    
  for ( i=0 ; i < rawData.length ; i++){
  
  var tr1 = document.createElement("TR");
  tableEl.appendChild(tr1);
  var tr1 = document.createElement("TR");
  tableEl.appendChild(tr1);
      
      //this method is to introduce the value in each column;
      //i defined the function- createListCell;
createListCell(tr1, rawData[i].IDDoctors);
createListCell(tr1, rawData[i].numPatients);

  } 
}

// i created the method that put me the values into the column;
function createListCell(tr1, value) {
        var td1 = document.createElement("TD");
        var textNode = document.createTextNode(value);
        td1.appendChild(textNode);
        tr1.appendChild(td1);
}

CreateTable()
