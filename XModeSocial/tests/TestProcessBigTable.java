import junit.framework.TestCase;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TestProcessBigTable extends TestCase {
  public static void main(String args[]) {
     //testTrue();
     testDataFileCreation();
     testCorrectData();
  }
  
  public static void testDataFileCreation() {
     assertTrue(new File("/tmp/data.csv").exists());
  } 
  
  public static void testCorrectData() {
     String fileName = "/tmp/data.csv";
     String line = null;
     
     try {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader buff = new BufferedReader(fileReader);
        // just read the first line
        if ( (line = buff.readLine()) != null) {
           // check if data is correct
           //assertTrue(line.matches("\\d+,[-+]?[0-9]*\\.?[0-9]+,[-+]?[0-9]*\\.?[0-9]+,[-+]?[0-9]*\\.?[0-9]+,(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})"));
           assertTrue(line.matches("1,29.7604,95.3698,0.0001,2017-05-01 00:00:01.0"));
        }
        buff.close();
     } catch (Exception e) {
        e.printStackTrace();
     }
  } 
  
  public static void testTrue() {
     assertTrue(true);
     //assertTrue(false);  // throws exception junit.framework.AssertionFailedError
  } 
  
}