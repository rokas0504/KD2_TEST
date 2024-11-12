package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.enums.WeekDays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Converter
public class WeekDayConverter implements AttributeConverter<List<WeekDays>, String> {

    @Override
    public String convertToDatabaseColumn(List<WeekDays> attribute) {
        if(attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream().map(WeekDays::name).collect(Collectors.joining(","));
    }

    @Override
    public List<WeekDays> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(",")).map(WeekDays::valueOf).collect(Collectors.toList());
    }
}
