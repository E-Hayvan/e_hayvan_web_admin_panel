package com.production.ehayvanbackendapi.ServiceTests;

import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.DTO.ScheduleDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateScheduleDTO;
import com.production.ehayvanbackendapi.Entities.*;
import com.production.ehayvanbackendapi.Mappers.PetMapper;
import com.production.ehayvanbackendapi.Mappers.ScheduleMapper;
import com.production.ehayvanbackendapi.Repositories.PetRepository;
import com.production.ehayvanbackendapi.Repositories.ScheduleRepository;
import com.production.ehayvanbackendapi.Services.PetService;
import com.production.ehayvanbackendapi.Services.ScheduleService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScheduleServiceTest {
    @SpyBean
    @Autowired
    private ScheduleService testScheduleService;

    @SpyBean
    @Autowired
    private ScheduleRepository testScheduleRepository;

    @Autowired
    DataSeed dataSeed;

    private Schedule testSchedule;

    private CreateOrUpdateScheduleDTO testCreateOrUpdateScheduleDTO;

    @Autowired
    private ScheduleMapper testScheduleMapper;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    @Transactional
    public void onEachTestStart() {
        testSchedule = new Schedule();
        testSchedule.setScheduleID(0);
        testSchedule.setDoseCount(12);
        testSchedule.setDoseFrequency(1000);
        testSchedule.setBeginningDate(LocalDate.of(1995, Month.APRIL, 30));

        testCreateOrUpdateScheduleDTO = new CreateOrUpdateScheduleDTO(testScheduleMapper.convertToDto(testSchedule));
    }

    @AfterEach
    public void onEachTestEnd() {
        testCreateOrUpdateScheduleDTO = null;
        testSchedule = null;
    }

    @Test
    public void testServiceGetByIdWhichNoExists() {
        int testScheduleId = 0;
        ScheduleDTO returnedScheduleDTO = testScheduleService.getScheduleById(testScheduleId);
        assertThat(returnedScheduleDTO).isNull();
    }

    @Test
    @Transactional
    public void testServiceGetByIdWhichInDatabase() {
        Schedule returnedSchedule = testScheduleRepository.save(testSchedule);
        ScheduleDTO returnedScheduleDTO = testScheduleService.getScheduleById(returnedSchedule.getScheduleID());

        assertThat(returnedScheduleDTO).isNotNull();
        assertThat(returnedScheduleDTO.getScheduleID()).isEqualTo(returnedSchedule.getScheduleID());
        assertThat(returnedScheduleDTO.getBeginningDate()).isEqualTo(returnedSchedule.getBeginningDate());
        assertThat(returnedScheduleDTO.getDoseCount()).isEqualTo(returnedSchedule.getDoseCount());

        testScheduleRepository.deleteById(returnedSchedule.getScheduleID());
    }

    @Test
    @Transactional
    public void testServiceDeleteSchedule() {
        // firstly save new Pet
        Schedule returnedSchedule = testScheduleRepository.save(testSchedule);
        Optional<Schedule> searchedSchedule = testScheduleRepository.findById(returnedSchedule.getScheduleID());
        assertThat(searchedSchedule.isPresent()).isEqualTo(true);

        ScheduleDTO deletedSchedule = testScheduleService.deleteSchedule(returnedSchedule.getScheduleID());

        assertThat(deletedSchedule.getScheduleID()).isEqualTo(returnedSchedule.getScheduleID());

        searchedSchedule = testScheduleRepository.findById(returnedSchedule.getScheduleID());
        assertThat(searchedSchedule.isPresent()).isEqualTo(false);
    }
}
