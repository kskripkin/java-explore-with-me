package ru.practicum.pub.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.comment.Comment;
import ru.practicum.validation.ValidateEvents;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServicePublicImpl implements CommentsServicePublic {

    private final CommentsRepository commentsRepository;

    private final ValidateEvents validateEvents;

    @Override
    public List<Comment> getCommentsByEvent(long eventId, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        validateEvents.validateFindEvent(eventId);
        return commentsRepository.getByEventId(eventId, PageRequest.of((from / size), size));
    }
}
