package com.demien.patterns.behavioral;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by dmitry on 18.08.14.
 */
public class VisitorTest {

    @Test
    public void visitorTest() {
        Visitor visitor=new Visitor();
        Visitor.FastShapeVisitor fastShapeVisitor=visitor. new FastShapeVisitor();
        Visitor.Circle circle=visitor.new Circle();
        Visitor.Square square=visitor. new Square();
        String result="";
        result=fastShapeVisitor.visit(circle);
        Assert.assertEquals("fast circle drawing", result);
        result=fastShapeVisitor.visit(square);
        Assert.assertEquals("fast square drawing", result);

    }

}
