package vn.demo.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.demo.hw.model.Person;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WebController {
    private List<Person> people = new ArrayList<>(List.of(
            new Person(1, "Nguyen Van A", "M", 20, "Developer", "Hanoi", 1000),
            new Person(2, "Tran Van B", "M", 4, "Doctor", "HCM", 3000),
            new Person(3, "Ngo Thi C", "F", 9, "Teacher", "Hanoi", 2000),
            new Person(4, "Nguyen Thi D", "F", 18, "Nurse", "Da Nang", 1500),
            new Person(5, "Nguyen Van E", "M", 24, "Developer", "HCM", 2500)
    ));

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("people", people);
        return "index";
    }

    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, List<Person>> groupedByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));

        if (groupedByCity.isEmpty()) {
            model.addAttribute("errorMessage", "Không có dữ liệu thành phố!");
        }

        model.addAttribute("peopleByCity", groupedByCity);
        return "groupPeopleByCity";
    }


    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCounts = people.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));

        model.addAttribute("jobCounts", jobCounts);
        return "groupJobByCount";
    }

    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        double avgSalary = people.stream().mapToDouble(Person::getSalary).average().orElse(0);
        List<Person> highEarners = people.stream()
                .filter(p -> p.getSalary() > avgSalary)
                .collect(Collectors.toList());

        model.addAttribute("aboveAverageSalaryPeople", highEarners);
        return "aboveAverageSalary";
    }

    @GetMapping("/longestName")
    public String longestName(Model model) {
        Person longestNamePerson = people.stream()
                .max(Comparator.comparingInt(p -> p.getName().length()))
                .orElse(null);

        model.addAttribute("longestName", longestNamePerson);
        return "longestName";
    }
}
