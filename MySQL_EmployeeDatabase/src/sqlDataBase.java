import javax.management.Query;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class sqlDataBase
{
    private static Connection conn;
    String dbURl = "jdbc:mysql://localhost:3306/company";
    String username = "root";
    String hostname = "localhost";
    String password = "ssiveyer01";


    public sqlDataBase() throws SQLException {
        connectDB();
        menu();
    }

    public void addNewEmployee() throws SQLException
    {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please Enter First Name");
            String fName = scan.nextLine();

            String mInit = null;
            boolean correct = false;
            while (!correct) {
                System.out.println("Please Enter Middle Initial");
                mInit = scan.nextLine();
                if (mInit.length() > 1) {
                    System.out.println("ERROR - value entered not one character, please try again");
                } else {
                    correct = true;
                }
            }

            System.out.println("Please Enter Last Name");
            String lName = scan.nextLine();

            String ssn = null;
            correct = false;
            while (!correct) {
                System.out.println("Please Enter SSN");
                ssn = scan.nextLine();
                if (ssn.length() != 9) {
                    System.out.println("ERROR - Value Entered not 9 characters, please try again");
                } else {
                    correct = true;
                }
            }

            System.out.println("Please Enter Birth Date");
            String bDate = scan.nextLine();

            System.out.println("Please enter Address");
            String address = scan.nextLine();


            String sex = null;
            correct = false;
            while (!correct) {
                System.out.println("Please Enter Sex(Either F or M");
                sex = scan.nextLine();
                if (sex.length() > 1) {
                    System.out.println("ERROR - value entered not one character, please try again");
                } else {
                    correct = true;
                }
            }

            System.out.println("Please enter Salary");
            int salary = Integer.parseInt(scan.nextLine());

            String Super_ssn = null;
            correct = false;
            while (!correct) {
                System.out.println("Please Enter Supervisor SSN");
                Super_ssn = scan.nextLine();
                if (Super_ssn.length() != 9) {
                    System.out.println("ERROR - Value Entered not 9 characters, please try again");
                } else {
                    correct = true;
                }
            }

            System.out.println("Please Enter Department Number");
            int dno = scan.nextInt();

            Statement stmt = conn.createStatement();
            String Query = "INSERT INTO EMPLOYEE(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES('" + fName + "','" + mInit + "','" + lName + "','" + ssn + "','" + bDate + "','" + address + "','" + sex + "'," + salary + ",'" + Super_ssn + "','" + dno + "');";
            stmt.execute(Query);
        }
        catch(Exception e)
        {
            System.out.println("ERROR - Make sure that All values entered are of right structure and/or Key Constraints are followed(Ssn is unique, Super_ssn/Dno already exist)");
        }

    }//finished

    public void viewEmployee() throws SQLException
    {
        System.out.println("Please enter Employee SSN");
        Scanner scan = new Scanner(System.in);
        String ssn = scan.nextLine();

        Statement stmt = conn.createStatement();
        String Query = "SELECT e.Fname, e.Minit, e.Lname, e.Ssn, e.Bdate, e.Address, e.Sex, e.Salary, e.Super_ssn, e.Dno, m.Fname, m.Minit, m.Lname, DEPARTMENT.Dname FROM EMPLOYEE e INNER JOIN(EMPLOYEE m, DEPARTMENT) ON (e.Super_ssn = m.Ssn AND e.Dno = DEPARTMENT.Dnumber) WHERE e.Ssn=" + ssn + ";";
        ResultSet st = stmt.executeQuery(Query);
        if (st.next() == false)
        {
            System.out.println("Employee Not Found");
            return;
        }
        else
        {
            do {
                System.out.print("Employee info: ");
                System.out.println(st.getString(1) + " " + st.getString(2) + " " + st.getString(3) + " " + st.getString(4) + " " + st.getString(5) + " " + st.getString(6) + " " + st.getString(7) + " " + st.getInt(8) + " " + st.getString(9) + " " + st.getInt(10));
                System.out.print("Manager Name: ");
                System.out.println(st.getString(11) + " " + st.getString(12) + " " + st.getString(13));
                System.out.print("Department: ");
                System.out.println(st.getString(14));
            }while(st.next());
        }
        Query = "SELECT DEPENDENT.Dependent_name FROM EMPLOYEE INNER JOIN DEPENDENT ON (EMPLOYEE.Ssn = DEPENDENT.Essn) WHERE EMPLOYEE.Ssn=" + ssn + ";";
        ResultSet set = stmt.executeQuery(Query);
        System.out.println("Dependents: ");
        while(set.next()) {
            System.out.println(set.getString(1));
        }



        } //finished

    public void modifyEmployee() throws SQLException
    {
        String Query = "LOCK TABLE EMPLOYEE WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        try
        {

            Scanner scan = new Scanner(System.in);
            String answer;
            String newInfo;

            //
            System.out.println("please enter SSN of the employee to be modified");
            String ssn = scan.nextLine();


            Query = "SELECT * FROM EMPLOYEE WHERE SSN=" + ssn;
            ResultSet st = stmt.executeQuery(Query);
            System.out.println("employee info:");
            if (st.next() == false) {
                System.out.println("Employee Not Found");
                return;
            } else {
                do {
                    System.out.println(st.getString(1) + " " + st.getString(2) + " " + st.getString(3) + " " + st.getString(4) + " " + st.getString(5) + " " + st.getString(6) + " " + st.getString(7) + " " + st.getInt(8) + " " + st.getString(9) + " " + st.getInt(10));
                } while (st.next());
            }

            System.out.println("Would you like to update Address(Enter Y or N)");
            answer = scan.nextLine();
            answer.toUpperCase(Locale.ROOT);
            if (answer.equals("Y") || answer.equals("YES")) {
                System.out.println("Please enter the updated address");
                newInfo = scan.nextLine();
                Query = "UPDATE EMPLOYEE SET Address='" + newInfo + "' WHERE Ssn=" + ssn;
                stmt.execute(Query);
            }
            System.out.println("Would you like to update Sex(Enter Y or N)");
            answer = scan.nextLine();
            answer.toUpperCase(Locale.ROOT);
            if (answer.equals("Y") || answer.equals("YES")) {
                System.out.println("Please enter the updated Sex");
                newInfo = scan.nextLine();
                Query = "UPDATE EMPLOYEE SET Sex= '" + newInfo + "' WHERE Ssn=" + ssn;
                stmt.execute(Query);
            }
            System.out.println("Would you like to update Salary(Enter Y or N)");
            answer = scan.nextLine();
            answer.toUpperCase(Locale.ROOT);
            if (answer.equals("Y") || answer.equals("YES")) {
                System.out.println("Please enter the updated salary");
                newInfo = scan.nextLine();
                Query = "UPDATE EMPLOYEE SET Salary=" + newInfo + " WHERE Ssn=" + ssn;
                stmt.execute(Query);
            }
            System.out.println("Would you like to update Supervisor SSN(Enter Y or N)");
            answer = scan.nextLine();
            answer.toUpperCase(Locale.ROOT);
            if (answer.equals("Y") || answer.equals("YES")) {
                System.out.println("Please enter the updated Supervisor SSN");
                newInfo = scan.nextLine();
                Query = "UPDATE EMPLOYEE SET Super_ssn=" + newInfo + " WHERE Ssn=" + ssn;
                stmt.execute(Query);
            }
            System.out.println("Would you like to update Department Number(Enter Y or N)");
            answer = scan.nextLine();
            answer.toUpperCase(Locale.ROOT);
            if (answer.equals("Y") || answer.equals("YES")) {
                System.out.println("Please enter the updated Department Number");
                newInfo = scan.nextLine();
                Query = "UPDATE EMPLOYEE SET Dno=" + newInfo + " WHERE Ssn=" + ssn;
                stmt.execute(Query);

            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR - Make sure all values that are updated follow structure for values and follow all Foreign Key constraints");
        }
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//finished

    public void removeEmployee() throws SQLException {
        String Query = "LOCK TABLE EMPLOYEE WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        try
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter the SSN of the employee to be deleted");
            String ssn = scan.nextLine();
            Query = "SELECT * FROM EMPLOYEE WHERE Ssn=" + ssn + ";";
            stmt = conn.createStatement();
            ResultSet st = stmt.executeQuery(Query);
            if (st.next() == false)
            {
                System.out.println("Employee Not Found");
                return;
            }
            else
            {
                do
                {
                    System.out.println(st.getString(1) + " " + st.getString(2) + " " + st.getString(3) + " " + st.getString(4) + " " + st.getString(5) + " " + st.getString(6) + " " + st.getString(7) + " " + st.getInt(8) + " " + st.getString(9) + " " + st.getInt(10));
                } while (st.next());
            }
            System.out.println("Are you sure you want to delete this Employee(Enter Yes to confirm)");
            String confirmation = scan.nextLine();
            confirmation = confirmation.toUpperCase(Locale.ROOT);
            if (confirmation.equals("YES")) {
                Query = "DELETE FROM EMPLOYEE WHERE Ssn=" + ssn;
                stmt.execute(Query);
            } else
                System.out.println("Confirmation not given, not deleting");
        }
        catch (Exception e)
        {
            System.out.println("ERROR - Unable to remove Employee, Make sure there is no Integrity Contstrains(Delete Dependents/Change Super_ssn of other employes, etc.");
        }
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    } //finished

    public void addNewDependent() throws SQLException
    {
        String Query = "LOCK TABLE EMPLOYEE WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        Query = "LOCK TABLE DEPENDENT WRITE;";
        stmt.execute(Query);
        try {
            String Essn;
            String name;
            String sex;
            String Bdate;
            String Relationship;

            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter Employee Ssn");
            Essn = scan.nextLine();
            System.out.println("Please enter Dependent Name");
            name = scan.nextLine();
            System.out.println("Please enter Sex(Either M or F)");
            sex = scan.nextLine();
            System.out.println("Please enter Birthdate(YYYY-MM-DD)");
            Bdate = scan.nextLine();
            System.out.println("Please enter Relationship");
            Relationship = scan.nextLine();

            Query = "INSERT INTO DEPENDENT(Essn, Dependent_name, Sex, Bdate, Relationship) VALUES('" + Essn + "','" + name + "','" + sex + "','" + Bdate + "','" + Relationship + "');";
            stmt.execute(Query);
        }
        catch(Exception e)
        {
            System.out.println("ERROR - Make sure all values entered follow correct structure and/or foreign key constraint integreity is followed");
        }
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//finished

    public void removeDependent() throws SQLException
    {
        String Query = "LOCK TABLE EMPLOYEE WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        Query = "LOCK TABLE DEPENDENT WRITE";
        stmt.execute(Query);
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter Employee SSN of the Dependent");
        String essn = scan.nextLine();
        Query = "SELECT * FROM DEPENDENT WHERE Essn =" + essn + ";";
        ResultSet st = stmt.executeQuery(Query);
        while (st.next()) {
            System.out.println(st.getString(1) + " " + st.getString(2) + " " + st.getString(3) + " " + st.getString(4) + " " + st.getString(5));
        }
        System.out.println("Please enter name of person to be deleted");
        String n = scan.nextLine();
        Query = "DELETE FROM DEPENDENT WHERE (Essn=" + essn + " AND Dependent_name='" + n + "');";
        stmt.execute(Query);
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//FINISHED

    public static void addNewDepartment() throws SQLException
    {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please Enter Department Name");
            String Dname = scan.nextLine();
            System.out.println("Please Enter Department Number");
            String Dno = scan.nextLine();
            String ssn = "";
            boolean correct = false;
            while (!correct) {
                System.out.println("Please Enter Manager SSN");
                ssn = scan.nextLine();
                if (ssn.length() != 9)
                    System.out.println("ERROR - SSN not 9 characters long, try again");
                else
                    correct = true;
            }
            System.out.println("Please enter Manager Start Date(YYYY-MM-DD)");
            String Date = scan.nextLine();
            String Query = "INSERT INTO DEPARTMENT(Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES('" + Dname + "'," + Dno + ",'" + ssn + "','" + Date + "');";
            Statement stmt = conn.createStatement();
            stmt.execute(Query);
        }
        catch(Exception e)
        {
            System.out.println("ERROR - Make sure all Formating for the values is correct and/or Follow Key Constraints(Manager SSN exists/Department number is unique");
        }

    }//finished

    public static void viewDepartment() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter Department Number");
        String dno = scan.nextLine();
        String Query = "SELECT DEPARTMENT.Dname, DEPARTMENT.Dnumber, DEPARTMENT.Mgr_ssn, DEPARTMENT.Mgr_start_date, EMPLOYEE.Fname, EMPLOYEE.Minit, EMPLOYEE.Lname FROM DEPARTMENT INNER JOIN EMPLOYEE ON (DEPARTMENT.Mgr_ssn = EMPLOYEE.Ssn) WHERE DEPARTMENT.Dnumber=" + dno;
        Statement stmt = conn.createStatement();
        ResultSet set = stmt.executeQuery(Query);
        if (set.next() == false)
        {
            System.out.println("Department Not Found");
            return;
        }
        else
        {
            do
            {
                System.out.print("Department Info: ");
                System.out.println(set.getString(1) +  " " + set.getInt(2) +  " " + set.getString(3) +  " " + set.getString(4));
                System.out.print("Department Manager: ");
                System.out.println(set.getString(5) +  " " + set.getString(6) +  " " + set.getString(7));
            }while(set.next());
        }
        while(set.next())
        {

        }
        Query = "SELECT DEPT_LOCATIONS.Dlocation FROM DEPARTMENT INNER JOIN DEPT_LOCATIONS ON (DEPARTMENT.Dnumber = DEPT_LOCATIONS.Dnumber) WHERE DEPARTMENT.Dnumber =" + dno + ";";
        set = stmt.executeQuery(Query);
        System.out.println("Department Locations:");
        while(set.next())
        {
            System.out.println(set.getString(1));
        }

    }//finished

    public static void removeDepartment() throws SQLException {
        String Query = "LOCK TABLE DEPARTMENT WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Department Number to Be Deleted");
            String dno = scan.nextLine();
            Query = "SELECT * FROM DEPARTMENT WHERE Dnumber=" + dno + ";";
            ResultSet set = stmt.executeQuery(Query);
            while (set.next()) {
                System.out.println(set.getString(1) + " " + set.getInt(2) + " " + set.getString(3) + " " + set.getString(4));
            }
            System.out.println("Are you sure you want to delete this department(Enter Yes to confirm)");
            String confirmation = scan.nextLine();
            confirmation = confirmation.toUpperCase(Locale.ROOT);
            if (confirmation.equals("YES")) {
                Query = "DELETE FROM DEPARTMENT WHERE Dnumber = " + dno + ";";
                stmt.execute(Query);
            } else
                System.out.println("Confirmation not given, not deleting");
        }
        catch(Exception e)
        {
            System.out.println("ERROR - Make Sure that all Employees have changed Department and all Department Locations associated have been removed");
        }
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//FINISHED

    public static void addDepartmentLocation() throws SQLException {
        String Query = "LOCK TABLE DEPARTMENT WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        Query = "LOCK TABLE DEPT_LOCATIONS WRITE";
        stmt.execute(Query);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Department Number");
            String Dno = scan.nextLine();
            Query = "SELECT * FROM DEPT_LOCATIONS WHERE Dnumber =" + Dno + ";";
            ResultSet set = stmt.executeQuery(Query);
            while (set.next()) {
                System.out.println(set.getString(1) + " " + set.getString(2));
            }
        try {
            System.out.println("Enter a new location");
            String newLoc = scan.nextLine();
            Query = "INSERT INTO DEPT_LOCATIONS(Dnumber,Dlocation) VALUES(" + Dno + ",'" + newLoc + "');";
            stmt.execute(Query);
        }
        catch (Exception e)
        {
            System.out.println("ERROR - Make sure the Department Number entered exists already");
        }
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//FINISHED

    public static void removeDepartmentLocation() throws SQLException {
        String Query = "LOCK TABLE DEPARTMENT WRITE;";
        Statement stmt = conn.createStatement();
        stmt.execute(Query);
        Query = "LOCK TABLE DEPT_LOCATIONS WRITE";
        stmt.execute(Query);
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter Department Number");
        String dno = scan.nextLine();
        Query = "SELECT * FROM DEPT_LOCATIONS WHERE Dnumber = " + dno;
        ResultSet set = stmt.executeQuery(Query);
        while(set.next())
        {
            System.out.println(set.getString(2));
        }
        System.out.println("What location would you like to be deleted");
        String loc = scan.nextLine();
        Query = "DELETE FROM DEPT_LOCATIONS WHERE (Dnumber=" + dno + " AND Dlocation='" + loc + "');";
        stmt.execute(Query);
        Query = "UNLOCK TABLES";
        stmt.execute(Query);
    }//FINISHED

    public void menu() throws SQLException {
        Scanner scan = new Scanner(System.in);
        boolean x = true;
        while(x)
        {
            System.out.println("<<<<<<<<Available commands>>>>>>>");
            System.out.println("--------------------------------------------");
            System.out.println("1           = Add New Employee");
            System.out.println("2           = View Employee");
            System.out.println("3           = Modify Employee");
            System.out.println("4           = Remove Employee");
            System.out.println("5           = Add New Dependent");
            System.out.println("6           = Remove Dependent");
            System.out.println("7           = Add New Department");
            System.out.println("8           = View Department");
            System.out.println("9           = Remove Department");
            System.out.println("10          = Add Department Location");
            System.out.println("11           = Remove Department Location");
            System.out.println("12          = Quit");
            System.out.println("--------------------------------------------");
            System.out.println("Waiting for your command");
            String userChoice = scan.nextLine();

            if (userChoice.equals("1"))
            {
                addNewEmployee();
            }
            else if (userChoice.equals("2"))
            {
                viewEmployee();
            }
            else if (userChoice.equals("3"))
            {
                modifyEmployee();
            }
            else if (userChoice.equals("4"))
            {
                removeEmployee();
            }
            else if (userChoice.equals("5"))
            {
                addNewDependent();
            }
            else if (userChoice.equals("6"))
            {
                removeDependent();
            }
            else if (userChoice.equals("7"))
            {
                addNewDepartment();
            }
            else if (userChoice.equals("8"))
            {
                viewDepartment();
            }
            else if (userChoice.equals("9"))
            {
                removeDepartment();
            }
            else if (userChoice.equals("10"))
            {
                addDepartmentLocation();
            }
            else if (userChoice.equals("11"))
            {
                removeDepartmentLocation();
            }
            else if (userChoice.equals("12"))
            {
                x = false;
            }
            else
            {
                System.out.println("Could not recongnize command, try again");
            }
        }
        System.out.println("exiting");
    }


    void connectDB() throws SQLException
    {
        try
        {
            conn = DriverManager.getConnection(dbURl, username, password);

            if(conn != null)
            {
                System.out.println("Connected");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        sqlDataBase db = new sqlDataBase();

    }
}
