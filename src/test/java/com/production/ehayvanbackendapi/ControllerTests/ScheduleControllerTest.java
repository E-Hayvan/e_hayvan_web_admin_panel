package com.production.ehayvanbackendapi.ControllerTests;

import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.DTO.request.*;
import com.production.ehayvanbackendapi.Entities.Customer;
import com.production.ehayvanbackendapi.Entities.PetType;
import com.production.ehayvanbackendapi.Entities.Schedule;
import com.production.ehayvanbackendapi.Repositories.ScheduleRepository;
import com.production.ehayvanbackendapi.Services.ScheduleService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private ScheduleService testScheduleService;

    @SpyBean
    @Autowired
    private ScheduleRepository testScheduleRepository;

    private ScheduleDTO testScheduleDTO;

    private Schedule testSchedule;
    private CreateOrUpdateScheduleDTO testCreateOrUpdateScheduleDTO;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testCreateOrUpdateScheduleDTO = new CreateOrUpdateScheduleDTO();
        testCreateOrUpdateScheduleDTO.setBeginningDate(LocalDate.of(2024, Month.JANUARY, 4));
        testCreateOrUpdateScheduleDTO.setDoseFrequency(32);
        testCreateOrUpdateScheduleDTO.setDoseCount(10);

        testSchedule = new Schedule();
        testSchedule.setScheduleID(0);
        testSchedule.setBeginningDate(LocalDate.of(2024, Month.JANUARY, 4));
        testSchedule.setDoseFrequency(32);
        testSchedule.setDoseCount(10);
    }

    @AfterEach
    public void tearDown() {
        testCreateOrUpdateScheduleDTO = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testPetId = 0;
        this.mockMvc.perform(get("/api/schedules/" + testPetId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Integer testScheduleId = 1;
        testScheduleDTO = testScheduleService.getScheduleById(testScheduleId);

        this.mockMvc.perform(get("/api/schedules/" + testScheduleId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.scheduleID").value(testScheduleDTO.getScheduleID()))
                .andExpect(jsonPath("$.beginningDate").value(testScheduleDTO.getBeginningDate().format(formatter)));

        // assert that call it in ScheduleControllerTest.testGettingIDWhichExistsInDatabase
        verify(testScheduleService, times(2)).getScheduleById(testScheduleId);
    }

    @Test
    @Transactional
    public void testDeletingById() throws Exception {
        testSchedule.setScheduleID(0);
        testSchedule.setDoseFrequency(31);
        testSchedule = testScheduleRepository.save(testSchedule);

        this.mockMvc.perform(delete("/api/schedules/" + testSchedule.getScheduleID()).with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.doseFrequency").value(testSchedule.getDoseFrequency()));

        ScheduleDTO searchedScheduleDTO = testScheduleService.getScheduleById(testSchedule.getDoseFrequency());
        assertThat(searchedScheduleDTO).isNull();

        // assert that the method deleteSchedule of testScheduleService is executed once.
        verify(testScheduleService, times(1)).deleteSchedule(anyInt());
    }

    @Test
    @Transactional
    public void testGetAllSchedules() throws Exception {
        List<Schedule> listOfAllAddedSchedule = new ArrayList<>();

        testSchedule.setDoseFrequency(31);
        testSchedule.setScheduleID(0);
        listOfAllAddedSchedule.add(testScheduleRepository.save(testSchedule));

        testSchedule.setDoseFrequency(32);
        testSchedule.setScheduleID(0);
        listOfAllAddedSchedule.add(testScheduleRepository.save(testSchedule));

        testSchedule.setDoseFrequency(33);
        testSchedule.setScheduleID(0);
        listOfAllAddedSchedule.add(testScheduleRepository.save(testSchedule));

        this.mockMvc.perform(get("/api/schedules/all").with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[1].doseFrequency").value(listOfAllAddedSchedule.get(0).getDoseFrequency()))
                .andExpect(jsonPath("$[2].doseFrequency").value(listOfAllAddedSchedule.get(1).getDoseFrequency()));

        for (Schedule addedSchedule : listOfAllAddedSchedule) {
            testScheduleRepository.deleteById(addedSchedule.getScheduleID());
        }
    }
}
