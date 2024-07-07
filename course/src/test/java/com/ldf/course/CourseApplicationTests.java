package com.ldf.course;

import com.google.gson.Gson;
import com.ldf.course.DTO.CatDTO;
import com.ldf.course.entity.Cat;
import com.ldf.course.repository.CatRepo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
class CourseApplicationTests {
    @LocalServerPort
    private Integer port;

    @Autowired
    private CatRepo catRepo;

	@AfterEach
	void tearDown() {
		catRepo.deleteAll();
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		RestAssured.useRelaxedHTTPSValidation();
		Cat cat = new Cat();
		cat.setName("Test Cat");
		cat.setAge(3);
		cat.setWeight(5);
		catRepo.save(cat);
	}

    @Test
    void catCreateTest() {

		System.out.println(catRepo.findAll());
		var cat = catRepo.findByName("Test Cat");
        Response r = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api?id=" + cat.getId());

        Cat fetchedCat = r.as(Cat.class);
        Assertions.assertEquals("Test Cat", fetchedCat.getName());
        Assertions.assertEquals(3, fetchedCat.getAge());
        Assertions.assertEquals(5, fetchedCat.getWeight());
    }
}
