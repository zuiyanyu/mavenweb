package hadoop.mr;

import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Map<String, String> getenv = System.getenv();
        Set<String> keySet = getenv.keySet();
        for (String key : keySet) {
            System.out.println("key="+key +" ; value ="+getenv.get(key));
        }
    }
}
