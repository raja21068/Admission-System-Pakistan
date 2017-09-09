package admission.xmlparser;

/**
 *
 * @author Yougeshwar
 */
public class Report {
    private String name;
    private String jasperFile;
    private String filterClass;
    private String securityContext;
    private boolean accessible;
    private String description;

    public Report() {
    }

    public Report(String name, String jasperFile, String filterClass, String securityContext, String description) {
        this.name = name;
        this.jasperFile = jasperFile;
        this.filterClass = filterClass;
        this.securityContext = securityContext;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getJasperFile() {
        return jasperFile;
    }

    public void setJasperFile(String jasperFile) {
        this.jasperFile = jasperFile;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(String securityContext) {
        this.securityContext = securityContext;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
