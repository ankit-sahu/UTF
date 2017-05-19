package com.utf.lib.testfinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.utf.config.Config;

public class FileExplorer {

	public String projectLoc = Config.projectPath;
	public Stream<Path> paths;
	public List<String> pathList = new ArrayList<String>();
	
	public List<String> getAllFilesAndFolders(){
		
		try{
			paths = Files.walk(Paths.get(projectLoc));
			Iterator<Path> pathIterator = paths.iterator();
			while(pathIterator.hasNext()){
				String fullPath = pathIterator.next().toString();
				pathList.add(fullPath);
			}		
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			paths.close();
		}
		return pathList;
	}
	
	public List<String> getClassFiles(List<String> allPaths){
		
		List<String> classFiles = new ArrayList<String>();
		try{	
			for(String path : allPaths){
				if(path.endsWith(".class")){
					classFiles.add(path);
				}
			}
		}catch(Exception e){
			System.out.println("There are no class files present");
			e.printStackTrace();
		}
		return classFiles;
	}
	
	public List<String> getRelativePath(List<String> classFiles){
		List<String> relativeFilePaths = new ArrayList<String>();
		for(String classFile : classFiles){
			String relPath = classFile.replace(Config.packagesPath, "");
			System.out.println(relPath);
			relativeFilePaths.add(relPath);
		}
		return relativeFilePaths;
	}
	
	public List<String> getQualifiedName(List<String> relPaths){
		List<String> qualifiedNames = new ArrayList<String>();
		for(String relPath : relPaths){
			String[] qualName = relPath.split("/");
			qualifiedNames.add(String.join(".", qualName));
		}
		return qualifiedNames;
	}
	
	public List<String> getQualifiedClassNameWithoutExtention(List<String> qualifiedNames){
		List<String> qualifiedNamesWithoutExtentions = new ArrayList<String>();
		for(String name : qualifiedNames){
			qualifiedNamesWithoutExtentions.add(name.substring(0, name.length()-6));
		}
		return qualifiedNamesWithoutExtentions;
	}

	
//	public static void main(String[] args) throws ClassNotFoundException{
//		FileExplorer fe = new FileExplorer();
////		TestFinder tf = new TestFinder();
//
//		List<String> paths = fe.getAllFilesAndFolders();
//		List<String> classFiles = fe.getClassFiles(paths);
//		List<String> relFilepath =fe.getRelativePath(classFiles);
//		List<String> qualNames = fe.getQualifiedName(relFilepath);
//		List<String> withoutExtention = fe.getQualifiedClassNameWithoutExtention(qualNames);
//		for(String name : withoutExtention){
//			System.out.println(name);
//		}
//	}
	
}
