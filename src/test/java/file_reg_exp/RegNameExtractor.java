package file_reg_exp;

import java.io.File;
import java.util.List;

import embl.cba.fileutils.FileRegMatcher;
//import automic.utils.FileRegUtilsInterface;

public abstract class RegNameExtractor {
	
	
	
	static String testPath="D:/tempDat/STC1_project_(FV)/emt--test--subset--fv/raw_data";
	static String masterRegExp="(?<treatment>.+)--W(?<well>\\d+)--P(?<position>\\d+)--Z(?<slice>\\d+)--T(?<timePoint>\\d+)--(?<channel>.+)\\.tif";
	//static String masterRegExp="(?<nm>.+)--A568\\.tif";
	static String[] datasetGroups={"treatment","well","position"};
	
	public static void main(String[] args)throws Exception{
		System.out.println("Start test");
		
		FileRegMatcher regMatcher=new FileRegMatcher();
		
		regMatcher.setParameters(masterRegExp, datasetGroups);
		
		regMatcher.matchFiles(testPath);
		
		List<File> outputFiles=regMatcher.getMatchedFilesList();
		
		for (File f:outputFiles){
			System.out.println(f.getAbsolutePath());
		}
		
		System.out.println("End test");
		
		return;
	}
}
