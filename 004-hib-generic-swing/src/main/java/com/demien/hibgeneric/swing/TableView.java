package com.demien.hibgeneric.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.demien.hibgeneric.HibernateUtil;
import com.demien.hibgeneric.domain.IDisplayable;
import com.demien.hibgeneric.domain.Region;
import com.demien.hibgeneric.service.GenericServiceImpl;
import com.demien.hibgeneric.service.IGenericService;

public class TableView<T extends IDisplayable> extends JFrame {

	private static final long serialVersionUID = -4645755885782493419L;
	private IGenericService<T> service;
	private JTable table;
	private GenericTableModel<T> model;
	private final String caption;
	private TableEdit<?> selectSource;
	private Integer selectIndex;
	private T element;
	
	// UI Controls
    JButton btnInsert = new JButton("Insert");
    JButton btnUpdate = new JButton("Update");
    JButton btnDelete = new JButton("Delete");
    JButton btnSelect = new JButton("Select");


	public TableView(String caption, Class cl) {
		this.caption=caption;
		this.service=new GenericServiceImpl<T>(cl, HibernateUtil.getSessionFactory());
		this.model=new GenericTableModel<T>(service);	
		try {
			element=(T)service.getElementClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createControls();
	}
	
	public void setSelectSource(TableEdit<?> selectSource) {
		this.selectSource=selectSource;
	}
	
	public void setSelectIndex(Integer selectIndex) {
		this.selectIndex=selectIndex;
	}

    public final void createControls() {
        //Container c = getContentPane();
        //---- buttons
        JPanel pnlButtons = new JPanel();

            pnlButtons.add(btnInsert);
            pnlButtons.add(btnUpdate);
            pnlButtons.add(btnDelete);
            
            btnInsert.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    new TableEdit<T>(caption, TableEdit.EDIT_MODE.INSERT, element, TableView.this).display();
                }
            });
            
            btnUpdate.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    Object[] values = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        values[i] = model.getValueAt(selectedRow, i);
                    }                    
                    element.restore(values);
                    new TableEdit<T>(caption, TableEdit.EDIT_MODE.UPDATE, element, TableView.this).display();
                }
            });
            
            btnDelete.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    Object[] values = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        values[i] = model.getValueAt(selectedRow, i);
                    }                    
                    new TableEdit<T>(caption, TableEdit.EDIT_MODE.DELETE, element, TableView.this).display();
                }
            });
            

            
            pnlButtons.add(btnSelect);



        // ------ table
        table = new JTable(model);
        JPanel pnlTable = new JPanel();

        //add the table to the frame
        //this.add(new JScrollPane(table));
        //this.add(table);
        pnlTable.add(new JScrollPane(table));
        
        this.getContentPane().setLayout(new BorderLayout());
        this.add(pnlButtons, BorderLayout.NORTH);
        //this.add(pnlButtons);
        this.add(pnlTable, BorderLayout.CENTER);
        
        this.setTitle(caption);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);        
        this.pack();
        this.setLocationRelativeTo( null );
    }
	

	
	public void display() {
		if (selectSource!=null) {
			btnInsert.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			btnSelect.setVisible(true);
            btnSelect.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    Object[] values = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        values[i] = model.getValueAt(selectedRow, i);
                    }
                    element.restore(values);
                    selectSource.processSelectResult(selectIndex, element);
                    TableView.this.setVisible(false);
                }
            });		
		} else {
			btnInsert.setVisible(true);
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
			btnSelect.setVisible(false);
			
		}
		this.setVisible(true);
	}
	
	public void refreshTable() {
		model.fireTableDataChanged();
	}
	
	public IGenericService<T> getService(){
		return service;
	}
	
}
