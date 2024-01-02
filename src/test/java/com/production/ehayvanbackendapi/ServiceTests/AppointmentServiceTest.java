package com.production.ehayvanbackendapi.ServiceTests;

import com.production.ehayvanbackendapi.DTO.AppointmentDTO;
import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateAppointmentDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetDTO;
import com.production.ehayvanbackendapi.Entities.*;
import com.production.ehayvanbackendapi.Mappers.AppointmentMapper;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Repositories.AppointmentRepository;
import com.production.ehayvanbackendapi.Repositories.PetRepository;
import com.production.ehayvanbackendapi.Services.AppointmentService;
import com.production.ehayvanbackendapi.Services.PetService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentServiceTest {
    @SpyBean
    @Autowired
    private AppointmentService testAppointmentService;

    @SpyBean
    @Autowired
    private AppointmentRepository testAppointmentRepository;

    @Autowired
    DataSeed dataSeed;

    private Appointment testAppointment;

    private CreateOrUpdateAppointmentDTO testCreateOrUpdateAppointmentDTO;

    @Autowired
    private AppointmentMapper testAppointmentMapper;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    @Transactional
    public void onEachTestStart() {
        testAppointment = new Appointment();
        testAppointment.setAppointmentID(13);
        testAppointment.setPetOwnerID(new PetOwner());
        testAppointment.setPetID(new Pet());
        testAppointment.setVetID(new Veterinarian());
        testAppointment.getPetOwnerID().setPetOwnerID(1);
        testAppointment.getPetID().setPetID(1);
        testAppointment.getVetID().setVetID(1);
        testAppointment.getVetID().setAppointments(List.of());
        testAppointment.setAppointmentDate(LocalDateTime.of(2049, Month.APRIL, 21, 1, 1));

        testCreateOrUpdateAppointmentDTO = new CreateOrUpdateAppointmentDTO(testAppointmentMapper.convertToDto(testAppointment));
    }

    @AfterEach
    public void onEachTestEnd() {
        testCreateOrUpdateAppointmentDTO = null;
        testAppointment = null;
    }

    @Test
    public void testServiceGetByIdWhichNoExists() {
        int testAppointmentId = 0;
        AppointmentDTO returnedPetDTO = testAppointmentService.getAppointmentById(testAppointmentId);
        assertThat(returnedPetDTO).isNull();
    }

    @Test
    @Transactional
    public void testServiceGetByIdWhichInDatabase() {
        Appointment returnedAppointment = testAppointmentRepository.save(testAppointment);
        AppointmentDTO returnedAppointmentDTO = testAppointmentService.getAppointmentById(returnedAppointment.getAppointmentID());

        assertThat(returnedAppointmentDTO).isNotNull();
        assertThat(returnedAppointmentDTO.getAppointmentID()).isEqualTo(returnedAppointment.getAppointmentID());
        assertThat(returnedAppointmentDTO.getPetOwnerID()).isEqualTo(returnedAppointment.getPetOwnerID().getPetOwnerID());
        assertThat(returnedAppointmentDTO.getAppointmentDate()).isEqualTo(returnedAppointment.getAppointmentDate());

        testAppointmentRepository.deleteById(returnedAppointment.getAppointmentID());
    }

    @Test
    @Transactional
    public void testServicePostPet() {
        AppointmentDTO returnedAppointmentDTO = testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO);

        Optional<Appointment> searchedAppointmentOptional = testAppointmentRepository.findById(returnedAppointmentDTO.getAppointmentID());
        assertThat(searchedAppointmentOptional.isPresent()).isEqualTo(true);
        assertThat(searchedAppointmentOptional.orElseThrow().getPetID().getPetID()).isEqualTo(returnedAppointmentDTO.getPetID());

        testAppointmentRepository.deleteById(searchedAppointmentOptional.orElseThrow().getAppointmentID());
    }

    @Test
    @Transactional
    public void testServiceUpdatePet() {
        // firstly save new Appointment
        Appointment returnedAppointment = testAppointmentRepository.save(testAppointment);

        // convert to DTO and change email address of new created DTO
        testCreateOrUpdateAppointmentDTO = new CreateOrUpdateAppointmentDTO(testAppointmentMapper.convertToDto(testAppointment));
        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1945, Month.SEPTEMBER, 2, 1, 1));

        // update corresponding Appointment
        AppointmentDTO returnedAppointmentDTO = testAppointmentService.updateAppointment(returnedAppointment.getAppointmentID(), testCreateOrUpdateAppointmentDTO);

        // check if it is same as email that updated data and data we want to update
        Optional<Appointment> searchedAppointmentOptional = testAppointmentRepository.findById(returnedAppointment.getAppointmentID());
        assertThat(searchedAppointmentOptional.isPresent()).isEqualTo(true);
        assertThat(searchedAppointmentOptional.orElseThrow().getAppointmentDate()).isEqualTo(returnedAppointmentDTO.getAppointmentDate());

        // delete added data for testing
        testAppointmentRepository.deleteById(searchedAppointmentOptional.orElseThrow().getAppointmentID());
    }

    @Test
    @Transactional
    public void testServiceDeletePet() {
        // firstly save new Appointment
        Appointment returnedAppointment = testAppointmentRepository.save(testAppointment);
        Optional<Appointment> searchedAppointment = testAppointmentRepository.findById(returnedAppointment.getAppointmentID());
        assertThat(searchedAppointment.isPresent()).isEqualTo(true);

        AppointmentDTO deletedAppointment = testAppointmentService.deleteAppointment(returnedAppointment.getAppointmentID());

        assertThat(deletedAppointment.getAppointmentDate()).isEqualTo(returnedAppointment.getAppointmentDate());

        searchedAppointment = testAppointmentRepository.findById(returnedAppointment.getAppointmentID());
        assertThat(searchedAppointment.isPresent()).isEqualTo(false);
    }
}
