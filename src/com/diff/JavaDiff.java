package com.diff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * This application uses Java to read files and return a boolean indicating whether or not
 * the files are different.
 * 
 * In it's current form, the method (compareFilesForDifferences) exclusively returns a boolean,
 * it would probably be more useful to actually calculate the differences should that be desired
 * 
 */
public class JavaDiff {
	
	public boolean compareFilesForDifferences(String file1Location, String file2Location) {
		String line;
		Map<Integer, String> fileOneMap = new HashMap<>();
		int counter = 1;
		boolean different = false;
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(file1Location));
			while ((line = br1.readLine()) != null) {
				fileOneMap.put(counter++, line);
			}
			counter = 1;
			BufferedReader br2 = new BufferedReader(new FileReader(file2Location));
			compare : while ((line = br2.readLine()) != null) {
				if(!fileOneMap.get(counter++).equals(line)) {
					different = true;
					break compare;
				}
			}
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return different;
	}
	
	public static void main(String[] args) {
		boolean areDifferent = new JavaDiff().compareFilesForDifferences("./file1.txt", "./file2.txt");
		
		System.out.println(areDifferent);
	}
}
