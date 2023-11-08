import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PropertyResourceBundle;

public class UpdatePart1GUI implements ActionListener {

    private static JLabel adminIdL;
    private static JLabel lastNameL;
    private static JLabel updateL;
    private static JTextField adminIdT;
    private static JTextField lastNameT;
    private static JComboBox optionComBoBox;
    private static JButton findButt;
    private static JFrame frame1;

    CustomerProfDB dataBase;
    String fName;
    String adminId;
    String lastname;

    private String[]  updateOption = {"First Name","Last Name","Address",
            "Phone","Income","Use","Status","Model","Year","Type","Method"};
    public UpdatePart1GUI(CustomerProfDB dataBase, String fName)
    {
        frame1 = new JFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setTitle("Update Profile");
        frame1.setSize(230,200);
        JPanel Panel1 = new JPanel();
        Panel1.setLayout(null);
        frame1.add(Panel1);
        frame1.setLocationRelativeTo(null);
        this.dataBase = dataBase;
        this.fName = fName;

        adminIdL = new JLabel("AdminID : ");
        adminIdL.setBounds(20,20,85,25);
        Panel1.add(adminIdL);

        lastNameL = new JLabel("last Name");
        lastNameL.setBounds(20,50,85,25);
        Panel1.add(lastNameL);

        updateL = new JLabel("Update field");
        updateL.setBounds(20,80,85,25);
        Panel1.add(updateL);

        adminIdT = new JTextField();
        adminIdT.setBounds(100,20,85,25);
        Panel1.add(adminIdT);

        lastNameT = new JTextField();
        lastNameT.setBounds(100,50,85,25);
        Panel1.add(lastNameT);


        optionComBoBox = new JComboBox(updateOption);
        optionComBoBox.setBounds(100,80,85,25);
        Panel1.add(optionComBoBox);


        findButt = new JButton("FIND");
        findButt.addActionListener(this);
        findButt.setBounds(100,120,85,25);
        Panel1.add(findButt);


        frame1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userOption = String.valueOf(optionComBoBox.getSelectedItem());
        adminId = adminIdT.getText();
        lastname = lastNameT.getText();

        try {
            CustomerProf currentPro = dataBase.findProfile(adminId,lastname);
            UpdatePart2GUI part2Gui = new UpdatePart2GUI(dataBase,currentPro,fName,adminId,lastname,userOption);
            frame1.dispose();
        }catch (Exception ex)
        {
            System.out.println("Error, unable to find profile");

        }



    }
}
