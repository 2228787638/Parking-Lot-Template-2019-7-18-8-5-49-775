package com.thoughtworks.parking_lot.controller;

import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ParkingLotControllerTest {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void should_return_a_new_ParkingLot_when_request_addParkingLot() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"place1");
        mockMvc.perform(post("/parkinglots").contentType(MediaType.APPLICATION_JSON).
                content(gson.toJson(parkingLot)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(parkingLot.getName()));
    }

    @Test
    void getParkingLots() throws Exception {
        for(int i=0;i<28;i++){
            ParkingLot parkingLot=parkingLotRepository.save(new ParkingLot("parkinglot"+i,10,"place"+i));
        }
        mockMvc.perform(get("/parkinglots?page={page}",2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(13));
    }
    @Test
    void deleteParingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"place1");
        parkingLotRepository.save(parkingLot);
        mockMvc.perform(delete("/parkinglots/{parkingLotId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(parkingLot)));

    }
    @Test
    void getParkingLotById() throws Exception {
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"place1");
        parkingLotRepository.save(parkingLot);
        mockMvc.perform(get("/parkinglots/{parkingLotId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("parkingLot1"));
    }

    @Test
    void updateParkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"place1");
        ParkingLot updateParkingLot = new ParkingLot("parkingLot1",20,"place1");
        parkingLotRepository.save(parkingLot);
        mockMvc.perform(put("/parkinglots/{parkingLotId}",1).
                contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(updateParkingLot)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity").value(20));
    }

}