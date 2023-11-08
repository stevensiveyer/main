import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateProfileGUI implements ActionListener
{
    CustomerProfDB db;
    private JFrame frame;

    //Customer Info TextFields
    private JTextField adminIdBox;
    private JTextField firstNameBox;
    private JTextField lastNameBox;
    private JTextField addressBox;
    private JTextField phoneBox;
    private JTextField incomeBox;
    private JComboBox statusBox;
    private JComboBox useBox;

    //Vehicle Info TextFields
    private JTextField modelBox;
    private JTextField yearBox;
    private JComboBox typeBox;
    private JComboBox methodBox;

    public CreateProfileGUI(CustomerProfDB database)
    {
        db = database;
        //set up frame
        frame = new JFrame();
        frame.setTitle("Create Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        frame.setSize(500, 700);
        frame.setBackground(Color.red);

        //set up a text header
        JLabel header = new JLabel("Create Profile");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.TOP);


        //set up submit button
        JButton submit = new JButton();
        submit.setText("Submit");
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setVerticalAlignment(JButton.BOTTOM);
        submit.addActionListener(this);


        JPanel textFields = new JPanel();
        textFields.setBounds(100, 100, 300, 700);
        textFields.setLayout(new GridLayout(0,1));


        //setting up the customer text fields
        adminIdBox = new JFormattedTextField();
        JLabel adminId = new JLabel("Admin ID:");
        adminId.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(adminId);
        textFields.add(adminIdBox);


        firstNameBox = new JFormattedTextField();
        JLabel firstName = new JLabel("First Name:");
        firstName.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(firstName);
        textFields.add(firstNameBox);

        lastNameBox = new JFormattedTextField();
        JLabel lastName = new JLabel("Last Name:");
        lastName.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(lastName);
        textFields.add(lastNameBox);


        addressBox = new JFormattedTextField();
        JLabel address = new JLabel("Address:");
        address.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(address);
        textFields.add(addressBox);

        phoneBox = new JFormattedTextField();
        JLabel phone = new JLabel("Phone Number:");
        phone.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(phone);
        textFields.add(phoneBox);

        incomeBox = new JFormattedTextField();
        JLabel income = new JLabel("Income:");
        income.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(income);
        textFields.add(incomeBox);


        String[] statusOptions = {"Active", "Inactive"};
        statusBox = new JComboBox(statusOptions);
        JLabel status = new JLabel("Status:");
        status.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(status);
        textFields.add(statusBox);


        String[] useOptions = {"Personal", "Bussiness", "Both"};
        useBox = new JComboBox(useOptions);
        JLabel use = new JLabel("Use:");
        use.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(use);
        textFields.add(useBox);


        //setting up the vehicles text fields
        modelBox = new JFormattedTextField();
        JLabel model = new JLabel("Model:");
        model.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(model);
        textFields.add(modelBox);


        yearBox = new JFormattedTextField();
        JLabel year = new JLabel("Year:");
        year.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(year);
        textFields.add(yearBox);


        String[] typeOptions = {"sedan", "hatchback", "luxury", "sport", "other"};
        typeBox = new JComboBox(typeOptions);
        JLabel type = new JLabel("Type:");
        textFields.add(type);
        textFields.add(typeBox);



        String[] methodOptions = {"new", "certified pre-owned", "used", "other"};
        methodBox = new JComboBox(methodOptions);
        JLabel method = new JLabel("Method:");
        method.setHorizontalTextPosition(JLabel.LEFT);
        textFields.add(method);
        textFields.add(methodBox);

        textFields.add(submit);

        //adding to frame
        frame.add(textFields, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String adminID = adminIdBox.getText();
        String firstName = firstNameBox.getText();
        String lastName = lastNameBox.getText();
        String address = addressBox.getText();
        String phone = phoneBox.getText();
        String incomeS = incomeBox.getText();
        Float income;
        try{
            income = Float.parseFloat(incomeS);
        }
        catch (Exception E)
        {
            JOptionPane.showMessageDialog(null,"ERROR - Income Field is not a number, Please Try Again");
            return;
        }


        String status = String.valueOf(statusBox.getSelectedItem());
        String use = String.valueOf(useBox.getSelectedItem());
        String model = modelBox.getText();
        String year = yearBox.getText();
        String type = String.valueOf(typeBox.getSelectedItem());
        String method = String.valueOf(methodBox.getSelectedItem());

        if(adminID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty() || incomeS.isEmpty() || status.isEmpty() || use.isEmpty() || model.isEmpty() || year.isEmpty() || type.isEmpty() || method.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"ERROR - Field left empty. Please Try again");
            return;
        }
        else
        {
            VehicleInfo v = new VehicleInfo(model, year, type, method);
            CustomerProf c = new CustomerProf(adminID, firstName, lastName, address, phone, income, status, use, v);
            db.insertNewProfile(c);
            db.writeAllCustomerProf();

            frame.dispose();

        }



    }
}
