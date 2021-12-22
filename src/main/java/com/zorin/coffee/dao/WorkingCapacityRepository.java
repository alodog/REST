/**
 * @author Zorin Sergey
 */

package com.zorin.coffee.dao;

import com.zorin.coffee.entity.WorkingCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingCapacityRepository extends JpaRepository<WorkingCapacity, Integer> {

}
