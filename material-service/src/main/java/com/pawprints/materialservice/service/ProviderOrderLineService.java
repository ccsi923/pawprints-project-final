package com.pawprints.materialservice.service;

import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.ProviderOrderLine;
import com.pawprints.materialservice.repository.ProviderOrderLineRepository;
import com.pawprints.materialservice.repository.ProviderOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProviderOrderLineService {

    private static final Logger LOGGER = LogManager.getLogger(ProviderOrderLineService.class);

    @Autowired
    private ProviderOrderLineRepository providerOrderLineRepository;

    @Autowired
    private ProviderOrderRepository providerOrderRepository;

    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(Integer ordId){
        LOGGER.info("INIT - findAllProviderOrderLineByOrderId");
        ProviderOrder providerOrder = providerOrderRepository.findById(ordId).orElseThrow(()-> new NotFoundException("Order with id " + ordId +
                " not found"));
        LOGGER.info("END - findAllProviderOrderLineByOrderId");
        return providerOrderLineRepository.findAllByProviderOrder(providerOrder);
    }

    public List<Object[]> expensesPerDateBetween(LocalDate date, LocalDate date2){
        LOGGER.info("INIT - expensesPerDateBetween");
        LOGGER.info("END - expensesPerDateBetween");
        return providerOrderLineRepository.expensesPerDateBetween(date, date2);
    }

    public List<Object[]> expensesPerDate(){
        LOGGER.info("INIT - expensesPerDate");
        LOGGER.info("END - expensesPerDate");
        return providerOrderLineRepository.expensesPerDate();
    }
}
