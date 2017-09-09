package admission.reports;

import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Yougeshwar
 */
public interface ReportFilterProvider {
    public JRBeanCollectionDataSource getJRDataSource();
    public Map fillReportParameter(Map map);
}
