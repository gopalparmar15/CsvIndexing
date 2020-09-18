import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.lanag.String;
import java.util.List;


public class Data {
    public static void main(String[] args) {
     //   Student student=new Student();

        String[] abc= new String[0];
        try {
            List<String> lines= Files.readAllLines(Paths.get("E:\\csv\\data1.csv"));
            for(String line:lines) {
                line = line.replace("\"", "");
                String []result=line.split(",");
              //  student.setId(result[0]);
                //student.setQuestion(result[1]);
                abc = result;
                for (String str : result) {
                  //  student.setId(str);
                //    System.out.print(str + " ");

                }
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }//aa run
    }
}