package projectuas.streamingPlatform.service.impl;

import org.springframework.stereotype.Service;
import projectuas.streamingPlatform.data.entity.Movie;
import projectuas.streamingPlatform.data.repository.MovieRepository;
import projectuas.streamingPlatform.service.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long movie_id) {
        return movieRepository.findById(movie_id).orElse(null);
    }

    @Override
    public Movie pushMovie(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie updateMovie(Movie updatedMovie, Long movie_id) {
        return movieRepository.findById(movie_id).map(movie -> {
            movie.setMovieName(updatedMovie.getMovieName());
            movie.setMovieTags(updatedMovie.getMovieTags());
            movie.setMovieBackdropUrl(updatedMovie.getMovieBackdropUrl());
            movie.setMoviePosterUrl(updatedMovie.getMoviePosterUrl());
            movie.setGenre(updatedMovie.getGenre());
            movie.setYear(updatedMovie.getYear());
            movie.setDurationInMinute(updatedMovie.getDurationInMinute());
            movie.setTrailerLink(updatedMovie.getTrailerLink());
            movie.setDescription(updatedMovie.getDescription());
            return movieRepository.save(movie);
        }).orElse(null);
    }

    @Override
    public void deleteMovie(Long movie_id) {

    }
}
