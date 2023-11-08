import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePart2GUI implements ActionListener {
    private static CustomerProfDB dataBase;
    private static String fName;
    private static String adminId;
    private static String lastName;
    private static String userOption;


    private static JLabel entryLabel;
    private static JTextField updateText;
    private static JComboBox OptionBox;
    private static String[] useBoxOption ={"Personal", "Bussiness", "Both"};
    private static String[] statusBoxOption ={"Active", "Inactive"};
    private static String[] typeBoxOption =  {"sedan", "hatchback", "luxury", "sport", "other"};
    private static String[] methodBoxOption = {"new", "certified pre-owned", "used", "other"};
    private static JButton enterButt;

    private static CustomerProf currentProfile;
    JFrame frame1;



    public UpdatePart2GUI(CustomerProfDB dataBase,CustomerProf currentProfile,String fName,String adminId,String lastName,String userOption )
    {
        this.dataBase = dataBase;
        this.fName = fName;
        this.userOption = userOption;
        this.adminId = adminId;
        this.lastName = lastName;
        this.currentProfile = currentProfile;
        frame1 = new JFrame();
        frame1.setTitle("Update");
        frame1.setSize(200,200);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        frame1.add(panel1);
        frame1.setLocationRelativeTo(null);
        panel1.setLayout(null);

        //Test
        currentProfile.printCustomerInfo();

        entryLabel = new JLabel("");
        entryLabel.setBounds(10,50,85,20);
        panel1.add(entryLabel);

        enterButt = new JButton("UPDATE");
        enterButt.addActionListener(this);
        enterButt.setHorizontalAlignment(JButton.CENTER);
        enterButt.setBounds(20,80,85,25);
        panel1.add(enterButt);

        if(userOption.equals("First Name"))
        {
            entryLabel.setText("FirstName : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            dataBase.writeAllCustomerProf();
            panel1.add(updateText);

        }else if(userOption.equals("Last Name"))
        {
            entryLabel.setText("Last name : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Address"))
        {
            entryLabel.setText("Address : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Phone"))
        {
            entryLabel.setText("Phone : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Income"))
        {
            entryLabel.setText("Income : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Use"))
        {
            OptionBox = new JComboBox(useBoxOption);
            OptionBox.setBounds(80,50,85,20);
            panel1.add(OptionBox);
            entryLabel.setText("Use : ");

        }else if(userOption.equals("Status"))
        {
            OptionBox = new JComboBox(statusBoxOption);
            OptionBox.setBounds(80,50,85,20);
            panel1.add(OptionBox);
            entryLabel.setText("Status : ");

        }else if(userOption.equals("Model"))
        {
            entryLabel.setText("Model : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Year"))
        {
            entryLabel.setText("Year : ");
            updateText = new JTextField();
            updateText.setBounds(80,50,85,20);
            panel1.add(updateText);

        }else if(userOption.equals("Type"))
        {
            OptionBox = new JComboBox(typeBoxOption);
            OptionBox.setBounds(80,50,85,20);
            panel1.add(OptionBox);
            entryLabel.setText("Type : ");

        }else if(userOption.equals("Method"))
        {
            OptionBox = new JComboBox(useBoxOption);
            OptionBox.setBounds(80,50,85,20);
            panel1.add(OptionBox);
            entryLabel.setText("Method : ");

        }

        frame1.setVisible(true);





    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(userOption.equals("First Name"))
        {
            String s = updateText.getText();
            currentProfile.updateFirstName(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Last Name"))
        {
            String s = updateText.getText();
            currentProfile.updateLastName(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Address"))
        {
            String s = updateText.getText();
            currentProfile.updateAddress(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Phone"))
        {
            String s = updateText.getText();
            currentProfile.updatePhone(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Income"))
        {
            String s = updateText.getText();
            float income = Float.parseFloat(s);
            currentProfile.updateIncome(income);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Use"))
        {
            String s =String.valueOf(OptionBox.getSelectedItem());
            currentProfile.updateUse(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Status"))
        {
            String s =String.valueOf(OptionBox.getSelectedItem());
            currentProfile.updateStatus(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Model"))
        {
            String s = updateText.getText();
            currentProfile.updateModel(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Year"))
        {
            String s = updateText.getText();
            currentProfile.updateYears(s);
            dataBase.writeAllCustomerProf();

        }else if(userOption.equals("Type"))
        {
            String s =String.valueOf(OptionBox.getSelectedItem());
            currentProfile.updateType(s);
            dataBase.writeAllCustomerProf();
        }else if(userOption.equals("Method"))
        {
            String s =String.valueOf(OptionBox.getSelectedItem());
            currentProfile.updateMethod(s);
            dataBase.writeAllCustomerProf();

        }
        frame1.dispose();
        MainMenuGUI main = new MainMenuGUI(fName,"NONE");

    }
}
