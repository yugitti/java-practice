import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.time.format.DateTimeFormatter;

public class InterfaceSample {
    public static void run(){
        Named s = new Student("yugi", 60, 80);
        Named t = new Teacher("fumie", "math");
        var st = new Named[]{s, t};

        for(var _st: st){
            System.out.println(_st.name());
        }

        // Custom Lambda Interface
        //// ラムダ式を使用して、人の名前を返すNamedインスタンスを作成
        Named personNamed = () -> "Alice";

        //// ラムダ式を使用して、動物の名前を返すNamedインスタンスを作成
        Named animalNamed = () -> "Charlie the Dog";

        //// 名前を出力
        System.out.println("Person's name: " + personNamed.name());
        System.out.println("Animal's name: " + animalNamed.name());

        Named personNamed2 = new Named() {
            @Override
            public String name() {
                return "Alice";
            }
        };

        NamedFunc toReplace = (String name) -> name.replace(" ", "-");
        System.out.println(toReplace.process("Yugi Tsutomu"));

        // 標準API

        //// Runnable
        Runnable task = () -> {
            String thredName = Thread.currentThread().getName();
            System.out.println("Hello from " + thredName);
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Done");

        //// Function
        Function<String, String> toReplace2 = (String name) -> name.replace(" ", "-");
        System.out.println(toReplace2.apply("Yugi Fumie"));

        //// Correction
        List<String> names = List.of("Tsutomu", "Fumie", "Kotaro");
        Consumer<String> stringConsumer=  (String name) -> System.out.println("This is " + name);
        names.forEach(stringConsumer);
        names.forEach(System.out::println);

        //// Supplier
        Supplier<String> getToday = () -> DateTimeFormatter.ofPattern("yyyy年M月d日").format(LocalDate.now());

        System.out.println(getToday.get());
    }
}
