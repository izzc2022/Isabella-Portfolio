import java.io.*;

public class PatientRecordSystemMenuRobust extends PatientRecordSystemMenu{
    // The following methods are declared as static as they belong to the class rather than an instance of it.
    // The keyboard is declared as final as it is unchanging
    private static final BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    private static PatientRecordSystem prs = new PatientRecordSystem();

    public static void main(String[] args) { // exception handling now included for main menu
        try {
            runMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred with running the main menu: " + e.getMessage());
        } finally {
            try {
                keyboard.close(); // catches potential keyboard closing problems
            } catch (IOException e) {
                System.out.println("Unable to close keyboard: " + e.getMessage());
            }
        }
    }

    // all of the original menu's methods are overloaded within this robust class, in order to add more robustness and exception throwing/checking/handling
    // to ensure the system runs much more smoothly and prevents system crashing.
    private static void runMainMenu() {
        try {
            boolean start = true; 
            while (start) {
                displayMenu();
                String option = getUserInput("Please enter an option (1-9 or D or X): ");
                switch (option.toUpperCase()) { // cases accepted based on user input. Each input corresponds to a particular item/functionality
                    case "1":
                        addMeasurementObservationType();
                        pressEnter(); // pressEnter() method asks the user to press the ENTER key in order to continue the program
                        // to prevent the menu from appearing too quickly and without warning. This gives the user time to read the previous data/messages.
                        break;
                    case "2":
                        addCategoryObservationType();
                        pressEnter();
                        break;
                    case "3":
                        addPatient();
                        pressEnter();
                        break;
                    case "4":
                        addMeasurementObservation();
                        pressEnter();
                        break;
                    case "5":
                        addCategoryObservation();
                        pressEnter();
                        break;
                    case "6":
                        displayObservationDetails();
                        pressEnter();
                        break;
                    case "7":
                        displayPatientRecord();
                        pressEnter();
                        break;
                    case "8":
                        saveData();
                        pressEnter();
                        break;
                    case "9":
                        loadData();
                        pressEnter();
                        break;
                    case "D":
                        displayAllData();
                        pressEnter();
                        break;
                    case "X":
                        System.out.println("Thank you. Goodbye!");
                        // The entire program ends as the boolean value that runs the while loop is set to false, triggering the closing of the system. 
                        // The switch functionality also ceases, leading to a program exit also.
                        start = false;
                        break;
                    default: // if the input does not match with any of the above cases, the default is called
                        System.out.println("Error: Invalid option. Look at the menu options and try again!");
                        pressEnter();
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error with adding and/or loading methods in the main menu.");
        }
        catch (FailedSearchException e) // Custom exception caught
        {
            System.out.println("Failed search has occurred with methods in the main menu.");
        }
        catch (Exception e)
        {
            System.out.println("Error with saving and/or loading data in the main menu");
        }
    }


    private static void displayMenu() { // display saved within a separate method to improve accessibility and presentation
        System.out.println("============================");
        System.out.println("   PATIENT RECORD SYSTEM");
        System.out.println("============================");
        System.out.println("1. Add a measurement observation type");
        System.out.println("2. Add a category observation type");
        System.out.println("3. Add a patient");
        System.out.println("4. Add a measurement observation");
        System.out.println("5. Add a category observation");
        System.out.println("6. Display details of an observation type");
        System.out.println("7. Display a patient record by the patient id");
        System.out.println("8. Save data");
        System.out.println("9. Load data");
        System.out.println("D. Display all data for inspection");
        System.out.println("X. Exit");
    }

    private static String getUserInput(String prompt) { // method to obtain user input, as to avoid repetition
        System.out.print(prompt);
        try {
            return keyboard.readLine();
        } catch (IOException e) { // catches issues from inability to gather user response
            System.out.println("Error reading user input: " + e.getMessage());
            return "";
        }
    }

    private static void addMeasurementObservationType() throws IOException { 
        // all values within the system are converted to uppercase, to improve consistency and to ensure 
        // that cases (such as lower or upper) are irrelevant when searching, saving and/or dealing with data
        String id = getUserInput("Enter observation type code: ");
        String code = getUserInput("Enter observation type name: ");
        String unit = getUserInput("Enter observation type unit: ");
        prs.addMeasurementObservationType(id.toUpperCase(), code.toUpperCase(), unit.toUpperCase());
    }

    private static void addCategoryObservationType() throws IOException {
        try 
        {
            String code = getUserInput("Enter observation type code: ");
            String name = getUserInput("Enter observation type name: ");

            int number = Integer.parseInt(getUserInput("Enter number of categories: "));
            String[] categories = new String[number];

            for (int i = 0; i < number; i++) {
                String categoryName = getUserInput("Enter category number " + (i + 1) + ": ");
                categories[i] = categoryName.toUpperCase();
            }
            prs.addCategoryObservationType(code.toUpperCase(), name.toUpperCase(), categories);
        }
        catch (NumberFormatException e) { // if the input given for the number of categories is not an integer value, a message is thrown and the observation is not added.
            System.out.println("Invalid input for number of categories. Whole integer was expected.");
            return;
        }
    }

    private static void addPatient() throws IOException {
        String id = getUserInput("Enter Patient ID: ").toUpperCase();
        String name = getUserInput("Enter Patient Name: ").toUpperCase();
        prs.addPatient(id, name);
    }

    private static void addMeasurementObservation() throws IOException {
        String patientId = getUserInput("Enter Patient ID: ").toUpperCase();
        String observationCode = getUserInput("Enter observation type code: ").toUpperCase();

        try {
            // ensures that the value of 'value' is a double
            double value = Double.parseDouble(getUserInput("Enter observation type value: "));
            prs.addMeasurementObservation(patientId.toUpperCase(), observationCode.toUpperCase(), value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Input for value must be a double, decimal or whole integer. Please try again");
        }
    }

    private static void addCategoryObservation() throws IOException {
        String patientId = getUserInput("Enter Patient ID: ").toUpperCase();
        String observationCode = getUserInput("Enter observation type code: ").toUpperCase();
        String category = getUserInput("Enter observation type value/category: ").toUpperCase();
        prs.addCategoryObservation(patientId.toUpperCase(), observationCode.toUpperCase(), category.toUpperCase());
    }

    private static void displayObservationDetails() throws IOException {
        // this method uses the correct observation search by first checking whether the desired observation is 
        // of a category type or a measurement type. Based on the answer, and the code inputted by user, it 
        // will use/display the correct method and results.
        String type = getUserInput("Enter 'C' to search Category patient observations or 'M' to search Measurement patient observations: ");
        if (type.equalsIgnoreCase("C")) {
            String code = getUserInput("Enter code: ");
            prs.searchCategoryObs(code);
        } else if (type.equalsIgnoreCase("M")) {
            String code = getUserInput("Enter code: ");
            prs.searchMeasurementObs(code.toUpperCase());
        } else {
            System.out.println("Invalid input was given. Only 'M' or 'C' is accepted. Try again");
        }
    }

    private static void displayPatientRecord() throws FailedSearchException, IOException {
        String id = getUserInput("Enter Patient ID to search for: ");
        prs.searchPatientDetails(id.toUpperCase());
    }

    private static void saveData() throws Exception{
        prs.saveData();
    }

    private static void loadData() throws Exception{
        try {
            PatientRecordSystem newPrs = new PatientRecordSystem(); // the system is reset, so that the new values can be inputted.
            // the reset occurs before the data is loaded in order to avoid duplicate variables/codes, or any data clashes.
            prs = newPrs;
            newPrs.loadData();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
    }

    private static void displayAllData() throws Exception{
        prs.displayData();
    }

    private static void pressEnter() throws Exception { // method for 'ENTER' case. This function ensures the system is readable and data reading is paced/easy
        System.out.println("\n");
        boolean end = false; // boolean loop created to ensure the keyboard accepts 'ENTER' as input only.
        String enter = getUserInput("Press ENTER to continue: ");
        while (!end) {
            if (enter.equals("")) {
                end = true; // if ENTER is given as input, the while loop ends as start is set to false and the menu reappears.
            } else {
                enter = getUserInput("Incorrect Input. Press ENTER to continue: ");
            }
        }
    }
}




