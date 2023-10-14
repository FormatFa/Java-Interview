/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readtest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author root
 */
public class Readtest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<String> result = new ArrayList();
        System.out.println("fl="+new File("C:\\Users\\root\\Downloads\\Java-Interview-main").listFiles().length);
       readDir(new File("C:\\Users\\root\\Downloads\\Java-Interview-main"),result);
       String resultStr = "";
       for(String item:result) {
           resultStr += item;
           resultStr += "\n";
       }
       FileUtils.writeStringToFile(new File("result.txt"), resultStr, "utf-8");
    }
    public static void readDir(File dir,List<String> resultContainer) throws IOException {
         File[] files = dir.listFiles();
        for(File file : files) {
            
            if(file.isDirectory()) {
               
                readDir(file,resultContainer);
            }
            else {

                if(!file.getName().endsWith(".md")) {
                    System.out.println("skip:"+file.getName());
                    continue;
                }
                int readCount = 0;
                System.out.println(file.getName());
                String text = FileUtils.readFileToString(file, "utf-8");
                String lines[] = text.split("\n");
                for(String line:lines) {
//                                            System.out.println(line);
                       line=line.trim();
                    if( (line.startsWith("##")||line.startsWith("> "))&&(line.endsWith("?")||line.endsWith("？"))) {
                    
                        line= line.replace("#","").replace(">","");
                        line = line.replaceAll("\\d+.", "").trim();
                        resultContainer.add(line);
                        readCount+=1;
//                                                System.out.println(line);

                    }
                }
                System.out.println("cou="+readCount);
            
            }
        }
    }
}
