import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayAllProfilesGUI implements ActionListener {
    //Declaring labels
    private static CustomerProfDB dataBase;
    private static JLabel adminIdL;
    private static JLabel firstNameL;
    private static JLabel lastNameL;
    private static JLabel addressL;
    private static JLabel PhoneL;
    private static JLabel IncomeL;
    private static JLabel useL;
    private static JLabel statusL;
    private static JLabel modelL;
    private static JLabel yearL;
    private static JLabel typeL;
    private static JLabel methodL;

    //Declaring values
    private static JLabel adminIdV;
    private static JLabel firstNameV;
    private static JLabel lastNameV;
    private static JLabel addressV;
    private static JLabel PhoneV;
    private static JLabel IncomeV;
    private static JLabel useV;
    private static JLabel statusV;
    private static JLabel modelV;
    private static JLabel yearV;
    private static JLabel typeV;
    private static JLabel methodV;

    //Buttons
    private static JButton nextButton;
    private static JButton previousButton;
    private static JButton mainmenuButton;

    private String adminID = "None";
    private String firstName= "None";
    private String lastName= "None";
    private String address= "None";
    private String phone= "None";
    private float income= 0;
    private String status= "None";
    private String use= "None";
    private String model= "None";
    private String year= "None";
    private String type= "None";
    private String method= "None";

    private boolean bProfilesExist;
    private int currentIndex = 0;
    private CustomerProf currentProfile;
    private JFrame frame1 = new JFrame();
    private String curAdminId;
    String fName;
    ArrayList<CustomerProf> curAdminProfiles;




    public DisplayAllProfilesGUI(CustomerProfDB database,String fname,String curAdminId,int displayAt)
    {
        this.dataBase = database;
        this.fName = fname;
        this.curAdminId = curAdminId;
        this.curAdminProfiles = new ArrayList<CustomerProf>();

        for(int x = 0; x < this.dataBase.getNumCustomer(); x++)
        {
            CustomerProf cust = this.dataBase.getCustomerAt(x);
            if(cust.getadminID().equals(curAdminId))
            {
                System.out.println("Found " + cust.getFirstName());
                this.curAdminProfiles.add(cust);
            }
        }
        JPanel panel1 = new JPanel();
        frame1.setTitle("Display All Profiles");
        frame1.setSize(350,360);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);
        frame1.setLocationRelativeTo(null);
        panel1.setLayout(null);

        if (curAdminProfiles.size() != 0)
        {
            bProfilesExist = true;
        }else {
            bProfilesExist = false;
        }

        //Setting up attributes
        if (bProfilesExist = true)
        {
            //C:\\Users\\yeyin\\Desktop\\Test.txt
            System.out.println(curAdminProfiles.size());
            System.out.println(currentIndex);

            try {
                currentProfile = curAdminProfiles.get(currentIndex);
                adminID = currentProfile.getadminID();
                firstName = currentProfile.getFirstName();
                lastName = currentProfile.getLastName();
                address = currentProfile.getAddress();
                phone = currentProfile.getPhone();
                income = currentProfile.getIncome();
                use = currentProfile.getUse();
                status = currentProfile.getStatus();
                VehicleInfo vehInfo = currentProfile.getVehicleInfo();
                model = vehInfo.getModel();
                year = vehInfo.getYear();
                type = vehInfo.getType();
                method = vehInfo.getMethod();
                currentIndex++;

            }catch (Exception e)
            {
                System.out.println("Error, no profile exist");
            }



        }else {
            System.out.println("Error, no profile exist");

        }



        //Setting up AdminId
        adminIdL = new JLabel("AdminID : ");
        adminIdL.setBounds(110,10,80,25);
        adminIdV = new JLabel(adminID);
        adminIdV.setBounds(200,10,80,25);
        panel1.add(adminIdV);
        panel1.add(adminIdL);


        //Setting up first name
        firstNameL = new JLabel("First Name : ",SwingConstants.LEFT);
        firstNameL.setBounds(110,30,200,25);
        firstNameV = new JLabel(firstName);
        firstNameV.setBounds(200,30,200,25);
        panel1.add(firstNameL);
        panel1.add(firstNameV);


        //Setting up last name
        lastNameL = new JLabel("Last Name : ",SwingConstants.LEFT);
        lastNameL.setBounds(110,50,200,25);
        lastNameV = new JLabel(lastName);
        lastNameV.setBounds(200,50,200,25);
        panel1.add(lastNameL);
        panel1.add(lastNameV);


        //Setting up address
        addressL = new JLabel("First Name : ",SwingConstants.LEFT);
        addressL.setBounds(110,70,200,25);
        addressV = new JLabel(address);
        addressV.setBounds(200,70,200,25);
        panel1.add(addressL);
        panel1.add(addressV);


        //Setting up phone
        PhoneL = new JLabel("Phone# : ",SwingConstants.LEFT);
        PhoneL.setBounds(110,90,200,25);
        PhoneV = new JLabel(phone);
        PhoneV.setBounds(200,90,200,25);
        panel1.add(PhoneL);
        panel1.add(PhoneV);


        //Setting up income
        IncomeL = new JLabel("Income : ",SwingConstants.LEFT);
        IncomeL.setBounds(110,110,200,25);
        IncomeV = new JLabel(String.valueOf(income));
        IncomeV.setBounds(200,110,200,25);
        panel1.add(IncomeL);
        panel1.add(IncomeV);


        //Setting up use
        useL = new JLabel("Use : ",SwingConstants.LEFT);
        useL.setBounds(110,130,200,25);
        useV = new JLabel(use);
        useV.setBounds(200,130,200,25);
        panel1.add(useL);
        panel1.add(useV);


        //Setting up status
        statusL = new JLabel("status : ",SwingConstants.LEFT);
        statusL.setBounds(110,150,200,25);
        statusV = new JLabel(status);
        statusV.setBounds(200,150,200,25);
        panel1.add(statusL);
        panel1.add(statusV);


        //Setting up Model
        modelL = new JLabel("Model : ",SwingConstants.LEFT);
        modelL.setBounds(110,170,200,25);
        modelV = new JLabel(model);
        modelV.setBounds(200,170,200,25);
        panel1.add(modelL);
        panel1.add(modelV);


        //Setting up Years
        yearL = new JLabel("Years : ",SwingConstants.LEFT);
        yearL.setBounds(110,190,200,25);
        yearV = new JLabel(year);
        yearV.setBounds(200,190,200,25);
        panel1.add(yearL);
        panel1.add(yearV);


        //Setting up Type
        typeL = new JLabel("Type : ",SwingConstants.LEFT);
        typeL.setBounds(110,210,200,25);
        typeV = new JLabel(type);
        typeV.setBounds(200,210,200,25);
        panel1.add(typeL);
        panel1.add(typeV);


        //Setting up Method
        methodL = new JLabel("Method : ",SwingConstants.LEFT);
        methodL.setBounds(110,230,200,25);
        methodV = new JLabel(method);
        methodV.setBounds(200,230,200,25);
        panel1.add(methodL);
        panel1.add(methodV);

        //Setting up button
        nextButton = new JButton("NEXT");
        nextButton.setBounds(190,270,70,25);
        nextButton.addActionListener(this);
        panel1.add(nextButton);

        previousButton = new JButton("PREV");
        previousButton.setBounds(100,270,70,25);
        previousButton.addActionListener(this);
        panel1.add(previousButton);

        mainmenuButton = new JButton("MAIN");
        mainmenuButton.setBounds(145,300,70,25);
        mainmenuButton.addActionListener(this);
        panel1.add(mainmenuButton);




        frame1.setVisible(true);


    }


    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton)
        {
            if (bProfilesExist = true && currentIndex  < (curAdminProfiles.size() - 1)) {
                currentIndex++;

                currentProfile = curAdminProfiles.get(currentIndex);
                adminID = currentProfile.getadminID();
                firstName = currentProfile.getFirstName();
                lastName = currentProfile.getLastName();
                address = currentProfile.getAddress();
                phone = currentProfile.getPhone();
                income = currentProfile.getIncome();
                use = currentProfile.getUse();
                status = currentProfile.getStatus();
                VehicleInfo vehInfo = currentProfile.getVehicleInfo();
                model = vehInfo.getModel();
                year = vehInfo.getYear();
                type = vehInfo.getType();
                method = vehInfo.getMethod();


                //Setting profile values
                adminIdV.setText(adminID);
                firstNameV.setText(firstName);
                lastNameV.setText(lastName);
                addressV.setText(address);
                PhoneV.setText(phone);
                IncomeV.setText(String.valueOf(income));
                useV.setText(use);
                statusV.setText(status);
                modelV.setText(model);
                yearV.setText(year);
                typeV.setText(type);
                methodV.setText(method);





            } else{
                System.out.println("Error, no more profile");
            }


        }else if(e.getSource() == previousButton)
        {
            if (bProfilesExist = true && (currentIndex - 1) >= 0) {
                //Getting previous profile
                currentIndex--;
                currentProfile = curAdminProfiles.get(currentIndex);
                adminID = currentProfile.getadminID();
                firstName = currentProfile.getFirstName();
                lastName = currentProfile.getLastName();
                address = currentProfile.getAddress();
                phone = currentProfile.getPhone();
                income = currentProfile.getIncome();
                use = currentProfile.getUse();
                status = currentProfile.getStatus();
                VehicleInfo vehInfo = currentProfile.getVehicleInfo();
                model = vehInfo.getModel();
                year = vehInfo.getYear();
                type = vehInfo.getType();
                method = vehInfo.getMethod();


                //Setting profile values
                adminIdV.setText(adminID);
                firstNameV.setText(firstName);
                lastNameV.setText(lastName);
                addressV.setText(address);
                PhoneV.setText(phone);
                IncomeV.setText(String.valueOf(income));
                useV.setText(use);
                statusV.setText(status);
                modelV.setText(model);
                yearV.setText(year);
                typeV.setText(type);
                methodV.setText(method);


            } else{
                System.out.println("Error, at 0 index");
            }

        }else if(e.getSource() == mainmenuButton)
        {

            MainMenuGUI mainGui = new MainMenuGUI(fName, "None");
            frame1.dispose();

        }

    }
}
