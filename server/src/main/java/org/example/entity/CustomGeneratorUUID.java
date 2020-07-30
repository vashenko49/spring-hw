package org.example.entity;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.util.UUID;

public class CustomGeneratorUUID implements ValueGenerator<String> {
    @Override
    public String generateValue(Session session, Object o) {
        return UUID.randomUUID().toString();
    }
}
