package com.covid19.recovereds;


import com.covid19.recovereds.entity.Recovered;
import com.covid19.recovereds.repository.RecoveredRepository;
import com.covid19.recovereds.service.RecoveredService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecoveredServiceTest {

    @MockBean
    private RecoveredRepository recoveredRepository;

    @Autowired
    private RecoveredService recoveredService;

    @Test
    @DisplayName("Prueba que nos permite validar la logica al procesar peticion para buscar todos los recuperados")
    void givenRecoveredsWhenFindAllThenValidateRegistersOk() {
        //Arrange
        List<Recovered> recovereds = new ArrayList<>();
        recovereds.add(new Recovered(8, "Yisus", 50));
        recovereds.add(new Recovered(9, "Santiago", 51));
        recovereds.add(new Recovered(10, "Jorge", 52));

        //Act
        when(recoveredRepository.findAll()).thenReturn(recovereds);
        List<Recovered> result = recoveredService.retrieveAllrecovereds();

        //Assert
        assertEquals(recovereds.get(0), result.get(0));
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Prueba que nos permite validar la logica al procesar peticion para buscar recuperado por id y que este sea en minuscula")
    void givenRecoveredWhenFindByIdThenValidateResponseLowerCaseOk() {
        //Arrange
        Recovered recovered = new Recovered(6, "Isaac Puerta", 5);

        //Act
        when(recoveredRepository.findById(6)).thenReturn(Optional.of(recovered));
        Recovered result = recoveredService.retrieveRecoveredById(6);

        //Assert
        assertEquals(recovered.getName().toLowerCase(), result.getName());
    }

    @Test
    @DisplayName("Prueba que valida la correcta creación de un recuperado")
    void givenRecoveredWhenCreateRegisterThenValidateCreateOk() {
        Recovered recovered = new Recovered(7, "El Tio Bob", 55);
        when(recoveredRepository.save(recovered)).thenReturn(recovered);
        assertEquals(recovered, recoveredService.createRecovered(recovered));
    }
}
