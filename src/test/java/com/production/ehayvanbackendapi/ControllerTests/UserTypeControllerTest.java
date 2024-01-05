package com.production.ehayvanbackendapi.ControllerTests;

import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.Entities.PetType;
import com.production.ehayvanbackendapi.Entities.UserType;
import com.production.ehayvanbackendapi.Repositories.UserTypeRepository;
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
public class UserTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private UserTypeService testUserTypeService;

    @SpyBean
    @Autowired
    private UserTypeRepository testUserTypeRepository;

    private UserTypeDTO testUserTypeDTO;

    private UserType testUserType;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testUserTypeDTO = new UserTypeDTO();
        testUserTypeDTO.setUserTypeID(0);
        testUserTypeDTO.setType("dinosaur");

        testUserType = new UserType();
        testUserType.setType("dinosaur");
        testUserType.setUserTypeID(0);
    }

    @AfterEach
    public void tearDown() {
        testUserTypeDTO = null;
        testUserType = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testUserTypeId = 0;
        this.mockMvc.perform(get("/api/usertypes/" + testUserTypeId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        Integer testUserTypeID = 1;
        testUserTypeDTO = testUserTypeService.getUserTypeById(testUserTypeID);

        this.mockMvc.perform(get("/api/usertypes/" + testUserTypeID).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userTypeID").value(testUserTypeDTO.getUserTypeID()));

        // assert that call it in UserTypeControllerTest.testGettingIDWhichExistsInDatabase
        verify(testUserTypeService, times(2)).getUserTypeById(testUserTypeID);
    }

    @Test
    @Transactional
    public void testGetAllUserTypes() throws Exception {
        List<UserType> listOfAllAddedUserType = new ArrayList<>();

        testUserType.setType("Afaki");
        testUserType.setUserTypeID(0);
        listOfAllAddedUserType.add(testUserTypeRepository.save(testUserType));

        testUserType.setType("Bazar");
        testUserType.setUserTypeID(0);
        listOfAllAddedUserType.add(testUserTypeRepository.save(testUserType));

        testUserType.setType("Deodorant");
        testUserType.setUserTypeID(0);
        listOfAllAddedUserType.add(testUserTypeRepository.save(testUserType));

        this.mockMvc.perform(get("/api/usertypes/all").with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[3].type").value(listOfAllAddedUserType.get(0).getType()))
                .andExpect(jsonPath("$[4].type").value(listOfAllAddedUserType.get(1).getType()));

        for (UserType addedUserType : listOfAllAddedUserType) {
            testUserTypeRepository.deleteById(addedUserType.getUserTypeID());
        }
    }
}
