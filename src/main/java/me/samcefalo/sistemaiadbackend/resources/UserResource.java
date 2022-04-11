package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.mappers.UserMapper;
import me.samcefalo.sistemaiadbackend.models.User;
import me.samcefalo.sistemaiadbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/tecnicos")
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> find(@PathVariable int id) {
        User user = userService.find(id);
        return ResponseEntity.ok().body(userMapper.mapToDTO(user, UserDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(userService.findPage(page, linesPerPage, orderBy, direction)
                        .map(user -> userMapper.mapToDTO(user, UserDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserInsertDTO userInsertDTO) {
        User user = userMapper.mapToModel(userInsertDTO, User.class);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UserInsertDTO userInsertDTO, @PathVariable int id) {
        User user = userMapper.mapToModel(userInsertDTO, User.class);
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}