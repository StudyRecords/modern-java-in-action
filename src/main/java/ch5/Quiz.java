package ch5;

import ch4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static ch4.Dish.Type;
import static java.util.stream.Collectors.toList;

public class Quiz {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", false, 500, Type.OTHER),
            new Dish("rice", false, 200, Type.OTHER),
            new Dish("season fruit", false, 120, Type.OTHER),
            new Dish("pizza", false, 550, Type.MEAT),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    public static void main(String[] args) {
        // Quiz 5-1. 처음 등장하는 두 고기요리 필터링
        List<Dish> meat = menu.stream()
                .filter(dish -> dish.getType() == Type.MEAT) // ENUM 비교 시 == / equals() -> ==가 더 유용하다 생각함
                .limit(2)
                .collect(toList());

        // Quiz 5-2-1. map 이해하기. 각 숫자의 제곱근 List 반환
        int[] arr = {1, 2, 3, 4, 5};
        List<Integer> before = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> after = before.stream()
                .map(num -> num * num)
                .toList();

        // Quiz 5-2-2. flatMap 이해하기. 두 배열의 모든 조합 만들기
        List<Integer> a1 = Arrays.asList(1, 2, 3);
        List<Integer> a2 = Arrays.asList(3, 4);

        List<int[]> result = a1.stream()
                .flatMap(num1 -> a2.stream()
                        .map(num2 -> new int[]{num1, num2}))
                .collect(toList());

        // Quiz 5-2-3. flatMap 이해하기. 5-2-2에서 만든 조합 중 합이 3의 배수인 조합만 반환하기
        List<int[]> result2 = a1.stream()
                .flatMap(num1 -> a2.stream()
                        .filter(num2 -> (num1 + num2) % 3 == 0)
                        .map(num2 -> new int[]{num1, num2}))
                .collect(toList());

        // Quiz 5-3. reduce 이해하기. map과 reduce 사용해서 메뉴 개수 구하기
        int menuCnt = menu.stream()
                .map(dish -> 1)
                .reduce(0, (a, b) -> a+b);

        long menuCnt2 = menu.stream().count();


        // Quiz 5-4. iterate 이해하기. 피보나치수열 집합 무한스트림으로 구현하기
        Stream.iterate(new int[]{0, 1}, arr1 -> new int[]{arr1[1], arr1[0]+arr1[1]})
                .limit(20)
                .forEach(arr1-> System.out.println("(" + arr1[0]+", "+arr1[1] + ")"));

    }

}
