package com.abunko.alyans.userinfobyphone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController()
public class UserController {
    public static final Log LOG = LogFactory.getLog(UserController.class);

    private UserDao userDao = new UserDao();

    @GetMapping("/info")
    public String getUerInfoByPhone(@RequestParam String phone) throws SQLException {
        LOG.info("hello user");
        LOG.error("hello user");
        LOG.warn("hello user");
        LOG.debug("hello user");
        return userDao.getUerinfoByPhone(phone);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        LOG.info("hello world");
        LOG.error("hello world");
        LOG.warn("hello world");
        LOG.debug("hello world");
        return "hello World";
    }


}

