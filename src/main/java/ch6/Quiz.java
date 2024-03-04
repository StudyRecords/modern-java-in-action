package ch6;

import ch4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Quiz {
    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", false, 500, Dish.Type.OTHER),
            new Dish("rice", false, 200, Dish.Type.OTHER),
            new Dish("season fruit", false, 120, Dish.Type.OTHER),
            new Dish("pizza", false, 550, Dish.Type.MEAT),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {

        menu.stream()
                .forEach(System.out::println);


        // 6-1. 리듀싱으로 문자열 연결하기 (joinig이 더 가독서잉 좋다)
        String useJoining = menu.stream()
                .map(Dish::getName)
                .collect(joining());
        String useReducing = menu.stream()
                .collect(reducing("", Dish::getName, (str1, str2) -> str1 + str2));

        Map<Dish.Type, List<Dish>> dishes = menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500,
                                toList())));
    }
}
