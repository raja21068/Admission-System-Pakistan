package admission.utils;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class CategoryMapping {
    
    public static Integer[] getMappedArray(String code){
        Integer[] array = null;
        switch(code){
            case IConstant.GM_DUR_QUOTA:
                array = new Integer[]{
                    0, 1, 2, 3
                };
                break;
            case IConstant.SFM_QUOTA:
                array = new Integer[]{
                    11, 12, //17
                };
                break;
            case IConstant.SFE_QUOTA:
                break;
            case IConstant.SUE_QUOTA:
                array = new Integer[]{
                    6, 7, 9
                };
                break;
            case IConstant.DP_QUOTA:
                array = new Integer[]{
                    4
                };
                break;
            case IConstant.NO_QUOTA:
                array = new Integer[]{
                    10, 13, 14, 15, 16
                };
                break;
            case IConstant.AP_QUOTA:
                array = new Integer[]{
                    18
                };
                break;
            case IConstant.AC_QUOTA:
                array = new Integer[]{
                };
                break;
            case IConstant.SP_QUOTA:
                array = new Integer[]{
                    5
                };
                break;
            case IConstant.FR_QUOTA:
                array = new Integer[]{
                    19, 20
                };
                break;
        }
        return array;
    }
}
