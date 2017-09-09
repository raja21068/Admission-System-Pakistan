/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.controller;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import admission.utils.IConstant;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Raja Kumar & Jay Kumar
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            AnnotationConfiguration configure = new AnnotationConfiguration().configure("/admission/controller/hibernate.cfg.xml");
            configure.setProperty("hibernate.connection.driver_class", IConstant.DB.DRIVER);
            configure.setProperty("hibernate.connection.url", IConstant.DB.URL);
            configure.setProperty("hibernate.connection.username", IConstant.DB.USERNAME);
            configure.setProperty("hibernate.connection.password", IConstant.DB.PASSWORD);
            
            sessionFactory = configure.buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
