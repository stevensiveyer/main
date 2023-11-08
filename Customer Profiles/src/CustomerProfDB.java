import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProfDB
{
    private int numCustomer;
    private int currentCustomerIndex;
    private String fileName;
    private ArrayList<CustomerProf> customerList;

    public CustomerProfDB(String fName)
    {
        fileName = fName;
        customerList = new ArrayList<CustomerProf>();
        numCustomer = 0;
        currentCustomerIndex = 0;
        initializeDatabase(fileName);

    }

    public void insertNewProfile(CustomerProf customer)
    {
        customerList.add(customer);
        numCustomer++;
    }

    public void insertProfileAt(CustomerProf cu, int index)
    {
        customerList.set(index,cu);
    }
    public boolean deleteProfile(String adminID, String lastName)
    {
        for(int x = 0; x < customerList.size(); x++)
        {
            CustomerProf currCustomer = customerList.get(x);
            if(currCustomer.getadminID().equals(adminID)  && currCustomer.getLastName().equals(lastName))
            {
                customerList.remove(x);
                numCustomer--;
                return true;
            }
        }
        return false;
    }

    public CustomerProf findProfile(String adminID,String lastName)
    {
        for(int x = 0; x < customerList.size(); x++)
        {
            currentCustomerIndex = x;
            CustomerProf currCustomer = customerList.get(x);

            if(currCustomer.getadminID().equals(adminID) && currCustomer.getLastName().equals(lastName))
                return currCustomer;
        }
        return null;
    }

    public int FindIndex(String adminId, String lastName)
    {
        int index = -1;
        for(int x = 0; x < customerList.size(); x++)
        {
            CustomerProf currCustomer = customerList.get(x);

            if(currCustomer.getadminID().equals(adminId) && currCustomer.getLastName().equals(lastName))
            {
                index = x;
                System.out.println("Found at index" + index);
                break;
            }

        }
        return index;


    }

    public CustomerProf findFirstProfile()
    {
        return customerList.get(0);
    }

    public CustomerProf findNextProfile()
    {
        if(customerList.get(currentCustomerIndex + 1) != null)
        {
            return customerList.get(currentCustomerIndex + 1);
        }
        else
        {
            System.out.println("Error, out of range");
            return  null;
        }
    }

    public void writeAllCustomerProf()
    {
        try
        {
            File f = new File(fileName);
            FileWriter john = new FileWriter(f);
            for(int x = 0; x < customerList.size(); x++)
            {
                CustomerProf cust = customerList.get(x);
                VehicleInfo vec = cust.getVehicleInfo();
                john.write(cust.getadminID() + "\n" + cust.getFirstName() + "\n" + cust.getLastName() + "\n" + cust.getAddress() + "\n"
                        + cust.getPhone() + "\n" + cust.getIncome() + "\n" + cust.getStatus() + "\n" + cust.getUse() +"\n" + vec.getModel()+"\n" +vec.getYear()+"\n" +vec.getType()+"\n" +vec.getMethod() + "\n");

            }
            john.close();

        }
        catch(IOException e)
        {
            System.out.println("Error, unable to write to database");
        }


    }

    public int getNumCustomer(){ return numCustomer;}

    public CustomerProf getCustomerAt(int index)
    {
        return customerList.get(index);
    }

    public void displayALL()
    {
        for(int i= 0; i < customerList.size();i++)
        {
            customerList.get(i).printCustomerInfo();
        }
    }


    private void initializeDatabase(String f)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String info;
            String adminID = "None";
            String firstName = "None";
            String lastName = "None";
            String address = "None";
            String phone = "None";
            float income = 0;
            String status = "None";
            String use  = "None";
            String model = "None";
            String year = "None";
            String type = "None";
            String method = "None";
            int count = 0;

            while ((info = br.readLine()) != null){
                if (count == 0)
                {
                    adminID = info;
                    count++;
                }else if(count == 1)
                {
                    firstName = info;
                    count++;
                }else if(count == 2)
                {
                    lastName = info;
                    count++;

                }else if(count == 3)
                {
                    address = info;
                    count++;

                }else if(count == 4)
                {
                    phone = info;
                    count++;

                }else if(count == 5)
                {
                    income = Float.parseFloat(info);
                    count++;

                }else if(count == 6)
                {
                    status = info;
                    count++;

                }else if(count == 7)
                {
                    use = info;
                    count++;

                }else if(count == 8)
                {
                    model = info;
                    count++;

                }else if(count == 9)
                {
                    year = info;
                    count++;

                }else if(count == 10)
                {
                    type = info;
                    count++;

                }else if(count == 11)
                {
                    method = info;
                    count = 0;


                    VehicleInfo v = new VehicleInfo(model,year,type,method);
                    CustomerProf c = new CustomerProf(adminID,firstName,lastName,address,phone,income,status,
                            use,v);
                    insertNewProfile(c);

                }





            }

            br.close();

        }catch (Exception ex)
        {
            System.out.print("Error, failure to read the file");
        }
        return;

    }


}
