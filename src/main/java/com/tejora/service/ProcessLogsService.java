package com.tejora.service;

import com.tejora.dto.ProcessLogsRequestDto;
import com.tejora.dto.ProcessLogsResponseDto;

public interface ProcessLogsService {
    ProcessLogsResponseDto processLogs(ProcessLogsRequestDto request);
}
