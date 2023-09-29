package com.mitocode.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.VitalSignsDTO;
import com.mitocode.model.VitalSigns;
import com.mitocode.service.IVitalSignsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vitalsigns")
@RequiredArgsConstructor
public class VitalSignsController {

    private final IVitalSignsService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VitalSignsDTO>> findAll() {
        List<VitalSignsDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> findById(@PathVariable("id") Integer id) {
        VitalSignsDTO dto = this.convertToDto(service.findById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VitalSignsDTO> save(@Valid @RequestBody VitalSignsDTO dto) {
        VitalSigns obj = service.save(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdVitalSigns())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody VitalSignsDTO dto) {
        dto.setIdVitalSigns(id);
        VitalSigns obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(1) @PathVariable("id") Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private VitalSignsDTO convertToDto(VitalSigns obj) {
        return mapper.map(obj, VitalSignsDTO.class);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<VitalSignsDTO>> listPage(Pageable pageable) {
        Page<VitalSignsDTO> page = service.listPage(pageable).map(p -> mapper.map(p, VitalSignsDTO.class));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    private VitalSigns convertToEntity(VitalSignsDTO dto) {
        return mapper.map(dto, VitalSigns.class);
    }
}
