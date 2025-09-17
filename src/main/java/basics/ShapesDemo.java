package basics;

public class ShapesDemo {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(), new Rectangle()};
        for (Shape shape : shapes) {
            shape.draw();
        }

    }
}
