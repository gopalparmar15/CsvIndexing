import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;

import static org.apache.lucene.index.DirectoryReader.*;

//TODO:FOLLOW CODING CONVENTION
class CSVIndexing {
    public static void main(String[] args) throws IOException, ParseException {

       new StandardAnalyzer();
        //directory is loaction of path
        Directory dir = FSDirectory.open(Paths.get("C:/Users/GOPAL PARMAR/Desktop/index1/index"));
        //indexWriterconfig StandardAnalyzer is analyzed text into token
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(  new StandardAnalyzer());
        //create indexing
        IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
        //add data in field using add method

        List<String> result=display();
        for (String s : result) {
            s = s.replace("\"", "");
            String[] abc = s.split(",");
            addDoc(indexWriter,abc[0],abc[1]);
        }
        indexWriter.close();

    }

    private static void addDoc(IndexWriter w, String id, String question) throws IOException{
        //create document
        Document doc = new Document();
        //add id
        doc.add(new StringField("id",id,Field.Store.YES));
        //add name
        doc.add(new StringField("question", question, Field.Store.YES));
        w.addDocument(doc);

    }
    public static List<String> display()
    {
        String[] abc = new String[0];
        List<String> lines = null;
        try {
             lines= Files.readAllLines(Paths.get("E:\\csv\\data1.csv"));
            for(String line:lines) {
                line = line.replace("\"", "");
                abc=line.split(",");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
