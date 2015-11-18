/**
 * Quick test to run vbscript from java
 * 
 * @author gingerk1d
 * @date 2015-11-18
 */

package com.gingerk1d.java2vbs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VBSRunner {
	
	private final static String wscript = "wscript";
	
	/**
	 * Runs a VBScript with the passed parameters. Automatically adds double quotes to params with spaces. 
	 * Throws Exception if double quotes passed in parameter list.
	 * Throws Exception if error with running WScript
	 * 
	 * @author gingerk1d
	 * @date 2015-11-18
	 * @param scriptAbsolutePath
	 * @param paramList
	 * @throws Exception
	 */
	public static void runVBSScriptFromAbsolutePath(String scriptAbsolutePath, List<String> paramList) throws Exception {
		//set the param list
		String stringParams = "";
		if (paramList != null) {
			stringParams = " ";
			for (int i=0;i<paramList.size();i++) {
				//add a leading whitespace
				//cut out whitespace
				String oneParam = paramList.get(i).trim();
				
				//if the param has a space ensure it's surrounded by double quotes
				if (oneParam.contains("\"")) throw new Exception("Can't pass double quotes in parameter: "+oneParam);
					
				if (oneParam.contains(" ")) {
					oneParam = "\""+oneParam+"\"";
				}
				
				stringParams = stringParams+oneParam;
				
				//if there are more values add a trailing space
				if (i<paramList.size()-1) stringParams = stringParams+" ";
			}//for
			System.out.println("Created Param List: "+stringParams);			
		} //if
		
		try {
			String cmdScript = wscript +" "+scriptAbsolutePath + stringParams;
			System.out.println(cmdScript);
			
			Runtime.getRuntime().exec(cmdScript);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error Running Script: "+scriptAbsolutePath, e);
		}
	}
	
	/**
	 * Runs a VBScript with the passed parameters. Automatically adds double quotes to params with spaces. 
	 * Throws Exception if double quotes passed in parameter list.
	 * Throws Exception if error with running WScript
	 * 
	 * @author gingerk1d
	 * @date 2015-11-18
	 * @param absolutePath
	 * @param paramList
	 * @throws Exception
	 */
	public static void runVBSScriptFromClasspath(String scriptClasspath, List<String> paramList) throws Exception {
		//set the param list
		URL url = VBSRunner.class.getClassLoader().getResource(scriptClasspath);
		File file = new File(url.toURI());
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		runVBSScriptFromAbsolutePath(absolutePath, paramList);
	}

}
