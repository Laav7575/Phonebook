import java.io.*;

public class MyPhoneBook {
  int numEntries; //global variables, count number of entries
  String [][] contacts = new String [5][100]; //store entry names

  public MyPhoneBook () {
    numEntries = 0;
  } // PhoneBook constructor

  public void add (String name, String lastN, String phoneNumber, String email, String address) {
    contacts [0][numEntries] = name; //0 is the column for first names
    contacts [4][numEntries] = lastN; //4 is the column for last names
    contacts [1][numEntries] = phoneNumber; //1 is the column for phone numbers
    contacts [2][numEntries] = email; //2 is the column for emails
    contacts [3][numEntries] = address; //3 is the column for address
    numEntries++; //adds 1 to number of entries counter
  } // add procedural (void) method

  public String [][] lookUp (String name, String lastN, boolean useFirstName) { 
    String [][] data = new String [5][100]; //creates new array to store matches
    if (useFirstName == true) { //user wants to use first name
      for (int cnt = 0 ; cnt < numEntries; cnt++) { //runs through array to find all matches
        if (name.equalsIgnoreCase(contacts [0][cnt])) { //if any first name matches given first name
          data [0][cnt] = contacts [0][cnt];
          data [1][cnt] = contacts [1][cnt];
          data [2][cnt] = contacts [2][cnt];
          data [3][cnt] = contacts [3][cnt];
          data [4][cnt] = contacts [4][cnt]; //adds the matched contacts information to new array
        }
      }
    }
    else if (useFirstName == false) { //user wants to use last name
      for (int cnt = 0 ; cnt < numEntries; cnt++) { //runs through array to find all matches
        if (lastN.equalsIgnoreCase(contacts [4][cnt])) { //if any last name matches given last name
          data [0][cnt] = contacts [0][cnt];
          data [1][cnt] = contacts [1][cnt];
          data [2][cnt] = contacts [2][cnt];
          data [3][cnt] = contacts [3][cnt];
          data [4][cnt] = contacts [4][cnt]; //adds the matched contacts information to new array
        }
      }
    } 
    return data; //returns data array with all matches
  } // lookUp functional (2D String array) method

  public boolean remove (String name, String lastN) {
    for (int cnt = 0 ; cnt < numEntries; cnt++) { //runs array to find first match that matches given name
      if (name.equalsIgnoreCase (contacts [0][cnt]) && lastN.equalsIgnoreCase (contacts[4][cnt])) { //if first name and last name matches
        for (int cnt1 = cnt + 1 ; cnt++ < numEntries ; cnt1++) { //replaces index in array to remove it
          contacts [0][cnt - 1] = contacts [0][cnt1];
          contacts [1][cnt - 1] = contacts [1][cnt1];
          contacts [2][cnt - 1] = contacts [2][cnt1];
          contacts [3][cnt - 1] = contacts [3][cnt1];
          contacts [4][cnt - 1] = contacts [4][cnt1];
        }
        numEntries--; //subtract the number of entries by 1 
        return true; //name matched
      }
    }
    return false; //if no name matches, returns false
  } //remove functional (boolean) method

  public boolean edit (String name, String lastN, String num, String email, String address)  {
    boolean removed2 = remove(name, lastN); //stores return value from remove method
    if (removed2 == false) { //if remove method returns false
      return false; //this method also returns false since no contact match found
    }
    add(name,lastN,num,email,address); //else, calls add method to replace the removed contact with the changed information
    return true; //returns true to show contact has been edited
  } //edit functional (boolean) method


  public String [][] viewAll () {
    return contacts; //returns contacts array
  }

  public int numEntries2 () {
    return numEntries; //returns numEntries
  }

  public void read () {
    try {
      BufferedReader input; //object to connect to file for read only
      input = new BufferedReader (new FileReader ("data.txt"));
      String line = input.readLine (); //Read a line of characters.
      numEntries = 0; //since program is restarting, numEntries starts at 0
      int count = 0; 
      while (line != null)   { //File is terminated by a null.
        count++; //adds one every time a new line is read to keep track of what line is being read
            if ((count%5) == 1) { //this means it is reading a line with a first name
              contacts [0][(numEntries)] = line; //stores to corresponding array column
            }
            else if (count%5 == 2) { //this means it is reading a line with a last name
              contacts [4][numEntries] = line; //stores to corresponding array column
            }
            else if (count%5 == 3) { //this means it is reading a line with a phone number
              contacts [1][numEntries] = line; //stores to corresponding array column
            }
            else if (count%5 == 4) { //this means it is reading a line with an email name
              contacts [2][numEntries] = line; //stores to corresponding array column
            }
            else { //this means it is reading a line with an address
              contacts [3][(numEntries)] = line; //stores to corresponding array column
              numEntries++; //after all five sets of information have been stored, one complete entry has been added
            }
            line = input.readLine (); //reads next line
            }
      input.close (); //close the file
    }
    catch (Exception e) {
        System.out.println ("error"); //tells user there was an error
    } //need this line for try catch
  }

  public void store () {
    try {
      PrintWriter output; //object to connect to a file
      output = new PrintWriter (new FileWriter ("data.txt")); //instantiate object
      for (int number = 0; number < numEntries ; number ++) { //loops through all contacts to write to file
        output.println (contacts [0][number]); //write first name from array column to file
        output.println (contacts [4][number]); //write last name from array column to file
        output.println (contacts [1][number]); //write phone number from array column to file
        output.println (contacts [2][number]); //write email from array column to file
        output.println (contacts [3][number]); //write address from array column to file
      }
      output.close (); //you must close the object to save the data in the file
      System.out.println ("contacts saved."); //tells user writing to file was successful
    }
    catch (Exception e) { //need this line for try catch
      System.out.println ("error"); //tells user there was an error
    }
  }
}