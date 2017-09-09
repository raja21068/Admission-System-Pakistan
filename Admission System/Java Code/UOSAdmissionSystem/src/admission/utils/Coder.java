package admission.utils;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class Coder {
    static public class Encoder{
        public static String jurisdictionEncode(String data){
            if(data == null) return "";
            switch (data) {
                case "Both":
                    data = "B";
                    break;
                case "Inside":
                    data = "I";
                    break;
                case "Out of":
                    data = "O";
                    break;
            }
            return data;
        }
        public static String genderEncode(String data){
            if(data == null) return "";
            switch (data) {
                case "Male":
                    data = "M";
                    break;
                case "Female":
                    data = "F";
                    break;
                case "Other":
                    data = "O";
                    break;
            }
            return data;
        }
        public static String encode(String data){
            if(data == null) return "";
            switch (data) {
                case "Self":
                    data = "S";
                    break;
                case "Father":
                    data = "F";
                    break;
                case "Urban":
                    data = "U";
                    break;
                case "Rural":
                    data = "R";
                    break;
            }
            return data;
        }
        public static String sessionEncode(String data){
            if(data == null) return "";
            switch (data) {
                case "Morning":
                    data = "M";
                    break;
                case "Evening":
                    data = "E";
                    break;
                case "Non":
                    data = "N";
                    break;
            }
            return data;
        }
        public static String adminEncode(String data){
            if(data == null) return "";
            switch (data) {
                case "Super":
                    data = "S";
                    break;
                case "Operator":
                    data = "O";
                    break;
            }
            return data;
        }
        public static byte booleanEncode(boolean data){
            if(data)
                return 1;
            else return 0;
        }
        public static String groupEncode(String data){
            if(data == null) return "";
            switch (data) {
                case "Medical":
                    data = "PM";
                    break;
                case "Engineering":
                    data = "PE";
                    break;
                case "Commerce":
                    data = "PC";
                    break;
                case "Commerce General":
                    data = "CG";
                    break;
                case "Arts":
                    data = "AR";
                    break;
                case "Humanities":
                    data = "HM";
                    break;
                case "General Science":
                    data = "GS";
                    break;
                case "Commerce2":
                    data = "CM";
                    break;
                case "General":
                    data = "G";
                    break;
                case "AODP":
                    data = "AODP";
                    break;
            }

            return data;
        }
    }
    static public class Decoder{
        public static String jurisdictionDecode(String data){
            if(data == null) return "";
            switch (data) {
                case "B":
                    data = "Both";
                    break;
                case "I":
                    data = "Inside";
                    break;
                case "O":
                    data = "Out of";
                    break;
            }
            return data;
        }
        public static String genderDecode(String data){
            if(data == null) return "";
            switch (data) {
                case "M":
                    data = "Male";
                    break;
                case "F":
                    data = "Female";
                    break;
                case "O":
                    data = "Other";
                    break;
            }
            return data;
        }
        public static String decode(String data){
            if(data == null) return "";
            switch (data) {
                case "S":
                    data = "Self";
                    break;
                case "F":
                    data = "Father";
                    break;
                case "U":
                    data = "Urban";
                    break;
                case "R":
                    data = "Rural";
                    break;
            }
            return data;
        }
        public static String sessionDecode(String data){
            if(data == null) return "";
            switch (data) {
                case "M":
                    data = "Morning";
                    break;
                case "E":
                    data = "Evening";
                    break;
                case "N":
                    data = "Non";
                    break;
            }
            return data;
        }
        public static String adminDecode(String data){
            if(data == null) return "";
            switch (data) {
                case "S":
                    data = "Super";
                    break;
                case "O":
                    data = "Operator";
                    break;
            }
            return data;
        }
        public static boolean booleanDecode(byte data){
            return data == 1;
        }
        public static String groupDecode(String data){
            if(data == null) return "";
            switch (data) {
                case "PM":
                    data = "Medical";
                    break;
                case "PE":
                    data = "Engineering";
                    break;
                case "PC":
                    data = "Commerce";
                    break;
                case "CG":
                    data = "Commerce General";
                    break;
                case "AR":
                    data = "Arts";
                    break;
                case "HM":
                    data = "Humanities";
                    break;
                case "GS":
                    data = "General Science";
                    break;
                case "CM":
                    data = "Commerce";
                    break;
                case "G":
                    data = "General";
                    break;
                case "AODP":
                    data = "AODP";
                    break;
            }
            return data;
        }
    }
}