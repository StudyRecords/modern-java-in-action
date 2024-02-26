package ch5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

// 5-6. 실전 연습
public class Main {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public void main(String[] args) throws IOException {

        // 1번 : 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오. -> filter, sorted
        List<Transaction> in2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        // 2번 : 거래자가 근무하는 모든 도시를 중복없이 나열하시오. -> map, distinct (distinct 대신 toSet도 사용 가능)
        List<String> cities = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        // 3번 : 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오. -> filter, sorted
        List<Trader> cambridges = transactions.stream()
                .map(Transaction::getTrader)             //.map(t -> t.getTrader())    //람다보다는 메서드 참조를 지향하자!!
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        // 4번 : 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오. -> map, sorted
        // List<String> 이 아니라 String으로 반환해야 한다. (여러 이름을 하나의 String으로 만들기!)
        String names = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1 + " " + s2);
//                .collect(joining());          // 6장. joining() -> 더 효율적!


        // 5번 : 밀라노에 거래자가 있는가? -> anyMatch
        boolean isMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        // 6번 : 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오. -> forEach
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)         //.map(t -> t.getValue())
                .forEach(System.out::println);

        // 7번 : 전체 트랜잭션 중 최댓값은 얼마인가? -> reduce(Integer::max)
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)         //.map(t -> t.getValue())
                .reduce(Integer::max);

        // 8번 : 전체 트랜잭션 중 최솟값은 얼마인가? -> reduce(Integer::min)
        // 최솟값(Integer)이 아니라 Transaction을 반환해야 한다.
        Optional<Transaction> minTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> minTransaction2 = transactions.stream()
                .min(comparing(Transaction::getValue));

    }


}
