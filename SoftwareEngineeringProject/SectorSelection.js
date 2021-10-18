  
//this function makes me to go to the main page of the Section;

function Section(DocOrDep)
    {
        //so if my selected option is different than null i want ;
        //choose is the name of my dropdown list-MainPrincipal.java;
        
        if (DocOrDep.Choose.selectedIndex != 0)
        {
            //if this is true i set my location with value, defined in MainPrincipal.java ;
            document.location=DocOrDep.Choose.options[DocOrDep.Choose.selectedIndex].value
        }
    }
    