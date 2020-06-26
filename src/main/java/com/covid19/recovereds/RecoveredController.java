package com.covid19.recovereds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/recovered")
public class RecoveredController {

    @Autowired
    private RecoveredService recoveredService;

    @GetMapping
    private ResponseEntity<List<Recovered>> retrieveAllRecovereds() {
        return ok(recoveredService.retieveAllrecovereds());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Recovered> retrieveRecoveredById(@PathVariable int id) {
        return ok(recoveredService.retrieveRecoveredById(id));
    }

    @PostMapping
    public ResponseEntity<Recovered> createRecovered(@RequestBody Recovered recovered) {
        return new ResponseEntity<>(recoveredService.createRecovered(recovered), HttpStatus.CREATED);
    }
}
