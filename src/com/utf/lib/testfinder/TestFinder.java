package com.utf.lib.testfinder;

import java.lang.reflect.*;
import java.util.List;

import com.utf.config.Config;

public class TestFinder {

	public String path = Config.projectPath;
	
	public void getClassesWhichExtendFromTestCase(List<String> qualifiedNames) throws ClassNotFoundException{
		
		for(String qualName : qualifiedNames){
			Class<?> cls = Class.forName(qualName);
			System.out.println(cls);
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException{
		FileExplorer fe = new FileExplorer();
		TestFinder tf = new TestFinder();

		List<String> paths = fe.getAllFilesAndFolders();
		List<String> classFiles = fe.getClassFiles(paths);
		tf.getClassesWhichExtendFromTestCase(classFiles);		
	}
}
