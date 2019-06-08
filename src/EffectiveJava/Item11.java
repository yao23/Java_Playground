package EffectiveJava;

public class Item11 {
    // hashCode method with lazily initialized cached hash code
    private int hashCode; // Automatically initialized to 0
    private short areaCode = 123;
    private short prefix = 456;
    private short lineNum = 789;

    @Override public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }
}