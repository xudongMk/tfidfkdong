# tfidfkdong

## 文本分词后利用tfidf算法计算每个文档的tfidf值，以便后期跑实验使用

1.读取文件夹下的文档并分词

[ReadFiles类的使用](https://github.com/xudongMk/tfidfkdong/blob/master/textRankdong/src/xudong/ReadFiles.java)

    readFile方法：读取所有文件夹下的文件路径，返回一个list文件路径列表
    readContent方法：将一个文档中的所有内容读成一个字符串，返回一个string文档内容
    readFileAllContent方法：将所有文档中的内容读到一起，返回一个map，key是文档名，value是文档内容
    cutWordtoMap方法：将每个文档中内容分词，返回一个map，key是文档名，value是分完词后的内容，是一个字符串，单词以空格分割。
    
注：分词软件，[张华平分词软件:](http://ictclas.nlpir.org/downloads) 

2.读取分完词的文档并计算每个文档的单词tfidf的值

[Tfidf类的使用](https://github.com/xudongMk/tfidfkdong/blob/master/textRankdong/src/xudong/Tfidf.java)

        该类中定义了两个静态变量：一个是文档索引一个是单词索引
        static Map<String, Integer> docIndex=new HashMap<String, Integer>();
        static Map<String, Integer> wordIndex=new LinkedHashMap<String, Integer>();
        
        saveIndex方法：String wordfile,String docfile,Map<String,String> doc_words三个参数，该方法将步骤1中分完词的map传进来，处理成文档索引和单词索引并保存到文件中。
        tfCalculate方法：String wordAll参数是分完词的文档内容，计算每个文档中单词的tf值，并返回一个map，key是单词，value是单词tf值
        tfidfCalculate方法：int D, Map<String,String> doc_words,Map<String,Float> tf三个参数，D是所有文档的数量，doc_words是步骤1中分完词的map，tf是单个文档的单词的tf值。
        
3.程序主类

[ComputeTfIdf类的使用](https://github.com/xudongMk/tfidfkdong/blob/master/textRankdong/src/xudong/ComputeTfIdf.java)

        3.1首先创建一个ReadFiles对象，将文件读到map，对应的是（文档名，文档内容）Map<String,String> doc_content=rf.readFileAllContent(filepath);
        3.2分词 Map<String,String> doc_words=rf.cutWordtoMap((HashMap<String, String>) doc_content);计算总文档数量int D=doc_words.keySet().size();
        3.3调用saveIndex方法,创建文档索引和单词索引，然后保存文档索引和单词索引到文件 Tfidf.saveIndex(wordfile, docfile, doc_words);
        3.4计算每个文档的单词的tfidf值并保存到文本
        
4.主程序入口

[Test类main方法](https://github.com/xudongMk/tfidfkdong/blob/master/textRankdong/src/xudong/Test.java)

    String filepath="";//定义文档的文件路径，是一个文件夹
	  String outputPath="";//定义输出文件
		String docfile="";//定义文档索引输出路径
		String wordfile="";//定义单词索引输出路径
		ComputeTfIdf.compute(filepath, outputPath, docfile, wordfile);



5.工具类

[FileUtils类](https://github.com/xudongMk/tfidfkdong/blob/master/textRankdong/src/xudong/FileUtils.java)

此类是HFUT电商所杜非师兄编写，[他的github地址:](https://github.com/df19900725)

