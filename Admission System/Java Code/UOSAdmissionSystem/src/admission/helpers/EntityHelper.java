package admission.helpers;

import admission.controller.DatabaseManager;
import admission.model.security.EntityPrivilege;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class EntityHelper {
    public static <T> List<T> getEntities(Class<T> modelClass) {
        Integer userId = CommonHelper.getActiveUserId();
        List<EntityPrivilege> data = DatabaseManager.getData(EntityPrivilege.class, "user.id = " + userId + " AND modelName = '" + modelClass.getName() + "'", "modelId");
        List<T> list = new ArrayList<>();
        for (EntityPrivilege ep : data) {
            T t = DatabaseManager.getSingleRecord(modelClass, ep.getModelId());
            list.add(t);
        }
        return list;
    }
}
