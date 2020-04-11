package hu.callcenter.domain.service;

import hu.callcenter.domain.model.CallLog;
import hu.callcenter.domain.model.LogTime;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    public static final String SEPARATOR = " ";

    private int id;
    private LogTime lastCallEnd = new LogTime(8, 0, 0);

    public List<CallLog> parse(List<String> lines) {
        return lines.stream()
                .map(this::createCallLog)
                .collect(Collectors.toList());
    }

    public LogTime parseTime(String line) {
        String[] items = line.split(SEPARATOR);
        int hour = getValue(items[0]);
        int minute = getValue(items[1]);
        int second = getValue(items[2]);
        return new LogTime(hour, minute, second);
    }

    private CallLog createCallLog(String line) {
        String[] items = line.split(SEPARATOR);
        LogTime callBegin = parseTime(String.join(SEPARATOR, items[0], items[1], items[2]));
        LogTime callEnd = parseTime(String.join(SEPARATOR, items[3], items[4], items[5]));
        LogTime speakBegin = null;
        if (lastCallEnd.isAvailable(callBegin, callEnd)) {
            speakBegin = LogTime.isBefore(lastCallEnd, callBegin) ? callBegin : lastCallEnd;
            lastCallEnd = callEnd;
        }
        return new CallLog(++id, callBegin, speakBegin, callEnd);
    }

    private int getValue(String text) {
        return Integer.parseInt(text);
    }
}
