package com.codegym.formatter;

import com.codegym.model.Status;
import com.codegym.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StatusFormatter implements Formatter<Status> {
    private StatusService statusService;

    @Autowired
    public StatusFormatter(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    public Status parse(String text, Locale locale) throws ParseException {
        return statusService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Status object, Locale locale) {
        return "[" + object.getId() + ", " +object.getStatus() + "]";
    }
}
