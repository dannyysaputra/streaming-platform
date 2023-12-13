package projectuas.streamingPlatform.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectuas.streamingPlatform.data.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
