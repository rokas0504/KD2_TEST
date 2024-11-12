package org.example.enums;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public enum WeekDays implements HasSelectedProperty{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);

    @Override
    public BooleanProperty selectedProperty() {return selectedProperty;}
    public void setSelected(boolean selected) {this.selectedProperty.set(selected);}
    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
