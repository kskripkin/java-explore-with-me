package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.model.comment.Comment;
import ru.practicum.pub.comments.CommentsRepository;

@RequiredArgsConstructor
@Component
public class ValidateComments {

    private final CommentsRepository commentsRepository;

    public void findComment(long commentId) {
        if(commentsRepository.findById(commentId).isEmpty()) {
            throw new NotFoundException("Comment not found");
        }
    }

    public void validateObject(Comment comment) {
        if (comment.getText() == null || comment.getText().equals("")) {
            throw new ValidationException("Comment not valid. Text cannot be empty or null");
        }
    }
}
