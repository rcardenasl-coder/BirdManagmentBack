package com.birdregistry.bird_registry_api.bird.infraestructure.controller;

import com.birdregistry.bird_registry_api.bird.application.dto.BirdDto;
import com.birdregistry.bird_registry_api.bird.application.ports.in.BirdService;
import com.birdregistry.bird_registry_api.bird.config.utils.Constants;
import com.birdregistry.bird_registry_api.bird.infraestructure.wrapper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/bird")
@RequiredArgsConstructor
public class BirdController {
    private final BirdService service;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<BirdDto>> create(@RequestBody BirdDto birdDto){
        BirdDto birdDto1 = service.create(birdDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(Constants.SUCCESS, String.format(Constants.SAVE_SUCCESS_MESSAGE,"Bird"),birdDto1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<BirdDto>>> findAll(){
        List<BirdDto> birdDTOs = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(Constants.SUCCESS,String.format(Constants.SEARCH_SUCCESS_MESSAGE,"All Bird"),birdDTOs));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ApiResponse<BirdDto>> findById(@PathVariable Long id){
        BirdDto birdDto = service.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(Constants.SUCCESS,String.format(Constants.SEARCH_SUCCESS_MESSAGE, "Bird"),birdDto));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse<BirdDto>> update(@PathVariable Long id, @RequestBody BirdDto birdDto){
        BirdDto birdDto1 = service.update(birdDto.toBuilder().birdCode(id).build());
        return ResponseEntity.ok(new ApiResponse<>(Constants.SUCCESS,String.format(Constants.UPDATE_SUCCESS_MESSAGE,"Bird"),birdDto1));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(Constants.SUCCESS,String.format(Constants.DELETE_SUCCESS_MESSAGE,"Bird"),null));
    }
}
