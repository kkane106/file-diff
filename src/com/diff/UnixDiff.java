package com.diff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * The following example illustrates how you could step out of Java to use the runtime environment
 * to execute commands in that environment.
 * 
 * This example assumes a unix runtime environment (using 'diff'), however, the Runtime.getRuntime()
 * method returns the current runtime, thus, if the runtime had powershell instead, you could use 
 * powershell commands instead of unix.
 */
public class UnixDiff {
	public void runBashCmd(String cmd) {
		// get the current runtime
		Runtime rt = Runtime.getRuntime();
		String line;
		try {
			// execute a command in the runtime, this returns a process
			Process p = rt.exec(cmd);
			// read the process's stream (e.g. the return value from the bash command)
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			// read the error (e.g.	diff: file.txt: No such file or directory) from the processs
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while((line = error.readLine()) != null) {
				System.err.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UnixDiff().runBashCmd("diff file1.txt file2.txt");;
	}
}
