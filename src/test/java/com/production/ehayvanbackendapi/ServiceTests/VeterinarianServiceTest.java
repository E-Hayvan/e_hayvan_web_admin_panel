package com.production.ehayvanbackendapi.ServiceTests;

import com.production.ehayvanbackendapi.DTO.VeterinarianDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateVeterinarianDTO;
import com.production.ehayvanbackendapi.Entities.*;
import com.production.ehayvanbackendapi.Mappers.VeterinarianMapper;
import com.production.ehayvanbackendapi.Repositories.VeterinarianRepository;
import com.production.ehayvanbackendapi.Services.VeterinarianService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VeterinarianServiceTest {
    @SpyBean
    @Autowired
    private VeterinarianService testVeterinarianService;

    @SpyBean
    @Autowired
    private VeterinarianRepository testVeterinarianRepository;

    @Autowired
    DataSeed dataSeed;

    private Veterinarian testVeterinarian;

    private CreateOrUpdateVeterinarianDTO testCreateOrUpdateVeterinarianDTO;

    @Autowired
    private VeterinarianMapper testVeterinarianMapper;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    @Transactional
    public void onEachTestStart() {

        testVeterinarian = new Veterinarian();
        testVeterinarian.setClinic("cincinella");
        testVeterinarian.setUser(new Customer());
        testVeterinarian.getUser().setUserTypeID(new UserType(2));
        testVeterinarian.getUser().setName("Yakisikli");
        testVeterinarian.getUser().setSurname("Guvenlik");
        testVeterinarian.getUser().setEmail("Stella@Mtella.com");
        testVeterinarian.getUser().setPassword("akaryakit");

        testCreateOrUpdateVeterinarianDTO = new CreateOrUpdateVeterinarianDTO(testVeterinarianMapper.convertToDto(testVeterinarian));
    }

    @AfterEach
    public void onEachTestEnd() {
        testCreateOrUpdateVeterinarianDTO = null;
        testVeterinarian = null;
    }

    @Test
    public void testServiceGetByIdWhichNoExists() {
        int testVeterinarianId = 0;
        VeterinarianDTO returnedVeterinarianDTO = testVeterinarianService.getVeterinarianById(testVeterinarianId);
        assertThat(returnedVeterinarianDTO).isNull();
    }

    @Test
    @Transactional
    public void testServiceGetByIdWhichInDatabase() {
        Veterinarian returnedVeterinarian = testVeterinarianRepository.save(testVeterinarian);
        VeterinarianDTO returnedVeterinarianDTO = testVeterinarianService.getVeterinarianById(returnedVeterinarian.getVetID());

        assertThat(returnedVeterinarianDTO).isNotNull();
        assertThat(returnedVeterinarianDTO.getVetID()).isEqualTo(returnedVeterinarian.getVetID());
        assertThat(returnedVeterinarianDTO.getClinic()).isEqualTo(returnedVeterinarian.getClinic());
        assertThat(returnedVeterinarianDTO.getUser().getPassword()).isEqualTo(returnedVeterinarian.getUser().getPassword());

        testVeterinarianRepository.deleteById(returnedVeterinarian.getVetID());
    }

    @Test
    @Transactional
    public void testServicePostVeterinarian() {
        VeterinarianDTO returnedVeterinarianDTO = testVeterinarianService.postVeterinarian(testCreateOrUpdateVeterinarianDTO);

        Optional<Veterinarian> searchedVeterinarianOptional = testVeterinarianRepository.findById(returnedVeterinarianDTO.getVetID());
        assertThat(searchedVeterinarianOptional.isPresent()).isEqualTo(true);
        assertThat(searchedVeterinarianOptional.orElseThrow().getVetID()).isEqualTo(returnedVeterinarianDTO.getVetID());

        testVeterinarianRepository.deleteById(searchedVeterinarianOptional.orElseThrow().getVetID());
    }

    @Test
    @Transactional
    public void testServiceDeleteVeterinarian() {
        // firstly save new Veterinarian
        Veterinarian returnedVeterinarian = testVeterinarianRepository.save(testVeterinarian);
        Optional<Veterinarian> searchedVeterinarian = testVeterinarianRepository.findById(returnedVeterinarian.getVetID());
        assertThat(searchedVeterinarian.isPresent()).isEqualTo(true);

        VeterinarianDTO deletedVeterinarian = testVeterinarianService.deleteVeterinarian(returnedVeterinarian.getVetID());

        assertThat(deletedVeterinarian.getVetID()).isEqualTo(returnedVeterinarian.getVetID());

        searchedVeterinarian = testVeterinarianRepository.findById(returnedVeterinarian.getVetID());
        assertThat(searchedVeterinarian.isPresent()).isEqualTo(false);
    }
}
