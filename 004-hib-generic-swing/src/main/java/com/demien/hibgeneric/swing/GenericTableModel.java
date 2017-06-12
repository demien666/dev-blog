package com.demien.hibgeneric.swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.demien.hibgeneric.dao.DAOException;
import com.demien.hibgeneric.domain.IDisplayable;
import com.demien.hibgeneric.service.IGenericService;

public class GenericTableModel<T extends IDisplayable> extends AbstractTableModel {

	private static final long serialVersionUID = 1561915663812379605L;
	private IGenericService<T> service; 
	
	public GenericTableModel(IGenericService<T> service) {
		this.service=service;
	}
	
	public List<T> getElements() {
		try {
			return service.getAll();
		} catch (DAOException e) {
			DialogUtils.showErrorDialog(e.getMessage());
		}
		return null;
	}

	@Override
	public int getRowCount() {
		List<T> items=getElements();
		return items.size();
	}

	@Override
	public int getColumnCount() {
		Class<T> cl= service.getElementClass();
		try {
			T element=cl.newInstance();
			return element.getColumnNames().length;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		List<T> items=getElements();
		T element=items.get(rowIndex);
		return element.getColumnValues()[columnIndex];
	}
	
    @Override
    public String getColumnName(int column)
    {
		Class<T> cl= service.getElementClass();
		try {
			T element=cl.newInstance();
			return element.getColumnNames()[column];
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return "";
    }	

}
