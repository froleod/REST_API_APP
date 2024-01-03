package com.ldf.course.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE) // поля автоматически приватные
public class CatDTO {
    String name;
    int weight;
    int age;
}
