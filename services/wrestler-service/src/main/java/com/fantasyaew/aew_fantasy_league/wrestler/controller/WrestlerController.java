package com.fantasyaew.aew_fantasy_league.wrestler.controller;

import com.fantasyaew.aew_fantasy_league.wrestler.dto.PopularityUpdateRequest;
import com.fantasyaew.aew_fantasy_league.wrestler.dto.WrestlerRequest;
import com.fantasyaew.aew_fantasy_league.wrestler.dto.WrestlerResponse;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import com.fantasyaew.aew_fantasy_league.wrestler.service.WrestlerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wrestlers")
@RequiredArgsConstructor
public class WrestlerController {

    private final WrestlerService wrestlerService;

    @PostMapping
    public ResponseEntity<WrestlerResponse> create(@Valid @RequestBody WrestlerRequest request) {
        WrestlerResponse response = wrestlerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WrestlerResponse> update(@PathVariable UUID id,
                                                   @Valid @RequestBody WrestlerRequest request) {
        WrestlerResponse response = wrestlerService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrestlerResponse> getById(@PathVariable UUID id) {
        WrestlerResponse response = wrestlerService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WrestlerResponse>> getAll() {
        List<WrestlerResponse> responses = wrestlerService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<WrestlerResponse>> getPopular(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "promotion", required = false) Wrestler.Promotion promotion) {
        List<WrestlerResponse> responses = wrestlerService.getPopular(limit, promotion);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        wrestlerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/popularity")
    public ResponseEntity<WrestlerResponse> updatePopularity(@PathVariable UUID id,
                                                             @Valid @RequestBody PopularityUpdateRequest request) {
        WrestlerResponse response = wrestlerService.updatePopularity(id, request.getPopularityScore());
        return ResponseEntity.ok(response);
    }
}

