package org.example.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.scene.control.ListView;
import org.example.hibernateController.GenericHibernate;

import java.util.Arrays;
import java.util.List;

public class DataPopulator {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("courseworkKD2");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);

    public void fillTableAll(ListView table, Class<?> entityClass) {
        table.getItems().clear();
        List<?> itemList = hibernate.getAll(entityClass);
        table.getItems().addAll(itemList);
    }
    public <E extends Enum<E>> void fillTableWithEnums(ListView table, Class <E> enumType){
        table.getItems().clear();
        List<E> itemsList = Arrays.asList(enumType.getEnumConstants());
        table.getItems().addAll(itemsList);
    }
}
