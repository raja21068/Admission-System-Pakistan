package admission.view.beans;

/**
 *
 * @author Yougeshwar
 */
public class FeeBean {
    private String name;
    private int amount;

    public FeeBean(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
