package de.embl.cba.utils.fileutils;

import java.io.File;

import javax.swing.JFileChooser;


public abstract class PathMapperTest {
	
	
	
	
	public static void main(String[] args)throws Exception{
		System.out.println("Start test");
		
		JFileChooser fileChooser=new JFileChooser();
		int returnValue=fileChooser.showDialog(null, "Select test file");
		
		if (returnValue==JFileChooser.APPROVE_OPTION){
			File selectedFile=fileChooser.getSelectedFile();
		
		
			System.out.println("Selected test file: "+ selectedFile);
			System.out.println("Modified Path: "+ PathMapper.asEMBLClusterMounted(selectedFile.toPath()).toString());
		}
		System.out.println("End test");
		
		return;
	}
}
