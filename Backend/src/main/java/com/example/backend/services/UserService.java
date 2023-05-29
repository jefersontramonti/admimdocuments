package com.example.backend.services;

import com.example.backend.dto.UserDTO;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userUtil::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userUtil::toUserDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userUtil.toUser(userDTO);
        user = userRepository.save(user);
        return userUtil.toUserDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userUtil.updateUser(user, userDTO);
        User updatedUser = userRepository.save(user);
        return userUtil.toUserDTO(updatedUser);
    }
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Converter User para UserDTO antes de retornar
        return userUtil.toUserDTO(user);
    }
}
