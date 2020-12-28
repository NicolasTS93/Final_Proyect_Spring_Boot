package com.example.info2020.Final_Project.services;

import com.example.info2020.Final_Project.DAO.Comment;
import com.example.info2020.Final_Project.DAO.Post;
import com.example.info2020.Final_Project.DAO.User;
import com.example.info2020.Final_Project.controller.request.CreateComment;
import com.example.info2020.Final_Project.controller.request.UpdateComment;
import com.example.info2020.Final_Project.controller.response.CommentDto;
import com.example.info2020.Final_Project.repository.CommentRepository;
import com.example.info2020.Final_Project.repository.PostRepository;
import com.example.info2020.Final_Project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

   public CommentServiceImpl (UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository){
       this.userRepository = userRepository;
       this.postRepository = postRepository;
       this.commentRepository = commentRepository;
   }


    @Override
    public String createComment(CreateComment body) {
       User foundUser;
       Post foundPost;
       try{
           foundUser = userRepository.findById(body.getAuthorID()).orElseThrow();
           foundPost = postRepository.findById(body.getPostId()).orElseThrow();
       }catch (Exception e){
           System.out.println("ERROR al buscar author o post: " + e);
           return "No se encontro autor o post valido";
       }

       Comment comment = new Comment();
       comment.setAuthor(foundUser);
       comment.setPost(foundPost);
       comment.setCreationDate(LocalDate.now());
       comment.setComment(body.getComment());

       try{
           commentRepository.save(comment);
           return "El comentario fue creado con exito";
       }catch (Exception e){
           System.out.println("ERROR al momento de crear el comentario: " + e);
           return "No se pudo crear el comentario correctamente";
       }

    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
       Post foundPost;
       try{
           foundPost = postRepository.findById(postId).orElseThrow();
       }catch (Exception e){
           System.out.println("ERROR al buscar el post del comentario: "+ e);
           return List.of();
       }
       return foundPost.getComments()
               .stream()
               .map(CommentDto::new)
               .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getComments(Long postId, int limit) {
        Post foundPost;
        try{
            foundPost = postRepository.findById(postId).orElseThrow();
        }catch (Exception e){
            System.out.println("ERROR al buscar el post del comentario: "+ e);
            return List.of();
        }
        return foundPost.getComments()
                .stream()
                .sorted((comment1, comment2) -> comment1.getCreationDate().compareTo(comment2.getCreationDate()))
                .limit(limit)
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String updateComment(Long commentId, UpdateComment body) {
       var optionalComment = commentRepository.findById(commentId);
       if (!optionalComment.isPresent()){
           return "El ID del comentario que desea modificar no es valido";
       }

       var foundComment = optionalComment.get();
       foundComment.setComment(body.getComment());

        try {
            commentRepository.save(foundComment);
            return "Se modifico al comentario correctamente";
        }catch (Exception e){
            System.out.println("ERROR - al momento de modificar al comentario: " + e);
            return "Ocurrio un error mientras se trataba de modificar al comentario";
        }



    }

    @Override
    public String deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return "Se borro con exito al commentario";
        }catch (Exception e){
            System.out.println("ERROR - al momento de borrar al comentario: " + e);
            return "Ocurrio un error mientras se trataba de eliminar al comentario";
        }
    }
}
