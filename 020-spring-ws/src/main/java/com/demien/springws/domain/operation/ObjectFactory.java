//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 02:49:08 PM CET 
//


package com.demien.springws.domain.operation;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.demien.springws.domain.operation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.demien.springws.domain.operation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoryGetByIdResponse }
     * 
     */
    public CategoryGetByIdResponse createCategoryGetByIdResponse() {
        return new CategoryGetByIdResponse();
    }

    /**
     * Create an instance of {@link CategorySaveRequest }
     * 
     */
    public CategorySaveRequest createCategorySaveRequest() {
        return new CategorySaveRequest();
    }

    /**
     * Create an instance of {@link CategorySaveResponse }
     * 
     */
    public CategorySaveResponse createCategorySaveResponse() {
        return new CategorySaveResponse();
    }

    /**
     * Create an instance of {@link CategoryGetByIdRequest }
     * 
     */
    public CategoryGetByIdRequest createCategoryGetByIdRequest() {
        return new CategoryGetByIdRequest();
    }

}
