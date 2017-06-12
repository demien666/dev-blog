package com.demien.hibgeneric.domain;

import com.demien.hibgeneric.swing.TableView;

public interface IDisplayable {
    Object[] getColumnValues();
    String[] getColumnNames();
    Class[] getColumnClasses();
    void restore(Object[] values);
    TableView<?> getSelectForm();
}
