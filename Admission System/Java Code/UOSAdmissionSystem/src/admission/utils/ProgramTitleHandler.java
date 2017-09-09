package admission.utils;

/**
 *
 * @author Yougeshwar
 */
public class ProgramTitleHandler {

    public static String handleMaster(String program, String discipline, String shift) {
        String posName = (program.equals("M.B.A")
                ? (shift.equals("MORNING") ? program + " (PREVIOUS)" : program + " First Year")
                : program.equals("M.C.S")
                || program.equals("LL.M")
                || //                    program.equals("LL.B") || 
                program.equals("M.COM")
                || //                    program.equals("B.COM") || 
                program.equals("M.P.A")
                ? program + " (PREVIOUS)"
                : program.equals("PGD")
                || program.startsWith("1 Year Con")
                ? program + " " + discipline
                : program.equals("BPEHSS")
                || program.equals("M.LIS")
                || program.equals("MPEHSS")
                ? discipline + " (" + program + ")"
                : program.equals("M.Sc (HONS)") ? program + " " + discipline
                : program + " (PREVIOUS) " + discipline);

        return posName += (shift.equals("MORNING") ? "" : " EVENING");
    }

    public static String handleBachelor(String program, String discipline, String shift) {
        return program.equals("B.B.A") ? program + " (HONS) FIRST YEAR"
                : (program.equals("B.P.A")
                || program.equals("PHARM-D")
                || program.equals("LL.M")) ? program + " FIRST YEAR"
                : program + " (" + discipline + ") FIRST YEAR";
    }
}
