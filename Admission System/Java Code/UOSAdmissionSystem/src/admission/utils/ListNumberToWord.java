package admission.utils;

/**
 *
 * @author Yougeshwar
 */
public class ListNumberToWord {

    public static String convert(int i) {
        if (i == 1) {
            return "1st";
        } else if (i == 2) {
            return "2nd";
        } else if (i == 3) {
            return "3rd";
        } else if (i == 4) {
            return "4th";
        } else {
            return "PROVISIONAL SUPPLEMENTARY";
        }
    }
}
