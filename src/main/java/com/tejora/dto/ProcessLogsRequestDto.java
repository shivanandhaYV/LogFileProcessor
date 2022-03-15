package com.tejora.dto;


import com.tejora.exception.QuestionNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ProcessLogsRequestDto {


    @NotNull(message = "logFiles Required")
    private List<String> logFiles;
    @NotNull(message = "parallelFileProcessingCount Required")
    private Integer parallelFileProcessingCount;


    public List<String> getLogFiles() {
        return logFiles;
    }

    public void setLogFiles(List<String> logFiles) {
        this.logFiles = logFiles;
    }

    public Integer getParallelFileProcessingCount() {
        return parallelFileProcessingCount;
    }

    public void setParallelFileProcessingCount(Integer parallelFileProcessingCount) {
        if(parallelFileProcessingCount != null && parallelFileProcessingCount <= 0 ){
            throw new QuestionNotFoundException("Parallel File Processing count must be greater than zero!");
        }

        this.parallelFileProcessingCount = parallelFileProcessingCount;
    }
}
