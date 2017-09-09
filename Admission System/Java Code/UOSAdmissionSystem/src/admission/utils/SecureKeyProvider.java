package admission.utils;

import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public final class SecureKeyProvider {
    
    public static String encypt(int year, String pt, String part, List<Long> usedCodeList) {
        String str = part + pt + (String.valueOf(year)).substring(2) + "-"; // FYB14-
        long key;
        while (true) {
            key = getKey(10);
            if (!usedCodeList.contains(key)) {
                usedCodeList.add(key);
                break;
            }
        }
        return str + key + "-" + IConstant.PREFIX;
    }

    private static long getKey(long length) {
        if (length > 18) {
            throw new IllegalStateException("To many digits");
        }
        long totalLength = (long) Math.pow(10, length - 1) * 9;

        long number = (long) (Math.random() * totalLength) + (long) Math.pow(10, length - 1) * 1;

        String value = number + "";
        if (value.length() != length) {
            throw new IllegalStateException("The random number '" + value + "' is not '" + length + "' digits");
        }
        return number;
    }
}
