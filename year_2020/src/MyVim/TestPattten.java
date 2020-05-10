package MyVim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattten
{
    public static void main(String[] args) {
        Pattern pattern=Pattern.compile("u1s1");
        String s="0123456789u1s1141516";
        Matcher matcher=pattern.matcher(s);
        if(matcher.find()){
            System.out.println(matcher.start());
        }
    }
}
