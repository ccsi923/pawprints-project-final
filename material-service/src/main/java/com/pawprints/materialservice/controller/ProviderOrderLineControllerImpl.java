package com.pawprints.materialservice.controller;

import com.pawprints.materialservice.controller.impl.ProviderOrderLineController;
import com.pawprints.materialservice.model.ProviderOrderLine;
import com.pawprints.materialservice.model.dto.DateBetweenRequest;
import com.pawprints.materialservice.repository.ProviderOrderLineRepository;
import com.pawprints.materialservice.service.ProviderOrderLineService;
import com.pawprints.materialservice.service.ProviderOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProviderOrderLineControllerImpl implements ProviderOrderLineController {

    @Autowired
    private ProviderOrderLineService providerOrderLineService;

    @GetMapping("/providerorderlines/by/providerorder/{probid}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(@PathVariable("probid") Integer probId){
        return providerOrderLineService.findAllProviderOrderLineByOrderId(probId);
    }

    @PutMapping("/expenses/between")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> expensesPerDateBetween(@RequestBody DateBetweenRequest dateBetweenRequest){
        return providerOrderLineService.expensesPerDateBetween(dateBetweenRequest.getStart(), dateBetweenRequest.getEnd());
    }

    @GetMapping("/expenses/per/date")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> expensesPerDate(){
        return providerOrderLineService.expensesPerDate();
    }
}
