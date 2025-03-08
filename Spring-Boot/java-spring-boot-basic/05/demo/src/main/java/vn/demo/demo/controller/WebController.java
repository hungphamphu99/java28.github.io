package vn.demo.demo.controller;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import vn.demo.demo.model.PageResponse;
import vn.demo.demo.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class WebController {
    private List<Person> people = new ArrayList<>();

    public WebController() {
        Faker faker = new Faker();
        Random rd = new Random();
        for (int i = 0; i < 55; i++) {
            Person person = new Person(
                    i + 1,
                    faker.name().fullName(),
                    rd.nextInt(2) == 1 ? "M" : "F",
                    faker.number().numberBetween(18, 60)
            );
            people.add(person);
        }
    }


    @GetMapping("/")
    public String getHome(Model model,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false, defaultValue = "1") int page) {
        List<Person> peopleFound = new ArrayList<>();
        if (keyword != null) {
            peopleFound = people.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        } else {
            peopleFound = people;
        }

        PageResponse<Person> pageResponse = new PageResponse<>(peopleFound, 10, page);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("people", peopleFound);
        model.addAttribute("person", people.get(0));

        model.addAttribute("name", "Nguyen Van A");
        model.addAttribute("salary", 1000);
        return "index";
    }

    @GetMapping("/person/{id}") // http://localhost:8080/person/1
    public String getPerson(Model model, @PathVariable int id) {
        Person person = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (person != null) {
            List<Person> relatedPeople = people.stream()
                    .filter(p -> p.getId() != person.getId() && p.getGender().equals(person.getGender()))
                    .sorted((p1, p2) -> Integer.compare(p2.getId(), p1.getId())) // Sắp xếp giảm dần theo ID
                    .limit(4) // Giới hạn 4
                    .toList();

            model.addAttribute("relatedPeople", relatedPeople);
        }

        model.addAttribute("person", person);
        return "person";
    }

}