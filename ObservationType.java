public abstract class ObservationType {
    private String code;
    private String name;
    
    public ObservationType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTypeDetails()
    {
        String result = "Observation Code: " + code + "\n";
        result += "Name: " + name + "\n";
        return result;
    }

    public String toString()
    {
        return code + "; " + name + "; ";
    }
}

