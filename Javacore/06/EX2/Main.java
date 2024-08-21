package EX2;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Rectangular rectangular = new Rectangular(9.0, 9.0);
        Square square = new Square(4);

        System.out.println(rectangular.display());
        System.out.println(square.display());
    }
}
