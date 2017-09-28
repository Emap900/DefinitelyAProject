package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class can run bash commands and save the stdout as output.
 * This class can take 1-3 parameters:
 * 1 parameter: bash command as a string.
 * 2 parameters:  relative location of sh file and bash command as 2 strings
 * 3 parameters:  
 * @author wche900
 *
 */
public class BashProcess {

	// this is an array to store stdout from bash
	private ArrayList<String> _result;
	public  BashProcess(String command) {
		_result = new ArrayList<String>();
		
		//build process
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			int exitStatus = process.waitFor();

			// if exitStatus is 0 when the process output nornally
			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					_result.add(line);
				}
			}else {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}	
	public  BashProcess(String path, String command) {
		_result = new ArrayList<String>();
		try {
			ProcessBuilder pb = new ProcessBuilder(path,command);
			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					_result.add(line);
				}
			}else {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}	

	public BashProcess(String path, String command, String functionName) {
		System.out.println("0");
		_result = new ArrayList<String>();
		try {
			System.out.println("1");
			ProcessBuilder pb = new ProcessBuilder(path,command,functionName);
			Process process = pb.start();

			System.out.println("2");
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			System.out.println("3");
			int exitStatus = process.waitFor();

			System.out.println("4");
			if (exitStatus == 0) {
				System.out.println("5");
				String line;
				while ((line = stdout.readLine()) != null) {
					System.out.println("6");
					_result.add(line);
				}
			}else {
				System.out.println("7");
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getresult() {
		return _result;	
	}


}
