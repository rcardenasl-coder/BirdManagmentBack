package com.birdregistry.bird_registry_api;

import com.birdregistry.bird_registry_api.bird.application.dto.BirdDto;
import com.birdregistry.bird_registry_api.bird.domain.model.Bird;
import com.birdregistry.bird_registry_api.bird.domain.model.valueobjs.*;
import com.birdregistry.bird_registry_api.bird.domain.ports.out.BirdPersistencePort;
import com.birdregistry.bird_registry_api.bird.infraestructure.wrapper.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BirdControllerIntegrationTest {

    private static final String BASE_URL = "/api/v1/bird/";
    private static final String ERROR_ID = "ID should not be provided when creating";
    private static final String ERROR_NOT_FOUND = "The bird was not found";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BirdPersistencePort birdPersistencePort;

    @Nested
    class GetBirdsTest {

        @Test
        void shouldGetAllBirds() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get(BASE_URL)
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
            Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        }
    }

    @Nested
    class SearchId{
        @Test
        void shouldSearchId() throws Exception {
            Bird bird = Bird.builder()
                    .id(1L)
                    .name(new Name("Loro"))
                    .type(new Type("Psiatico"))
                    .color(new Color("Verde"))
                    .dateCreate(new DateCreate(LocalDate.now()))
                    .description(new Description("Ave tropical"))
                    .build();

            Mockito.when(birdPersistencePort.existsBird(Mockito.anyLong())).thenReturn(true);
            Mockito.when(birdPersistencePort.findById(Mockito.anyLong())).thenReturn(bird);

            MvcResult mvcResult = mockMvc.perform(get(BASE_URL+"/search/1")
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

            Assertions.assertEquals(200,mvcResult.getResponse().getStatus());
        }

        @Test
        void shouldErrorNotExists() throws Exception {

            Mockito.when(birdPersistencePort.existsBird(Mockito.anyLong())).thenReturn(false);
            Mockito.when(birdPersistencePort.findById(Mockito.anyLong())).thenReturn(null);

            MvcResult mvcResult = mockMvc.perform(get(BASE_URL+"/search/1")
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

            Assertions.assertEquals(404,mvcResult.getResponse().getStatus());
            Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains(ERROR_NOT_FOUND));
        }
    }

    @Nested
    class CreateBirdTest {

        @Test
        void shouldCreateBirdSuccessfully() throws Exception {
            BirdDto birdDto = BirdDto.builder()
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();
            Bird bird = Bird.builder()
                    .id(1L)
                    .name(new Name("Loro"))
                    .type(new Type("Psiatico"))
                    .color(new Color("Verde"))
                    .dateCreate(new DateCreate(LocalDate.now()))
                    .description(new Description("Ave tropical"))
                    .build();

            Mockito.when(birdPersistencePort.saveOrUpdate(Mockito.any(Bird.class))).thenReturn(bird);

            MvcResult mvcResult = mockMvc.perform(post(BASE_URL)
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andReturn();

            Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
        }

        @Test
        void shouldReturnErrorWhenIdProvided() throws Exception {
            BirdDto bird = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();

            MvcResult mvcResult = mockMvc.perform(post(BASE_URL)
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsBytes(bird)))
                    .andReturn();

            Assertions.assertEquals(400, mvcResult.getResponse().getStatus());
            Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains(ERROR_ID));
        }
    }

    @Nested
    class UpdateBirdTest {

        @Test
        void shouldUpdateBirdSuccessfully() throws Exception {

            BirdDto birdDTO = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();
            Bird bird = Bird.builder()
                    .id(1L)
                    .name(new Name("Loro"))
                    .type(new Type("Psiatico"))
                    .color(new Color("Verde"))
                    .dateCreate(new DateCreate(LocalDate.now()))
                    .description(new Description("Ave tropical"))
                    .build();

            Mockito.when(birdPersistencePort.existsBird(1L)).thenReturn(true);
            Mockito.when(birdPersistencePort.saveOrUpdate(Mockito.any(Bird.class))).thenReturn(bird);

            MvcResult mvcResult = mockMvc.perform(put(BASE_URL + "1/update")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsBytes(birdDTO)))
                    .andReturn();

            Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        }

    }

    @Nested
    class DeleteBird {

        @Test
        void shouldDeleteBirdSuccessfully() throws Exception {

            Mockito.when(birdPersistencePort.existsBird(Mockito.anyLong())).thenReturn(true);
            Mockito.doNothing().when(birdPersistencePort).deleteById(Mockito.anyLong());

            MvcResult mvcResult = mockMvc.perform(delete(BASE_URL + "1/delete")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();

            Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

            String jsonResponse = mvcResult.getResponse().getContentAsString();

            ApiResponse<?> response = objectMapper.readValue(jsonResponse, ApiResponse.class);

            Assertions.assertEquals("Success", response.status());
            Assertions.assertEquals("Bird deleted successfully", response.message());
            Assertions.assertNull(response.data());
        }
    }

    @Nested
    class ExceptionHandler{
        @Test
        void shouldTriggerGenericExceptionHandler() throws Exception {
            BirdDto birdDTO = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();

            Mockito.when(birdPersistencePort.existsBird(Mockito.anyLong())).thenReturn(true);
            Mockito.when(birdPersistencePort.saveOrUpdate(Mockito.any(Bird.class)))
                    .thenThrow(new RuntimeException("Simulated generic exception"));

            MvcResult mvcResult = mockMvc.perform(put(BASE_URL + "1/update")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsBytes(birdDTO)))
                    .andReturn();

            Assertions.assertEquals(500, mvcResult.getResponse().getStatus());
            Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("Simulated generic exception | Path: PUT /api/v1/bird/1/update"));
        }
        @Test
        void shouldUpdateBirdError() throws Exception {

            BirdDto birdDTO = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();

            Mockito.when(birdPersistencePort.existsBird(1L)).thenReturn(true);

            MvcResult mvcResult = mockMvc.perform(put(BASE_URL + "1/update")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsBytes(birdDTO)))
                    .andReturn();

            Assertions.assertEquals(500, mvcResult.getResponse().getStatus());
        }
    }
    @Nested
    class DTOTests {

        @Test
        void shouldReturnBadRequestWhenDateCreateIsNull() throws Exception {
            BirdDto birdDto = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(null) // Fecha nula
                    .description("Ave tropical")
                    .build();

            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("The value for DateCreate cannot be null")));
        }

        @Test
        void shouldReturnBadRequestWhenDateCreateIsFuture() throws Exception {
            LocalDate futureDate = LocalDate.now().plusDays(5);
            BirdDto birdDto = BirdDto.builder()
                    .birdCode(1L)
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(futureDate) // Fecha futura
                    .description("Ave tropical")
                    .build();

            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("The value for DateCreate be a future date")));
        }

        @Test
        void shouldCreateBirdWhenDateCreateIsToday() throws Exception {
            BirdDto birdDto = BirdDto.builder()
                    .name("Loro")
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now()) // Fecha actual
                    .description("Ave tropical")
                    .build();

            Bird bird = Bird.builder()
                    .id(1L)
                    .name(new Name("Loro"))
                    .type(new Type("Psiatico"))
                    .color(new Color("Verde"))
                    .dateCreate(new DateCreate(LocalDate.now()))
                    .description(new Description("Ave tropical"))
                    .build();

            Mockito.when(birdPersistencePort.saveOrUpdate(Mockito.any(Bird.class))).thenReturn(bird);

            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andExpect(status().isCreated());
        }

        @Test
        void shouldReturnBadRequestWhenNameIsNull() throws Exception {
            BirdDto birdDto = BirdDto.builder()
                    .birdCode(1L)
                    .name(null) // Nombre nulo
                    .type("Psiatico")
                    .color("Verde")
                    .dateCreate(LocalDate.now())
                    .description("Ave tropical")
                    .build();

            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("The value for Name cannot be null or empty")));
        }

        @Test
        void shouldReturnBadRequestWhenIdProvidedAndTypeEmpty() throws Exception {
            BirdDto birdDto = BirdDto.builder()
                    .birdCode(1L) // ID proporcionado
                    .name("Loro")
                    .type("") // Tipo vacío
                    .color("Verde")
                    .dateCreate(LocalDate.parse("2025-03-24"))
                    .description("Ave tropical")
                    .build();

            mockMvc.perform(post(BASE_URL) // Usa BASE_URL directamente ya que el endpoint es "/"
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(birdDto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("The value for Type cannot be null or empty")));
        }
    }

}
