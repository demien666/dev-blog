package com.demien.patterns.behavioral;

/**
 Actors:
 1. Visitor. several visit(Element) - for every subclass of Element.
 2.Element. Element has accept(Visitor) method.
 Goal: logic separation: element calls visitor's method, which calls element's method
 */
public class Visitor {

    public interface ShapeVisitor {
        public String visit(Circle element);
        public String visit(Square element);
    }

    public abstract class Shape {
        public abstract void accept(ShapeVisitor visitor);

        public String draw() {
            return "drawing";
        };
    }

    public class Circle extends Shape {

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }

    }

    public class Square extends Shape {

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }

    }

    public class FastShapeVisitor implements ShapeVisitor {

        @Override
        public String visit(Circle element) {
            return "fast circle "+element.draw();
        }

        @Override
        public String visit(Square element) {
            return "fast square "+element.draw();
        }
    }
}
