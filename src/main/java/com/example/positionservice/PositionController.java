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
    public Position getPositionById(@PathVariable ("id") Long id) {
        return positionService.findPositionById(id);
    }

    @GetMapping("/positions/")
    public Collection<Position> getAllPositions() {
        return positionService.findAllPositions();
    }

    @PostMapping("/positions/")
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/positions/")
    public Position updatePosition(@RequestBody Position position) {
        return positionService.updatePosition(position);
    }

    @DeleteMapping("/positions/{id}")
    public void deletePosition(@PathVariable ("id") Long id) {
        positionService.deletePositionById(id);
    }
}
