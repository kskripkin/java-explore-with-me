package ru.practicum.pub.comments;

import ru.practicum.model.comment.Comment;

import java.util.List;

public interface CommentsServicePublic {

    List<Comment> getCommentsByEvent(long eventId,
                                     Integer from,
                                     Integer size);

}
