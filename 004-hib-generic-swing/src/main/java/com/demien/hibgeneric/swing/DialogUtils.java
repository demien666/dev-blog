package com.demien.hibgeneric.swing;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogUtils {
    public static void showErrorDialog(String message) {
    	showErrorDialog(null, message);
    }
    public static void showErrorDialog(Component parentComponent, String message) {
    	JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
