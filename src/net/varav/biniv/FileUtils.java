/*
 * Copyright (c) 2015, Varav Inc. All rights reserved.
 * License terms are included in LICESNSE.txt file.
 */
package net.varav.biniv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FileUtils {
	
	public static int SUCCESS = 0;
	public static int UNABLE_TO_WRITE = 1;
	public static int FILE_EXISTS = 2;

	public static String readFile(String path) {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;	
		try {
			reader = new BufferedReader(new FileReader(path));
			String buffer;
			while((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
		} catch (IOException e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JOptionPane.showMessageDialog(null, "<html><br>Could not read from file: " + path + "<br>" + "Error: " + e.toString() + "</html>", "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ee) {}
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public static String[] readFile(String path1, String path2) {
		return new String[] {
			readFile(path1),
			readFile(path2)
		};
	}
	
	public static int writeToFile(String info, String path) {
		return writeToFile(info, path, true, 8192); //8192 is default
	}
	
	public static int writeToFile(String data, String path, boolean overrite, int bufferSize) {
		File file0 = new File(path);
		if(file0.exists() && !overrite) return FILE_EXISTS;
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
			String replaced = data.replaceAll("\t", "    ");
			String[] lines = replaced.split("\n");
			for(int i = 0; i < lines.length; i++) {
				writer.println(lines[i]);
			}
			writer.flush();
			writer.close();
			return SUCCESS;
		} catch (IOException e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				JOptionPane.showMessageDialog(null, "<html><br>Could not write to file: " + path + "<br>" + "Error: " + e.toString() + "</html>", "Error!", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ee) {}
		e.printStackTrace();
		}
		return UNABLE_TO_WRITE;
	}
	
}
