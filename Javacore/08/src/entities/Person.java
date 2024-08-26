package entities;

class Person {
     private String name;
     private String major;
     public Person(String name, String major) {
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
