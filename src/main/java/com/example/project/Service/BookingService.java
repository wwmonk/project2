package com.example.project.Service;

import com.example.project.DTO.BookingDTO;
import com.example.project.DTO.StoreDTO;
import com.example.project.Entity.BookingEntity;
import com.example.project.Entity.StoreEntity;
import com.example.project.Repository.BookingRepository;
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
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public void memberRemove(int id) {
        bookingRepository.deleteById(id);
    }

    public Page<BookingDTO> bookingList(Pageable page) {
        int cutPage = page.getPageNumber() - 1;
        int pageCnt = 10;

        Pageable pageable = PageRequest.of(cutPage, pageCnt,
                Sort.by(Sort.Direction.DESC, "id"));
        Page<BookingEntity> bookingEntities = bookingRepository.findAll(pageable);

        Page<BookingDTO> bookingDTOS = bookingEntities.map(data ->
                modelMapper.map(data, BookingDTO.class));

        return bookingDTOS;
    }
    public void bookingSave(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = modelMapper.map(bookingDTO,
                BookingEntity.class);
        bookingRepository.save(bookingEntity);
    }
    public void bookingUpdate(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = modelMapper.map(bookingDTO,
                BookingEntity.class);
        bookingRepository.save(bookingEntity);
    }
    public BookingDTO bookingRead(int id) {
        Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        BookingDTO bookingDTO = modelMapper.map(bookingEntity, BookingDTO.class);
        return bookingDTO;
    }
}
