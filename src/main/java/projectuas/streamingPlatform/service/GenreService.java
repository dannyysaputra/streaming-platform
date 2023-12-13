package projectuas.streamingPlatform.service;

import projectuas.streamingPlatform.data.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    Genre getGenreById(long id);
}
