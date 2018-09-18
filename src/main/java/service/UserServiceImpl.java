package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
import validator.UserValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = null;
    private UserDao userDao = UserDaoImpl.getInstance();
    private UserValidator userValidator = UserValidator.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    public void addUser(User user) {
        try {
            if (userValidator.isValidate(user)) {
                userDao.saveUser(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(Long userId) throws IOException {
        userDao.removeUserById(userId);
    }

    public User getUserById(Long userId) throws IOException {
        List<User> users = getAllUsers();

        for (User user : users
                ) {
            boolean isFoundUser = user.getId().equals(userId);
            if (isFoundUser) {
                return user;
            }

        }

        return null;
    }

    public User getUserByLogin(String login) throws IOException {
        List<User> users = getAllUsers();

        for (User user : users
                ) {
            boolean isFoundUser = user.getLogin().equals(login);
            if (isFoundUser) {
                return user;
            }

        }

        return null;
    }


}
