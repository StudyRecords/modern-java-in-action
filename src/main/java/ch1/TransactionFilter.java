package ch1;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class TransactionFilter {
    public static void main(String[] args) {


        List<Transaction> transactions = Arrays.asList(
            new Transaction(12000, new Currency("USD")),
            new Transaction(500, new Currency("EUR")),
            new Transaction(2000, new Currency("KRW"))
        );

        // 자바 8 이전 코드
//        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
//        for(Transaction transaction : transactions){
//            if(transaction.getPrice() > 1000){
//                Currency currency = transaction.getCurrency();
//                List<Transaction> tmp = transactionsByCurrencies.get(currency);
//                if(tmp == null){
//                    tmp = new ArrayList<>();
//                    transactionsByCurrencies.put(currency, tmp);
//                }
//                tmp.add(transaction);
//            }
//        }

        // 자바 8 스트림 적용
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 1000)
                .collect(groupingBy(Transaction::getCurrency));

    }

    public static class Currency{
        public static String currency;

        Currency(String currency){
            this.currency = currency;
        }


        public static String getCurrency(){
            return currency;
        }
    }

    public static class Transaction{

        public static int price;
        public static Currency currency;

        Transaction(int price, Currency currency){
            this.price = price;
            this.currency = currency;
        }

        public static int getPrice(){
            return price;
        }
        public Currency getCurrency(){
            return currency;
        }

    }
}
