package admission.utils;

import java.awt.Component;
import java.awt.Container;

/**
 *
 * @author Yougeshwar
 */
public class ContainerController {
    public static void setContainerEnabled(Container container, boolean aFlag) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(aFlag);
            if (component instanceof Container) {
                setContainerEnabled((Container)component, aFlag);
            }
        }
    }
}
