package xudong;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUtils {
  
  public static void buildFileUrl( String url ){
    File file = new File(url);
    if( file.exists() ){
      file.delete();
    }
  }
  
  public static String readContent(String fileDir) throws IOException{
    
    BufferedReader reader = read(fileDir,"utf-8");
    String outStr = "";
    
    String lineTxt = "";
    int count = 0;
    
    while( (lineTxt=reader.readLine()) != null ){
      
      ++count;
      if( count%10000 == 0  ){
        System.out.println("sample "+count);
      }
      outStr += lineTxt;
      
    }
    
    reader.close();
    
    System.out.println("total lines:\t"+count);
    return outStr;
    
    
  }

  public static void readSamples(String fileDir, int numOfSamples) throws IOException{
    
    BufferedReader reader = read(fileDir);
    String lineTxt = "";
    int count = 0;
    
    while( (lineTxt=reader.readLine()) != null ){
      
      ++count;
      if( count<=numOfSamples ){
        System.out.println("sample "+count+":\t"+lineTxt);
      }
      
    }
    
    System.out.println("total lines:\t"+count);
    
  }
  
  /*****read file and return BufferedReader*****/
  public static BufferedReader read ( String fileDir ) {
    
    return (read(fileDir, "utf-8"));
    
  }
  
  /*****read file and return BufferedReader*****/
  public static BufferedReader read ( File file ) {
    
    return (read(file, "utf-8"));
    
  }
  
  public static BufferedReader read ( File file, String encoding){
    
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader( new InputStreamReader( new FileInputStream( file),encoding));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return reader;
    
  }
  
  public static BufferedReader read ( String fileDir, String encoding){
    
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File( fileDir)),encoding));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return reader;
    
  }
  
  public static List<String> readList(String filePath ) throws IOException{
    
    List<String> list = new ArrayList<String>();
    BufferedReader reader = read(filePath);
    
    String lineTxt = "";
    int count=0;
    
    while( (lineTxt=reader.readLine()) != null ){
      ++count;
      if( count%500000 == 0 )
        System.out.println("read lines:\t"+count);
    
      list.add(lineTxt);
      
    }
    
    return list;
    
  }
  
  public static Set<String> readSet(String filePath ) throws IOException{
    
    Set<String> set = new HashSet<String>();
    BufferedReader reader = read(filePath);
    
    String lineTxt = "";
    int count=0;
    
    while( (lineTxt=reader.readLine()) != null ){
      ++count;
      if( count%500000 == 0 )
        System.out.println("read lines:\t"+count);
    
      set.add(lineTxt);
      
    }
    
    return set;
    
  }
  
  public static Map<String,String> readFileToMap(String file) throws IOException{

    return readFileToMap(file,"\t");
    
  }

  public static Map<String,String> readFileToMap(String file, String delimate) throws IOException{
    
    Map<String,String> outMap = new HashMap<String,String>();
    BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( file)));
      
    String lineTxt = "";
    int count=0;
    
    while( (lineTxt=reader.readLine()) != null ){
      ++count;
      if( count%100000 == 0 )
        System.out.println("read lines:\t"+count);
    
      outMap.put(lineTxt.split(delimate)[0], lineTxt.split(delimate)[1]);
      
    }
    
    System.out.println("read completely:\t"+outMap.size());
    reader.close();
    return outMap;
    
  }
  
  public static Map<String,String> readFileToMapByLine(String file) throws IOException{

    return readFileToMapByLine(file,"\t");
    
  }
  
  public static Map<String,String> readFileToMapByLine(String file, String delimate) throws IOException{
    
    Map<String,String> outMap = new HashMap<String,String>();
    BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( file)));
      
    String lineTxt = "";
    int count=0;
    
    while( (lineTxt=reader.readLine()) != null ){
      ++count;
      if( count%100000 == 0 )
        System.out.println("read lines:\t"+count);
    
      outMap.put(lineTxt.split(delimate)[0].trim(), lineTxt.split(delimate)[1]);
      
    }
    
    System.out.println(file+"\tread completely:\t"+outMap.size());
    reader.close();
    return outMap;
    
  }
  
  public static int getFileLines ( File fileDir) throws IOException{
    
    BufferedReader reader = null;
    int count = 0;
    try {
      reader = new BufferedReader( new InputStreamReader( new FileInputStream( fileDir)));

      
      while( reader.readLine() !=null){
        ++count;
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return count;
    
  }
  
  /*****write content to file
   * @throws IOException 
   * @throws FileNotFoundException *****/
  public static void save ( String fileDir, String content, boolean isOverwrite ) throws FileNotFoundException, IOException{
    
    save(fileDir, content, isOverwrite, "utf-8");
    
  }
  
 
  public static void save ( String fileDir, String content, boolean isOverwrite, 
      String encoding) throws IOException, FileNotFoundException{
    
    BufferedWriter writer = null;
    
    if( isOverwrite ){
      
      writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File( fileDir)),encoding));
      writer.write(content);
      
    } else {
      
      writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File( fileDir), true),encoding));
      writer.append(content);
      
    }
    
    writer.close();
    
  }
  
  /*********
   * write List to file***/
  public static void saveList( String fileDir, List<?> list ) throws FileNotFoundException, IOException{
    
    String saveStr = "";
    int count = 0;
    
    for( Object obj : list ){
      
      saveStr += obj+"\n";
      
      ++count;
      if( count%500000 == 0 ){
//        save(fileDir,saveStr,false);
        saveStr = "";
      }
    }
    
    save(fileDir,saveStr,true);
    
  }
  
  /*********
   * write List to file***/
  public static void saveSet( String fileDir, Set<?> list ) throws FileNotFoundException, IOException{
    
    String saveStr = "";
    int count = 0;
    
    for( Object obj : list ){
      
      saveStr += obj+"\n";
      
      ++count;
      if( count%500000 == 0 ){
//        save(fileDir,saveStr,false);
        saveStr = "";
      }
    }
    
    save(fileDir,saveStr,true);
    
  }
  
  public static void saveMapTwoValues( String fileDir, Map<String,Integer> map ){

    String outStr = "";
    for( String str : map.keySet() ){
      
      outStr += str+"\t"+map.get(str)+"\n";
      
    }
    
    try {
      save(fileDir,outStr,true);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    
  }
  
  public static void saveMapTwoStringValues( String fileDir, Map<String,String> map ){

    String outStr = "";
    for( String str : map.keySet() ){
      
      outStr += str+"\t"+map.get(str)+"\n";
      
    }
    
    try {
      save(fileDir,outStr,false);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    
  }
  
  /*****write List to file
   * @throws IOException 
   * @throws FileNotFoundException *****/
  public static void save ( String fileDir, List<Object[]> content, boolean isOverwrite ) throws FileNotFoundException, IOException{
    
    save(fileDir, content, isOverwrite, "gbk", "\t");
    
  }
  
  public static void save ( String fileDir, List<Object[]> content, boolean isOverwrite, 
      String encoding, String separator) throws IOException, FileNotFoundException{
    
    BufferedWriter writer = null;
    
    if( isOverwrite ){
      
      writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File( fileDir)),encoding));
      
      String strTmp = "";
      int count = 0;
      
      for( Object[] array : content){
        ++count;
        
        if( count%5000 == 0 ){
          
          System.out.println("file saved "+count+" ...");
          writer.write(strTmp);
          strTmp = "";
          
        }
        
        for ( int i=0; i<array.length; i++){
          strTmp += array[i]+separator;
        }
        
        strTmp += "\n";
      }
      
      writer.write(strTmp);
      
    } else {
      
      writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File( fileDir), true),encoding));

      String strTmp = "";
      int count = 0;
      
      for( Object[] array : content){
        ++count;
        
        if( count%5000 == 0 ){
          
          System.out.println("file saved "+count+" ...");
          writer.append(strTmp);
          strTmp = "";
          
        }
        
        for ( int i=0; i<array.length; i++){
          strTmp += array[i]+separator;
        }
        
        strTmp += "\n";
      }
      
      writer.append(strTmp);
      
    }
    
    writer.close();
    
  }
  
  /*******
   * save Map<String,List<String>> to file
   * *************/
  public static void saveMap(String fileDir, Map<Object,List<Object>> map){
    
    String fileStr = "";
    
    int count = 0;
    for(Object obj : map.keySet() ){
      
      for( Object o : map.get(obj) ){
        
        fileStr += obj+"\t"+o+"\n";
        
        ++count;
        if( count%100000 == 0 ){
          try {
            
            save(fileDir,fileStr,false);
            fileStr = "";
            
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        
      }
      
    }
    
    try {
      save(fileDir,fileStr,false);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public static void saveMapList(String fileDir, Map<String,List<String>> map){
    
    for(Object obj : map.keySet() ){
      
      String fileStr = "";
      for( String o : map.get(obj) ){
        
        fileStr += o+"\n";
        
      }
      
      try {
        save(fileDir+obj,fileStr,false);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    }
    
    
    
  }
  
  /********make the line string of file as arrays and read*********/
  public static List<Object[]> readListArrayFromFile( String fileDir ){
    
    return readListArrayFromFile(fileDir, "gbk", "\t");
    
  }
  
  public static List<Object[]> readListArrayFromFile( String fileDir, String encoding, String delimiter ){
    
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = null;
    int count=0;
    try {
      reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File( fileDir)),encoding));
      
      String lineTxt = "";
      
      while ( (lineTxt=reader.readLine()) != null ){
        
        ++count;
        if( count%1000000==0 ){
          System.out.println("reading lines "+count);
        }
        list.add(lineTxt.split(delimiter));
        
        
      }
      
      
    } catch (IOException e) {
      e.printStackTrace();
    } 
    
    
    
    
    return list;
    
  }
  
  
  /********make the line string of file as Map<String,String> and read*********/
  public static Map<Object,Object> readMapFromFile( String fileDir ){
    
    return readMapFromFile(fileDir, "gbk", "\t");
    
  }
  
  public static Map<Object,Object> readMapFromFile( String fileDir, String encoding, String delimiter ){
    
    Map<Object,Object> map = new HashMap<Object,Object>();
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File( fileDir)),encoding));
      
      String lineTxt = "";
      int count=0;
      while ( (lineTxt=reader.readLine()) != null ){
        
        ++count;
        if( count%1000000==0 ){
          System.out.println("reading lines "+count);
        }
        String[] array = lineTxt.split("\t");
        map.put(array[0], array[1]);
        
        
      }
      
      
    } catch (IOException e) {
      e.printStackTrace();
    } 
    
    
    
      return map;
    
  }
  
}
