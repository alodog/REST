package com.zorin.coffee.service;

import com.zorin.coffee.dao.OperationHistoryRepository;
import com.zorin.coffee.dao.WorkingCapacityRepository;
import com.zorin.coffee.entity.OperationHistory;
import com.zorin.coffee.entity.Operations;
import com.zorin.coffee.entity.WorkingCapacity;
import com.zorin.coffee.exeption_handing.NoSuchOperationHistoryException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Zorin Sergey
 */

@Service
public class OperationHistoryServiceImpl implements OperationHistoryService {

    @Autowired
    private OperationHistoryRepository operationHistoryRepository;

    @Autowired
    private WorkingCapacityRepository workingCapacityRepository;

    @Override
    public List<OperationHistory> getAllOperationsHistory() {
        return operationHistoryRepository.findAll();
    }

    @Override
    public OperationHistory getOperationHistory(int id) throws NoSuchOperationHistoryException {

        Optional<OperationHistory> optional = operationHistoryRepository.findById(id);
        if(!optional.isPresent()){
            throw new NoSuchOperationHistoryException("История данной операции отсутствует!");
        }
        return optional.get();
    }

    @Override
    public void saveOperationHistory(OperationHistory operation) {
        WorkingCapacity workingCapacity = workingCapacityRepository.getById(1);
        if(operation.getOperation().equals(Operations.ON)){
            workingCapacity.setSwitchedOn(true);
            workingCapacityRepository.save(workingCapacity);
        }
        if(operation.getOperation().equals(Operations.OFF)){
            workingCapacity.setSwitchedOn(false);
            workingCapacityRepository.save(workingCapacity);
        }
        operationHistoryRepository.save(operation);
    }

    @Override
    public void deleteOperationHistory(int id) {
        operationHistoryRepository.deleteById(id);
    }

    public boolean isSwitchOn() {
        return workingCapacityRepository.getById(1).isSwitchedOn();
    }


}
