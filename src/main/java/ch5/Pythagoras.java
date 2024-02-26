package ch5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pythagoras {
    public static void main(String[] args) {
        // flatMap 사용
        Stream<int[]> useFlatMap = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        // flatMap 사용 X
        Stream<Stream<int[]>> noFlatMap = IntStream.rangeClosed(1, 100).boxed()
                .map(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
    }
}
