import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicPractice {
    static public void run(){
        var a = 2;
        if(a<3){
            System.out.println("small");
        }

        switch(a){
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            case 4:
                System.out.println("four");
                break;
            default:
                break;
        }

        System.out.println(switch(a){
            case 1->"one";
            case 2->"two";
            case 3->"three";
            case 4->"four";
            default -> "other";
        });

        // list
        var names = List.of("yugi", "tsutomu", "fumie");
        //// 値の取得
        System.out.println(names.get(1));
        //// listの長さ
        System.out.println(names.size());
        //// listに追加
        //names.add("kotaro"); --> これはエラー、listはmutable
        var integers = List.of(1, 2, 3);
        System.out.println(integers);
        for(var integer: integers){
            System.out.println(integer.getClass().getName()); // intではなくintegerクラスになっている
        }
        var author2 = Arrays.asList("YUGI", "TSUTOMU");
        System.out.println(author2);


        // 変更できるlist
        var author = new ArrayList<String>(List.of("yugi", "tsutomu"));
        System.out.println(author);
        author.add("fumie");
        System.out.println(author.get(2));
        author.addFirst("Kotaro");
        System.out.println(author);


        // 配列
        var arr1 = new int[3];
        var arr2 = new int[]{1,2,3};

        for(var ar1: arr1){
            System.out.println(ar1);
        }
        for(var ar2: arr2){
            System.out.println(ar2);
        }
        System.out.println(arr1.length);

        var mat = new int[][]{{1,2}, {3,4}};
        for(var ma: mat){
            for(var m: ma){
                System.out.println(m);
            }
        }
        System.out.println(mat[1][0]);

        // レコードクラス
        record Exam(String name, String subject, int score){}

        var e1 = new Exam("yugi", "math", 60);
        var ee  = new Exam[3];
        ee[0] = e1;
        ee[1] = new Exam("fumie", "japanese", 100);
        ee[2] = new Exam("kotaro", "english", 200);

        for(var e: ee){
            System.out.println(e);
        }
        for(var e: ee){
            System.out.println(e.name());
        }

        // mapクラス --> immutable
        var fruits = Map.of("apple", 100, "orange", 50);
        System.out.println(fruits.get("orange"));
        System.out.println(fruits.get("banana"));
        System.out.println(fruits.getOrDefault("banana", 0));

        // HashMapクラス --> mutable
        var fruits2 = new HashMap<String, Integer>(fruits);
        System.out.println(fruits2.size());
        fruits2.put("banana", 200);
        System.out.println(fruits2.size());
        System.out.println(fruits2.getOrDefault("banana", 0));

        //// mapの各keyとvalueを取得
        for(Map.Entry<String, Integer> entry: fruits2.entrySet()){
            System.out.println(entry);
        }
        //// mapの各keyを取得
        for(var key: fruits2.keySet()){
            System.out.println(key + ":" + fruits2.get(key));
        }

        //// practice
        var fruits3 = List.of("apple", "banana", "grape");
        var newFruits = new ArrayList<String>();
        for(var f: fruits3){
            if(f.length() == 5){
                newFruits.add(f);
            }
        }

        var flagAll = true;
        var flagAny = false;
        for(var newF: newFruits){
            flagAll = flagAll && newF.contains("p");
            flagAny = flagAny || newF.contains("p");
        }

        if(flagAll){
            System.out.println("5文字ちょうどの文字列のすべてが「p」を含みます");
        }
        if(flagAny){
            System.out.println("5文字ちょうどの文字列のどれか1つでも「p」を含みます");
        }

        // Stream
        //// filter
        var count = fruits3.stream().filter(s -> s.length() == 5).count();
        System.out.println(count);

        /// allMatch
        var allMatch = fruits3.stream().allMatch(word -> word.contains("p"));
        System.out.println("allMatch: " + allMatch);

        /// anyMatch
        var anyMatch = fruits3.stream().anyMatch(word -> word.contains("p"));
        System.out.println("anyMatch: " + anyMatch);

        /// nonmatch
        var nonMatch = fruits3.stream().noneMatch(word -> word.contains("x"));
        System.out.println("nonMatch: " + nonMatch);

        //// sort
        var sort = fruits3.stream().sorted().toList();
        System.out.println(sort);
        var sortReverse = fruits3.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(sortReverse);

        //// skip / limit
        var skip = fruits3.stream().skip(1).collect(Collectors.toMap(word->word, String::length));
        System.out.println(skip);
        var limit = fruits3.stream().limit(2).collect(Collectors.toMap(word->word, String::length));
        System.out.println(limit);

        //// distinct
        var fruits4 = List.of("apple", "apple", "banana", "orange");
        var distinct = fruits4.stream().distinct().toList();
        System.out.println(distinct);

        // Stream -> Collect

        //// list -> stream -> set
        var fruitHashSet = fruits3.stream().collect(Collectors.toSet());
        System.out.println(fruitHashSet);

        //// list -> stream -> list
        var fruitList = fruits3.stream().filter(word -> word.contains("p")).collect(Collectors.toList());
        System.out.println(fruitList);

        //// list -> stream -> string
        var fruitJoining = fruits3.stream().collect(Collectors.joining());
        System.out.println(fruitJoining);

        //// list -> stream -> map
        var fruitMap = fruits3.stream().collect(Collectors.toMap(word -> word, String::length));
        System.out.println(fruitMap);

        //// list -> stream -> integer(max)
        //// 必ずcomparatorが必要
        var fruitMaxLength = fruits3.stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length)));
        System.out.println(fruitMaxLength);


        // optional
        List<String> names2 = Arrays.asList("John", "Sarah", "Mark", "Tanya");
        // Streamを使って特定の条件に一致する最初の要素を検索
        Optional<String> foundName = names2.stream()
                .filter(name -> name.startsWith("S"))
                .findAny()
                .map(String::toUpperCase);
        System.out.println(foundName.stream().toList());

        //プリミティブ型
        var nums = new int[]{1, 2, 3};
        var sum = IntStream.of(nums).sum();
        System.out.println("sum: " + sum);
        var average  = IntStream.of(nums).average();
        System.out.println("average: " + average);
        var range = IntStream.range(0, 10).toArray();
        for(int i=0; i<range.length; i++){
            System.out.printf("[%s] => %s%n", i, range[i]);
        }
        var range2 = IntStream.iterate(10, i ->i< 100, i->i+10).limit(3).toArray();
        for(int i=0; i<range2.length; i++){
            System.out.printf("[%s] => %s%n", i, range2[i]);
        }
        var random = new Random().ints(5, 0, 100).toArray();
        for(int i=0; i<random.length; i++){
            System.out.printf("[%s] => %s%n", i, random[i]);
        }
        var others = IntStream.iterate(10, i-> i< 1000, i->i+10).map(i -> i*i ).average();
        System.out.println(others.orElse(0));


//        System.out.println(count);
//        var red = fruits3.stream().reduce("", (acc, value) -> {
//            return acc + value;
//        });





        ////いろいろな書き方
//        System.out.println(red);
//        var toMap = fruits3.stream().collect(Collectors.toMap(word -> word, String::length));
//        System.out.println(toMap);
//        toMap = fruits3.stream().collect(Collectors.toMap(word -> word, word -> word.length()));
//        System.out.println(toMap);
//        toMap = fruits3.stream().collect(Collectors.toMap(word -> word, word -> {return word.length();}));
//        System.out.println(toMap);
//        Function<String, Integer> func = (String word) -> { return word.length(); };
//        toMap = fruits3.stream().collect(Collectors.toMap(word -> word, func));
//        System.out.println(toMap);
//        func = String::length;
//        toMap = fruits3.stream().collect(Collectors.toMap(word -> word, func));
//        System.out.println(toMap);



    }
}
