package com.example.project.Service;

import com.example.project.Entity.MemberEntity;
import com.example.project.Repository.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {
    private final LoginAttemptRepository loginAttemptRepository;

    public void saveLoginInfo(String name, LocalDateTime loginTime) {
        MemberEntity loginAttempt = new MemberEntity();
        loginAttempt.setName(name);
        loginAttempt.setLogintime(loginTime);
        loginAttemptRepository.save(loginAttempt);
    }

    public void saveLogoutInfo(String name, LocalDateTime logouttime) {
        MemberEntity loginAttempt = loginAttemptRepository.findByName(name);
        if (loginAttempt != null) {
            loginAttempt.setLogouttime(logouttime);
            loginAttemptRepository.save(loginAttempt);
        }
    }
}
