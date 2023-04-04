package ru.practicum.priv.comments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.comment.Comment;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments/user")
public class CommentsController {

    private final CommentService commentService;

    @PostMapping("/{userId}/event/{eventId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Comment addComment(@PathVariable Long userId,
                              @PathVariable Long eventId,
                              @RequestBody Comment comment) {
        log.info("POST /comments/event/{}/event/{} {}", userId, eventId, comment);
        return commentService.addComment(userId, eventId, comment);
    }

    @PatchMapping("/{userId}/event/{eventId}/comment/{commentId}")
    public Comment editComment(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @PathVariable Long commentId,
                               @RequestBody Comment comment) {
        log.info("PATCH /comments/user/{}/event/{}/comment/{} {}", userId, eventId, commentId, comment);
        return commentService.editComment(userId, eventId, commentId, comment);
    }
}
