package com.learnthistime.learnthistime.service;

import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.repo.RequestRepo;

public class RequestRepoService {
    RequestRepo mRequestRepo;

    public RequestRepoService(RequestRepo requestRepo) {
        mRequestRepo = requestRepo;
    }

    public boolean insert(User user) {
        try {
//            if (mRequestRepo.findById(user.getUserName()).isPresent()) {
//                return false;
//            }
//            mUserRepo.save(user);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

}
