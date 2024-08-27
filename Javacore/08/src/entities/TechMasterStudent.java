package entities;

public class TechMasterStudent {
     private String name;
     private String major;
     public TechMasterStudent(String name, String major) {
            this.name = name;
            this.major = major;
     }
    public Double getPoint(){
        return 0.0;
    };

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }
}
