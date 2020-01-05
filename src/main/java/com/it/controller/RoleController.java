package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.RoleResponseDto;
import com.it.model.Role;
import com.it.service.RoleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> readAll() {
        final List<Role> entity = roleRepository.findAll();

        final List<RoleResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, RoleResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleResponseDto> read(@PathVariable Long id) {
        Role entity = roleRepository.findById(id);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) throws Exception {
        final Role entity = mapper.map(requestDto, Role.class);
        roleRepository.save(entity);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) throws Exception {
        Role entity = roleRepository.findById(id);
        roleRepository.update(entity);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }

}
