package src.pingtuGames;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class codeUtil {
    public static String getCode(){
        ArrayList<Character> list = new ArrayList<>();//存放大小写的字母验证码
//        添加字母到集合中
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a'+i));//a-z
            list.add((char) ('A'+i));//A-Z
        }
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
           int randomIndex  = r.nextInt(list.size());
            Character randomValue = list.get(randomIndex);
            result = result+randomValue ;
        }
//        System.out.println(result);
//        添加随机数
        int number = r.nextInt(10);
//       拼接字符串
        result = result+number;
        char[] chari = result.toCharArray();
        int index = r.nextInt(chari.length);
        char number1 = chari[4];
        chari[4] = chari[index];
        chari[index] = number1;
//        把数组转换为字符
        StringBuilder sb = new StringBuilder();
        sb.append(chari);
        String code = sb.toString();
        return code;

    }
}
