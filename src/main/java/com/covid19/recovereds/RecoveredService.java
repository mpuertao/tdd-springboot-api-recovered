package com.covid19.recovereds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecoveredService {

    @Autowired
    private RecoveredRepository recoveredRepository;


    public List<Recovered> retieveAllrecovereds() {
        return recoveredRepository.findAll();
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
}
