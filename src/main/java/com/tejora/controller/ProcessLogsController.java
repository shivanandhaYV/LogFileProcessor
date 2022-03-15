package com.tejora.controller;

import com.tejora.dto.ProcessLogsRequestDto;
import com.tejora.dto.ProcessLogsResponseDto;
import com.tejora.service.ProcessLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/process-logs")
@Validated
public class ProcessLogsController {

    @Autowired
    private ProcessLogsService service;

    @PostMapping
    public ResponseEntity<ProcessLogsResponseDto> processLogs(@Valid @RequestBody ProcessLogsRequestDto request){
        return ResponseEntity.ok().body(service.processLogs(request));
    }


}
