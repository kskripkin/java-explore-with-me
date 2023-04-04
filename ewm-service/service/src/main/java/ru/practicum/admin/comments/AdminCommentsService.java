package ru.practicum.admin.comments;

import ru.practicum.model.comment.Comment;

import java.util.List;

public interface AdminCommentsService {

    void deleteComment(Long commentId);

    List<Comment> getComments(String text,
                              Long authorId,
                              Long eventId,
                              String rangeStart,
                              String rangeEnd,
                              Integer from,
                              Integer size);
}
