package ch2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ch2.Color.GREEN;
import static ch2.Color.RED;

public class ComparatorEx {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(GREEN, 10)
        );

        // ex1. Comparator
        // ** int 는 compareTo() 메서드를 사용할 수 없으므로, 해당 메서드를 가지고 있는 Integer 객체로 변환해야 한다.
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        inventory.sort((Apple a1, Apple a2) -> {
            return a1.getWeight().compareTo(a2.getWeight());
        });

        // 3-6. 메서드 참조 사용
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }


}