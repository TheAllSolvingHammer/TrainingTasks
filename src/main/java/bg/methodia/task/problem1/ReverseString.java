package bg.methodia.task.problem1;

import java.util.ArrayList;
import java.util.List;

public class ReverseString {
    private String textToConvert;

    public ReverseString(String textToConvert) {
        this.textToConvert = textToConvert;
    }

    public String reverseString(){
        int[] array = textToConvert.codePoints().toArray();
        char[] chars = new char[array.length*2];
        int index=0;
        for(int i=array.length-1;i>=0;i--){
            char[] smallChars=Character.toChars(array[i]);
            for (char smallChar : smallChars) {
                chars[index++]=smallChar;
            }
        }
        return new String(chars,0,index);
    }

}
