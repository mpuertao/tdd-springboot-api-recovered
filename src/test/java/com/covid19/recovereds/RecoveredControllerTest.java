package com.covid19.recovereds;

import com.covid19.recovereds.controller.RecoveredController;
import com.covid19.recovereds.entity.Recovered;
import com.covid19.recovereds.exceptionhandling.exceptions.DataBaseEmptyException;
import com.covid19.recovereds.service.RecoveredService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecoveredController.class)
class RecoveredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecoveredService recordService;

    @Test
    @DisplayName("Prueba que nos permite validar respuesta exitosa para el controlador cuando consultan por todos los recuperados")
    void givenRecoveredWhenFindAllThenResponseOk() throws Exception {
        //Arrange
        List<Recovered> recovereds = new ArrayList<>();
        recovereds.add(new Recovered(1, "Manuela", 25));
        recovereds.add(new Recovered(2, "Viviana", 28));
        recovereds.add(new Recovered(3, "Danny Barrientos", 40));

        //Act
        when(recordService.retrieveAllrecovereds()).thenReturn(recovereds);

        //Assert
        mockMvc.perform(
                get("/recovered")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Prueba q nos permite validar respuesta ok por recuperado por id")
    void givenRecoveredWhenFindByIdThenResponseOk() throws Exception {
        Recovered recovered = new Recovered(4, "Lina", 28);
        when(recordService.retrieveRecoveredById(4)).thenReturn(recovered);
        mockMvc.perform(get("/recovered/{id}", 4)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Prueba que nos permite validar el copntrolador cuando se crea un recuperado")
    void givenRecoveredWhenCreateRegisterThenReturnsOk() throws Exception {
        Recovered recovered = new Recovered(5, "Carlos", 58);
        when(recordService.createRecovered(recovered)).thenReturn(recovered);
        mockMvc.perform(
                post("/recovered")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recovered)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Prueba que nos permite validar el error de busqueda de un usuario")
    void givenRecoveredSearchNull() throws Exception{
        Recovered recovered = new Recovered();
        when(recordService.retrieveRecoveredById(10000)).thenThrow(new NoSuchElementException());
        MockHttpServletResponse response = mockMvc.perform(get("/recovered/{id}", 10000)
                .contentType("application/json"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Prueba que nos permite validar el error de que no existe ningun usuario creado")
    void givenRecoveredDBIsEmpty() throws Exception{
        Recovered recovered = new Recovered();
        when(recordService.retrieveAllrecovereds()).thenThrow(new DataBaseEmptyException());
        MockHttpServletResponse response = mockMvc.perform(get("/recovered")
                .contentType("application/json"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Prueba que nos permite validar un envio incorrecto de la data")
    void givenRecoveredIsDataEmpty() throws Exception{
        Recovered recovered = new Recovered();
        when(recordService.createRecovered(recovered)).thenThrow(new RuntimeException());
        MockHttpServletResponse response = mockMvc.perform(
                post("/recovered")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recovered)))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
