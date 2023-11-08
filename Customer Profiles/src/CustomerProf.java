public class CustomerProf
{
    private String adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private float income;
    private String status;
    private String use;
    private VehicleInfo vehicleInfo;

    public CustomerProf(String ad, String fn, String ln, String a, String p, float i, String s, String u, VehicleInfo v)
    {
        adminID = ad;
        firstName = fn;
        lastName = ln;
        address = a;
        phone = p;
        income = i;
        status = s;
        use = u;
        vehicleInfo = v;
    }



    public void printCustomerInfo()
    {
        System.out.println("=========================================================");
        System.out.println("adminID = "+adminID);
        System.out.println("First name = "+firstName);
        System.out.println("Last name = "+lastName);
        System.out.println("Address = "+address);
        System.out.println("Phone = "+phone);
        System.out.println("Income = "+income);
        System.out.println("Status = "+status);
        System.out.println("Use = "+use);
        System.out.println("Model = "+vehicleInfo.getModel());
        System.out.println("Year = "+vehicleInfo.getYear());
        System.out.println("Type = "+vehicleInfo.getType());
        System.out.println("Method = "+vehicleInfo.getMethod());
        System.out.println("=========================================================");

    }

    public String getadminID()
    {
        return adminID;
    }


    public String getFirstName()
    {
        return firstName;


    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public float getIncome()
    {
        return income;
    }

    public String getStatus()
    {
        return status;
    }

    public String getUse()
    {
        return use;
    }

    public VehicleInfo getVehicleInfo()
    {
        return vehicleInfo;
    }

    public void updateFirstName(String fn)
    {
        firstName = fn;
    }

    public void updateLastName(String ln)
    {
        lastName = ln;
    }

    public void updateAddress(String a)
    {
        address = a;
    }

    public void updatePhone(String p)
    {
        phone = p;
    }

    public void updateIncome(float i)
    {
        income = i;
    }

    public void updateStatus(String s)
    {
        status = s;
    }

    public void updateModel(String s){vehicleInfo.updateModel(s);}

    public void updateYears( String s){vehicleInfo.updateYear(s);}

    public void updateType( String s){vehicleInfo.updateType( s);}

    public void updateMethod(String s){vehicleInfo.updateMethod(s);}

    public void updateUse(String u) {use = u;}

    public void updateVehicleInfo(VehicleInfo v)
    {
        vehicleInfo = v;
    }

}
