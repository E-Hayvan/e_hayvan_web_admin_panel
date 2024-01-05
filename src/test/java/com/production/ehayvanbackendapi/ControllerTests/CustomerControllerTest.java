package com.production.ehayvanbackendapi.ControllerTests;

import com.production.ehayvanbackendapi.Controllers.CustomerController;
import com.production.ehayvanbackendapi.DTO.*;
import com.production.ehayvanbackendapi.DTO.request.*;
import com.production.ehayvanbackendapi.Entities.Customer;
import com.production.ehayvanbackendapi.Repositories.CustomerRepository;
import com.production.ehayvanbackendapi.Services.CustomerService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private CustomerService testCustomerService;

    @SpyBean
    @Autowired
    private CustomerRepository testCustomerRepository;

    private CustomerDTO testCustomerDTO;

    private Customer testCustomer;

    @Autowired
    DataSeed dataSeed;

    @BeforeAll
    public void setUp() {
        dataSeed.loadSeedToDatabase();
    }

    @BeforeEach
    public void onEachTestStart() {
        testCustomer = new Customer();
        testCustomer.setUserID(0);
        testCustomer.setName("elma");
        testCustomer.setSurname("manzukich");
        testCustomer.setEmail("astra@co");
        testCustomer.setPassword("satanaki");
    }

    @AfterEach
    public void tearDown() {
        testCustomer = null;
    }

    @Test
    public void testGettingByIDWhichNoExists() throws Exception{
        int testPetId = 0;
        this.mockMvc.perform(get("/api/customers/" + testPetId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testGettingByIDWhichExistsInDatabase() throws Exception {
        Integer testPetId = 1;
        testCustomerDTO = testCustomerService.getCustomerById(testPetId);

        this.mockMvc.perform(get("/api/customers/" + testPetId).with(httpBasic("test", "password")))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userID").value(testCustomerDTO.getUserID()))
                .andExpect(jsonPath("$.password").value(testCustomerDTO.getPassword()));

        // assert that call it in CustomerControllerTest.testGettingIDWhichExistsInDatabase
        verify(testCustomerService, times(2)).getCustomerById(testPetId);
    }

    @Test
    @Transactional
    public void testDeletingById() throws Exception {
        testCustomer.setUserID(0);
        testCustomer.setName("Apachi");
        testCustomer = testCustomerRepository.save(testCustomer);

        this.mockMvc.perform(delete("/api/customers/" + testCustomer.getUserID()).with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(testCustomer.getName()));

        CustomerDTO searchedCustomerDTO = testCustomerService.getCustomerById(testCustomer.getUserID());
        assertThat(searchedCustomerDTO).isNull();

        // assert that the method deleteCustomer of testCustomerService is executed once.
        verify(testCustomerService, times(1)).deleteCustomer(anyInt());
    }

    @Test
    @Transactional
    public void testGetAllCustomers() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Customer> listOfAllAddedCustomer = new ArrayList<>();

        testCustomer.setName("Afaki");
        testCustomer.setUserID(0);
        listOfAllAddedCustomer.add(testCustomerRepository.save(testCustomer));

        testCustomer.setName("Bazar");
        testCustomer.setUserID(0);
        listOfAllAddedCustomer.add(testCustomerRepository.save(testCustomer));

        testCustomer.setName("Deodorant");
        testCustomer.setUserID(0);
        listOfAllAddedCustomer.add(testCustomerRepository.save(testCustomer));


        this.mockMvc.perform(get("/api/customers/all").with(httpBasic("test", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[2].name").value(listOfAllAddedCustomer.get(0).getName()))
                .andExpect(jsonPath("$[3].name").value(listOfAllAddedCustomer.get(1).getName()));

        for (Customer addedCustomer : listOfAllAddedCustomer) {
            testCustomerService.deleteCustomer(addedCustomer.getUserID());
        }
    }
}
