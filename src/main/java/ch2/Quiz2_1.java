package ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch2.Color.GREEN;
import static ch2.Color.RED;

public class Quiz2_1 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(GREEN, 10)
        );

        prettyPrintApple(inventory, new PrintAppleColor());


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

    // 퀴즈 2-1
    public static void prettyPrintApple(List<Apple> inventory, PrintInterface pr) {
        for (Apple apple : inventory) {
            String str = pr.print(apple);
            System.out.println(str);
        }
    }


    //퀴즈 2-1
    interface PrintInterface {
        String print(Apple apple);
    }

    class PrintAppleWeight implements PrintInterface {

        @Override
        public String print(Apple apple) {
            int weight = apple.getWeight();
            return "사과의 무게는 " + weight + "kg 입니다.";
        }
    }

    static class PrintAppleColor implements PrintInterface {

        @Override
        public String print(Apple apple) {
            Color color = apple.getColor();
            return "사과의 색깔은 " + color.toString() + " 입니다.";
        }
    }


}

