/**
 * @author Zorin Sergey
 */

package com.zorin.coffee.service;

import com.zorin.coffee.entity.OperationHistory;
import com.zorin.coffee.exeption_handing.NoSuchOperationHistoryException;

import java.util.List;

public interface OperationHistoryService {

    List<OperationHistory> getAllOperationsHistory();
    OperationHistory getOperationHistory(int id) throws NoSuchOperationHistoryException;
    void saveOperationHistory(OperationHistory operation);
    void deleteOperationHistory(int id);
    boolean isSwitchOn();

}
