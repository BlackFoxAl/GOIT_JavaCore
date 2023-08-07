package Module8;

public class TestGeometricFigures {
    public static void main(String[] args) {
        new PrintFigure(new Circle());
        new PrintFigure(new Quad());
        new PrintFigure(new Rhombus());
        new PrintFigure(new Triangle());
        new PrintFigure(new Pentagon());
    }
}

abstract class Shape {
    public String getName() {
        return "shape";
    }
}

class PrintFigure {
    PrintFigure(Shape shape) {
        System.out.println(shape.getName());
    }
}

class Circle extends Shape {
    public String getName() {
        return "circle";
    }
}

class Quad extends Shape {
    public String getName() {
        return "quad";
    }
}

class Rhombus extends Shape {
    public String getName() {
        return "rhombus";
    }
}

class Pentagon extends Shape {
    public String getName() {
        return "pentagon";
    }
}

class Triangle extends Shape {
    public String getName() {
        return "triangle";
    }
}
