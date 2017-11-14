package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileUtils {
	
	/**
	 * 读取文件夹下的所有文件的内容
	 * @param path
	 */
	public static void readFilesDir(String path){
		LinkedList<File> list = new LinkedList<File>();
		File dir = new File(path);
		File[] files = dir.listFiles(); 
		
		for(File file : files){
			if(file.isDirectory()){
				list.add(file);
				System.out.println(file.getAbsolutePath());
			}else{
				//处理文件内容
				System.out.println(file.getAbsolutePath());
			}
		}
		
		File temp;
		while(!list.isEmpty()){
			temp = list.removeFirst();
			if(temp.isDirectory()){
				files = temp.listFiles();
				if(files == null) continue;
				for(File file : files){
					if(file.isDirectory()){
						list.add(file);
					}else{
						//处理文件内容
						System.out.println(file.getAbsolutePath());
					}
				}
			}else{
				//处理文件内容,这种情况好像不会发生
				System.out.println("-------------");
			}
		}
	}
	
    /**
     * 读取单个文件的内容
     * @param file
     * @return 文件的内容，String
     */
	public static String readContents(File file){
		StringBuilder res = new StringBuilder();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			while(br.ready()){
				res.append(br.readLine() + "\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res.toString();
	}
	
	/**
	 * 保存文件
	 */
	public static void saveFiles(String contents, String output){
		File outputFile = new File(output);
		try {
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));
			bw.write(contents);
			
			bw.flush();
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("保存成功！！！");
	}
	
	public static void main(String[] args) {
		String path = "F:/testfile";
		
		readFilesDir(path);
				
	}
}
