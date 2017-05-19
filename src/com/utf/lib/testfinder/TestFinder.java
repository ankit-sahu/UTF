package com.utf.lib.testfinder;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utf.config.Config;

public class TestFinder {

	public String path = Config.projectPath;
	
	public List<Class<?>> getClassesWhichExtendFromTestCase(List<String> qualifiedNames) throws ClassNotFoundException{
		List<Class<?>> classesExtendingFromTestCaseClass = new ArrayList<Class<?>>();
		for(String qualName : qualifiedNames){
			Class<?> cls = Class.forName(qualName);
			Class<?> superClass = cls.getSuperclass();
			String currentClass = superClass.toString();
			if(currentClass.endsWith(".TestCase")){
				classesExtendingFromTestCaseClass.add(cls);
			}	
		}
		return classesExtendingFromTestCaseClass;
	}
	
	public Map<Class<?>, Method[]> getMethodsOfTestClasses(List<Class<?>> testClasses){
		Map<Class<?>, Method[]> testClassMethodMap = new HashMap<Class<?>, Method[]>();
		for(Class<?> testClass : testClasses){
			Method testMethods[] = testClass.getDeclaredMethods();
			testClassMethodMap.put(testClass, testMethods);
		}
		return testClassMethodMap;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException{
		FileExplorer fe = new FileExplorer();
		TestFinder tf = new TestFinder();

		List<String> paths = fe.getAllFilesAndFolders();
		List<String> classFiles = fe.getClassFiles(paths);
		List<String> relFilepath =fe.getRelativePath(classFiles);
		List<String> qualNames = fe.getQualifiedName(relFilepath);
		List<String> withoutExtention = fe.getQualifiedClassNameWithoutExtention(qualNames);
		List<Class> classesExtendingFromTestCaseClass = tf.getClassesWhichExtendFromTestCase(withoutExtention);
		for(Class parentClass : classesExtendingFromTestCaseClass){
			System.out.println(parentClass.toString());
		}
	}
}
