package com.production.ehayvanbackendapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.DTO.request.*;
import com.production.ehayvanbackendapi.Services.AppointmentService;
import com.production.ehayvanbackendapi.Services.PetOwnerService;
import com.production.ehayvanbackendapi.Services.VeterinarianService;
import com.production.ehayvanbackendapi.TestUtils.DataSeed;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private AppointmentService testAppointmentService;

    @SpyBean
    @Autowired
    private PetOwnerService testPetOwnerService;

    @SpyBean
    @Autowired
    private VeterinarianService testVeterinarianService;

    @Autowired
    private ObjectMapper objectMapper;

    private AppointmentDTO testAppointmentDTO;
    private CreateOrUpdateAppointmentDTO testCreateOrUpdateAppointmentDTO;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testCreateOrUpdateAppointmentDTO = new CreateOrUpdateAppointmentDTO();
        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(2024, Month.JANUARY, 4, 1, 1));
        testCreateOrUpdateAppointmentDTO.setVetID(1);
        testCreateOrUpdateAppointmentDTO.setPetOwnerID(1);
        testCreateOrUpdateAppointmentDTO.setPetID(1);
    }

    @AfterEach
    public void tearDown() {
        testCreateOrUpdateAppointmentDTO = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testPetId = 0;
        this.mockMvc.perform(get("/api/appointments/" + testPetId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Integer testPetId = 1;
        testAppointmentDTO = testAppointmentService.getAppointmentById(testPetId);

        this.mockMvc.perform(get("/api/appointments/" + testPetId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.appointmentID").value(testAppointmentDTO.getAppointmentID()))
                .andExpect(jsonPath("$.appointmentDate").value(testAppointmentDTO.getAppointmentDate().format(formatter)));

        // assert that call it in AppointmentControllerTest.testGettingIDWhichExistsInDatabase
        verify(testAppointmentService, times(2)).getAppointmentById(testPetId);
    }

    @Test
    @Transactional
    public void testPostingByID() throws Exception{
        when(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO)).thenReturn(new AppointmentDTO());
        this.mockMvc.perform(post("/api/appointments").with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // assert that just called in AppointmentControllerTest.testPostingID
        verify(testAppointmentService, times(1)).postAppointment(any());
    }

    @Test
    @Transactional
    public void testUpdatingById() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Integer testAppointmentId = 1;
        when(testAppointmentService.updateAppointment(testAppointmentId, testCreateOrUpdateAppointmentDTO)).thenReturn(new AppointmentDTO());

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1071, Month.JANUARY, 1, 1, 2));
        this.mockMvc.perform(put("/api/appointments/update/" + testAppointmentId).with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.appointmentDate").value(testCreateOrUpdateAppointmentDTO.getAppointmentDate().format(formatter)));

        // assert that the method updateAppointment of testAppointmentService is executed once
        verify(testAppointmentService, times(1)).updateAppointment(anyInt(), any());
    }

    @Test
    @Transactional
    public void testDeletingById() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1999, Month.APRIL, 1, 1, 1));
        testAppointmentDTO = testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO);

        this.mockMvc.perform(delete("/api/appointments/" + testAppointmentDTO.getAppointmentID()).with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.appointmentDate").value(testCreateOrUpdateAppointmentDTO.getAppointmentDate().format(formatter)));

        AppointmentDTO searchedAppointmentDTO = testAppointmentService.getAppointmentById(testAppointmentDTO.getAppointmentID());
        assertThat(searchedAppointmentDTO).isNull();

        // assert that the method deleteAppointment of testAppointmentService is executed once.
        verify(testAppointmentService, times(1)).deleteAppointment(anyInt());
    }

    @Test
    @Transactional
    public void testGetAllAppointments() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<AppointmentDTO> listOfAllAddedAppointmentDTO = new ArrayList<>();

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1999, Month.APRIL, 1, 1, 1));
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1000, Month.FEBRUARY, 1, 1, 1));
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        this.mockMvc.perform(get("/api/appointments/all").with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[1].appointmentDate").value(listOfAllAddedAppointmentDTO.get(0).getAppointmentDate().format(formatter)))
                .andExpect(jsonPath("$[2].appointmentDate").value(listOfAllAddedAppointmentDTO.get(1).getAppointmentDate().format(formatter)));

        for (AppointmentDTO addedAppointmentDTO : listOfAllAddedAppointmentDTO) {
            testAppointmentService.deleteAppointment(addedAppointmentDTO.getAppointmentID());
        }
    }

    @Test
    @Transactional
    public void testGetAllAppointmentsForPetOwner() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        List<PetOwnerDTO> listOfAllAddedPetOwnerDTO = new ArrayList<>();
        CreateOrUpdatePetOwnerDTO otherPetOwner = new CreateOrUpdatePetOwnerDTO();
        otherPetOwner.setUser(new CreateOrUpdateCustomerDTO("ziyaretci", "miyaretci", "ankara@met.com", "allright"));

        listOfAllAddedPetOwnerDTO.add(testPetOwnerService.postPetOwner(otherPetOwner));
        listOfAllAddedPetOwnerDTO.add(testPetOwnerService.postPetOwner(otherPetOwner));

        List<AppointmentDTO> listOfAllAddedAppointmentDTO = new ArrayList<>();

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1999, Month.APRIL, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setPetOwnerID(listOfAllAddedPetOwnerDTO.get(0).getPetOwnerID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1000, Month.FEBRUARY, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setPetOwnerID(listOfAllAddedPetOwnerDTO.get(0).getPetOwnerID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(90, Month.JUNE, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setPetOwnerID(listOfAllAddedPetOwnerDTO.get(1).getPetOwnerID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        this.mockMvc.perform(get("/api/appointments/all/" + listOfAllAddedPetOwnerDTO.get(0).getPetOwnerID()).with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(listOfAllAddedPetOwnerDTO.size())))
                .andExpect(jsonPath("$[0].petOwnerID").value(listOfAllAddedPetOwnerDTO.get(0).getPetOwnerID()))
                .andExpect(jsonPath("$[1].petOwnerID").value(listOfAllAddedPetOwnerDTO.get(0).getPetOwnerID()));

        for (AppointmentDTO addedAppointmentDTO : listOfAllAddedAppointmentDTO) {
            testAppointmentService.deleteAppointment(addedAppointmentDTO.getAppointmentID());
        }

        for (PetOwnerDTO addedPetOwner : listOfAllAddedPetOwnerDTO) {
            testPetOwnerService.deletePetOwner(addedPetOwner.getPetOwnerID());
        }
    }

    @Test
    @Transactional
    public void testGetAllAppointmentsForVeterinarian() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        List<VeterinarianDTO> listOfAllAddedVeterinarianDTO = new ArrayList<>();
        CreateOrUpdateVeterinarianDTO otherVeterinarian = new CreateOrUpdateVeterinarianDTO();
        otherVeterinarian.setUser(new CreateOrUpdateCustomerDTO("ziyaretci", "miyaretci", "ankara@met.com", "allright"));
        otherVeterinarian.setClinic("soyle bilelim");

        listOfAllAddedVeterinarianDTO.add(testVeterinarianService.postVeterinarian(otherVeterinarian));
        listOfAllAddedVeterinarianDTO.add(testVeterinarianService.postVeterinarian(otherVeterinarian));

        List<AppointmentDTO> listOfAllAddedAppointmentDTO = new ArrayList<>();

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1999, Month.APRIL, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setVetID(listOfAllAddedVeterinarianDTO.get(0).getVetID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(1000, Month.FEBRUARY, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setVetID(listOfAllAddedVeterinarianDTO.get(0).getVetID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        testCreateOrUpdateAppointmentDTO.setAppointmentDate(LocalDateTime.of(90, Month.JUNE, 1, 1, 1));
        testCreateOrUpdateAppointmentDTO.setVetID(listOfAllAddedVeterinarianDTO.get(1).getVetID());
        listOfAllAddedAppointmentDTO.add(testAppointmentService.postAppointment(testCreateOrUpdateAppointmentDTO));

        this.mockMvc.perform(get("/api/appointments/all/veterinarian/" + listOfAllAddedVeterinarianDTO.get(0).getVetID()).with(httpBasic("test", "password"))
                        .content(objectMapper.writeValueAsString(testCreateOrUpdateAppointmentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(listOfAllAddedVeterinarianDTO.size())))
                .andExpect(jsonPath("$[0].vetID").value(listOfAllAddedVeterinarianDTO.get(0).getVetID()))
                .andExpect(jsonPath("$[1].vetID").value(listOfAllAddedVeterinarianDTO.get(0).getVetID()));

        for (AppointmentDTO addedAppointmentDTO : listOfAllAddedAppointmentDTO) {
            testAppointmentService.deleteAppointment(addedAppointmentDTO.getAppointmentID());
        }

        for (VeterinarianDTO addedVeterinarian : listOfAllAddedVeterinarianDTO) {
            testVeterinarianService.deleteVeterinarian(addedVeterinarian.getVetID());
        }
    }
}
