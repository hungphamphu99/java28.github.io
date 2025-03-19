package vn.demo.demo;



import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.repository.MovieRepository;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_movies() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        for (int i = 0; i < 150; i++) {
            // Tao entity
            String name = faker.funnyName().name();
            String thumbnail = "https://placehold.co/600x400?text=" + name.substring(0, 1).toUpperCase();
            Boolean status = faker.bool().bool();

            int rdIndexType = rd.nextInt(MovieType.values().length);
            MovieType type = MovieType.values()[rdIndexType];

            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .thumbnail(thumbnail)
                    .releaseYear(faker.number().numberBetween(1990, 2021))
                    .trailer("https://www.youtube.com/embed/W_0AMP9yO1w?si=JcCeGorHalCHKCPl")
                    .status(status)
                    .rating(faker.number().randomDouble(1, 5, 10))
                    .type(type)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();

            // Luu vao DB
            movieRepository.save(movie);
        }
    }



}