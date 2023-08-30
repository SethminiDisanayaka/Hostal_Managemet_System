package lk.ijse.hostal_management_system.util;

import lk.ijse.hostal_management_system.entity.Reservation;
import lk.ijse.hostal_management_system.entity.Room;
import lk.ijse.hostal_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

        private static SessionFactoryConfig factoryConfig;
        private final SessionFactory sessionFactory;

        private SessionFactoryConfig() {

            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Room.class);
            configuration.addAnnotatedClass(Reservation.class);

            Properties properties = new Properties();
            try {
                properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
            } catch (IOException e) {
                System.out.println("file not found");
            }
            configuration.mergeProperties(properties);

            sessionFactory = configuration.buildSessionFactory();
        }

        public static SessionFactoryConfig getInstance() {
            return (null == factoryConfig)
                    ? factoryConfig = new SessionFactoryConfig()
                    : factoryConfig;
        }

        public Session getSession() {
            return sessionFactory.openSession();
        }
}
