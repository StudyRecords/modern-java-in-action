package ch7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// 문자열의 단어 수를 세는 메서드 구현
public class Main {

    public static void main(String[] args) {

        final String SENTENCE = "The greatest glory in living lies not in never falling, but in rising every time we fall";       // 17개

        // 1
        System.out.println("1. 단순 for문으로 구현하기 (순차) : " + countWordsIteratively(SENTENCE) + "개");

        // 2
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                        .mapToObj(SENTENCE::charAt);
        System.out.println("2. 문자열 스트림으로 탐색 (순차) : "+countWordsWithStream(stream) + "개");

        // 3 (잘못된 방법에 대한 예시)
//        System.out.println("3. 문자열 스트림으로 탐색 (병렬) : "+countWordsWithStream(stream.parallel()) + "개");      // (X)

        // 4 (3을 수정하여 올바른 병렬 사용하기에 대한 예시)
        Spliterator<Character> spliterator = new CustomSpliterator(SENTENCE);
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        System.out.println("3. 병렬 스트림 올바른 방식으로 사용하기 (병렬) : "+ countWordsWithStream(stream2) + "개");


    }

    // 1. 단순 for문으로 구현하기 (순차)
    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c:s.toCharArray()){
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else{
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    // 2. 문자열 스트림으로 탐색 (순차)
    private static int countWordsWithStream(Stream<Character> stream){
        WordCounter wc = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wc.getCounter();
    }
}
