package com.ftn.e2.isa.blood_simple.service;
import com.ftn.e2.isa.blood_simple.dto.UpdatePasswordDTO;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.AddressRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

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
            Address address = addressRepository.findById(userToUpdate.getAddress().getId()).orElse(null);

            userToUpdate.setPassword(updateUserDTO.getPassword());
            userToUpdate.setName(updateUserDTO.getName());
            userToUpdate.setSurname(updateUserDTO.getSurname());
            userToUpdate.setGender(updateUserDTO.getGender());
            address.setId(updateUserDTO.getAddressId());
            address.setStreet(updateUserDTO.getAddressStreet());
            address.setNumber(updateUserDTO.getAddressNumber());
            address.setCity(updateUserDTO.getAddressCity());
            address.setCountry(updateUserDTO.getAddressCountry());
            userToUpdate.setPhoneNumber(updateUserDTO.getPhoneNumber());
            userToUpdate.setJob(updateUserDTO.getJob());
            userToUpdate.setBio(updateUserDTO.getBio());

            addressRepository.save(address);
            userRepository.save(userToUpdate);
        }
        return status;
    }

    public boolean updatePassword(UpdatePasswordDTO passwordDTO) {
        boolean status = userRepository.existsById(passwordDTO.getId());
        if (status) {
            User userToUpdate = userRepository.findById(passwordDTO.getId()).orElse(null);
            assert userToUpdate != null;
            if(!userToUpdate.getPassword().equals(passwordDTO.getCurrentpassword()))
            {
                return false;
            }
            if(!passwordDTO.getNewpassword().equals(passwordDTO.getRepeatedpassword()))
            {
                return false;
            }

            userToUpdate.setPassword(passwordDTO.getNewpassword());

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
