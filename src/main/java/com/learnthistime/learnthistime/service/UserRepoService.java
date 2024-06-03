package com.learnthistime.learnthistime.service;

import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepoService {
    UserRepo mUserRepo;
    UserRepoService(UserRepo userRepo) {
        this.mUserRepo = userRepo;
    }

    public boolean insert(User user) {
        try {
            if (mUserRepo.findById(user.getUserName()).isPresent()) {
                return false;
            }
            mUserRepo.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertMultipleUsers(User users[]) {
        try {
            mUserRepo.saveAll(Arrays.stream(users).toList());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        return mUserRepo.findAll();
    }

    public User getUserFromUserName(final String userName) {
        System.out.println("Searching for userName: " + userName);
//        Optional<User> user = mUserRepo.findById(userName);
        List<User> users = getAllUsers();
        for (User user: users) {
            System.out.println("user: " + user.getUserName());
            if (user.getUserName().equals(userName)) return user;
        }
//        return user.orElse(null);
        return null;
    }

    public boolean updateUser(final User user) {
        mUserRepo.save(user);
        return true;
    }
}
