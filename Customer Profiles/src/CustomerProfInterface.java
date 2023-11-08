import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

public class CustomerProfInterface
{
    private String adminID;
    private CustomerProfDB db;
    private String file;

    public CustomerProfInterface(String f) //Check
    {
        this.db = new CustomerProfDB(f);
        file = f;
        Scanner scan = new Scanner(System.in);
        getUserChoice();


    }

    public void getUserChoice() // Check
    {
        Scanner scan = new Scanner(System.in);
        boolean b = true;
        while (b)
        {
            System.out.println("please enter your adminID");
            adminID = scan.nextLine();
            System.out.println("<<<<<<<<Available commands>>>>>>>");
            System.out.println("--------------------------------------------");
            System.out.println("Find            = Find customer profile");
            System.out.println("Update          = update customer profile");
            System.out.println("Display All     = display all customer profiles");
            System.out.println("Create          = Create a new customer profile");
            System.out.println("Write           = Write all profiles into .txt file");
            System.out.println("Delete          = Delete customer profile");
            System.out.println("Quit            = to quit");
            System.out.println("--------------------------------------------");
            System.out.println("Waiting for your command");


            String userChoice = scan.nextLine();
            userChoice = userChoice.toUpperCase();

            if (userChoice.equals("QUIT")) {
                b = false;
            }else if(userChoice.equals("FIND")){
                findCustomerProf();

            }else if(userChoice.equals("UPDATE")){
                updateCustomerProf();

            }else if(userChoice.equals("DISPLAY ALL")){
                displayAllCustomerProf();

            }else if(userChoice.equals("CREATE")){
                createNewCustomerProf();

            }else if(userChoice.equals("WRITE")){
                writeToDB();

            }else if(userChoice.equals("DELETE")){
                deleteCustomerProf();
            }else{
                System.out.println("Error - Unable to read command");
            }
        }

        System.out.println("Good bye");



    }
    public void deleteCustomerProf() //Check
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the Last name of the person you want to delete");
        String lName = s.nextLine();
        if(db.deleteProfile(adminID, lName))
            System.out.println("Customer successfully deleted");
        else
            System.out.println("User was not deleted, make sure adminID and Last name were entered properly");
    }
    public void findCustomerProf() //Check
    {
        Scanner scan = new Scanner(System.in);
        String lastName;
        System.out.println("Enter person last name");
        lastName = scan.nextLine();

        CustomerProf c1 = db.findProfile(adminID,lastName);
        displayCustomerProf(c1);



    }
    public void updateCustomerProf() //Check
    {
        Scanner scan = new Scanner(System.in);
        String lastName;
        System.out.println("Enter last name of the profile you wish to update");
        lastName = scan.nextLine();
        int index = this.db.FindIndex(adminID,lastName);
        if (index > -1)
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter Address:");
            String address = s.nextLine();
            this.db.findProfile(adminID, lastName).updateAddress(address);
            System.out.println("Please enter phone number:");
            String phone = s.nextLine();
            this.db.findProfile(adminID, lastName).updatePhone(phone);
            System.out.println("Please enter customer status(Active or Inactive):");
            String St = s.nextLine();
            this.db.findProfile(adminID, lastName).updateStatus(St);
            System.out.println("Please enter Customers use(Personal, Bussiness, or Both):");
            String use = s.nextLine();
            this.db.findProfile(adminID, lastName).updateUse(use);
            VehicleInfo vehicle = createNewVehicleInfo();

            this.db.findProfile(adminID, lastName).updateAddress(address);
            System.out.println("Profile has been successfully updated");

        }else{
            System.out.println("Error - unable to find "+lastName);

        }



    }
    public void displayCustomerProf(CustomerProf cust) //Check
    {
        if (cust == null)
        {
            System.out.println("Error - unable to find person");
        }
        else{
            cust.printCustomerInfo();
        }

    }
    public void displayAllCustomerProf()
    {
        for(int x = 0; x < this.db.getNumCustomer(); x++)
        {
            CustomerProf cust = this.db.getCustomerAt(x);
            if(cust.getadminID().equals(adminID))
            {
                displayCustomerProf(cust);
            }
        }
    }
    public void writeToDB()
    {
        this.db.writeAllCustomerProf();

    }
    public void createNewCustomerProf()//Check
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter New adminId:");
        String NewadminId = s.nextLine();
        System.out.println("Please enter First Name:");
        String fName = s.nextLine();
        System.out.println("Please enter Last Name:");
        String lName = s.nextLine();
        System.out.println("Please enter Address:");
        String address = s.nextLine();
        System.out.println("Please enter phone number:");
        String phone = s.nextLine();
        System.out.println("Please enter income:");
        float income = Float.parseFloat(s.nextLine());
        System.out.println("Please enter customer status(Active or Inactive):");
        String St = s.nextLine();
        System.out.println("Please enter Customers use(Personal, Bussiness, or Both:");
        String use = s.nextLine();
        VehicleInfo vehicle = createNewVehicleInfo();

        CustomerProf cust = new CustomerProf(NewadminId, fName, lName, address, phone, income, St, use, vehicle);

        db.insertNewProfile(cust);

        System.out.println("New customer has been added to the database");


    }
    public VehicleInfo createNewVehicleInfo()//Check
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Now you will be entering the Vehicles info");
        System.out.println("What is the name of the model");
        String model = s.nextLine();
        System.out.println("What is the year of the vehicle");
        String year = s.nextLine();
        System.out.println("What is the vehicle type(sedan, hatchback, luxury, sport, other)");
        String type = s.nextLine(); //set up to check if specific types are entered
        System.out.println("What is the method(new, certified pre-owned, used, other)");
        String method = s.nextLine();//set up to check if specifics are entered
        VehicleInfo v = new VehicleInfo(model, year, type, method);
        return v;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file path");
        String path = scan.nextLine();
        CustomerProfInterface CP1 = new CustomerProfInterface(path);
        //C:\\Users\\yeyin\\OneDrive\\Desktop\\Test1.txt
        //C:\Users\sivey\interfaceTest.txt
    }


}
