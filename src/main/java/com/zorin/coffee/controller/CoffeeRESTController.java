package com.zorin.coffee.controller;

import com.zorin.coffee.entity.OperationHistory;
import com.zorin.coffee.entity.Operations;
import com.zorin.coffee.exeption_handing.CoffeeMachineSwitchedOff;
import com.zorin.coffee.exeption_handing.NoSuchOperationHistoryException;
import com.zorin.coffee.exeption_handing.NoSwitchedOnException;
import com.zorin.coffee.service.OperationHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author Zorin Sergey
 */
@RestController
@RequestMapping("/")
@Api(tags = "Контроллер кофеварки", description = "Позволяет созхранять историю операций кофеварки.")
public class CoffeeRESTController {

    @Autowired
    private OperationHistoryService operationHistoryService;

    @GetMapping("/operations")
    @ApiOperation(value = "Возвращает список истории всех операций")
    public List<OperationHistory> getAllOperationsHistory(){
        return operationHistoryService.getAllOperationsHistory();
    }

    @GetMapping("/operations/{id}")
    @ApiOperation(value = "Возвращает истрию операции с заданным Id")
    public OperationHistory getOperationHistory(@PathVariable int id) throws NoSuchOperationHistoryException {
        return operationHistoryService.getOperationHistory(id);
    }

    @PostMapping("/operations")
    @ApiOperation(value = "Если включена кофеварка, то позволяет выполнить операцию и сохранить ее.")
    public OperationHistory addNewOperationHistory(@RequestBody OperationHistory operationHistory) throws NoSwitchedOnException {
        if(!operationHistoryService.isSwitchOn() && !operationHistory.getOperation().equals(Operations.ON)){
            throw new NoSwitchedOnException("Кофе машина выключена!");
        }
        operationHistoryService.saveOperationHistory(operationHistory);
        return operationHistory;
    }

    @PutMapping("/operations")
    @ApiOperation(value = "Позволяет редактировать операцию по переданному Id.")
    public OperationHistory updateOperationHistory(@RequestBody OperationHistory operationHistory){
        operationHistoryService.saveOperationHistory(operationHistory);
        return operationHistory;
    }

    @DeleteMapping("/operations/{id}")
    @ApiOperation(value = "Удаляет операцию по переданному Id.")
    public String deleteOperationHistory(@PathVariable int id){
        operationHistoryService.deleteOperationHistory(id);
        return "Операция с Id=" + id + " была удалена";
    }

    @ExceptionHandler
    public ResponseEntity<CoffeeMachineSwitchedOff> handlerSwitchOnException(NoSwitchedOnException exception){
        CoffeeMachineSwitchedOff status = new CoffeeMachineSwitchedOff();
        status.setInfo(exception.getMessage());
        return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<CoffeeMachineSwitchedOff> handlerNoSuchException(NoSuchOperationHistoryException exception){
        CoffeeMachineSwitchedOff status = new CoffeeMachineSwitchedOff();
        status.setInfo(exception.getMessage());
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }
}
