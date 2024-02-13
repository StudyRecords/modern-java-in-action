package ch2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch2.Color.GREEN;
import static ch2.Color.RED;

public class Apples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(GREEN, 10)
        );


        // 4. 익명 클래스 (3. 동작 파리미터화 할 때 사용한 filterApples 메서드 사용,
        List<Apple> redApples  = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean filter(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });

        // 5. 람다 표현식
        List<Apple> apples = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));

    }


    // 0. 초록 사과만 필터링하는 메서드
    List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> greenList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                greenList.add(apple);
            }
        }
        return greenList;
    }

    // 1. 요구 사항 변경! 빨간 사과를 찾고 싶다 -> Color 를 파라미터에 추가
    List<Apple> filterGreenApples(List<Apple> inventory, Color color) {
        List<Apple> greenList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                greenList.add(apple);
            }
        }
        return greenList;
    }

    // 2. 색과 무게를 분류기준에 번갈아가며 적용하고 싶다!!!
    // flag가 true 면 Color를 기준으로 분류
    List<Apple> filterGreenApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> greenList = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && color.equals(apple.getColor())) ||
                    (!flag && apple.getWeight() > weight)) {
                greenList.add(apple);
            }
        }
        return greenList;
    }

    // 3. 동작 파라미터화 적용!
    static List<Apple> filterApples(List<Apple> inventory, ApplePredicate pre) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (pre.filter(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    // 6. 리스트 형식으로 추상화
    public static <T> List<T> filterList(List<T> list, Predicate<T> pre){
        List<T> result = new ArrayList<>();
        for(T t:list){
            if(pre.test(t)){
                result.add(t);
            }
        }
        return result;
    }


    // 동작 파라미터화를 구현하기 위한 인터페이스와 구현클래스
    // Predicate : 인수로 값을 받아 true 나 false 를 반환하는 함수
    public interface ApplePredicate {
        boolean filter(Apple apple);
    }

    class ColorPredicate implements ApplePredicate {

        @Override
        public boolean filter(Apple apple) {
            return GREEN.equals(apple.getColor());
        }
    }

    class WeightPredicate implements ApplePredicate {

        @Override
        public boolean filter(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    // 6. 리스트 형식으로 추상화할 때 필요한 인터페이스
    public interface Predicate<T>{
        boolean test(T t);
    }

}

