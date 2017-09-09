package admission.enums;

/**
 *
 * @author Yougeshwar
 */
public enum MessageEnum {
    MSG_SAVE(" Record saved Successfully"),
    MSG_DELETE("Record deleted Successfully"),
    MSG_CLEAR(""),
    MSG_SAVE_QUESTION("Do you want to save changes?"),
    MSG_DELETE_QUESTION("Do you want to delete?"),
    MSG_NO_FOUND("Record not found!"),
    MSG_NO_SELECTED("Record not selected!"),
    MSG_01("Do you also want to update choices?"),
    MSG_02("Seat no is empty"),
    MSG_03("SSC or HSC Board not selected"),
    MSG_04("Graduation university not selected"),
    MSG_05("Please fill valid credential details"),
    MSG_06("Please fill valid grdaduation credential details"),
    MSG_07("Do you want remove row?"),
    MSG_08("No choice remaining"),
    MSG_09("Select atleast one campus"),
    MSG_10("Object not in service"),
    MSG_11("Admission year or Program type not selected"),
    MSG_12("District not selected"),
    MSG_13("Enter Seat no"),
    MSG_14("Seat no already used"),
    MSG_15("Admision list not selected"),
    MSG_16("Select all required items"),
    MSG_17("Candidate not found"),
    MSG_18(""),
    MSG_19("You have not rights for save form"),
    MSG_20("You have not rights for update form"),
    MSG_21("Country not selected"),
    MSG_22("Religion not selected"),
    MSG_23("No discipline choice selected"),
    MSG_24("You have not rights to view form"),
    MSG_25("This record is not logged"),
    MSG_26("Data loaded successfully"),
    MSG_27("Selection completed successfully"),
    MSG_28("Obtain marks are greater than total marks"),
    MSG_29("Fill required fields")
    ;
    
    private String msg;

    private MessageEnum(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
