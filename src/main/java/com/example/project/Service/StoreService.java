package com.example.project.Service;

import com.example.project.DTO.StoreDTO;
import com.example.project.Entity.StoreEntity;
import com.example.project.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public Page<StoreDTO> storeList(Pageable page) {
        int cutPage = page.getPageNumber()-1;
        int pageCnt = 10;

        Pageable pageable = PageRequest.of(cutPage, pageCnt,
                Sort.by(Sort.Direction.DESC, "id"));
        Page<StoreEntity> storeEntities = storeRepository.findAll(pageable);

        Page<StoreDTO> storeDTOS = storeEntities.map(data->
                modelMapper.map(data, StoreDTO.class));

        return storeDTOS;
    }
    public StoreDTO read(long id) {
        Optional<StoreEntity> storeEntity = storeRepository.findById(id);
        //StoreDTO result = modelMapper.map(StoreEntity, StoreDTO.class);
        StoreDTO result = storeEntity.map(data->modelMapper.map(data, StoreDTO.class)).orElse(null);

        return result;
    }
    public StoreDTO storeRead(Long id) {
        Optional<StoreEntity> storeEntity = storeRepository.findById(id);
        StoreDTO storeDTO = modelMapper.map(storeEntity, StoreDTO.class);
        return storeDTO;
    }
}
