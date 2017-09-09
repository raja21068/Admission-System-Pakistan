package admission.utils;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class NumberToText {
    public static String convert(Integer no){
        String text;
        switch(no){
            case 1:
                text = no + "st";
                break;
            case 2:
                text = no + "nd";
                break;
            case 3:
                text = no + "rd";
                break;
            default:
                text = no + "th";
                break;
        }
        
        return text;
    }
}
