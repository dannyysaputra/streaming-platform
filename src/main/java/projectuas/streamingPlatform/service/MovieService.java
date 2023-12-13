package projectuas.streamingPlatform.service;

import projectuas.streamingPlatform.data.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(Long movie_id);

    Movie pushMovie(Movie newMovie, Long genreId);

    Movie updateMovie(Movie updatedMovie, Long movie_id);

    void deleteMovie(Long movie_id);

    List<Movie> getByMovieName(String keyword);

    List<Movie> getByMovieNameAsc();
    List<Movie> getByMovieNameDesc();
    List<Movie> getByRatingAsc();
    List<Movie> getByRatingDesc();

}
