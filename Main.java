/*  Laavanya Thiagalingam
     Course Code: ICS3U0
     Assignment Title: Class, Objects and Methods Assignment
     Date Completed: December 6, 2021
     Program Description: A user-friendly phonebook made to store first name, last name, phone number, email and address, allowing the user to add, find, delete, edit, and view all from their contacts. 
*/
 
 import java.util.Scanner;

class Main {

    static Scanner kb; // a global variable
    public static void main (String[] args)
    {
        kb = new Scanner (System.in);
        MyPhoneBook pb = new MyPhoneBook (); //instantiate MyPhoneBook object
        pb.read(); //reads data as soon as program starts

        int choice = 0; //determines what user wants to use the phonebook for
        String name = ""; //variable to store first name,
        String num = ""; //phone number,
        String email = ""; //email,
        String lastN = ""; //last name,
        String address = ""; //and address
        boolean useFirstName = true; //determines if look-up uses first or last name
        
        System.out.println ("Welcome to Laav's greatest Phonebook!"); //welcome
        
        while (true) { //prints all options for user
          System.out.println("1) add new contact");
          System.out.println("2) find contact");
          System.out.println("3) delete contact");
          System.out.println("4) edit contact");
          System.out.println("5) view all contacts");
          System.out.println("6) exit");
          System.out.print("Please enter your choice (1-6): ");
          choice = kb.nextInt ();
          kb.nextLine(); //flush enter character
          if (choice < 1 || choice > 6) { //determines if user's choice was valid or not, if not, prints an error message
              System.out.println ("Error, please try again!");
          }
          else { //else, runs user's choice
            switch (choice) {
              case 1: //add contact by storing all information and adding to array using add method
                System.out.print ("Enter first name: ");
                name = kb.nextLine();
                System.out.print("Enter last name: ");
                lastN = kb.nextLine();
                System.out.print ("Enter phone number: ");
                num = kb.nextLine();
                System.out.print("Enter email: ");
                email = kb.nextLine();
                System.out.print("Enter address: ");
                address = kb.nextLine();
                pb.add (name,lastN,num,email,address);
                break; //returns to menu
              case 2: //find contact
                System.out.print ("Would you like to find the contact using their first or last name? (first or last): ");
                String firstOrLast = kb.nextLine(); //asks user how they want to find contact and stores response
                if (firstOrLast.equalsIgnoreCase("first")) { 
                  System.out.print ("Enter first name of contact you would like to find: "); 
                  name = kb.nextLine(); //if they want to look up using first name, stores first name of contact they would like to find
                  useFirstName = true; //allows method to only check for first names that match in the array
                }
                else if (firstOrLast.equalsIgnoreCase("last")) {
                  System.out.print ("Enter last name of contact you would like to find: ");
                  lastN = kb.nextLine(); //if they want to look up using last name, stores last name of contact they would like to find
                  useFirstName = false; //allows method to only check for last names that match in the array
                }
                else {
                  System.out.println ("Please enter a valid answer");
                  break; //if user's response is invalid, prints error message
                }
                String [][] dataCopy2 = pb.lookUp (name, lastN, useFirstName); //calls on method lookUp and stores the return value to create pointer
                int matches = dataCopy2[0].length; //the number of matches is the length of the data array
                String allMatches = ""; //variable for ouput
                int numContact = 0;
                for (int i = 0; i < matches; i++) { //prints entire data array
                  if (dataCopy2[0][i] != null) { //prevents nulls in array from printing
                    numContact++; //shows user how many matches were made
                    allMatches = allMatches + "\n" + (numContact) + ". first name: " + dataCopy2 [0][i] + "\nlast name: " + dataCopy2 [4][i] + "\nphone number: " + dataCopy2 [1][i] + "\nemail: " + dataCopy2 [2][i] + "\naddress: " + dataCopy2 [3][i] + "\n"; //stores all information of each match using data pointer to array
                  }
                }
                if (allMatches.equals("")) {
                  System.out.println("No matches found"); //tells user if no matches were found
                  break; //returns to menu and prevents the rest of case from running
                }
                System.out.println(allMatches); //otherwise, prints all matches
                break; //returns to menu
              case 3: //remove
                System.out.print ("Enter first name of contact you would like to remove: ");
                name = kb.nextLine(); //stores first name of contact that user wants to remove
                System.out.print ("Enter last name of contact you would like to remove: ");
                lastN = kb.nextLine(); //stores last name of contact that user wants to remove
                boolean removed = pb.remove (name, lastN); //variable to store return value of method remove
                if (removed == true) {
                  System.out.println ("Contact removed"); //if method returns true, the contact has been removed
                }
                else {
                  System.out.println("Contact not found"); //else, the contact does not exist
                }
                break; //returns to menu
              case 4: //edit
                System.out.print ("Enter first name of contact you would like to edit: ");
                name = kb.nextLine(); //stores first name of contact that user wants to edit
                System.out.print ("Enter last name of contact you would like to edit: ");
                lastN = kb.nextLine(); //stores last name of contact that user wants to edit
                boolean edited = pb.edit(name,lastN,num,email,address); //variable to store return value of method edit
                if (edited == false) {
                  System.out.println ("Contact not found"); //if edit returns false, the contact does not exist, telling the user before they have to input any more information
                  break; //returns to menu and prevents the rest of case from running
                }
                System.out.print ("What would you like to change their number to?  ");
                num = kb.nextLine(); //stores the new number user would like to change to
                System.out.print ("What would you like to change their email to?  ");
                email = kb.nextLine(); //stores the new email user would like to change to
                System.out.print ("What would you like to change their address to?  ");
                address = kb.nextLine(); //stores the new address user would like to change to
                boolean edited2 = pb.edit(name,lastN,num,email,address); //variable to store the return value of edit using all the new information
                if (edited2 == true) {
                  System.out.println ("Contact edited"); //if edit method returns true, that means the contact has been edited
                }
                break; //returns to menu
              case 5: //view all
                String dataCopy[][];
                dataCopy = pb.viewAll(); //points to what contacts array points to
                int i2 = 0; //iterator
                String all = ""; //variable that stores output
                int numberE = pb.numEntries2(); //calls method that returns numEntries
                while (i2 < numberE) { //while not all contacts have been stored from array
                  all = all + "\n" + (i2+1) + ". first name: " +((dataCopy[0][i2])+"\nlast name: "+ (dataCopy[4][i2])+"\nphone number: " + (dataCopy[1][i2])+"\nemail: "+ (dataCopy[2][i2])+"\naddress: "+(dataCopy[3][i2])+"\n"); //stores information of each contact using array pointer
                  i2++; //lets user know how many contacts there are and iterates loop
                }
                if (all.equals("")) { //this means there are no contacts to view
                  System.out.println("No contacts available to view");
                  break; //ends case, returns to menu
                }
                System.out.println(all); //prints all contacts
                break; //returns to menu
              case 6: //exit
                System.out.println ("Exiting program"); //tells user
                pb.store(); //calls on store method to write all data to file
                System.exit(0); //exits program
              break;
            }
}
                }
            }
        }
                