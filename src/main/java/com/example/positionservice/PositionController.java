package com.example.positionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(originPatterns = "/**")
@RestController
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/positions/{id}")
    public Position getPositionById(@PathVariable("id") Long id) {
        return positionService.findPositionById(id);
    }

    @GetMapping("/positions")
    public Collection<Position> getAllPositions() {
        return positionService.findAllPositions();
    }

    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/positions")
    public Position updatePosition(@RequestBody Position position) {
        return positionService.updatePosition(position);
    }

    @DeleteMapping("/positions/{id}")
    public void deletePosition(@PathVariable("id") Long id) {
        positionService.deletePositionById(id);
    }

    @GetMapping("/positions/paged")
    public Collection<Position> getAllPositionsWithPagination(@RequestParam("page") int pageNumber,
                                                              @RequestParam("size") int pageSize) {
        return positionService.getAllPositionsWithPagination(pageNumber, pageSize);
    }

    @PostMapping("positions/batch/{size}")
    private Collection<Position> addBatchInOneCollection(@PathVariable("size") int size) {
        Collection<Position> positions = positionService.getBatch(size);
        return positionService.saveAll(positions);
    }
}
