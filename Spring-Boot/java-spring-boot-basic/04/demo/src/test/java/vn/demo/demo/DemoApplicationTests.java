package vn.demo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10);

    // Get even numbers
    Stream<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0);

    // Get numbers greater than 5
    Stream<Integer> getNumbersThanFive = numbers.stream().filter(n -> n > 5);

    // Get largest number
    Stream<Integer> getLargestNumber = numbers.stream().max(Integer::compareTo).stream();

    // Sum of all numbers
    int sumOfAllNumbers = numbers.stream().mapToInt(Integer::intValue).sum();

    Stream<Integer> sumAsStream = Stream.of(sumOfAllNumbers);


}
