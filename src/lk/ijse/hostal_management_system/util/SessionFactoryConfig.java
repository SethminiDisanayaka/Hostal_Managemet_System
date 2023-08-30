package lk.ijse.hostal_management_system.util;

import lk.ijse.hostal_management_system.entity.Room;
import lk.ijse.hostal_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

        private static SessionFactoryConfig factoryConfig;
        private final SessionFactory sessionFactory;

        private SessionFactoryConfig() {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
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
