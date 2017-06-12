package com.demien.patterns.behavioral;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by dmitry on 18.08.14.
 */
public class MementoTest {

    @Test
    public void mementoTest(){
        Memento memento=new Memento();
        Memento.Shape shape=memento. new Shape();
        Memento.ShapeMemento shapeMemento=memento. new ShapeMemento();
        shape.moveTo(0,0);
        shapeMemento.save(shape);
        shape.moveTo(1,1);
        shapeMemento.save(shape);

        shape=shapeMemento.getShapeForStep(0);
        Assert.assertEquals(0, shape.getX());
        Assert.assertEquals(0, shape.getY());

        shape=shapeMemento.getShapeForStep(1);
        Assert.assertEquals(1, shape.getX());
        Assert.assertEquals(1, shape.getY());

    }
}
