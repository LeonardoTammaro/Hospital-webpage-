//function mentioned in Utils.java;
function mueveReloj(){
    //am calling the function who get me hour and thedate;
    
     time();
     gettheDate();
}

// my function for the time;

function time() {
    // create to get the Date, as am not  putting an entry argument it get the actual date and hour- Date();
    
    momentoActual = new Date()
    
    //then i want select from that object the time(complete hour);
    hora = momentoActual.getHours()
    minuto = momentoActual.getMinutes()
    segundo = momentoActual.getSeconds()
    horaImprimible = hora + " : " + minuto + " : " + segundo

    //i want to show it in my page then use value-reloj is the name to set the clock-and form_reloj is the name of my form i want to put the hour;
    
    document.form_reloj.reloj.value = horaImprimible

    
 //The setTimeout function is used to make the delay before executing the statement. The statement is a simple function call and the delay is 1000 milliseconds (one second);
    setTimeout("mueveReloj()",1000)
    
}

//my function for the date;

function gettheDate() {
//Again i get the object date of javascript ;
    
Todays = new Date();
// i select the info i want, in this case the actual date;
// the function .getMonth() gets me the month before then i add one;
//with the getYear() returns the actual year minus 1900 then i add 1900;
//with the getDate() returns the day of the month;
TheDate = "" + (Todays.getMonth()+ 1) +" / "+ Todays.getDate() + " / " + (Todays.getYear() + 1900) 
    
//i want to show it in my page then use value-Date is the name to set the date-and form_reloj is the name of my form i want to put the hour;
    
document.form_reloj.Date.value = TheDate;

}