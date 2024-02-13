package ch3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class executeAroundPattern {


    public void main(String[] args) throws IOException {

        // 2-3. 전략 패턴을 사용하여 동작 파라미터화 구현
        String behaviorParameter = processFile(new Processor());

        // 3. 람다 표현식을 사용하여 동작 파라미터화 구현
        String lambda = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        String lambda2 = processFile((BufferedReader br) -> br.readLine());
        String lambda3 = processFile(BufferedReader::readLine);         // lambda2 와 동일한 의미!!!
    }

    // 1. 람다 적용 전
//    public String processFile() throws IOException{
//        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
//            return br.readLine();
//        }
//    }

    // 2-1. 함수형 인터페이스 선언(정의)하기
    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    // 2-1. 위의 함수형 인터페이스를 파라미터로 받는 메서드 정의하기 (파라미터화)
    public String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return brp.process(br);
        }
    }

    // 2-2. 함수형 인터페이스를 구현한 클래스 생성
    class Processor implements BufferedReaderProcessor {
        @Override
        public String process(BufferedReader br) throws IOException {
            return br.readLine() + br.readLine();
        }
    }

}



