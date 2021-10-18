function CreateTable1() {
    
 parentNode = document.getElementById('list1');
    
var tableEl = document.createElement("TABLE");
    
tableEl.setAttribute("id", "table6");
tableEl.setAttribute("border", "1");
parentNode.appendChild(tableEl);
    
  var tr4 = document.createElement("TR");
  tableEl.appendChild(tr4);
  var tr4 = document.createElement("TR");
  tableEl.appendChild(tr4);
    
    
    var td4 = document.createElement("TD");
        var textNode = document.createTextNode("Numbers of Doctor in each Department");
        td4.appendChild(textNode);
        tr4.appendChild(td4);    
    
  var tr = document.createElement("TR");
  tableEl.appendChild(tr);
  var tr = document.createElement("TR");
  tableEl.appendChild(tr);
    
    
    var td = document.createElement("TD");
        var textNode = document.createTextNode("ID Department");
        td.appendChild(textNode);
        tr.appendChild(td);
    
     var td2 = document.createElement("TD");
        var textNode = document.createTextNode("Total number of Doctors");
        td2.appendChild(textNode);
        tr.appendChild(td2);
    
    
  for ( i=0 ; i < rawData.length ; i++){
  
  var tr1 = document.createElement("TR");
  tableEl.appendChild(tr1);
  var tr1 = document.createElement("TR");
  tableEl.appendChild(tr1);
      
createListCell1(tr1, rawData[i].IDDepartments);
createListCell1(tr1, rawData[i].NumDoctors);

  } 
}

function createListCell1(tr1, value) {
        var td1 = document.createElement("TD");
        var textNode = document.createTextNode(value);
        td1.appendChild(textNode);
        tr1.appendChild(td1);
}

CreateTable1()
