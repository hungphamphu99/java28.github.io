package entities;

public class IctStudent extends TechMasterStudent {
    private Double javaPoint;
    private Double cssPoint;
    private Double htmlPoint;

    public IctStudent(String name, String major, double javaPoint, double cssPoint, double htmlPoint) {
        super(name, major);
        this.javaPoint = javaPoint;
        this.cssPoint = cssPoint;
        this.htmlPoint = htmlPoint;
    }


    @Override
    public Double getPoint() {
        return (2*this.javaPoint + this.cssPoint + this.htmlPoint)/4;
    }
}