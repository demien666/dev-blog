package com.demien.hibgeneric.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.demien.hibgeneric.domain.IDisplayable;
import com.demien.hibgeneric.service.IGenericService;

public class TableEdit<T extends IDisplayable> extends JFrame {

	public static enum EDIT_MODE {
		INSERT, UPDATE, DELETE
	}

	private final EDIT_MODE mode;
	private final T element;
	private JLabel[] labels;
	private JTextField[] fields;
	private final String caption;
	private final Object[] selectResults;
	private final TableView<T> parent;

	public TableEdit(String caption, EDIT_MODE mode, T element,
			TableView<T> parent) {
		this.mode = mode;
		this.element = element;
		this.caption = caption;
		this.selectResults = new Object[element.getColumnNames().length];
		this.parent=parent;
		createControls();

	}

	public void createControls() {
		this.setTitle(caption);
		int fieldCount = element.getColumnNames().length;
		labels = new JLabel[fieldCount];
		fields = new JTextField[fieldCount];
		this.getContentPane().setLayout(new GridLayout(fieldCount + 1, 3));
		String[] columnNames = element.getColumnNames();
		Object[] columnValues = element.getColumnValues();
		final Class<?>[] columnClasses = element.getColumnClasses();
		for (int i = 0; i < element.getColumnNames().length; i++) {
			// 1 label
			labels[i] = new JLabel(columnNames[i]);
			this.add(labels[i]);
			// 2 textFiled
			if (columnValues[i] != null) {
				fields[i] = new JTextField(columnValues[i].toString());
			} else {
				fields[i] = new JTextField();
			}
			this.add(fields[i]);
			// 3 select button
			final int ifinal = i;
			final Class<?> clfinal=columnClasses[i];
			if (element.getColumnValues()[i] != null) {
				selectResults[i] = element.getColumnValues()[i];
			}
			JButton btn = new JButton("Select");
			if (columnClasses[i].equals(Integer.class)
					|| columnClasses[i].equals(String.class)) {
				btn.setVisible(false);

			} else {
				btn.setVisible(true);
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Class<?> cl=clfinal;
						Object element=null;
						try {
							element = cl.newInstance();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (element instanceof IDisplayable) {
							TableView<?> tableView=((IDisplayable)element).getSelectForm();
							tableView.setSelectIndex(ifinal);
							tableView.setSelectSource(TableEdit.this);
							tableView.display();
						} else {
							JOptionPane.showMessageDialog(TableEdit.this, "Class "+cl.getName()+" should implement IDisplayable interface.");
							//throw new Exception("Class "+cl.getName()+" should implement IDisplayable interface.");
						}
						/*TableView selector = new TableView<T>(caption, service,
								ifinal, TableEdit.this);
						selector.display();*/
					}
				});
			}
			this.add(btn);
		}

		JButton btnDo = new JButton(mode.toString());
		JButton btnCancel = new JButton("Cancel");

		btnDo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processAction();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processCancel();
			}
		});

		this.add(btnDo);
		this.add(btnCancel);

		this.setTitle(mode.toString() + " : "
				+ element.getClass().getSimpleName());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public void display() {
		this.setVisible(true);
	}

	public void processSelectResult(int elementIndex, Object result) {
		selectResults[elementIndex] = result;
		fields[elementIndex].setText(result.toString());
	}

	private void processCancel() {
		this.setVisible(false);
		// this.dispose();
	}

	private void processAction() {
		Object[] values = new Object[fields.length];
		Class<?>[] classes = element.getColumnClasses();
		for (int i = 0; i < fields.length; i++) {
			// String
			if (classes[i].equals(String.class)) {
				values[i] = fields[i].getText();
			} else
			// Integer
			if (classes[i].equals(Integer.class)) {
				int intValue = Integer.parseInt(fields[i].getText());
				values[i] = new Integer(intValue);
			} else {
				values[i] = selectResults[i];
			}
		}
		// calling presenter
		element.restore(values);
		try {
		switch (mode) {
		case INSERT:
			parent.getService().save(element);
			break;
		case UPDATE:
			parent.getService().update(element);
			break;
		case DELETE:
			parent.getService().delete(element);
		}
		} catch (Exception e) {
			DialogUtils.showErrorDialog(this, e.getMessage());
		}
		// refresh parent form
		parent.refreshTable();
		// close form :
		processCancel();

	}

}
