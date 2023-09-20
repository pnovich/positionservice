package com.example.positionservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionRepository positionRepository;

    @PostConstruct
    public void initDatabase() {
        List<Position> positions = Arrays.asList(
                new Position("position1"),
                new Position("position2"),
                new Position("position3"),
                new Position("position4"),
                new Position("position5"),
                new Position("position6"),
                new Position("position7")
        );
        positionRepository.saveAll(positions);
    }


    public Position findPositionById(Long id) {
        return positionRepository.findById(id).get();
    }

    public Collection<Position> findAllPositions() {
        return positionRepository.findAll();
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Collection<Position> saveAll(Collection<Position> positions) {
        return positionRepository.saveAll(positions);
    }

    public Position updatePosition(Position position) {
        return positionRepository.save(position);
    }

    public void deletePositionById(Long id) {
        positionRepository.deleteById(id);
    }

    public Collection<Position> getAllPositionsWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Position> page = positionRepository.findAll(pageable);
        return page.getContent();
    }

}
