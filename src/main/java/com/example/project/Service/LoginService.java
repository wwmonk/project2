package com.example.project.Service;

import com.example.project.DTO.MemberDTO;
import com.example.project.Entity.MemberEntity;
import com.example.project.Repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MemberEntity> memberEntity = loginRepository.findByUsername(username);
        if (memberEntity.isPresent()) {
            return User.withUsername(memberEntity.get().getUsername())
                    .password(memberEntity.get().getPassword())
                    .roles(memberEntity.get().getRole().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("해당 아이디가 존재하지 않음");
        }
    }



    /*public MemberDTO login(String username, String password) {
        Optional<MemberEntity> memberEntity = loginRepository.findByUsername(username);
        MemberDTO memberDTO = modelMapper.map(memberEntity, MemberDTO.class);

        return memberDTO;
    }*/
}


