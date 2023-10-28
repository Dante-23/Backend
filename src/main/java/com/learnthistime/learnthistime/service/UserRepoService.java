package com.learnthistime.learnthistime.service;

import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

}
