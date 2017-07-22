import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder(); // StringBuilder
        for (String str : strs) {
            sb.append(str.length() + "#" + str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        String num = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                int subStrLen = Integer.valueOf(num);
                String str = s.substring(i + 1, i + subStrLen + 1);
                result.add(str);
                num = "";
                i += subStrLen; // i++ in for loop to increase one more step for next string
            } else {
                num += s.charAt(i);
            }
        }
        return result;
    }

    private static boolean compareStrList(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("apple");
        strs.add("#123");
        strs.add("foo#");
        strs.add("bar");
        boolean isCorrect = false;
        if (compareStrList(decode(encode(strs)), strs)) {
            isCorrect = true;
        } else {
            isCorrect = false;
        }

        System.out.println("Encode and decode works: " + (isCorrect ? "true" : "false"));
    }
}
