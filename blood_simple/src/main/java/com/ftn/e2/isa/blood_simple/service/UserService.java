package com.ftn.e2.isa.blood_simple.service;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user: users){
            usersDTO.add(new UserDTO(user));
        }
        return usersDTO;
    }

    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            return new UserDTO(user);
        }else{
            return null;
        }
    }

    public boolean updateUser(UserDTO updateUserDTO) {
        boolean status = userRepository.existsById(updateUserDTO.getId());
        if (status) {
            User userToUpdate = userRepository.findById(updateUserDTO.getId()).orElse(null);
            assert userToUpdate != null;

            userToUpdate.setPassword(updateUserDTO.getPassword());
            userToUpdate.setName(updateUserDTO.getName());
            userToUpdate.setSurname(updateUserDTO.getSurname());
            userToUpdate.setGender(updateUserDTO.getGender());
//            userToUpdate.setAddress(updateUserDTO.getAddress());
            userToUpdate.setPhoneNumber(updateUserDTO.getPhoneNumber());
            userToUpdate.setJob(updateUserDTO.getJob());
            userToUpdate.setBio(updateUserDTO.getBio());

            userRepository.save(userToUpdate);
        }
        return status;
    }

    public boolean deleteUser(String id){
        boolean status = userRepository.existsById(id);
        if(status){
            userRepository.deleteById(id);
        }
        return status;
    }
}
