package xudong;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//直接使用compute计算
		String filepath="";
	    String outputPath="";
		String docfile="";
		String wordfile="";
		ComputeTfIdf.compute(filepath, outputPath, docfile, wordfile);
	}
}
