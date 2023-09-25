class HelloWorld {
    public static void main(String args[]){
        System.out.println("Hello Poop!");
    
        var i = 1337;

        if (i == 1) {
            System.out.println(1);
        }

        else if (i == 2) {
            System.out.println(i);
        }

        else {
            System.out.println("Snoop doop");
        }

        String[] colors = {"Red", "Green", "Blue"};

        for (String col : colors){
            System.out.println(col);
        }

        Doot doot = new Doot();
        doot.poop();
    
        Shape shape = new Triangle(10.0, 5.0);
        double a = shape.calculateArea();
        System.out.println(a);
        }

    }

class Doot {
    public void poop() {
        System.out.println("nice poop bro");
    }
}

interface Shape {
    public double calculateArea();
    }
class Rectangle implements Shape {
    private double length;
    private double width;
    // ...
    public double calculateArea() {
        return length * width;
    }
}

class Triangle implements Shape {
    private double base;
    private double height;
    // ...
    
    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return (base*height)/2;
    }
}