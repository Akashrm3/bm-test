package com.bm;

import com.bm.model.Branch;
import com.bm.model.Schedule;
import com.bm.model.Vaccine;
import com.bm.repository.BranchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected BranchRepository branchRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    protected String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void shouldNotReturnBranchList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/branch")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void shouldCreateBranch() throws Exception {
        Branch branch = new Branch();
        Set<Vaccine> set = new HashSet<>();
        set.add(new Vaccine());
        branch.setVaccine(set);
        String json = toJson(branch);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/branch")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotScheduleAppointmentAsNoSlotAvailable() throws Exception {
        Branch branch = new Branch();
        Set<Vaccine> set = new HashSet<>();
        set.add(new Vaccine());
        branch.setVaccine(set);
        String json = toJson(branch);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/branch")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String resultString = result.getResponse().getContentAsString();
        Branch branchSaved = objectMapper.readValue(resultString, Branch.class);
        System.out.println(branchSaved.getId());
        Schedule schedule = new Schedule();
        schedule.setBranchId(branchSaved.getId());
        String payload = toJson(schedule);
        ResultActions resultActions2 = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/schedule")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    void shouldScheduleAppointment() throws Exception {
        branchRepository.deleteAll();
        Branch branch = new Branch();
        branch.setSlotAvailable(10);
        Set<Vaccine> set = new HashSet<>();
        set.add(new Vaccine());
        branch.setVaccine(set);
        String json = toJson(branch);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/branch")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String resultString = result.getResponse().getContentAsString();
        Branch branchSaved = objectMapper.readValue(resultString, Branch.class);
        System.out.println(branchSaved.getId());
        Schedule schedule = new Schedule();
        schedule.setBranchId(branchSaved.getId());
        String payload = toJson(schedule);
        ResultActions resultActions2 = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/schedule")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void shouldGenerateInvoice() throws Exception {
        branchRepository.deleteAll();
        Branch branch = new Branch();
        branch.setSlotAvailable(10);
        Set<Vaccine> set = new HashSet<>();
        set.add(new Vaccine());
        branch.setVaccine(set);
        String json = toJson(branch);
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/branch")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String resultString = result.getResponse().getContentAsString();
        Branch branchSaved = objectMapper.readValue(resultString, Branch.class);
        System.out.println(branchSaved.getId());
        Schedule schedule = new Schedule();
        schedule.setBranchId(branchSaved.getId());
        String payload = toJson(schedule);
        ResultActions resultActions2 = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/schedule")
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result2 = resultActions2.andReturn();
        String resultString2 = result2.getResponse().getContentAsString();
        Schedule scheduleSaved = objectMapper.readValue(resultString2, Schedule.class);
        ResultActions resultActions3 = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/invoice/appointment/"+scheduleSaved.getId())
                .header("x-api-key", "key")
                .header("x-api-secret", "secret")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
