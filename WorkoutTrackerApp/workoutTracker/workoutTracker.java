import java.io.*;
import java.text.*;
import java.util.*;

/** Author: Gregory Goodreau Version: 3/27/2020 - One Day build */

////////////////////////////
// WORKOUT TRACKER Class //
//////////////////////////
public class workoutTracker {

  /////////////////
  // LIFT MAXES //
  ///////////////
  static int deadMax, ohpMax, benchMax, rowsMax, squatsMax, chinMax;

  ///////////////////////////////////
  // WORKOUT TRACKER DRIVER CLASS //
  /////////////////////////////////
  public static void workoutTrackerDriver() {
    Scanner scan = new Scanner(System.in);

    /////////////////////////////////
    // OPTIONS FOR WORKOUT INPUTS //
    ///////////////////////////////
    System.out.println("Which workout do you want to track?");
    System.out.println(
        "- Enter O for OHP"
            + "\n"
            + "- Enter B for Bench"
            + "\n"
            + "- Enter D for Deadlift"
            + "\n"
            + "- Enter S for Squats"
            + "\n"
            + "- Enter C for Chinups"
            + "\n"
            + "- Enter R for rows"
            + "\n"
            + "- Enter 1 to exit program.");

    //////////////////////////////////////////
    // SWITCH STATEMENT FOR WORKOUT INPUTS //
    ////////////////////////////////////////
    boolean keepGoing = true;
    while (keepGoing) {
      String wInput = scan.nextLine();
      switch (wInput) {
        case "O":
          System.out.println("Enter your max weight pushed today for Overhead Press:");
          int ohpInput = scan.nextInt();
          ohpMax = ohpInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "B":
          System.out.println("Enter your max weight pushed today for Bench Press:");
          int benchInput = scan.nextInt();
          benchMax = benchInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "D":
          System.out.println("Enter your max weight pulled today for Deadlift:");
          int deadInput = scan.nextInt();
          deadMax = deadInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "S":
          System.out.println("Enter your max weight pushed today for Squats:");
          int squatsInput = scan.nextInt();
          squatsMax = squatsInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "C":
          System.out.println("Enter your max reps for Chinups:");
          int chinInput = scan.nextInt();
          chinMax = chinInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "R":
          System.out.println("Enter your max weight pulled today for Rows:");
          int rowsInput = scan.nextInt();
          rowsMax = rowsInput;
          System.out.println("Please select another option (O, B, D, S, C, R, or 1):");
          break;
        case "1":
          System.out.println("\n" + "You have selected to terminate the input session.");
          keepGoing = false;
          break;
      }
    }
  }

  ////////////////////////////////////////////
  // MAIN METHOD THAT WRITES INTO CSV FILE //
  //////////////////////////////////////////
  public static void main(String[] args) throws IOException {
    // if the file does not already exist, make it and start a PrintStream.
    workoutTrackerDriver();
    File f = new File("workouts.csv");
    try {
      if (f.exists() == false) {
        System.out.println("\n" + "Workout log not detected - file created.");
        f.createNewFile();

        /////////////////////////////////////
        // WRITES COLUMNS IF FILE CREATED //
        ///////////////////////////////////
        PrintWriter columnCreator = new PrintWriter(f);
        columnCreator.write(
            "Date"
                + ","
                + "OHP"
                + ","
                + "Bench"
                + ","
                + "Deadlift"
                + ","
                + "Squat"
                + ","
                + "Chinups"
                + ","
                + "Rows");
        columnCreator.close();
      }

      FileWriter fileWriter = new FileWriter(f, true);
      BufferedWriter logWorkout = new BufferedWriter(fileWriter);

      // sets current system date
      Date date = new Date();

      // formats current system date
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

      // New Line for all data entries
      logWorkout.newLine();

      ///////////////////////////////////
      // WRITES DATA ENTRIES INTO CSV //
      /////////////////////////////////
      logWorkout.write(
          dateFormat.format(date)
              + ","
              + ohpMax
              + ","
              + benchMax
              + ","
              + deadMax
              + ","
              + squatsMax
              + ","
              + chinMax
              + ","
              + rowsMax);

      // prints out once inputs are written successfully to file
      System.out.println("\n" + "Data written into workout log file.");

      // closes logWorkout stream
      logWorkout.close();

      // catches IOExceptions
    } catch (IOException e) {
      System.out.println("\n" + "Could not log workouts.");
    }
  }
}
