package org.example.utils;

import lombok.Getter;
import lombok.Setter;
import org.example.Darbuotojas;

@Getter
@Setter
public class DataTransfer {
    private final static DataTransfer instance = new DataTransfer();

    private String text;
    private Darbuotojas selectedDarbuotojas;
    public static DataTransfer getInstance() {
        return instance;
    }
}
