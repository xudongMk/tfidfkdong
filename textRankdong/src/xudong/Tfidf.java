package xudong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tfidf {
	/**
	 * 文档索引和单词索引
	 */
	static Map<String, Integer> docIndex=new HashMap<String, Integer>();
	static Map<String, Integer> wordIndex=new LinkedHashMap<String, Integer>();
	
	/**
	 * 计算每个文档的tf值
	 * @param wordAll
	 * @return
	 */
	public static Map<String,Float> tfCalculate(String wordAll){
		//存放（单词，单词数量）
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		//存放（单词，单词词频）
		HashMap<String, Float> tf = new HashMap<String, Float>();
		int wordCount=0;
		
		/**
		 * 统计每个单词的数量，并存放到map中去
		 * 便于以后计算每个单词的词频
		 * 单词的tf=该单词出现的数量n/总的单词数wordCount
		 */
		for(String word:wordAll.split(" ")){
			wordCount++;
			if(dict.containsKey(word)){
				dict.put(word,  dict.get(word)+1);
			}else{
				dict.put(word, 1);
			}
		}
		
		for(Map.Entry<String, Integer> entry:dict.entrySet()){
			float wordTf=(float)entry.getValue()/wordCount;
			tf.put(entry.getKey(), wordTf);
		}
		return tf;
	} 
	
	/**
	 * 
	 * @param D 总文档数
	 * @param doc_words 每个文档对应的分词
	 * @param tf 计算好的tf
	 * @return 每个文档中的单词的tfidf的值
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Map<String,Float> tfidfCalculate(int D, Map<String,String> doc_words,Map<String,Float> tf) throws FileNotFoundException, IOException{
		
		//单词索引和文档索引
		int doc_num=1;
		int word_num=1;
		String wordok="";
		String docok="";
		String wordfile="f:/dataok1/words_index.txt";
		String docfile="f:/dataok1/doc_index.txt";
		for(Map.Entry<String, String> entry:doc_words.entrySet()){
			
			String filename=entry.getKey();
			File filea=new File(filename);
			docIndex.put(filea.getName(), doc_num);
			
			docok =filea.getName()+"\t"+doc_num+"\n";
			
			FileUtils.save(docfile, docok, false);
			
			for(String word:entry.getValue().split(" ")){
				if(!wordIndex.containsKey(word)){
					wordIndex.put(word, word_num);					
					wordok=word+"\t"+word_num+"\n";
					FileUtils.save(wordfile, wordok, false);
					word_num++;				
				}
			}
			
			doc_num++;
			
		}
		

		HashMap<String,Float> tfidf=new HashMap<String, Float>();
		for(String key:tf.keySet()){
            int Dt=0;
			for(Map.Entry<String, String> entry:doc_words.entrySet()){
				
				String[] words=entry.getValue().split(" ");
				
				List<String> wordlist=new ArrayList<String>();
				for(int i=0;i<words.length;i++){
					wordlist.add(words[i]);
					
				}
				if(wordlist.contains(key)){
					Dt++;
				}
			}
			float idfvalue=(float) Math.log(Float.valueOf(D)/Dt);
			tfidf.put(key, idfvalue * tf.get(key));
			
		}		
		return tfidf;
	}
	
}
