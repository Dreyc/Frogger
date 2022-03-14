package util;

public class Utils {
    public static int[] convert(int num){
        String numString = String.valueOf(num);
        int[] res = new int[numString.length()];
        for(int i = 0; i < numString.length(); i++){
            res[i] = Integer.parseInt(numString.substring(i, i+1));
        }
        return res;
    }

    public static int[] convert(long num){
        String numString = String.valueOf(num);
        int[] res = new int[numString.length()];
        for(int i = 0; i < numString.length(); i++){
            res[i] = Integer.parseInt(numString.substring(i, i+1));
        }
        return res;
    }
}