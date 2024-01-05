package com.production.ehayvanbackendapi.ControllerTests;

import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.Entities.MedType;
import com.production.ehayvanbackendapi.Entities.PetType;
import com.production.ehayvanbackendapi.Repositories.PetTypeRepository;
import com.production.ehayvanbackendapi.Services.*;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private PetTypeService testPetTypeService;

    @SpyBean
    @Autowired
    private PetTypeRepository testPetTypeRepository;

    private PetTypeDTO testPetTypeDTO;

    private PetType testPetType;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testPetTypeDTO = new PetTypeDTO();
        testPetTypeDTO.setPetTypeID(0);
        testPetTypeDTO.setType("dinosaur");

        testPetType = new PetType();
        testPetType.setPetTypeID(0);
        testPetType.setType("dinosaur");
    }

    @AfterEach
    public void tearDown() {
        testPetTypeDTO = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testPetTypeId = 0;
        this.mockMvc.perform(get("/api/pettypes/" + testPetTypeId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        Integer testPetTypeID = 1;
        testPetTypeDTO = testPetTypeService.getPetTypeById(testPetTypeID);

        this.mockMvc.perform(get("/api/pettypes/" + testPetTypeID).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.petTypeID").value(testPetTypeDTO.getPetTypeID()));

        // assert that call it in PetTypeControllerTest.testGettingIDWhichExistsInDatabase
        verify(testPetTypeService, times(2)).getPetTypeById(testPetTypeID);
    }

    @Test
    @Transactional
    public void testGetAllPetTypes() throws Exception {
        List<PetType> listOfAllAddedPetType = new ArrayList<>();

        testPetType.setType("Afaki");
        testPetType.setPetTypeID(0);
        listOfAllAddedPetType.add(testPetTypeRepository.save(testPetType));

        testPetType.setType("Bazar");
        testPetType.setPetTypeID(0);
        listOfAllAddedPetType.add(testPetTypeRepository.save(testPetType));

        testPetType.setType("Deodorant");
        testPetType.setPetTypeID(0);
        listOfAllAddedPetType.add(testPetTypeRepository.save(testPetType));

        this.mockMvc.perform(get("/api/pettypes/all").with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[4].type").value(listOfAllAddedPetType.get(0).getType()))
                .andExpect(jsonPath("$[5].type").value(listOfAllAddedPetType.get(1).getType()));

        for (PetType addedPetType : listOfAllAddedPetType) {
            testPetTypeRepository.deleteById(addedPetType.getPetTypeID());
        }
    }
}
