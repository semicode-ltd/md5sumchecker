/*
@Description : Small Tool To Check MD5SUM For Files Downloaded With Direct Links :)
@Author : Mohamed Saif Eldeen
@Company : SemiCode Inc.
@License : GPL V2.0
@Date : Friday 15 May 2015
@OS : It's Fucking Java Run It Any Where ;)

 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License version 2,
 *   as published by the Free Software Foundation.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */



// Important Imports Needed in Programs

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.*;
import java.util.*;
import javax.swing.event.*;

	public class md5check extends JFrame { // Begin Of Class
		static JLabel main_label;
		static JButton check , about , exit , select;
		static JTextField first , second;
		static JFileChooser fc;
		static String result;
		static JFrame about_frame;
		public md5check() { // Constractor
			super("MD5SUM Checker By : Mohamed Saif Eldeen");
			main_label = new JLabel();
			check = new JButton("Check");
			about = new JButton("About");
			exit = new JButton ("Exit");
			select = new JButton("Select File");
			first = new JTextField(30);
			second = new JTextField(30);
			fc = new JFileChooser("browse");
			about_frame = new JFrame ("About MD5SUM Checker");
			setSize(600,300);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			main_label.setLayout(null);
			check.setBounds(250, 200, 90, 30);
			main_label.add(check);
			about.setBounds(100, 200, 90, 30);
			main_label.add(about);
			exit.setBounds(400, 200, 90, 30);
			main_label.add(exit);
			select.setBounds(230, 30, 130, 30);
			main_label.add(select);
			first.setBounds(150 , 100 , 300 , 30);
			main_label.add(first);
			first.setEditable(false);
			second.setBounds(150 , 150 , 300 , 30);
			main_label.add(second);
			add(main_label);
			setVisible(true);
			about_frame.setSize(300 , 400);
			about_frame.setLocationRelativeTo(null);
			about_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
select.addActionListener(new ActionListener(){ // When Clicking On Select Button It Select File :) 
public void actionPerformed (ActionEvent e) {
	fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fc.getSelectedFile();
	try {
	String name = selectedFile.getName();
	System.out.println("Name is : "+name);
	System.out.println("Check Sum is :"+getMD5Checksum(name.toString()));
	first.setText(getMD5Checksum(name)); 
	} catch (Exception ex){
	}
    	}
}
});

check.addActionListener(new ActionListener(){ // Compare Strings in Both Text Fields
public void actionPerformed (ActionEvent e) {
if (first.getText().equals(second.getText())){
JOptionPane.showMessageDialog(null,"Checked Successfull :)");
}
else {
JOptionPane.showMessageDialog(null, "Sorry :(" , "Unvalid File Redownload it" , JOptionPane.ERROR_MESSAGE);
}
}
});

exit.addActionListener(new ActionListener(){ // Exiting Application When Click on Exit Button
public void actionPerformed (ActionEvent e) {
JOptionPane.showMessageDialog(null , "*Bye & Visit Us : www.semicode.org*");
System.exit(0);
}
});

about.addActionListener(new ActionListener(){ // Simple Message Display Application's Developer info 
public void actionPerformed (ActionEvent ex) {
JOptionPane.showMessageDialog(null , "This Application Coded By : Mohamed Saif Eldeen Core Developer [At] SemiCode Inc. :)");
about_frame.setVisible(true);
}
});

}
	
	public byte[] createChecksum(String filename) throws Exception {
       InputStream fis =  new FileInputStream(filename);

       byte[] buffer = new byte[1024];
       MessageDigest complete = MessageDigest.getInstance("MD5");
       int numRead;

       do {
           numRead = fis.read(buffer);
           if (numRead > 0) {
               complete.update(buffer, 0, numRead);
           }
       } while (numRead != -1);

       fis.close();
       return complete.digest();
   	}

	public String getMD5Checksum(String filename) throws Exception {
	byte[] b = createChecksum(filename);
       	result = "";
       for (int i=0; i < b.length; i++) {
           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
       }
       return result;
   	}
	
public static void main(String[] args) { // Main Function :) >>> Program Execution Start From Here
try { // Set Application Look & Feel To Default System Look & Feel
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}
catch (Exception ex) {
ex.printStackTrace ();
}	
new md5check(); // Calling Class Constractor
}
}
