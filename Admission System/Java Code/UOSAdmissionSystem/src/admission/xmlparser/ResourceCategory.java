/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yougeshwar
 */
public class ResourceCategory {
    private String name;
    private List<Resource> resourceList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public void addResource(Resource resource) {
        resourceList.add(resource);
    }

    @Override
    public String toString() {
        return name;
    }
    
}
