package com.tejora.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FileDataDto {
    private Long requestId;
    private LocalTime timeStamp;
    private String exceptionName;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    @Override
    public String toString() {
        return "FileDataDto{" +
                "requestId=" + requestId +
                ", timeStamp=" + timeStamp +
                ", exceptionName='" + exceptionName + '\'' +
                '}';
    }
}
