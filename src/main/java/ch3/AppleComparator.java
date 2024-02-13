package ch3;

import ch2.Apple;

import java.util.Comparator;

// 1. 동작 파라미터화를 위한 클래스 구현 (전략 패턴)
public class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
