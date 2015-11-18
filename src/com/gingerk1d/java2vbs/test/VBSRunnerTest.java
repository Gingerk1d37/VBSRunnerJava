package com.gingerk1d.java2vbs.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gingerk1d.java2vbs.VBSRunner;

public class VBSRunnerTest {

	String blankTestFile = "blankTest.vbs";
	String paramTestFile = "paramTest.vbs";
	
	@Test
	public void testBlankScriptAbsolutePath() {
		try {
			URL url = this.getClass().getClassLoader().getResource(blankTestFile);
			File file = new File(url.toURI());
			System.out.println(file.getAbsolutePath());
			
			VBSRunner.runVBSScriptFromAbsolutePath(file.getAbsolutePath(), null);
			VBSRunner.runVBSScriptFromClasspath(blankTestFile, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testParamScriptNoParams() {
		try {	
			List<String> paramList = new ArrayList<String>();
			
			//VBSRunner.runVBSScriptFromAbsolutePath(file.getAbsolutePath(), null);
			VBSRunner.runVBSScriptFromClasspath(paramTestFile, paramList);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testParamScriptNullParams() {
		try {	
			List<String> paramList = new ArrayList<String>();
			
			//VBSRunner.runVBSScriptFromAbsolutePath(file.getAbsolutePath(), null);
			VBSRunner.runVBSScriptFromClasspath(paramTestFile, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}	
	
	@Test
	public void testParamScriptMultipleParams() {
		try {
			URL url = this.getClass().getClassLoader().getResource(paramTestFile);
			File file = new File(url.toURI());
			System.out.println(file.getAbsolutePath());
			
			List<String> paramList = new ArrayList<String>();
			paramList.add("ONE");
			paramList.add("TWO");
			paramList.add("THREE FOUR FIVE");
			paramList.add("SIX");
			paramList.add("7");
			paramList.add("8.000008");
			paramList.add("NINE=9");
			paramList.add("10 Eleven 12twelve");
			
			//VBSRunner.runVBSScriptFromAbsolutePath(file.getAbsolutePath(), null);
			VBSRunner.runVBSScriptFromClasspath(paramTestFile, paramList);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testParamScriptBadParams() {
		try {
			URL url = this.getClass().getClassLoader().getResource(paramTestFile);
			File file = new File(url.toURI());
			System.out.println(file.getAbsolutePath());
			
			List<String> paramList = new ArrayList<String>();
			paramList.add("ONE");
			paramList.add("TWO");
			paramList.add("THREE FOUR FIVE");
			paramList.add("SIX\"");
			paramList.add("7");
			paramList.add("8.000008");
			paramList.add("NINE=9");
			paramList.add("10 Eleven 12twelve");
			
			//VBSRunner.runVBSScriptFromAbsolutePath(file.getAbsolutePath(), null);
			VBSRunner.runVBSScriptFromClasspath(paramTestFile, paramList);
			Assert.fail("Should fail");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
