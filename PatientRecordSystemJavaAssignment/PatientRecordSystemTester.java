/* Whilst this tester class uses the skeleton provided on the LMS subject page, I have incorporated some of my own tests, trials and information
to ensure I have covered every possible piecfe of possible information, data, exception, issue, error, bug, or likewise. */
public class PatientRecordSystemTester
{
    public static void main(String [] args) throws Exception
    {
        testSaveData();
        testLoadData(); 
    }
    public static void testSaveData() throws Exception
    {
        // Create PatientRecordSystem
        // Add observation types, patients and observations
        PatientRecordSystem prs = new PatientRecordSystem();
        prs.addMeasurementObservationType("T100", "Blood Pressure", "psi");
        String [] categories = {"Group A", "Group B1", "Group B2"};
        prs.addCategoryObservationType("T200", "blood type", categories);
        String [] temp = {"low", "Medium", "high"};
        categories = temp;
        prs.addCategoryObservationType("T300", "stress level", categories);
        prs.addMeasurementObservationType("T400", "height", "cm");
        prs.addPatient("P100", "Smith");
        prs.addPatient("P200", "Adams");
        prs.addMeasurementObservation("P100", "T100", 120);
        prs.addCategoryObservation("P100", "T200", "Group A");

        // my test cases:
        String [] temp2 = {"1","2","3","4"};
        categories = temp2;
        prs.addMeasurementObservationType("T700", "heart rate", "bpm"); // test to see how well string numbers work
        prs.addCategoryObservationType("T500", "pain level", categories);
        prs.addPatient("P300", "jeremy fitzgerald");
        prs.addCategoryObservation("P300", "T500", "2");
        prs.addCategoryObservation("P300", "T500", "4"); // should NOT work, should return an error, as the code for this patient already exists
        prs.addCategoryObservation("P100", "T500", "5"); // should NOT work, as the category value does not exist. Won't throw error, as nothing is created, but DOES depict error message in the main menu.
        prs.addMeasurementObservation("P300", "T100", 87.36);
        prs.addMeasurementObservation("P300", "T100", 56.82); // should NOT work, should return an error, as the code for this patient already exists
        prs.addMeasurementObservation("P300", "T700", 95);

        // save data to file
        prs.saveData();
    }
    public static void testLoadData() throws Exception
    {
        testSaveData();
        PatientRecordSystem prs = new PatientRecordSystem();
        prs.loadData();
        System.out.println(prs);
    }
}
