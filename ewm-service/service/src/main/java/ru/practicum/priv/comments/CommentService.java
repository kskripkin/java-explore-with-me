package ru.practicum.priv.comments;

import ru.practicum.model.comment.Comment;

public interface CommentService {

    Comment addComment(Long userId,
                       Long eventId,
                       Comment comment);

    Comment editComment(Long userId,
                        Long eventId,
                        Long commentId,
                        Comment comment);
}
