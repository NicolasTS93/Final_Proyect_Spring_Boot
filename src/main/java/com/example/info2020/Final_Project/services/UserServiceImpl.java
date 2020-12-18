package com.example.info2020.Final_Project.services;
import com.example.info2020.Final_Project.DAO.User;
import com.example.info2020.Final_Project.controller.request.CreateUser;
import com.example.info2020.Final_Project.controller.request.UpdateUser;
import com.example.info2020.Final_Project.controller.response.UserDto;
import com.example.info2020.Final_Project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public String createUser(CreateUser body) {
        User user = new User();
        user.setName(body.getName());
        user.setLastname(body.getLastname());
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        user.setCreationDate(LocalDate.now());
        user.setCity(body.getCity());
        user.setState(body.getState());
        user.setCountry(body.getCountry());

        try {
            userRepository.save(user);
            return "Usuario guardado con exito";
        }catch(RuntimeException e){
            System.out.println("El error fue: " + e);
            return "Problema guardando el usuario";
        }

    }

    @Override
    public List<UserDto> getUsersCreatedAfterOf(LocalDate localDate) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCreationDate().isAfter(localDate))
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersFilteredBy(LocalDate creationDate, String city) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCreationDate().isAfter(creationDate) && user.getCity().equalsIgnoreCase(city))
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersFromCity(String city) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCity().equalsIgnoreCase(city))
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String updateUser(Long userId, UpdateUser body) {
        var optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            return "El ID de usuario ingresado no es valido";
        }

        var foundUser = optionalUser.get();
        foundUser.setName(body.getName());
        foundUser.setLastname(body.getLastname());
        foundUser.setEmail(body.getEmail());
        foundUser.setPassword(body.getPassword());
        foundUser.setCity(body.getCity());
        foundUser.setState(body.getState());
        foundUser.setCountry(body.getCountry());
        try {
            userRepository.save(foundUser);
            return "Se modifico al usuario correctamente";
        }catch (Exception e){
            System.out.println("Ocurrio un error cuando se editaba al usuario: " + e);
            return "Ocurrio un error mientras se trataba de editar al usuario";
        }

    }

    @Override
    public String deleteUser(Long userId) {
       try {
           userRepository.deleteById(userId);
           return "Se borro con exito al usuario";
       }catch (Exception e){
           System.out.println("Ocurrio un error al intentar borrar al usuario: " + e);
           return "No se pudo borrar al usuario";
       }
    }


}
