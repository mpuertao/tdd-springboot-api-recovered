package com.covid19.recovereds.service;

import com.covid19.recovereds.entity.Recovered;
import com.covid19.recovereds.repository.RecoveredRepository;
import com.covid19.recovereds.exceptionhandling.exceptions.DataBaseEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecoveredService {

    @Autowired
    private RecoveredRepository recoveredRepository;


    public List<Recovered> retrieveAllrecovereds() {
        List<Recovered> recoveredList = recoveredRepository.findAll();
        if (recoveredList.isEmpty()) throw new DataBaseEmptyException();
        return recoveredList;
    }

    public Recovered retrieveRecoveredById(int id) {
        Recovered recovered = recoveredRepository.findById(id).get();
        Recovered transform = new Recovered();
        transform.setName(recovered.getName().toLowerCase());
        transform.setId(recovered.getId());
        transform.setAge(recovered.getAge());
        return transform;
    }

    @Transactional
    public Recovered createRecovered(Recovered recovered) {
        return recoveredRepository.save(recovered);
    }

    @Transactional
    public Recovered updateRecovered(Recovered recovered) {
        return recoveredRepository.save(recovered);
    }

    @Transactional
    public void deleteRecovered(int id) {
        recoveredRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return recoveredRepository.existsById(id);
    }
}
