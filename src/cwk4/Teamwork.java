package cwk4; 


/**
 * Details of your team
 * 
 * @author (Artha Bante)
 * @version (14/04/2024)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS13";
        
        details[1] = "Bante";
        details[2] = "Artha";
        details[3] = "21085597";

        details[4] = "Gohil";
        details[5] = "Denil";
        details[6] = "21068447";

        details[7] = "Verma";
        details[8] = "Lakshay";
        details[9] = "21069929";


        details[10] = "NA";
        details[11] = "NA";
        details[12] = "NA";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}