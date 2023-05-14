package com.transfer.money.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class LoggingServiceImpl implements LoggingService {

    @Value("${ file:\"log.csv\" }")
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
    public synchronized void log(LogInfo logInfo) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        StringBuilder builder = new StringBuilder();
        builder.append(date).append(";");
        builder.append(time).append(";");
        builder.append(logInfo.cardFrom()).append(";");;
        builder.append(logInfo.cardTo()).append(";");
        builder.append(logInfo.sum()).append(";");
        builder.append(logInfo.commission()).append(";");
        builder.append(logInfo.result()).append("\n");
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
        System.out.println("CardFrom:" + logInfo.cardFrom());
        System.out.println("CardTo:" + logInfo.cardTo());
        System.out.println("Sum:" + logInfo.sum());
        System.out.println("Commission:" + logInfo.commission());
        System.out.println("Result:" + logInfo.result());
    }
}
