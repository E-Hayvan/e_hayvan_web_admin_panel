package com.production.ehayvanbackendapi.ServiceTests;

import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetDTO;
import com.production.ehayvanbackendapi.Entities.*;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Repositories.PetRepository;
import com.production.ehayvanbackendapi.Services.PetService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetServiceTest {
    @SpyBean
    @Autowired
    private PetService testPetService;

    @SpyBean
    @Autowired
    private PetRepository testPetRepository;

    @Autowired
    DataSeed dataSeed;

    private Pet testPet;

    private CreateOrUpdatePetDTO testCreateOrUpdatePetDTO;

    @Autowired
    private PetMapper testPetMapper;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    @Transactional
    public void onEachTestStart() {
        testPet = new Pet();
        testPet.setPetName("Ingiliz Ati");
        testPet.setPetTypeID(new PetType());
        testPet.getPetTypeID().setPetTypeID(1);
        testPet.setAge(12);
        testPet.setPetOwnerID(new PetOwner());
        testPet.getPetOwnerID().setUser(new Customer());
        testPet.getPetOwnerID().getUser().setUserTypeID(new UserType(1));
        testPet.getPetOwnerID().getUser().setName("And i return to nothingness");
        testPet.getPetOwnerID().getUser().setSurname("Shore");
        testPet.getPetOwnerID().getUser().setEmail("pain_remains_2@gmail.com");
        testPet.getPetOwnerID().getUser().setPassword("remainsssssss");

        testCreateOrUpdatePetDTO = new CreateOrUpdatePetDTO(testPetMapper.convertToDto(testPet));
    }

    @AfterEach
    public void onEachTestEnd() {
        testCreateOrUpdatePetDTO = null;
        testPet = null;
    }

    @Test
    public void testServiceGetByIdWhichNoExists() {
        int testPetId = 0;
        PetDTO returnedPetDTO = testPetService.getPetById(testPetId);
        assertThat(returnedPetDTO).isNull();
    }

    @Test
    @Transactional
    public void testServiceGetByIdWhichInDatabase() {
        Pet returnedPet = testPetRepository.save(testPet);
        PetDTO returnedPetDTO = testPetService.getPetById(returnedPet.getPetID());

        assertThat(returnedPetDTO).isNotNull();
        assertThat(returnedPetDTO.getPetID()).isEqualTo(returnedPet.getPetID());
        assertThat(returnedPetDTO.getPetOwnerID()).isEqualTo(returnedPet.getPetOwnerID().getPetOwnerID());
        assertThat(returnedPetDTO.getPetName()).isEqualTo(returnedPet.getPetName());

        testPetRepository.deleteById(returnedPet.getPetID());
    }

    @Test
    @Transactional
    public void testServicePostPet() {
        PetDTO returnedPetDTO = testPetService.postPet(testCreateOrUpdatePetDTO);

        Optional<Pet> searchedPetOptional = testPetRepository.findById(returnedPetDTO.getPetID());
        assertThat(searchedPetOptional.isPresent()).isEqualTo(true);
        assertThat(searchedPetOptional.orElseThrow().getPetID()).isEqualTo(returnedPetDTO.getPetID());

        testPetRepository.deleteById(searchedPetOptional.orElseThrow().getPetID());
    }

    @Test
    @Transactional
    public void testServiceUpdatePet() {
        // firstly save new Pet
        Pet returnedPet = testPetRepository.save(testPet);

        // convert to DTO and change email address of new created DTO
        testCreateOrUpdatePetDTO = new CreateOrUpdatePetDTO(testPetMapper.convertToDto(testPet));
        testCreateOrUpdatePetDTO.setPetName("Anaconda");
        testCreateOrUpdatePetDTO.setPetTypeID(1);

        // update corresponding PetOwner
        PetDTO returnedPetDTO = testPetService.updatePet(returnedPet.getPetID(), testCreateOrUpdatePetDTO);

        // check if it is same as email that updated data and data we want to update
        Optional<Pet> searchedPetOptional = testPetRepository.findById(returnedPet.getPetID());
        assertThat(searchedPetOptional.isPresent()).isEqualTo(true);
        assertThat(searchedPetOptional.orElseThrow().getPetOwnerID().getPetOwnerID()).isEqualTo(returnedPetDTO.getPetOwnerID());
        assertThat(searchedPetOptional.orElseThrow().getPetName()).isEqualTo(testCreateOrUpdatePetDTO.getPetName());
        assertThat(searchedPetOptional.orElseThrow().getPetTypeID().getPetTypeID()).isEqualTo(testCreateOrUpdatePetDTO.getPetTypeID());

        // delete added data for testing
        testPetRepository.deleteById(searchedPetOptional.orElseThrow().getPetID());
    }

    @Test
    @Transactional
    public void testServiceDeletePet() {
        // firstly save new Pet
        Pet returnedPet = testPetRepository.save(testPet);
        Optional<Pet> searchedPet = testPetRepository.findById(returnedPet.getPetID());
        assertThat(searchedPet.isPresent()).isEqualTo(true);

        PetDTO deletedPet = testPetService.deletePet(returnedPet.getPetID());

        assertThat(deletedPet.getPetID()).isEqualTo(returnedPet.getPetID());

        searchedPet = testPetRepository.findById(returnedPet.getPetID());
        assertThat(searchedPet.isPresent()).isEqualTo(false);
    }
}
