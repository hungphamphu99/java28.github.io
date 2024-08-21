package EX2;

public class Square extends Geometry {
    public Square(double side){
        super(side, side);
    }
public double calculateArea(){
        return getHeight()*getHeight();

}
public double calculatePerimeter(){
        return 4*getWidth();
}
public String display(){
        System.out.println("Square" +calculateArea());
        System.out.println("Perimeter" +calculatePerimeter());


    return "Side is"+getWidth();
}
}
