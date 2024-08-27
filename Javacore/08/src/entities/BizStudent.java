package entities;

public class BizStudent extends TechMasterStudent {
    private Double markingPoint;
    private Double salesPoint;

    public BizStudent(String name, String major, Double markingPoint, Double salesPoint) {
        super(name, major);
        this.markingPoint = markingPoint;
        this.salesPoint = salesPoint;
    }

    public Double getPoint() {
        return (2*markingPoint+salesPoint)/3;
    }
}
