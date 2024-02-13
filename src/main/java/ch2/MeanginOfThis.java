package ch2;

// 80p. 퀴즈 2-2 : 익명 클래스의 단점 (코드의 장황함)을 보여주는 사례 .. Runnable 익명클래스
public class MeanginOfThis {

    public final int value = 4;
    public void doIt(){
        int value = 6;
        Runnable r = new Runnable() {

            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeanginOfThis m = new MeanginOfThis();
        m.doIt();
    }

}

