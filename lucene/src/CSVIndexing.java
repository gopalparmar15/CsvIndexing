import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

//TODO:FOLLOW CODING CONVENTION
class CSVIndexing {
    public static void main(String[] args) throws IOException {

        //directory is loaction of path
        Directory dir = FSDirectory.open(Paths.get("F:\\csvindex"));
        //indexWriterconfig StandardAnalyzer is analyzed text into token
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(  new StandardAnalyzer());
        //create indexing
        IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
        //add data in field using add method

        List<String> result=display();
        for (String s : result) {
            s = s.replace("\"", "");
            String[] data = s.split(",");
            addDoc(indexWriter,data[0],data[1]);
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
        String[] str = new String[0];
        List<String> lines = null;
        try {
          lines= Files.readAllLines(Paths.get("E:\\csv\\data1.csv"));
            for(String line:lines) {
                line = line.replace("\"", "");

                str=line.split(",");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
