package com.production.ehayvanbackendapi.ServiceTests;

import com.production.ehayvanbackendapi.DTO.MedTypeDTO;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.PetTypeDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetDTO;
import com.production.ehayvanbackendapi.Entities.*;
import com.production.ehayvanbackendapi.Mappers.MedTypeMapper;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Repositories.MedTypeRepository;
import com.production.ehayvanbackendapi.Repositories.PetRepository;
import com.production.ehayvanbackendapi.Repositories.PetTypeRepository;
import com.production.ehayvanbackendapi.Services.MedTypeService;
import com.production.ehayvanbackendapi.Services.PetService;
import com.production.ehayvanbackendapi.Services.PetTypeService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetTypeServiceTest {
    @SpyBean
    @Autowired
    private PetTypeService testPetTypeService;

    @SpyBean
    @Autowired
    private PetTypeRepository testPetTypeRepository;

    @Autowired
    DataSeed dataSeed;

    private PetType testPetType;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    @Transactional
    public void onEachTestStart() {
        testPetType = new PetType();
        testPetType.setPetTypeID(17);
        testPetType.setPets(List.of());
        testPetType.setType("micky mouse");
    }

    @AfterEach
    public void onEachTestEnd() {
        testPetType = null;
    }

    @Test
    public void testServiceGetByIdWhichNoExists() {
        int testPetTypeId = 0;
        PetTypeDTO returnedPetTypeDTO = testPetTypeService.getPetTypeById(testPetTypeId);
        assertThat(returnedPetTypeDTO).isNull();
    }

    @Test
    @Transactional
    public void testServiceGetByIdWhichInDatabase() {
        PetType returnedPetType = testPetTypeRepository.save(testPetType);
        PetTypeDTO returnedPetTypeDTO = testPetTypeService.getPetTypeById(returnedPetType.getPetTypeID());

        assertThat(returnedPetTypeDTO).isNotNull();
        assertThat(returnedPetTypeDTO.getPetTypeID()).isEqualTo(returnedPetType.getPetTypeID());
        assertThat(returnedPetTypeDTO.getType()).isEqualTo(returnedPetType.getType());

        testPetTypeRepository.deleteById(returnedPetType.getPetTypeID());
    }
}
