package ru.practicum.admin.comments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.comment.Comment;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/comments")
public class AdminCommentsController {

    private final AdminCommentsService adminCommentsService;

    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) {
        log.info("DELETE /admin/comments/comment/{}", commentId);
        adminCommentsService.deleteComment(commentId);
    }

    @GetMapping
    public List<Comment> getComments(@RequestParam(value= "text", required = false) String text,
                                       @RequestParam(value= "authorId", required = false) Long authorId,
                                       @RequestParam(value= "eventId", required = false) Long eventId,
                                       @RequestParam(value= "rangeStart", required = false) String rangeStart,
                                       @RequestParam(value= "rangeEnd", required = false) String rangeEnd,
                                       @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                       @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /admin/comments?text={}&authorId={}&eventId={}&rangeStart={}&rangeEnd={}&from={}&size={}",
                text, authorId, eventId, rangeStart, rangeEnd, from, size);
        return adminCommentsService.getComments(text, authorId, eventId, rangeStart, rangeEnd, from, size);
    }


}
