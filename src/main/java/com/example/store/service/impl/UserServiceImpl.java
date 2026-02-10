package com.example.store.service.impl;

import com.example.store.model.dto.UserDTO;
import com.example.store.model.entity.User;
import com.example.store.repository.UserRepository;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO create(UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .description(dto.getDescription())
                .build();

        user = userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        user.setName(dto.getName());
        user.setCategory(dto.getCategory());
        user.setPrice(dto.getPrice());
        user.setQuantity(dto.getQuantity());
        user.setDescription(dto.getDescription());
        user = userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .category(user.getCategory())
                .price(user.getPrice())
                .quantity(user.getQuantity())
                .description(user.getDescription())
                .build();
    }
}