import java.util.Scanner;
import java.io.*;

public class PatientRecordSystem 
{
    private Patient[] patients;
    private ObservationType[] observationTypes;
    private int patientCount;
    private int observationCount;

    public PatientRecordSystem() {
        // sets the allowed capacity/maximum of each array
        patients = new Patient[100];
        patientCount = 0;
        observationTypes = new ObservationType[50];
        observationCount = 0;
    }

    public void addPatient(String id, String name) {
        id = id.toUpperCase();
        name = name.toUpperCase();

        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Invalid patient name or ID.");
            return;
        }
        try {
            if (searchPatient(id) == -1)
            {
                patients[patientCount] = new Patient(id, name);
                patientCount++;
                System.out.println("SUCCESS: Patient " + id + " added");
            }
            else if (searchPatient(id) != -1) {
                System.out.println("Patient with id " + id + " already exists!");
            }
            else 
            {
                // custom exception is used to improve clarity
                throw new FailedSearchException("Failed patient search and/or adding. Please try again");
            }
        }
        catch (FailedSearchException e)
        {
            System.out.println("Failed patient search and/or adding. Please try again");
        }

    }   

    public int searchPatient(String id)
    {
        // patient search is used in multiple methods to retrieve index information, as well as data on whether the id currently exists or not.
        for (int i = 0; i < patientCount; i++)
        {
            if (patients[i].getID().equals(id))
            {
                return i;
            }
        }
        return -1;
    }

    public void addCategoryObservation(String patientId, String observationCode, String category) {
        patientId = patientId.toUpperCase();
        observationCode = observationCode.toUpperCase();
        category = category.toUpperCase();

        int patientIndex = searchPatient(patientId);
        // result of patient search
        if (patientIndex == -1) {
            System.out.println("Patient not found when adding category observation.");
            return;
        }
        // use of other classes to retrieve data/information
        ObservationType observationType = getType(observationCode);
        // checks that the observation type is indeed of the CategoryObservationType through the provided observation code
        if (!(observationType instanceof CategoryObservationType)) {
            System.out.println("Invalid observation type. Must be of type category.");
            return;
        }

        CategoryObservationType categoryType = (CategoryObservationType) observationType;
        String[] categories = categoryType.getCategories();
        
        // needs to check whether the category given actually exists for the observation code given.
        boolean categoryMatch = false;
        for (int c = 0; c < categories.length; c++) {
            if (categories[c].equals(category)) {
                categoryMatch = true;
                break;
            }
        }
        
        // if there is no match, then an error message is printed, informing the user that the category presented is not valid.
        if (!categoryMatch) {
            System.out.println("Invalid category. Must be one of the categories that exists for this code/type.");
            return;
        }

        if (patients[patientIndex].getObservations().length > 0) {
            Observation[] temp = patients[patientIndex].getObservations();
            for (int j = 0; j < temp.length; j++) {
                // checks whether an observation of that code already exists for that specific patient, as patients can only have one observation under a particular code
                if (temp[j] != null && temp[j].getType() != null && temp[j].getType().getCode().equals(observationCode)) {
                    System.out.println("An observation of code " + observationCode + " already exists for patient with id " + patientId + ".");
                    return;
                }
            }
        }
        // the observation is added, and the observation adding method is called from withon the Patient class
        CategoryObservation observation = new CategoryObservation(observationType, category);
        patients[patientIndex].addObservation(observation);
    }
    
    public void addCategoryObservationType(String id, String name, String[] categories) {
        id = id.toUpperCase();
        name = name.toUpperCase();
        for (int i = 0; i < categories.length; i++)
        {
            categories[i] = categories[i].toUpperCase();
        }

        String observationExist = searchObs(id);
        if (observationExist != null) {
            System.out.println("Observation Code " + id + " already exists in system.");
            return;
        } else {
            ObservationType observationType = new CategoryObservationType(id, name, categories);
            observationTypes[observationCount] = observationType;
            observationCount++;
        }
    }
    
    public void addMeasurementObservation(String patientId, String observationCode, double value) {
        patientId = patientId.toUpperCase();
        observationCode = observationCode.toUpperCase();

        int patientIndex = searchPatient(patientId);
        if (patientIndex == -1) {
            System.out.println("Patient not found when adding measurement observation.");
            return;
        }
        ObservationType observationType = getType(observationCode);
        if (!(observationType instanceof MeasurementObservationType)) {
            System.out.println("Invalid observation type. Must be of type measurement.");
            return;
        }
        if (patients[patientIndex].getObservations().length > 0) {
            Observation[] temp = patients[patientIndex].getObservations();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] != null && temp[j].getType() != null && temp[j].getType().getCode().equals(observationCode)) {
                    System.out.println("An observation of code " + observationCode + " already exists for patient with id " + patientId + ".");
                    return;
                }
            }
        }
        MeasurementObservation observation = new MeasurementObservation(observationType, value);
        patients[patientIndex].addObservation(observation);
    }
    
    public void addMeasurementObservationType(String id, String name, String unit) {
        id = id.toUpperCase();
        name = name.toUpperCase();
        unit = unit.toUpperCase();

        String observationExist = searchObs(id);
        // the id is unique and can only exist once within the current system.
        if (observationExist != null) {
            System.out.println("Observation Code " + id + " already exists in system.");
            return;
        }
        else 
        {
            // the type is successfuly added, and the array count is increased
            ObservationType observationType = new MeasurementObservationType(id, name, unit);
            observationTypes[observationCount] = observationType;
            observationCount++;
        }
    }

    public void searchPatientDetails(String id) throws FailedSearchException {
        boolean patientFound = false;
        try {
            for (int i = 0; i < patientCount; i++) {
                // searches for patient with matching id and, if found, returns all attached observations and information
                if (patients[i].getID().equals(id)) {
                    System.out.println("Patient found:");
                    System.out.println("Patient ID: " + id);
                    System.out.println("Name: " + patients[i].getName());
                    System.out.println('\n');
    
                    patientFound = true;
                    break;
                }
            }
            if (!patientFound) {
                // throws custom exception if patient is not found
                throw new FailedSearchException("Patient not found while searching.");
            }
        } catch (FailedSearchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchCategoryObs(String code) {
        // searches for category observations attached to current patients
        try {
            boolean obsFound = false;
    
            for (int i = 0; i < patientCount; i++) {
                // first iterates through patients before iterating through all observations and checking each type
                if (patients[i].getObservations().length > 0) {
                    Observation[] temp = patients[i].getObservations();
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] instanceof CategoryObservation && temp[j].getType().getCode().equals(code.toUpperCase()))
                        {
                            CategoryObservation categoryObs = (CategoryObservation) temp[j];
                            System.out.println("\nObservation found:");
                            System.out.println("Patient ID: " + patients[i].getName());
                            System.out.println("Observation Details: " + temp[j].getType() + "; " + categoryObs.getCategory());
                            System.out.println('\n');
        
                            obsFound = true;
                            // loop is broken, as correct observation has been located
                            break;
                        } 
                    }
                }
            }
            if (!obsFound) {
                // throws custom exception if category observation is not found
                throw new FailedSearchException("Category Observation not found while searching.");
            }
        } 
        catch (FailedSearchException e) {
                System.out.println(e.getMessage());
        }
    }

    public void searchMeasurementObs(String code) {
        try {
            boolean obsFound = false;
    
            for (int i = 0; i < patientCount; i++) {
                if (patients[i].getObservations().length > 0) {
                    Observation[] temp = patients[i].getObservations();
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] instanceof MeasurementObservation && temp[j].getType().getCode().equals(code.toUpperCase()))
                        {
                            MeasurementObservation measurementObs = (MeasurementObservation) temp[j];
                            System.out.println("\nObservation found:");
                            System.out.println("Patient ID: " + patients[i].getName());
                            System.out.println("Observation Details: " + temp[j].getType() + "; " + measurementObs.getValue());
                            System.out.println('\n');
        
                            obsFound = true;
                            // loop is broken, as correct observation has been located
                            break;
                        } 
                    }
                }
            }
            if (!obsFound) {
                throw new FailedSearchException("Measurement Observation not found while searching.");
            }
        } 
        catch (FailedSearchException e) {
                System.out.println(e.getMessage());
        }
    }

    public String searchObs(String code) {
        for (int i = 0; i < observationCount; i++) {
            // checks if observation code exists within the observation array
            if (observationTypes[i].getCode().equals(code)) {
                return "Observation exists";
            }
        }
        return null;
    }

    public void saveData() throws Exception{
        // This method checks for, or allows for, the existence of files to be manipulated.
        // The files are first set up to be dealt with/saved to
        File fileMeas = new File("PRS-MeasurementObservationTypes.txt");
        File fileCat = new File("PRS-CategoryObservationTypes.txt");
        File filePat = new File("PRS-Patients.txt");
        File fileMO = new File("PRS-MeasurementObservations.txt");
        File fileCO = new File("PRS-CategoryObservations.txt");
        try {
            // this is useful for first time system users. If the user/platform is new and files are being created for the first time, a message prints.
            // Otherwise, for continued users, no message outputs.
            boolean createdMT = fileMeas.createNewFile();
            boolean createdCT = fileCat.createNewFile();
            boolean createdP = filePat.createNewFile();
            boolean createdMO = fileMO.createNewFile();
            boolean createdCO = fileCO.createNewFile();
            if (createdMT && createdCT && createdP && createdMO && createdCO) {
                System.out.println("Files created");
            } 
        } catch (IOException e) {
            // catches file existence-related errors
            System.err.format("Error with file/s existence: ");
        }

        try {
            // Data is added to the Category Observation Types file
            BufferedWriter outCat = new BufferedWriter(new FileWriter("PRS-CategoryObservationTypes.txt", false));
            for (int i = 0; i < observationCount; i++) {
                ObservationType observationType = observationTypes[i];
                if (observationType instanceof CategoryObservationType) {
                    CategoryObservationType categoryObservationType = (CategoryObservationType) observationType;
                    String text = categoryObservationType.getDetails();
                    String[] categories = categoryObservationType.getCategories();
                    for (int j = 0; j < categories.length; j++) { 
                        // this loop generates the correct number of commas to be displayed, and stops creating commas at the second last array element.
                        if (j < categories.length-1)
                        {
                            text += categories[j] + ", ";
                        }
                        else
                        {
                            text += categories[j];
                        }
                    }
                    outCat.write(text + "\n");
                }
            }
            System.out.println("Data successfully saved to Category Observation Types file");
            outCat.close();
        
            // Data is added to the Measurement Observation Types file
            BufferedWriter outMeas = new BufferedWriter(new FileWriter("PRS-MeasurementObservationTypes.txt", false));
            for (int i = 0; i < observationCount; i++) {
                ObservationType observationType = observationTypes[i];
                if (observationType instanceof MeasurementObservationType) {
                    MeasurementObservationType measurementObservationType = (MeasurementObservationType) observationType;
                    String text = measurementObservationType.getDetails();
                    outMeas.write(text + "\n");
                }
            }
            System.out.println("Data successfully saved to Measurement Observation Types file");
            outMeas.close();
        } catch (IOException e) {
            System.err.format("Error with writing to Observation Type file/s. ");
        }

        try{ 
            // Writes data to Patients file
            BufferedWriter outPat = new BufferedWriter(new FileWriter("PRS-Patients.txt", false));
            for (int i = 0; i < patientCount; i++) {
                String text = "" + patients[i].getDetails() + "\n"; 
                outPat.write(text);
            }
            System.out.println("Data successfully saved to Patients file");
            outPat.close();
        } catch (IOException e) {
            System.err.format("Error with writing to Patient file. ");
        }

        try { // Data is added to the Category Observations file
            BufferedWriter outCO = new BufferedWriter(new FileWriter("PRS-CategoryObservations.txt", false));
            for (int i = 0; i < patientCount; i++) { // iterates throgh patients before iterating through their own observations
                if (patients[i].getObservations().length > 0) {
                    Observation[] temp = patients[i].getObservations();
                    String text = "";
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] instanceof CategoryObservation)
                        {
                            //String observationString = temp[j].toString(); 
                            //observationString = observationString.replace("[Ljava.lang.String@;", ""); 
                            // observationString = observationString.replaceAll("@[^,]*,", "");
                            CategoryObservation categoryObs = (CategoryObservation) temp[j];
                            text +=  patients[i].getID() + "; ";
                            text += categoryObs.getType().getCode() + "; ";
                            text += categoryObs.getCategory() + "\n";
                        }
                    }
                    outCO.write(text);  
                }
            }
            System.out.println("Data successfully saved to Category Observations file");
            outCO.close();
        } 
        catch (IOException e) {
            System.err.format("Error with writing to category observation files. ");
        }

        try { // Data is added to the Measurement Observations file
            BufferedWriter outMO = new BufferedWriter(new FileWriter("PRS-MeasurementObservations.txt", false));
            for (int i = 0; i < patientCount; i++) {
                if (patients[i].getObservations().length > 0) {
                    Observation[] temp = patients[i].getObservations();
                    String text = "";
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j] instanceof MeasurementObservation)
                        {
                            MeasurementObservation measurementObs = (MeasurementObservation) temp[j];
                            text += patients[i].getID() + "; ";
                            text += measurementObs.getType().getCode() + "; ";
                            text += measurementObs.getValue() + "\n";
                        }
                    }
                    outMO.write(text);
                }
            }
            System.out.println("Data successfully saved to Measurement Observations file");
            outMO.close();
        } 
        catch (IOException e) 
        {
            System.err.format("Error with writing to measurement observation files. ");
        }
    }

    public void displayData() throws Exception
    {
        try 
        {
            // as the formatting was properly done during the writing stage, only readline operations occur here, until the files end is reached.
            File filePat = new File("PRS-Patients.txt");
            Scanner readPat = new Scanner(filePat);
            System.out.println("\nPatients File:");
            while (readPat.hasNextLine()) 
            {
                String data = readPat.nextLine();
                System.out.println(data);
            }
            readPat.close();
        } 
        catch (FileNotFoundException e) 
        {
            // Exception is thrown if the file cannot be retrieved/found
            System.out.println("An error occurred with displaying patient data from file.");
        }

        try 
        {
            File fileMeas = new File("PRS-MeasurementObservationTypes.txt");
            Scanner readMeas = new Scanner(fileMeas);
            System.out.println("\nMeasurement Observation Types File:");
            while (readMeas.hasNextLine()) 
            {
                String data = readMeas.nextLine();
                System.out.println(data);
            }
            readMeas.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred with displaying measurement observation types data from file.");
        }

        try 
        {
            File fileCat = new File("PRS-CategoryObservationTypes.txt");
            Scanner readCat = new Scanner(fileCat);
            System.out.println("\nCategory Observation Types File:");
            while (readCat.hasNextLine()) 
            {
                String data = readCat.nextLine();
                System.out.println(data);
            }
            readCat.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred with displaying category observation types data from file.");
        }

        try 
        {
            File fileMO = new File("PRS-MeasurementObservations.txt");
            Scanner readMO = new Scanner(fileMO);
            System.out.println("\nMeasurement Observations File:");
            while (readMO.hasNextLine()) 
            {
                String data = readMO.nextLine();
                System.out.println(data);
            }
            readMO.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred with displaying measurement observations data from file.");
        }

        try 
        {
            File fileCO = new File("PRS-CategoryObservations.txt");
            Scanner readCO = new Scanner(fileCO);
            System.out.println("\nCategory Observations File:");
            while (readCO.hasNextLine()) 
            {
                String data = readCO.nextLine();
                System.out.println(data);
            }
            readCO.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred with displaying category observations data from file.");
        }
    }

    public void loadData() throws Exception {
        try {
            // Measurement Observation Types file
            Scanner scannerMeas = new Scanner(new FileReader("PRS-MeasurementObservationTypes.txt"));
            while (scannerMeas.hasNextLine()) {
                String inputLine = scannerMeas.nextLine();
                // the ; symbol indicates that a new data element has been reached. A line is split based on this element 
                // and each data element is read in separately. This repeats until end of file
                String[] line = inputLine.split("; ");
                try {
                    String code = line[0].trim();
                    String name = line[1].trim();
                    String unit = line[2].trim();
                    // the add methods are utilised again to create a new instance of the data from the file lines.
                    addMeasurementObservationType(code, name, unit);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // catches the array going out of bounds and prevents crashing.
                    System.err.println("Error with measurement observation types file format.");
                }
            }
            scannerMeas.close();
        } catch (IOException e) {
            System.err.format("An error occurred with loading measurement observation types data from file.");
        }
    
        try {
            // Category Observation Types file
            Scanner scannerCat = new Scanner(new FileReader("PRS-CategoryObservationTypes.txt"));
            while (scannerCat.hasNextLine()) {
                String inputLine = scannerCat.nextLine();
                String[] line = inputLine.split("; ");
                try {
                    String code = line[0].trim();
                    String name = line[1].trim();
                    String category1 = line[2].trim();
    
                    // category array elements are split by their , symbol/element and are then placed into a new array
                    String[] arr = category1.split(", ");
                    addCategoryObservationType(code, name, arr);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error with category observation types file format.");
                }
            }
            scannerCat.close();
        } catch (IOException e) {
            System.err.format("An error occurred with loading category observation types data from file. ");
        }
    
        try {
            // Patients file
            Scanner scannerPat = new Scanner(new FileReader("PRS-Patients.txt"));
            while (scannerPat.hasNextLine()) {
                String inputLine = scannerPat.nextLine();
                String[] line = inputLine.split("; ");
                try {
                    // each line within the file is trimmed by the ; symbol, and is set to an appropriate variable name
                    String id = line[0].trim();
                    String name = line[1].trim();
                    // the variables of each line (each record) are then applied to the addPatient method and enter the PRS system/database
                    addPatient(id, name);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error with patients file format.");
                }
            }
            scannerPat.close();
        } catch (IOException e) {
            System.err.format("An error occurred with loading patients data from file. ");
        }
    
        try {
            // Category Observations file
            Scanner scannerCO = new Scanner(new FileReader("PRS-CategoryObservations.txt"));
            while (scannerCO.hasNextLine()) {
                String inputLine = scannerCO.nextLine();
                String[] line = inputLine.split("; ");
                try {
                    String id = line[0].trim();
                    String code = line[1].trim();
                    String category = line[2].trim();
                    addCategoryObservation(id, code, category);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error with category observations file format.");
                }
            }
            scannerCO.close();
        } catch (IOException e) {
            System.err.format("An error occurred with loading category observations data from file.");
        }
    
        try {
            // Measurement Observations file
            Scanner scannerMO = new Scanner(new FileReader("PRS-MeasurementObservations.txt"));
            while (scannerMO.hasNextLine()) {
                String inputLine = scannerMO.nextLine();
                String[] line = inputLine.split("; ");
                try {
                    String id = line[0].trim();
                    String code = line[1].trim();
                    // passes in a double value
                    double value = Double.parseDouble(line[2].trim());
                    addMeasurementObservation(id, code, value);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error with measurement observations file format.");
                } catch (NumberFormatException e) {
                    // error is thrown if the value element (expected to be a double) is not a double
                    System.err.println("Invalid value found when a double value was expected.");
                }
            }
            scannerMO.close();
        } catch (IOException e) {
            System.err.format("An error occurred with loading measurement observations data from file. ");
        }        
    }

    private ObservationType getType(String code) {
        // gets the type of the observation. Placed withon a method for simplicity and to avoid repeats.
        for (int i = 0; i < observationCount; i++) {
            if (observationTypes[i].getCode().equals(code)) {
                return observationTypes[i];
            }
        }
        return null;
    }

    public String toString() {
        // toString method used when the PRS system is printed or displayed. Formatted with simplicity in mind.
        // Portrays the patient id, name and observations of each patient, and then all of the data of the observation types.
        System.out.println("\n");
        System.out.println("-----------------------------------");
        System.out.println("   PATIENT RECORD SYSTEM DATA");
        System.out.println("-----------------------------------");
        String text1 = "\nPATIENTS DATA:\n";        
        for (int i = 0; i < patientCount; i++) {
            text1 += "" + patients[i].toString() + "\n";
        }
        String text2 = "\nOBSERVATION TYPES: \n\n";
        for (int i = 0; i < observationCount; i++) {
            if (observationTypes[i] instanceof MeasurementObservationType) 
            // separates the observations by their types, and prints their type alongside the information for user clarity
            {
                text2 += "" + "Measurement Observation Type - " + observationTypes[i].toString() + "\n";
            }
            else 
            // assumed that the second format is "category observation", as no other observation can possibly enter the system other than the two provided and prepped for.
            {
                text2 += "" + "Category Observation Type - " + observationTypes[i].toString() + "\n";
            }
        }
        return text1 + "\n" + text2;
    }
}