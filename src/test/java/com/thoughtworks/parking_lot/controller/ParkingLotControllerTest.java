package com.thoughtworks.parking_lot.controller;

import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {
    @MockBean
    private ParkingLotService parkingLotService;
    @MockBean
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void should_return_a_new_ParkingLot_when_request_addParkingLot() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot = new ParkingLot("1", 10, "where");
        when(parkingLotRepository.save(any(ParkingLot.class))).thenReturn(parkingLot);
        mockMvc.perform(post("/parkinglots").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(parkingLot)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getParkingLots() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>(20);
        for (int i = 15; i <28; i++) {
            ParkingLot parkingLot = new ParkingLot("parkinglot"+i,10,"place"+i);
            parkingLot.setId(i);
            parkingLots.add(parkingLot);
        }
        given(parkingLotService.getParkingLotsByPage(2, 15)).willReturn(parkingLots);
        mockMvc.perform(get("/parkinglots?page={page}",2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(13));
    }
    @Test
    public void deleteParingLot() throws Exception {
        given(parkingLotService.deleteParkingLot(1)).willReturn(new ParkingLot("parkingLot1",10,"place1"));
        mockMvc.perform(delete("/parkinglots/{parkingLotId}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("parkingLot1"));

    }
    @Test
    public void getParkingLotById() throws Exception {
        given(parkingLotService.getParkingLotById(1)).willReturn(new ParkingLot(1,"parkingLot1",10,"place1"));
        mockMvc.perform(get("/parkinglots/{parkingLotId}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("parkingLot1"));
    }

    @Test
    public void updateParkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot("parkingLot1",20,"place1");
        ParkingLot updateParkingLot = new ParkingLot(1,"parkingLot1",20,"place1");
        Gson gson = new Gson();
        given(parkingLotService.updateParkingLot(1,parkingLot)).willReturn(updateParkingLot);
        mockMvc.perform(put("/parkinglots/{parkingLotId}",1).
                contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(parkingLot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity").value(20));
    }
}