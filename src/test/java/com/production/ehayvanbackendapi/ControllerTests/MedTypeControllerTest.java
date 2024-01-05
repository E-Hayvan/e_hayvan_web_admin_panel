package com.production.ehayvanbackendapi.ControllerTests;

import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.Entities.MedType;
import com.production.ehayvanbackendapi.Repositories.MedTypeRepository;
import com.production.ehayvanbackendapi.Services.*;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private MedTypeService testMedTypeService;

    @SpyBean
    @Autowired
    private MedTypeRepository testMedTypeRepository;

    private MedTypeDTO testMedTypeDTO;

    private MedType testMedType;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testMedTypeDTO = new MedTypeDTO();
        testMedTypeDTO.setMedTypeName("sinavlarin bitisi");

        testMedType = new MedType();
        testMedType.setMedTypeID(0);
        testMedType.setMedType("Okulun Bitisi");
    }

    @AfterEach
    public void tearDown() {
        testMedTypeDTO = null;
        testMedType = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testMedTypeId = 0;
        this.mockMvc.perform(get("/api/medtypes/" + testMedTypeId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        Integer testMedTypeID = 1;
        testMedTypeDTO = testMedTypeService.getMedTypeById(testMedTypeID);

        this.mockMvc.perform(get("/api/medtypes/" + testMedTypeID).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.medTypeID").value(testMedTypeDTO.getMedTypeID()))
                .andExpect(jsonPath("$.medTypeName").value(testMedTypeDTO.getMedTypeName()));

        // assert that call it in MedTypeControllerTest.testGettingIDWhichExistsInDatabase
        verify(testMedTypeService, times(2)).getMedTypeById(testMedTypeID);
    }

    @Test
    @Transactional
    public void testDeletingById() throws Exception {
        testMedType.setMedTypeID(0);
        testMedType.setMedType("Kizil derili Tedavisi");
        testMedType = testMedTypeRepository.save(testMedType);

        this.mockMvc.perform(delete("/api/medtypes/" + testMedType.getMedTypeID()).with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.medTypeName").value(testMedType.getMedType()));

        MedTypeDTO searchedMedTypeDTO = testMedTypeService.getMedTypeById(testMedType.getMedTypeID());
        assertThat(searchedMedTypeDTO).isNull();

        // assert that the method deleteMedType of testMedTypeService is executed once.
        verify(testMedTypeService, times(1)).deleteMedType(anyInt());
    }

    @Test
    @Transactional
    public void testGetAllMedTypes() throws Exception {
        List<MedType> listOfAllAddedMedType = new ArrayList<>();

        testMedType.setMedType("Afaki");
        testMedType.setMedTypeID(0);
        listOfAllAddedMedType.add(testMedTypeRepository.save(testMedType));

        testMedType.setMedType("Bazar");
        testMedType.setMedTypeID(0);
        listOfAllAddedMedType.add(testMedTypeRepository.save(testMedType));

        testMedType.setMedType("Deodorant");
        testMedType.setMedTypeID(0);
        listOfAllAddedMedType.add(testMedTypeRepository.save(testMedType));


        this.mockMvc.perform(get("/api/medtypes/all").with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[3].medTypeName").value(listOfAllAddedMedType.get(0).getMedType()))
                .andExpect(jsonPath("$[4].medTypeName").value(listOfAllAddedMedType.get(1).getMedType()));

        for (MedType addedMedType : listOfAllAddedMedType) {
            testMedTypeService.deleteMedType(addedMedType.getMedTypeID());
        }
    }
}
