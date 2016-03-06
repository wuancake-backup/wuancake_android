package mytest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

      String line = "This order was placed for QT3000! OK?";
      String pattern = "(.*)(\\d+)(.*)";

    
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(line);
      
      if (m.find( )) {
         System.out.println("Found value: " + m.group(0) );
         System.out.println("Found value: " + m.group(1) );
         System.out.println("Found value: " + m.group(2) );
      } else {
         System.out.println("NO MATCH");
      }
   }
}
