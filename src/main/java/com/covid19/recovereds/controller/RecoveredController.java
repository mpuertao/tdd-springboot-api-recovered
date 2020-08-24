package com.covid19.recovereds.controller;


import com.covid19.recovereds.entity.Recovered;
import com.covid19.recovereds.service.RecoveredService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/recovered")
@CrossOrigin(origins = "http://localhost:4600")
public class RecoveredController {

    @Autowired
    private RecoveredService recoveredService;

    @GetMapping
    private ResponseEntity<List<Recovered>> retrieveAllRecovereds() {
        return ok(recoveredService.retrieveAllrecovereds());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Recovered> retrieveRecoveredById(@PathVariable int id) {
        return ok(recoveredService.retrieveRecoveredById(id));
    }

    @PostMapping
    public ResponseEntity<Recovered> createRecovered(@RequestBody Recovered recovered) {
        if (recoveredService.existsById(recovered.getId()))
            return new ResponseEntity("Id ya existe, favor validar", HttpStatus.NOT_FOUND);
        if (recovered.getId() == 0 || recovered.getName().isEmpty() || recovered.getAge() == 0)
            return new ResponseEntity("Error campos vacios, favor verificar", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(recoveredService.createRecovered(recovered), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recovered> updateRecovered(@PathVariable("id") int id, @RequestBody Recovered recovered) {
        if (!recoveredService.existsById(recovered.getId()) || !recoveredService.existsById(id))
            return new ResponseEntity("Id no existe, favor validar", HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(recovered.getName()) || recovered.getAge()==0)
            return new ResponseEntity("El nombre o edad no puede ser vacio ó '0'", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(recoveredService.updateRecovered(recovered), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecovered(@PathVariable("id") int id) {
        if (!recoveredService.existsById(id))
            return new ResponseEntity("Id no existe", HttpStatus.NOT_FOUND);
        recoveredService.deleteRecovered(id);
        return new ResponseEntity("Registro eliminado con exito", HttpStatus.OK);
    }
}
