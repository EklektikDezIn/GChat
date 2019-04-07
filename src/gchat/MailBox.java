package gchat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Micah
 */
public class MailBox {

    public String userName;
    public String ID;
    public String address;

    public MailBox(String UN, String Id, String Ad) {
        //Creates a new Email object
        userName = UN;
        ID = Id;
        address = Ad;
    }

     
    public String toString(String enter) {
        //Prints out a Email object as a String
        return "Name: " + userName + enter + " ID: " + ID + enter + " Address: " + address;
        
    }
    @Override
    public String toString() {
        //Prints out a Email object as a String
        return "Name: " + userName + ";" + " ID: " + ID + ";" + " Address: " + address;
        
        
    }
}
