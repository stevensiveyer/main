public class VehicleInfo
{
    private String model;
    private String year;
    private String type;
    private String method;

    public VehicleInfo(String m, String y, String t, String meth)
    {
        model = m;
        year = y;
        type = t;
        method = meth;
    }

    public String getModel()
    {
        return model;
    }

    public String getYear()
    {
        return year;
    }

    public String getType()
    {
        return type;
    }

    public String getMethod()
    {
        return method;
    }

    public void updateModel(String m)
    {
        model = m;
    }

    public void updateYear(String y)
    {
        year = y;
    }

    public void updateMethod(String m)
    {
        method = m;
    }

    public void updateType(String t)
    {
        type = t;
    }
}
