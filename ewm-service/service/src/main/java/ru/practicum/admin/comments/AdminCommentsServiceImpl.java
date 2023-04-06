package ru.practicum.admin.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.comment.Comment;
import ru.practicum.pub.comments.CommentsRepository;
import ru.practicum.validation.ValidateEvents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommentsServiceImpl implements AdminCommentsService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime DEFAULT_TIME_START_SEARCH = LocalDateTime.parse("1900-01-01 00:00:00", formatter);

    private final LocalDateTime DEFAULT_TIME_END_SEARCH = LocalDateTime.parse("2200-01-01 00:00:00", formatter);

    private final CommentsRepository commentsRepository;

    private final ValidateEvents validateEvents;

    private LocalDateTime localDateTimeStartRange;

    private LocalDateTime localDateTimeEndRange;

    @Override
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> getComments(String text, Long authorId, Long eventId, String rangeStart, String rangeEnd, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        if (rangeStart != null) {
            localDateTimeStartRange = LocalDateTime.parse(rangeStart, formatter);
        } else {
            localDateTimeStartRange = DEFAULT_TIME_START_SEARCH;
        }
        if (rangeStart != null) {
            localDateTimeEndRange = LocalDateTime.parse(rangeEnd, formatter);
        } else {
            localDateTimeEndRange = DEFAULT_TIME_END_SEARCH;
        }
        if (text == null) {
            text = "";
        }
        if (authorId == null && eventId != null) {
            return commentsRepository.findByEventId(text, eventId, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size));
        }
        if (eventId == null && authorId != null) {
            return commentsRepository.findByAuthorId(text, authorId, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size));
        }
        return commentsRepository.findByEventIdAndAuthorId(text, authorId, eventId, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size));
    }
}
