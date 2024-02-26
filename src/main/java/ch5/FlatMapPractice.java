package ch5;

import java.util.Arrays;
import java.util.stream.Stream;

public class FlatMapPractice {
    public static void main(String[] args) {
        String line = "My name is sunny";
        Stream<String> lines = Arrays.stream(new String[]{line, line, line});


        String[] strArr = line.split(" ");
        Stream<String> stream = Arrays.stream(strArr);

        // map을 썼을 때
        Stream<Stream<String>> useMap = lines.map(ln -> Arrays.stream(ln.split(" ")));

        // flatMap을 썼을 때
        Stream<String> useFlatMap = lines.flatMap(ln -> Arrays.stream(ln.split(" ")));
    }
}
