import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Applicable {
    public static void removeDistinct(){
        var data = "abcccbaabcc";

        // standard source code
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<data.length(); i++){
            char ch = data.charAt(i);
            if(i>0 && ch == data.charAt(i-1)){
                continue;
            }
            builder.append(ch);
        }
        String result = builder.toString();
        System.out.println(result);

        // other source code
        char prev = 0;
        StringBuilder builder2 = new StringBuilder();
        for(char ch: data.toCharArray()){
            if(ch == prev){
                continue;
            }
            builder2.append(ch);
            prev = ch;
        }
        String result2 = builder2.toString();
        System.out.println(result2);

    }

    public static void test_checkFloat(){
        var testStrings = List.of(
                "",
                "012",
                ".12",
                "12.",
                "1.2.3",
                "1..3",
                "0",
                "12",
                "12.3",
                "0.3",
                "12.30",
                "-",
                "--0.2",
                "-10",
                "-0.45",
                "-0.550");
        testStrings.forEach(s -> System.out.printf("%s -> %b%n", s, checkFloat2(s)));
    }

    public static void runLengthCompression(){
        String data = "abbcccbaaaabccccccccccccddd";
        var counter = 0;

        char prev = 0;
        StringBuilder builder = new StringBuilder();

        for(char ch: data.toCharArray()){
            if(ch == prev){
                if(counter == 10){
                    // 11個目
                    builder.append(9);
                    builder.append(ch);
                    counter = 0;
                }else{
                    counter++;
                }
            }else{
                if(counter < 2){
                    builder.append(ch);
                }else{
                    builder.append(counter-2);
                    builder.append(ch);
                }
                prev = ch;
                counter = 1;
            }
        }
        if(counter >= 2){
            builder.append(counter-2);
        }

        String result = builder.toString();
        System.out.println(result);

    }

    static boolean checkFloat(String data){
        enum FloatState {
            START, ZERO, INT, FRAC_START, FRAC, MINUS
        };

        var state = FloatState.START;
        for(char ch: data.toCharArray()){
            switch (state){
                case FloatState.START -> {
                    if(ch == '0'){
                        state = FloatState.ZERO;
                    }else if('1' <= ch && ch <= '9'){
                        state = FloatState.INT;
                    }else if(ch == '-'){
                        state = FloatState.MINUS;
                    }
                    else{
                        return false;
                    }
                }
                case FloatState.MINUS -> {
                    if(ch == '0'){
                        state = FloatState.ZERO;
                    }else if('1' <= ch && ch <= '9'){
                        state = FloatState.INT;
                    }else{
                        return false;
                    }
                }
                case FloatState.ZERO -> {
                    if(ch == '.'){
                        state = FloatState.FRAC_START;
                    }else{
                        return false;
                    }
                }
                case FloatState.INT ->{
                    if(ch == '.'){
                        state = FloatState.FRAC_START;
                    }else if(!('0' <= ch && ch <= '9')){
                        return false;
                    }
                }
                case FloatState.FRAC_START -> {
                    if(!('0' <= ch && ch <= '9')){
                        return false;
                    }else{
                        state = FloatState.FRAC;
                    }
                }
                case FloatState.FRAC ->{
                    if(!('0' <= ch && ch <= '9')){
                        return false;
                    }
                }
            }

        }

        if(state == FloatState.START || state == FloatState.FRAC_START || state == FloatState.MINUS){
            return false;
        }else if(state == FloatState.FRAC && data.charAt(data.length()-1) == '0'){
            return false;
        }else{
            return true;
        }

    }

    static public boolean checkFloat2(String data){
        Pattern pat = Pattern.compile("(-0|-[1-9][0-9]*|0|[1-9][0-9]*)(\\.[0-9]*[1-9])?");
        Matcher mat = pat.matcher(data);
        return mat.matches();
    }


}
