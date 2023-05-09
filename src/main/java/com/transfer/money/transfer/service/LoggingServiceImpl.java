package com.transfer.money.transfer.service;

import com.transfer.money.domain.entity.log.LogInfo;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class LoggingServiceImpl implements LoggingService {

    private String file = "log.csv";

    LoggingServiceImpl() {
        StringBuilder builder = new StringBuilder();
        builder.append("App run:\n");
        builder.append("Date").append(";");
        builder.append("Time").append(";");
        builder.append("CardFrom").append(";");
        builder.append("CardTo").append(";");
        builder.append("Sum").append(";");
        builder.append("Commission").append(";");
        builder.append("Result");
        writeToFile(builder.toString());
    }

    @Override
    public void log(LogInfo logInfo) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        StringBuilder builder = new StringBuilder();
        builder.append(date).append(";");
        builder.append(time).append(";");
        builder.append(logInfo.getCardFrom()).append(";");;
        builder.append(logInfo.getCardTo()).append(";");
        builder.append(logInfo.getSum()).append(";");
        builder.append(logInfo.getCommission()).append(";");
        builder.append(logInfo.getResult()).append(";");
        //Запись в файл
        writeToFile(builder.toString());
        //Вывод в консоль
        view(logInfo, date, time);
    }

    private void writeToFile(String text) {
        try(FileWriter writer = new FileWriter(file, true))
        {
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private void view(LogInfo logInfo, LocalDate date, LocalTime time) {
        System.out.println("Date:" + date);
        System.out.println("Time:" + time);
        System.out.println("CardFrom:" + logInfo.getCardFrom());
        System.out.println("CardTo:" + logInfo.getCardTo());
        System.out.println("Sum:" + logInfo.getSum());
        System.out.println("Commission:" + logInfo.getCommission());
        System.out.println("Result:" + logInfo.getResult());
    }
}
