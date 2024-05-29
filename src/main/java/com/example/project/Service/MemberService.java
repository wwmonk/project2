package com.example.project.Service;

import com.example.project.DTO.BookingDTO;
import com.example.project.DTO.MemberDTO;
import com.example.project.Entity.BookingEntity;
import com.example.project.Entity.MemberEntity;
import com.example.project.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;


    public void delete(Integer id) {
        memberRepository.deleteById(id);
    }
    public MemberEntity memberSave(MemberDTO memberDTO) {
        if (memberDTO == null) {
            throw new IllegalStateException("회원 정보가 null입니다.");
        }
        //기존 사용하는 아이디를 조회
        Optional<MemberEntity> read = memberRepository
                .findByUsername(memberDTO.getUsername());
        if (read.isPresent()) {
            return null;
        }
        if (read.isEmpty()) {
            //memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            MemberEntity memberEntity = modelMapper.map(memberDTO,
                    MemberEntity.class);
            memberEntity.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            return memberRepository.save(memberEntity);
        }
        return null;
    }
    public void memberUpdate(MemberDTO memberDTO) {
        Optional<MemberEntity> read = memberRepository.findByUsername(memberDTO.getUsername());
        if(memberDTO.getPassword() != null || memberDTO.getPassword().isEmpty()) {
            memberDTO.setPassword(read.get().getPassword());
        } else {
            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        }

        MemberEntity memberEntity = modelMapper.map(memberDTO,
                MemberEntity.class);
        memberRepository.save(memberEntity);
    }
    public MemberDTO memberRead(int id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        MemberDTO memberDTO = modelMapper.map(memberEntity, MemberDTO.class);
        return memberDTO;
    }
    public Page<MemberDTO> memberList(Pageable page) {
        int cutPage = page.getPageNumber() - 1;
        int pageCnt = 10;

        Pageable pageable = PageRequest.of(cutPage, pageCnt,
                Sort.by(Sort.Direction.DESC, "id"));
        Page<MemberEntity> memberEntities = memberRepository.findAll(pageable);

        Page<MemberDTO> memberDTOS = memberEntities.map(data ->
                modelMapper.map(data, MemberDTO.class));

        return memberDTOS;
    }
}
