package dumshenko.daniil.todolist.repository;

import dumshenko.daniil.todolist.domain.model.Comment;
import dumshenko.daniil.todolist.domain.repository.CommentRepository;
import dumshenko.daniil.todolist.mapper.CommentMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dumshenko.daniil.todolist.repository.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

  private final CommentMapper commentMapper;
  private final CommentJpaRepository commentJpaRepository;

  @Autowired
  public CommentRepositoryImpl(CommentMapper commentMapper, CommentJpaRepository commentJpaRepository) {
    this.commentMapper = commentMapper;
    this.commentJpaRepository = commentJpaRepository;
  }

  @Override
  public Comment save(Comment comment) {
    CommentEntity commentEntity = commentMapper.toEntity(comment);

    CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);

    return commentMapper.toDomain(commentEntitySaved);
  }

  @Override
  public List<Comment> findAll() {
    return commentJpaRepository.findAll().stream().map(commentMapper::toDomain).toList();
  }

  @Override
  public Optional<Comment> findById(UUID commentId) {
    return commentJpaRepository.findById(commentId).map(commentMapper::toDomain);
  }

  @Override
  public Optional<Comment> findByTitle(String title) {
    return commentJpaRepository.findByTitle(title).map(commentMapper::toDomain);
  }

  @Override
  public void delete(Comment comment) {
    commentJpaRepository.deleteById(comment.getId());
  }
}
