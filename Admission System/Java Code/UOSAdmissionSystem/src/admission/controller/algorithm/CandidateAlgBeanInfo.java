/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.controller.algorithm;

import java.beans.*;

/**
 *
 * @author RAJA DELL LAPTOP
 */
public class CandidateAlgBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( admission.controller.algorithm.CandidateAlg.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor
    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_area = 0;
    private static final int PROPERTY_categories = 1;
    private static final int PROPERTY_choices = 2;
    private static final int PROPERTY_currentSelection = 3;
    private static final int PROPERTY_degreeYears = 4;
    private static final int PROPERTY_districtId = 5;
    private static final int PROPERTY_gender = 6;
    private static final int PROPERTY_id = 7;
    private static final int PROPERTY_jurisdication = 8;
    private static final int PROPERTY_lastCredetialPercentage = 9;
    private static final int PROPERTY_optionalSubjectIds = 10;
    private static final int PROPERTY_percentage = 11;
    private static final int PROPERTY_programGrp = 12;
    private static final int PROPERTY_programId = 13;
    private static final int PROPERTY_provinceCode = 14;
    private static final int PROPERTY_score = 15;
    private static final int PROPERTY_seatNo = 16;
    private static final int PROPERTY_selection = 17;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[18];
    
        try {
            properties[PROPERTY_area] = new PropertyDescriptor ( "area", admission.controller.algorithm.CandidateAlg.class, "getArea", "setArea" ); // NOI18N
            properties[PROPERTY_categories] = new PropertyDescriptor ( "categories", admission.controller.algorithm.CandidateAlg.class, "getCategories", "setCategories" ); // NOI18N
            properties[PROPERTY_choices] = new PropertyDescriptor ( "choices", admission.controller.algorithm.CandidateAlg.class, "getChoices", "setChoices" ); // NOI18N
            properties[PROPERTY_currentSelection] = new PropertyDescriptor ( "currentSelection", admission.controller.algorithm.CandidateAlg.class, "getCurrentSelection", "setCurrentSelection" ); // NOI18N
            properties[PROPERTY_degreeYears] = new PropertyDescriptor ( "degreeYears", admission.controller.algorithm.CandidateAlg.class, "getDegreeYears", "setDegreeYears" ); // NOI18N
            properties[PROPERTY_districtId] = new PropertyDescriptor ( "districtId", admission.controller.algorithm.CandidateAlg.class, "getDistrictId", "setDistrictId" ); // NOI18N
            properties[PROPERTY_gender] = new PropertyDescriptor ( "gender", admission.controller.algorithm.CandidateAlg.class, "getGender", "setGender" ); // NOI18N
            properties[PROPERTY_id] = new PropertyDescriptor ( "id", admission.controller.algorithm.CandidateAlg.class, "getId", "setId" ); // NOI18N
            properties[PROPERTY_jurisdication] = new PropertyDescriptor ( "jurisdication", admission.controller.algorithm.CandidateAlg.class, "isJurisdication", "setJurisdication" ); // NOI18N
            properties[PROPERTY_lastCredetialPercentage] = new PropertyDescriptor ( "lastCredetialPercentage", admission.controller.algorithm.CandidateAlg.class, "getLastCredetialPercentage", "setLastCredetialPercentage" ); // NOI18N
            properties[PROPERTY_optionalSubjectIds] = new PropertyDescriptor ( "optionalSubjectIds", admission.controller.algorithm.CandidateAlg.class, "getOptionalSubjectIds", "setOptionalSubjectIds" ); // NOI18N
            properties[PROPERTY_percentage] = new PropertyDescriptor ( "percentage", admission.controller.algorithm.CandidateAlg.class, "getPercentage", "setPercentage" ); // NOI18N
            properties[PROPERTY_programGrp] = new PropertyDescriptor ( "programGrp", admission.controller.algorithm.CandidateAlg.class, "getProgramGrp", "setProgramGrp" ); // NOI18N
            properties[PROPERTY_programId] = new PropertyDescriptor ( "programId", admission.controller.algorithm.CandidateAlg.class, "getProgramId", "setProgramId" ); // NOI18N
            properties[PROPERTY_provinceCode] = new PropertyDescriptor ( "provinceCode", admission.controller.algorithm.CandidateAlg.class, "getProvinceCode", "setProvinceCode" ); // NOI18N
            properties[PROPERTY_score] = new PropertyDescriptor ( "score", admission.controller.algorithm.CandidateAlg.class, "getScore", "setScore" ); // NOI18N
            properties[PROPERTY_seatNo] = new PropertyDescriptor ( "seatNo", admission.controller.algorithm.CandidateAlg.class, "getSeatNo", "setSeatNo" ); // NOI18N
            properties[PROPERTY_selection] = new PropertyDescriptor ( "selection", admission.controller.algorithm.CandidateAlg.class, "getSelection", "setSelection" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties
    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events
    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_addCurrentSelection0 = 0;
    private static final int METHOD_emptyCurrentSelection1 = 1;
    private static final int METHOD_isCatNotAllocatedWithChoiceNo2 = 2;
    private static final int METHOD_isContainCategory3 = 3;
    private static final int METHOD_isPosAllocated4 = 4;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[5];
    
        try {
            methods[METHOD_addCurrentSelection0] = new MethodDescriptor(admission.controller.algorithm.CandidateAlg.class.getMethod("addCurrentSelection", new Class[] {boolean.class, int.class, int.class, int.class, int.class, int.class, admission.controller.algorithm.CategoryAlg.class, admission.controller.algorithm.DisciplineAlg.class, int.class, admission.controller.algorithm.AdmissionController.class})); // NOI18N
            methods[METHOD_addCurrentSelection0].setDisplayName ( "" );
            methods[METHOD_emptyCurrentSelection1] = new MethodDescriptor(admission.controller.algorithm.CandidateAlg.class.getMethod("emptyCurrentSelection", new Class[] {})); // NOI18N
            methods[METHOD_emptyCurrentSelection1].setDisplayName ( "" );
            methods[METHOD_isCatNotAllocatedWithChoiceNo2] = new MethodDescriptor(admission.controller.algorithm.CandidateAlg.class.getMethod("isCatNotAllocatedWithChoiceNo", new Class[] {boolean.class, int.class, int.class})); // NOI18N
            methods[METHOD_isCatNotAllocatedWithChoiceNo2].setDisplayName ( "" );
            methods[METHOD_isContainCategory3] = new MethodDescriptor(admission.controller.algorithm.CandidateAlg.class.getMethod("isContainCategory", new Class[] {admission.enums.CategoryEnum.class})); // NOI18N
            methods[METHOD_isContainCategory3].setDisplayName ( "" );
            methods[METHOD_isPosAllocated4] = new MethodDescriptor(admission.controller.algorithm.CandidateAlg.class.getMethod("isPosAllocated", new Class[] {boolean.class, int.class})); // NOI18N
            methods[METHOD_isPosAllocated4].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods
    // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx


//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable properties of this bean.
     * May return null if the information should be obtained by automatic
     * analysis.
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean. May return null if the information
     * should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will belong
     * to the IndexedPropertyDescriptor subclass of PropertyDescriptor. A client
     * of getPropertyDescriptors can use "instanceof" to check if a given
     * PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of events
     * fired by this bean. May return null if the information should be obtained
     * by automatic analysis.
     */
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods implemented
     * by this bean. May return null if the information should be obtained by
     * automatic analysis.
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     * returned by getPropertyDescriptors.
     * <P>
     * Returns -1 if there is no default property.
     */
    @Override
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will mostly
     * commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array returned
     * by getEventSetDescriptors.
     * <P>
     * Returns -1 if there is no default event.
     */
    @Override
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to represent the
     * bean in toolboxes, toolbars, etc. Icon images will typically be GIFs, but
     * may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from this
     * method.
     * <p>
     * There are four possible flavors of icons (16x16 color, 32x32 color, 16x16
     * mono, 32x32 mono). If a bean choses to only support a single icon we
     * recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background so they can be
     * rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested. This should be one of the
     * constant values ICON_COLOR_16x16, ICON_COLOR_32x32, ICON_MONO_16x16, or
     * ICON_MONO_32x32.
     * @return An image object representing the requested icon. May return null
     * if no suitable icon is available.
     */
    @Override
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) {
                    return null;
                } else {
                    if (iconColor16 == null) {
                        iconColor16 = loadImage(iconNameC16);
                    }
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) {
                    return null;
                } else {
                    if (iconColor32 == null) {
                        iconColor32 = loadImage(iconNameC32);
                    }
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) {
                    return null;
                } else {
                    if (iconMono16 == null) {
                        iconMono16 = loadImage(iconNameM16);
                    }
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) {
                    return null;
                } else {
                    if (iconMono32 == null) {
                        iconMono32 = loadImage(iconNameM32);
                    }
                    return iconMono32;
                }
            default:
                return null;
        }
    }
    
}
