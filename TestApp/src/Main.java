import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	    DoStringStuff();
    }

    private static void DoStringStuff() {
        String s = "Hello Sharon!";
        IntStream stream = s.chars();

        //stream.forEach(c -> System.out.println((char)c));

        //stream.map(Character::toUpperCase).forEach(c -> System.out.print((char)c));
        stream.map(Character::toUpperCase).forEach(System.out::print);

        // TODO: why doesn't work?
//        stream.map(String::toUpperCase)
//                .forEach(System.out::print);

        Map<Integer,Integer> intMap = new HashMap<>();
        intMap.put(1,1);
    }
}
