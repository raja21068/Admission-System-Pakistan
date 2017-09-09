package admission.utils;

/**
 *
 * @author Adeel
 */
public class AmountToWordConverter {

    private static final String[] TENS_NAMES
            = {
                "", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY",
                "EIGHTY", "NINETY"
            };

    private static final String[] ONES_NAMES
            = {
                "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
                "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN",
                "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"
            };

    private AmountToWordConverter() {
    }

    public static String converter(String aStrAmount)
            throws Exception {
        String output = "";

        try {
            String totalAmount = aStrAmount;
            int index = totalAmount.indexOf(".");

            String beforeDecimal = totalAmount;

            if (index > -1) {
                beforeDecimal = totalAmount.substring(
                        0,
                        index
                );
            }

            output = evaluate(beforeDecimal) + "RUPEES ONLY";

            if (index > -1) {
                String afterDecimal = totalAmount.substring((index + 1));
                output += (" AND " + evaluate(afterDecimal) + " PAISE");
            }
        } catch (Exception exception) {
            System.out.println("Error in AmountToWordConverter.converter(String aStrAmount) method");
            throw exception;
        }

        return output;
    }

    public static String converter(long iAmount) {
        String output = "";
        long i;
        try {
            i = iAmount;
            output = converter(i + "");
        } catch (Exception ex) {
            System.out.println("Error in AmountToWordConverter.converter(int iAmount) method");
            ex.printStackTrace();
        }
        return output;
    }

    private static String evaluate(String text)
            throws Exception {
        long number = 0;

        try {
            number = Long.parseLong(text);
        } catch (NumberFormatException eNumberFormatException) {
            System.out.println("Error in AmountToWordConverter.evaluate(String text) method");
            throw eNumberFormatException;
        }

        return evaluate(number);
    }

    private static String evaluate(long number) {
        long temp = number;

        long crore = temp / 10000000;
        temp %= 10000000;

        long lakh = temp / 100000;
        temp %= 100000;

        long thousands = temp / 1000;
        temp %= 1000;

        long hundreds = temp / 100;
        temp %= 100;

        StringBuilder result = new StringBuilder(30);

        if (crore > 0) {
            result.append(evaluate(crore)).append(" CRORE ");
        }

        if (lakh > 0) {
            result.append(evaluate(lakh)).append(" LAKH ");
        }

        if (thousands > 0) {
            result.append(evaluate(thousands)).append(" THOUSAND ");
        }

        if (hundreds > 0) {
            result.append(evaluate(hundreds)).append(" HUNDRED ");
        }

        if (temp != 0) {
            if (number >= 100) {
                result.append("AND ");
            }

            if ((0 < temp) && (temp <= 19)) {
                result.append(ONES_NAMES[(int) temp]);
            } else {
                long tens = temp / 10;
                long ones = temp % 10;
                result.append(TENS_NAMES[(int) tens]).append(" ");
                result.append(ONES_NAMES[(int) ones]);
            }
        }

        if ((result
                .toString()).trim()
                .equals("")) {
            result.append(" ZERO ");
        }

        return result.toString();
    }
}
