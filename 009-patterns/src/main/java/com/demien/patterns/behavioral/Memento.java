package com.demien.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 Actors:
 1. memento class
 2. client
 Goal: memento object saves it state on every change.
   so, we can get it state, for every step we want.
 */
public class Memento {

    public class Shape {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void moveTo(int x, int y) {
            this.x=x;
            this.y=y;
        }

    }

    public class ShapeMemento {
        private List<Shape> history=new ArrayList<Shape>();

        public void save(Shape shape) {
            Shape historyShape=new Shape();
            historyShape.moveTo(shape.getX(), shape.getY());
            history.add(historyShape);
        }

        public Shape getShapeForStep(int index) {
            Shape result=history.get(index);
            return result;
        }
    }

}
