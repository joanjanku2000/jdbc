package configuration;

import entities.User;
import org.hibernate.cfg.Configuration;

public class SessionConfiguration {

    public static Configuration createConfiguration(){
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class);
    }
}
