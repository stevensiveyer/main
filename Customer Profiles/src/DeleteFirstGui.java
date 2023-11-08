import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFirstGui implements ActionListener{
    private String fname;
    private String curAdminId;
    private String lastName;

    JFrame frame1;
    private static JLabel adminLabel;
    private static JTextField adminTextField;
    private static JLabel lastNamel;
    private static JTextField lastNameTextField;
    private static JButton enterButton;
    private String option;
    private CustomerProfDB database;


    public DeleteFirstGui(CustomerProfDB database,String fname, String option)
    {
        this.fname = fname;
        this.option = option;
        this.database = database;
        //C:\\Users\\yeyin\\Desktop\\Test.txt
        frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        frame1.setTitle("AdminID");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);
        panel1.setLayout(null);
        frame1.setSize(200,160);
        frame1.setLocationRelativeTo(null);

        //Setting up label
        adminLabel = new JLabel("AminID : ");
        adminLabel.setBounds(20,20,80,25);
        panel1.add(adminLabel);

        //Setting up textfield
        adminTextField = new JTextField();
        adminTextField.setBounds(90,20,80,25);
        panel1.add(adminTextField);

        lastNamel = new JLabel("Last Name : ");
        lastNamel.setBounds(20,40,80,25);
        panel1.add(lastNamel);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(90,40,80,25);
        panel1.add(lastNameTextField);

        //Setting up button
        enterButton = new JButton("ENTER");
        enterButton.setBounds(90,80,80,25);
        enterButton.addActionListener(this);
        panel1.add(enterButton);

        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        curAdminId = adminTextField.getText();
        lastName = lastNameTextField.getText();
        if (database.deleteProfile(curAdminId,lastName))
        {
            database.writeAllCustomerProf();
            DeleteProfileConformation conformation = new DeleteProfileConformation(fname);
            frame1.dispose();

        }else{
            System.out.println("Error, profile not found");

        }



    }


}
