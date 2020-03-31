package org.resliv.service;

import org.resliv.model.User;
import org.resliv.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User findByChatId(long chatId) {
        return userRepo.findByChatId(chatId);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }
}
