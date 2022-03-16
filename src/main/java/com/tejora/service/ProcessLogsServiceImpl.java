package com.tejora.service;

import com.tejora.dto.FileDataDto;
import com.tejora.dto.ProcessLogsRequestDto;
import com.tejora.dto.ProcessLogsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProcessLogsServiceImpl implements ProcessLogsService {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProcessLogsResponseDto processLogs(ProcessLogsRequestDto request) {

        List<FileDataDto> logFileData = request
                .getLogFiles()
                .parallelStream()
                .limit(request.getParallelFileProcessingCount())
                .map(data -> {
                    String[] rowData = data.split(" ");
                    FileDataDto fileDataDto = new FileDataDto();
                    fileDataDto.setExceptionName(rowData[2]);
                    fileDataDto.setRequestId(Long.valueOf(rowData[0]));
                    LocalTime timeStamp = convertDateTIme(Long.valueOf(rowData[1]));
                    fileDataDto.setTimeStamp(LocalTime.of(timeStamp.getHour(), floorTheMints(timeStamp.getMinute())));
                    return fileDataDto;
                }).collect(Collectors.toList());





        System.out.println("filesData = " + logFileData);
        return null;
    }


    private List<String> processFile(String fileUrl) {
        List<String> fileData = null;
        try {
            File file = restTemplate.execute(fileUrl, HttpMethod.GET, null, clientHttpResponse -> {
                File ret = File.createTempFile("download", "tmp");
                StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
                return ret;
            });
            fileData = Files.readAllLines(file.toPath());
        } catch (Exception ex) {

        }
        return fileData;

    }

    private LocalTime convertDateTIme(Long seconds) {
        Instant instant = Instant.ofEpochSecond(seconds);
        ZonedDateTime zdtUtc = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        ZonedDateTime zdtMontréal = zdtUtc.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        return zdtMontréal.toLocalTime();
    }


    private List<LocalTime> dayHours() {
        return IntStream.range(1, 24).mapToObj(hour -> LocalTime.of(hour, 0)).collect(Collectors.toList());
    }


    private int floorTheMints(int mints) {
        int timeFlooring = 15;
        int floorMints = mints;
        if (mints > 0 && mints <= 60) {
            floorMints = (mints / timeFlooring) * timeFlooring;
        }
        return floorMints;
    }

    public static boolean dateBetween(LocalTime start, LocalTime end, LocalTime time) {
        if (start.isAfter(end)) {
            return !time.isBefore(start) || !time.isAfter(end);
        } else {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }
}
