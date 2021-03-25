package be.technifutur.exoSecu.init;


import be.technifutur.exoSecu.entities.User;
import be.technifutur.exoSecu.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserInit implements InitializingBean {

    private final UserService service;

    List<User> users = Collections.singletonList(
        new User("Jules", "1234")
    );


    public UserInit(UserService service) {
        this.service = service;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        users.forEach(service::insertUser);
    }
}
