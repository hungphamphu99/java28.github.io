package EX2;

public class Rectangular extends Geometry{
    public Rectangular(Double width, Double height) {
        super(width, height);
    }

    public double calculateArea() {
        return getWidth()*getHeight();
    }
    public double calculatePerimeter() {
        return 2*(getWidth()+getHeight());
    }
    public String display(){
        System.out.println("Rectangular Area: " + calculateArea());
        System.out.println("Rectangular Perimeter: " + calculatePerimeter());
        return "width: " + getWidth() + ", height: " + getHeight();
    }
}
