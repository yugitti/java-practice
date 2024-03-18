import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class SchoolTest {

    static void run(Student s){

        System.out.printf("sum: %s, average: %s, max: %s", s.sum(), s.average(), s.maxScore());
    }

    static void displaySummery(Student s){
        var sm = s.summary();
        for(Map.Entry<String, ? extends Serializable> entry: sm.entrySet()){
            System.out.printf("%s -> %s\n", entry.getKey(), entry.getValue());
        }

    }
}
