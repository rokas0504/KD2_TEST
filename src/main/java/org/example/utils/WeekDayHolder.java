package org.example.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeekDayHolder {
    private List<Object> selectedWeekDays = new ArrayList<>();
    private final static WeekDayHolder instance = new WeekDayHolder();

    public static WeekDayHolder getInstance() {
        return instance;
    }
}
