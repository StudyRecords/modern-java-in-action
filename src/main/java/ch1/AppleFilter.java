package ch1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AppleFilter {

    public static final String GREEN = "green";
    public static final String RED = "red";


    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(GREEN, 10)
        );

        // SOL 1. 메서드 참조 사용
        List<Apple> greenApples = filterApples(inventory, Apple::isGreen);
        List<Apple> heavyApples = filterApples(inventory, Apple::isHeavy);

        // SOL 2. 람다 표현식 사용
        List<Apple> greenApples2 = filterApples(inventory, (Apple apple) -> GREEN.equals(apple.getColor()));
        List<Apple> heavyApples2 = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);
        List<Apple> mixApples = filterApples(inventory, (Apple apple) ->
                apple.getWeight() > 150 || RED.equals(apple.getColor()));

        List<Apple> streamApples = inventory.stream()
                .filter((Apple apple) -> GREEN.equals(apple.getColor()))
                .toList();
    }


    // 자바 8 이전 : 초록 사과 List
//    List<Apple> filterGreenApples(List<Apple> inventory) {
//        List<Apple> greenList = new ArrayList<>();
//        for (Apple apple : inventory) {
//            if (apple.getColor().equals("green")) {
//                greenList.add(apple);
//            }
//        }
//        return greenList;
//    }

    // 자바 8 이전 : 무게가 150 이상인 사과 List
//    List<Apple> filter150Apples(List<Apple> inventory) {
//        List<Apple> heavyist = new ArrayList<>();
//        for (Apple apple : inventory) {
//            if (apple.getWeight() > 150) {
//                heavyist.add(apple);
//            }
//        }
//        return heavyist;
//    }

    public static class Apple {
        private String color;
        private int weight;

        Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return this.color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return this.weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        // 동적 파라미터화를 구현하기 위한 함수
        public static boolean isGreen(Apple apple) {
            return GREEN.equals(apple.getColor());
        }

        public static boolean isHeavy(Apple apple) {
            return apple.getWeight() > 150;
        }

    }

    // Predicate : 인수로 값을 받아 true 나 false 를 반환하는 함수
    public interface Predicate<T> {
        boolean test(T t);
    }


    // test 메서드가 `predicate`라는 이름의 파라미터(값)로 전달됨
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> pre) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (pre.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


}

