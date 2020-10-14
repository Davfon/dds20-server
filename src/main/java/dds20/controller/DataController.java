package dds20.controller;

import dds20.entity.Data;
import dds20.rest.dto.DataGetDTO;
import dds20.rest.dto.InquiryPostDTO;
import dds20.rest.dto.MessagePostDTO;
import dds20.rest.mapper.DTOMapper;
import dds20.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Controller
 * This class is responsible for handling all REST request that are related to the data.
 * The controller will receive the request and delegate the execution to the DataService and finally return the result.
 */
@RestController
public class DataController {

    private final DataService dataService;

    DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DataGetDTO getInfo() {
        Data data = dataService.getData();
        return DTOMapper.INSTANCE.convertEntityToDataGetDTO(data);
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void postMessage(@RequestBody MessagePostDTO messagePostDTO) {
        Data data = DTOMapper.INSTANCE.convertMessagePostDTOtoEntity(messagePostDTO);
        dataService.saveData(data);
    }

    @PostMapping("/inquiry")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void postInquiry(@RequestBody InquiryPostDTO inquiryPostDTO) {
        Data data = dataService.getDataFromTransId(inquiryPostDTO.getTransId());
    }
}
