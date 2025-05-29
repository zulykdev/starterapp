package com.zulykdev.starter.service;

import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.util.exception.EmailAlreadyExistsException;
import com.zulykdev.starter.model.User;
import com.zulykdev.starter.repository.UserRepository;
import com.zulykdev.starter.security.JwtUtil;
import com.zulykdev.starter.util.mappers.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import static com.zulykdev.starter.util.Constants.EMAIL_ALREADY_EXISTS_MESSAGE;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS_MESSAGE);
        }
        User user = UserMapper.INSTANCE.toUser(userRequest);
        user.setActive(true);
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtUtil.generateToken(user.getEmail()));
        if (user.getPhones() != null) {
            user.getPhones().forEach(phone -> phone.setUser(user));
        }
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.toUserResponse(savedUser);
    }
}
