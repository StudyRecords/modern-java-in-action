package ch7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public CustomSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {         // 탐색할 요소 남아있으면 true 반환
        action.accept(string.charAt(currentChar++));        // 현재 문자를 소비한다.  -> ???
        return currentChar < string.length();       // 소비할 문자가 남아있으면 true 반환
    }

    @Override
    public Spliterator<Character> trySplit() {               // Spliterator 2개로 분할하기!!! -> 여기에다 분할의 기준을 써주면 되겠네~ (whitespace일 때에만 분할하세요)
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int i = currentSize / 2 + currentChar; i < string.length(); i++) {
            if (Character.isWhitespace(string.charAt(i))) {
                Spliterator<Character> spliterator = new CustomSpliterator(string.substring(currentChar, i));
                currentChar = i;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
