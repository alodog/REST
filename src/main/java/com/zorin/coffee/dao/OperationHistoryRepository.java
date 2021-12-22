/**
 * @author Zorin Sergey
 */

package com.zorin.coffee.dao;

import com.zorin.coffee.entity.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Integer> {

}
