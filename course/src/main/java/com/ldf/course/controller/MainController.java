package com.ldf.course.controller;

import com.ldf.course.DTO.CatDTO;
import com.ldf.course.entity.Cat;
import com.ldf.course.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j // lombok
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @Operation(summary = "Добавляет кота в базу данных", description = "Метод в качестве параметра получает DTO кота и " +
            "собирает и сохраняет сущность в базу данных")
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info("New row: " + catRepo.save(Cat.builder()
                .age(catDTO.getAge())
                .weight(catDTO.getWeight())
                .name(catDTO.getName())
                .build()));
    }
    @Operation(summary = "Получение списка котов", description = "Метод достает список всех котов из " +
            "базы данных")
    @SneakyThrows // для обработки исключений
    @GetMapping("/api/all")
    public List<Cat> getAllCats() {
        return catRepo.findAll();
    }

    @Operation(summary = "Получение одного кота", description = "Метод в качестве параметра получает id кота и " +
            "выводит его в качестве json объекта или, при отсутствии кота с таким id, вызывает исключение")
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(summary = "Удаление кота", description = "Метод в качестве параметра получает id кота и " +
            "удаляет его из базы данных")
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }


    @Operation(summary = "Изменение параметров кота", description = "Метод в качестве параметра получает кота и " +
            "если его не существует, то создает новую запись в БД, иначе изменяет его поля")
    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such cat :/";
        }
        return catRepo.save(cat).toString();
    }


}
