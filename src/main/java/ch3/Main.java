package ch3;

import ch2.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ch2.Color.GREEN;
import static ch2.Color.RED;
import static java.util.Comparator.*;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(GREEN, 10)
        );

        // 1. 동작 파라미터화
        inventory.sort(new AppleComparator());

        // 2. 익명 클래스 사용
        inventory.sort(new Comparator<Apple>(){
            public int compare(Apple o1, Apple o2) {
                Integer o1Weight = o1.getWeight();
                Integer o2Weight = o2.getWeight();
                return o1Weight.compareTo(o2Weight);
            }
        });

        // 3. 람다 표현식 사용
        inventory.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));   // 람다의 파라미터 형식 추론

        // 3-1. comparing 메서드를 사용하여 가독성 향상시키기
        Comparator<Apple> c = comparing((Apple a) -> a.getWeight());   // 람다의 파라미터 형식 추론
        inventory.sort(c);

        // 4. 메서드 참조 사용
        inventory.sort(comparing(Apple::getWeight));
//        inventory.sort(AppleComparator::compare);
    }
}
