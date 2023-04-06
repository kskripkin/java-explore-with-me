package ru.practicum.pub.comments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.comment.Comment;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments/event")
public class CommentsControllerPublic {

    private final CommentsServicePublic commentsServicePublic;

    @GetMapping("/{eventId}")
    public List<Comment> getCategoriesOne(@PathVariable long eventId,
                                          @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                          @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /comments/event/{}", eventId);
        return commentsServicePublic.getCommentsByEvent(eventId, from, size);
    }
}
