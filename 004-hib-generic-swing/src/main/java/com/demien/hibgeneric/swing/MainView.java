package com.demien.hibgeneric.swing;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.demien.hibgeneric.HibernateUtil;
import com.demien.hibgeneric.domain.Country;
import com.demien.hibgeneric.domain.Location;
import com.demien.hibgeneric.domain.Region;
import com.demien.hibgeneric.service.GenericServiceImpl;
import com.demien.hibgeneric.service.IGenericService;

public class MainView extends JFrame {

	public void display() {
        //JFrame frame = new JFrame("Test frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
              
        JMenuBar menuBar = new JMenuBar();
         
        JMenu fileMenu = new JMenu("File");                 
        JMenu newMenu = new JMenu("New");
        JMenu dictMenu=new JMenu("Dictionary");
        fileMenu.add(newMenu);
         
        JMenuItem miTxtFile = new JMenuItem("Text file");
        newMenu.add(miTxtFile);
         
        JMenuItem miImgFile = new JMenuItem("Image file");
        newMenu.add(miImgFile);
         
        fileMenu.addSeparator();
         
        JMenuItem miExit = new JMenuItem("Exit");
        fileMenu.add(miExit);
        
        JMenuItem miDictRegion=new JMenuItem("Region");
        miDictRegion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {          
            	new TableView<Region>("Region", Region.class).display();
            }
        });
        dictMenu.add(miDictRegion);
        
        JMenuItem miDictCountry=new JMenuItem("Country");
        miDictCountry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {   
            	new TableView<Country>("Country", Country.class).display();
            }
        });
        dictMenu.add(miDictCountry);
        
        JMenuItem miDictLocation=new JMenuItem("Locations");
        miDictLocation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {   
            	new TableView<Location>("Location", Location.class).display();
            }
        });
        dictMenu.add(miDictLocation);
         
        miExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
         
        menuBar.add(fileMenu);
        menuBar.add(dictMenu);
                 
        this.setJMenuBar(menuBar);
         
        //frame.setPreferredSize(new Dimension(270, 225));
        GraphicsEnvironment env =GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		
	}
}
