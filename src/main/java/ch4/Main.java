package ch4;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Main {
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
//        List<String> threeHighCaloriesMenus = menu.stream()
//                .filter(d -> d.getCalories() > 300)
//                .map(Dish::getName)
//                .limit(3)       // 선착순 3개
//                .collect(toList());
//
//        System.out.println(threeHighCaloriesMenus.toString());
        menu.stream().forEach(System.out::println);
    }




}
