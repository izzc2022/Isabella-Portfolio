import java.io.*;
// the original menu class, before the robust (and better) menu was developed
public class PatientRecordSystemMenu {
    private static final BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    private static PatientRecordSystem prs = new PatientRecordSystem();

    public static void main(String[] args) throws Exception {
        boolean start = true;
        while (start) {
            displayMenu();
            String option = getUserInput("Please enter an option (1-9 or D or X): ");
            switch (option.toUpperCase()) {
                case "1":
                    addMeasurementObservationType();
                    break;
                case "2":
                    addCategoryObservationType();
                    break;
                case "3":
                    addPatient();
                    break;
                case "4":
                    addMeasurementObservation();
                    break;
                case "5":
                    addCategoryObservation();
                    break;
                case "6":
                    displayObservationTypeDetails();
                    break;
                case "7":
                    displayPatientRecord();
                    break;
                case "8":
                    saveData();
                    break;
                case "9":
                    loadData();
                    break;
                case "D":
                    displayAllData();
                    break;
                case "X":
                    System.out.println("Thank you. Goodbye!");
                    start = false;
                    break;
                default:
                    System.out.println("Error: Invalid option. Look at the list and try again!");
            }
        }
        keyboard.close();
    }

    private static void displayMenu() {
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

    private static String getUserInput(String prompt) throws IOException {
        System.out.print(prompt);
        return keyboard.readLine();
    }

    private static void addMeasurementObservationType() throws IOException {
        String id = getUserInput("Enter observation type code: ");
        String code = getUserInput("Enter observation type name: ");
        String unit = getUserInput("Enter observation type unit: ");
        prs.addMeasurementObservationType(id.toUpperCase(), code.toUpperCase(), unit.toUpperCase());
    }

    private static void addCategoryObservationType() throws IOException {
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

    private static void addPatient() throws IOException {
        String id = getUserInput("Enter Patient ID: ").toUpperCase();
        String name = getUserInput("Enter Patient Name: ").toUpperCase();
        prs.addPatient(id, name);
    }

    private static void addMeasurementObservation() throws IOException {
        String patientId = getUserInput("Enter Patient ID: ").toUpperCase();
        String observationCode = getUserInput("Enter observation type code: ").toUpperCase();

        try {
            double value = Double.parseDouble(getUserInput("Enter observation type value: "));
            prs.addMeasurementObservation(patientId.toUpperCase(), observationCode.toUpperCase(), value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again");
        }
    }

    private static void addCategoryObservation() throws IOException {
        String patientId = getUserInput("Enter Patient ID: ").toUpperCase();
        String observationCode = getUserInput("Enter observation type code: ").toUpperCase();
        String category = getUserInput("Enter observation type value/category: ").toUpperCase();
        prs.addCategoryObservation(patientId.toUpperCase(), observationCode.toUpperCase(), category.toUpperCase());
    }

    private static void displayObservationTypeDetails() throws IOException {
        String type = getUserInput("Enter 'C' to search Category type or 'M' to search Measurement type: ");
        if (type.equalsIgnoreCase("C")) {
            String code = getUserInput("Enter code: ");
            prs.searchCategoryObs(code);
        } else if (type.equalsIgnoreCase("M")) {
            String code = getUserInput("Enter code: ");
            prs.searchMeasurementObs(code.toUpperCase());
        } else {
            System.out.println("Invalid input. Try again");
        }
    }

    private static void displayPatientRecord() throws FailedSearchException, IOException {
        String id = getUserInput("Enter Patient ID to search for: ");
        prs.searchPatientDetails(id.toUpperCase());
    }

    private static void saveData() throws Exception{
        prs.saveData();
    }

    private static void loadData() {
        try {
            PatientRecordSystem newPrs = new PatientRecordSystem();
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
}