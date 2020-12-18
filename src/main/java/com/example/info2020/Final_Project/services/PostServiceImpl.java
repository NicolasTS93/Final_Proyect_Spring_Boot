package com.example.info2020.Final_Project.services;

import com.example.info2020.Final_Project.DAO.Post;
import com.example.info2020.Final_Project.DAO.User;
import com.example.info2020.Final_Project.controller.request.CreatePost;
import com.example.info2020.Final_Project.controller.request.UpdatePost;
import com.example.info2020.Final_Project.controller.response.PostDto;
import com.example.info2020.Final_Project.repository.PostRepository;
import com.example.info2020.Final_Project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String createPost(CreatePost body) {
        User foundUser = new User();
        try {
            foundUser =  userRepository.findById(body.getAuthorId()).orElseThrow();

        }catch (Exception e){
            System.out.println("Error al buscar el autor: " + e);
            return "No se pudo crear el Post porque el id del Usuario no es valido";
        }
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setContent(body.getContent());
        post.setCreationDate(LocalDate.now());
        post.setPublished(body.isPublished());
        post.setAuthor(foundUser);

        try {
            postRepository.save(post);
            return "Post creado con exito";
        }catch (Exception e){
            System.out.println("Error al publicar el Post: " + e);
            return "No se pudo crear el Post";
        }
    }

    @Override
    public List<PostDto> getPots() {
        return postRepository.findAll().stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPotsByContainKeyWord(String keYWord) {
        return postRepository.findAll().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(keYWord.toLowerCase()))
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPotsIsNotPublished(boolean notPublished) {
        return postRepository.findAll().stream()
                .filter(post -> notPublished != post.isPublished() )
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPotsFilterBy(String keYWord, boolean notPublished) {
        return postRepository.findAll().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(keYWord.toLowerCase())&&
                        notPublished != post.isPublished())
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String updatePost(Long postId, UpdatePost body) {
        var optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent()){
            return "El ID del post que desea modificar no es valido";
        }

        var foundPost = optionalPost.get();
        foundPost.setTitle(body.getTitle());
        foundPost.setDescription(body.getDescription());
        foundPost.setContent(body.getContent());
        foundPost.setPublished(body.isPublished());

        try {
            postRepository.save(foundPost);
            return "Se modifico al post correctamente";
        }catch (Exception e){
            System.out.println("Error a la hora de modificar al post: " + e);
            return "Ocurrio un error mientras se trataba de editar al post";
        }

    }

    @Override
    public String deletePost(Long postId) {
        try {
            postRepository.deleteById(postId);
            return "Se borro con exito al post";
        }catch (Exception e){
            System.out.println("Error al momento de borrar el post: " + e);
            return "Ocurrio un error mientras se trataba de eliminar al post";
        }
    }
}
