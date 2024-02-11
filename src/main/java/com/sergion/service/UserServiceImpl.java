package com.sergion.service;

import com.sergion.model.IUser;
import com.sergion.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl<T extends IUser, ID extends String> implements IUserService<T, ID> {

    private List<T> userList = new ArrayList<>();
    private Map<String, String> groupMap = new HashMap<>();

    private String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public void generateData(){
        userList.add((T) new User(generateUserId(), "User_1", "3", "Offline", "user"));
        userList.add((T) new User(generateUserId(), "User_2", "4", "Online", "user"));
        userList.add((T) new User(generateUserId(), "User_3", "3", "Offline", "user"));
        userList.add((T) new User(generateUserId(), "User_4", "4", "Online", "user"));
        userList.add((T) new User(generateUserId(), "Admin_1", "1", "Online", "admin"));
        userList.add((T) new User(generateUserId(), "Admin_2", "2", "Online", "admin"));

        groupMap.put("1", "Admin_Group1");
        groupMap.put("2", "Admin_Group2");
        groupMap.put("3", "User_Group1");
        groupMap.put("4", "User_Group2");
    }

    @Override
    public List<T> findAll() {
        return userList;
    }

    @Override
    public List<T> findById(ID id) {
        return userList.stream()
                .filter(user -> user.getId().contentEquals(id))
                .collect(Collectors.toList());
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> users) {
        List<S> savedUsers = new ArrayList<>();
        users.forEach(user -> {
            savedUsers.add(user);
        });
        return savedUsers;
    }

    @Override
    public <S extends T> S save(S user) {
        User newUser = new User();
        newUser.setId(generateUserId());
        newUser.setName(user.getName());
        newUser.setUserType(user.getUserType());
        newUser.setStatus(user.getStatus());
        newUser.setGroupId(user.getGroupId());
        userList.add((T) newUser);
        return (S) newUser;
    }

    @Override
    public T delete(ID id) {
        T userToDelete = findById(id).stream().findFirst().orElse(null);
        if (userToDelete != null) {
            userList.remove(userToDelete);
        }
        return userToDelete;
    }

    @Override
    public Map<String, List<T>> findAllGroupByGroupId() {
        //TODO Implement logic to group users by groupId
        //Hard to implement due to method not receiving Id value
        return null;
    }
    @Override
    public String updateStatus(ID id, String status) {
        User userToUpdate = (User) findById(id).stream().findFirst().orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setStatus(status);
        }
        return status;
    }

    public User updateUser(ID id, User user) {
        User userToUpdate = (User) findById(id).stream().findFirst().orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setStatus(user.getStatus());
            userToUpdate.setName(user.getName());
            userToUpdate.setUserType(user.getUserType());
            userToUpdate.setGroupId(user.getGroupId());
        }
        return userToUpdate;
    }

}
