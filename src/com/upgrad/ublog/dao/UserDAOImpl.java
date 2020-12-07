package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: 3.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.6. findByEmailId() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the USER table defined
 *  in the database. (Hint: You should get the connection using the Database class)
 * TODO: 3.7. create() method should take user details as input and insert these details
 *  into the USER table. Return the same User object which was passed as an input
 *  argument. (Hint: You should get the connection using the Database class)
 */

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance = new UserDAOImpl();

    private UserDAOImpl() {
    }

    public User create(User user) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String userName= user.getEmailId();
        String userPassword=user.getPassword();
        String insertQuery="insert into user( emailId,password) values ("+userName+","+ userPassword+")";
                statement.executeUpdate(insertQuery);
return user ;
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public User findByEmailId(String emailId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String selectQuery="select emailId from user where emailId ="+emailId;
        statement.executeQuery(selectQuery);
        return null;
    }




    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User temp = new User();
            temp.setUserId(1);
            temp.setEmailId("temp@temp.temp");
            temp.setPassword("temp");
            userDAO.create(temp);
            System.out.println(userDAO.findByEmailId("temp@temp.temp"));
        } catch (Exception e) {
            System.out.println("FAILED");
        }

//        /**
//         * Following should be the desired output of the main method.
//         * User{userId=11, emailId='temp@temp.temp', password='temp'}
//         */
//    }
    }
}
