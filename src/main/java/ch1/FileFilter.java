package ch1;

import java.io.File;

public class FileFilter {
    public static void main(String[] args) {

        // 자바 8 이전
        File[] hiddenFiles1 = new File(".").listFiles(new java.io.FileFilter() {
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // 자바 8의 '메서드 참조' 활용
        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);
        // :: 메서드 참조를 이용하여 isHidden() 함수를 listFiles() 메서드로 전달
    }
}
