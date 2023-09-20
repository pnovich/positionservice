package com.example.positionservice;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class PositionService {
    Logger logger = LoggerFactory.getLogger(PositionService.class);
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


    @Cacheable(value = "positions", key = "#id")
    public Position findPositionById(Long id) {
        logger.info("getting position by id");
        return positionRepository.findById(id).get();
    }

    @Cacheable(value = "positions")
    public Collection<Position> findAllPositions() {
        logger.info("getting all positions from database");
        return positionRepository.findAll();
    }

    @CacheEvict(value = "positions", allEntries = true, beforeInvocation = true)
    @CachePut(value = "positions")
    public Position createPosition(Position position) {
        logger.info("creating position");
        return positionRepository.save(position);
    }

    @CacheEvict(value = "positions", allEntries = true, beforeInvocation = true)
    @CachePut(value = "positions")
    public Collection<Position> saveAll(Collection<Position> positions) {
        logger.info("saving positions collection");
        return positionRepository.saveAll(positions);
    }

    @CacheEvict(value = "positions", allEntries = true, beforeInvocation = true)
    @CachePut(value = "positions", key = "#position.id")
    public Position updatePosition(Position position) {
        logger.info("updating position with id = " + position.getId());
        return positionRepository.save(position);
    }

    @CacheEvict(cacheNames = "positions", allEntries = true)
    public void deletePositionById(Long id) {
        logger.info("deleting position with id = " + id);
        positionRepository.deleteById(id);
    }

    public Collection<Position> getAllPositionsWithPagination(int pageNumber, int pageSize) {
        logger.info("getting paged collection of positions");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Position> page = positionRepository.findAll(pageable);
        return page.getContent();
    }

    public List<Position> getBatch(int batchSize) {
        List positions = new ArrayList();
        for (int i = 0; i < batchSize; i++) {
            positions.add(new Position("position from batch" + Math.random() * 1000 + 500));
        }
        return positions;
    }

}
