package ru.practicum.priv.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CustomerMapper;
import ru.practicum.model.comment.Comment;
import ru.practicum.pub.comments.CommentsRepository;
import ru.practicum.validation.ValidateComments;
import ru.practicum.validation.ValidateEvents;
import ru.practicum.validation.ValidateUsers;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository commentsRepository;

    private final ValidateEvents validateEvents;

    private final ValidateUsers validateUsers;

    private final ValidateComments validateComments;

    private final CustomerMapper customerMapper;

    @Override
    public Comment addComment(Long userId, Long eventId, Comment comment) {
        validateUsers.findUser(userId);
        validateEvents.findEvent(eventId);
        validateComments.validateObject(comment);
        comment.setCreated(LocalDateTime.now());
        comment.setAuthorId(userId);
        comment.setEventId(eventId);
        return commentsRepository.save(comment);
    }

    @Override
    public Comment editComment(Long userId, Long eventId, Long commentId, Comment comment) {
        validateUsers.findUser(userId);
        validateEvents.findEvent(eventId);
        validateComments.findComment(commentId);
        validateComments.validateObject(comment);
        Comment commentSource = commentsRepository.getById(commentId);
        Comment commentEdit = customerMapper.updateComment(commentSource, comment);
        return commentsRepository.save(commentEdit);
    }
}
