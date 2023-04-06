package ru.practicum.pub.comments;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * " +
            "from comments " +
            "where event_id = ?1 ", nativeQuery = true)
    List<Comment> getByEventId(long eventId, Pageable pageable);

    @Query(value = "select * " +
            "from comments " +
            "where text ilike %?1% and author_id = ?2 and created >= ?3 and created <= ?4 ", nativeQuery = true)
    List<Comment> findByAuthorId(String text, Long authorId, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from comments " +
            "where text ilike %?1% and event_id = ?2 and created >= ?3 and created <= ?4 ", nativeQuery = true)
    List<Comment> findByEventId(String text, Long eventId, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from comments " +
            "where text ilike %?1% and author_id = ?2 and event_id = ?3 and created >= ?4 and created <= ?5 ", nativeQuery = true)
    List<Comment> findByEventIdAndAuthorId(String text, Long authorId, Long eventId, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);
}
