package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.SearchService;
import com.ecsimsw.springelk.dto.SearchResponse;
import com.ecsimsw.springelk.utils.LimitedSizePagination;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @LimitedSizePagination(maxSize = 20)
    @GetMapping("/code/{name}")
    public ResponseEntity<List<SearchResponse>> searchByWord(
            @PathVariable String name,
            @PageableDefault(sort = "star", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(searchService.findCodeByWord(name, pageable));
    }

    @GetMapping("/code/count/{name}")
    public ResponseEntity<Integer> countByWord(@PathVariable String name) {
        return ResponseEntity.ok(searchService.countCodeByWord(name));
    }

    @LimitedSizePagination(maxSize = 20)
    @GetMapping("/code/class/{name}")
    public ResponseEntity<List<SearchResponse>> searchByClass(
            @PathVariable String name,
            @PageableDefault(sort = "star", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(searchService.findCodeByClassName(name, pageable));
    }

    @LimitedSizePagination(maxSize = 20)
    @GetMapping("/code/variable/{name}")
    public ResponseEntity<List<SearchResponse>> searchByVariable(
            @PathVariable String name,
            @PageableDefault(sort = "star", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(searchService.findCodeByVariableName(name, pageable));
    }

    @GetMapping("/code/count/class/{name}")
    public ResponseEntity<Integer> countByClass(@PathVariable String name) {
        return ResponseEntity.ok(searchService.countCodeByClassName(name));
    }

    @GetMapping("/code/count/variable/{name}")
    public ResponseEntity<Integer> countByVariable(@PathVariable String name) {
        return ResponseEntity.ok(searchService.countCodeByVariableName(name));
    }
}
