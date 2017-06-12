package com.demien.hibgeneric;

import com.demien.hibgeneric.swing.MainView;


public class App {
	public static void main(String[] args) {
		// just to initialize hibernate 
		HibernateUtil.getSessionFactory();
		
		// show main window
		new MainView().display();

	}
}
