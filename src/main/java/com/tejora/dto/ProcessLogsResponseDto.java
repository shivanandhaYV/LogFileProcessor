package com.tejora.dto;

import java.util.List;

public class ProcessLogsResponseDto {
    private List<ProcessLogsDto> response;


    public List<ProcessLogsDto> getResponse() {
        return response;
    }

    public void setResponse(List<ProcessLogsDto> response) {
        this.response = response;
    }
}
