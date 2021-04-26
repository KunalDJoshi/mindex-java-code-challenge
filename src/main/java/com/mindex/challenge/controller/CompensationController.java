package com.mindex.challenge.controller;

/** 
 *CompensationController class contains Post and Get methods for the compensation.
 *@author Kunal Joshi
 *version 1.0
 *@since 2021-04-25
 */
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeNotFoundException;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;
    /**
    This is compensation REST endpoint to create by employeeId. 
    */
    @PostMapping("/employee/{id}/compensation")
    public ResponseEntity<Compensation> create(@PathVariable String id, @RequestBody Compensation comp) {
        LOG.debug("Received compensation create request for [{}]", comp);
        try {
            comp = compensationService.create(id, comp);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.warn("Failure while creating employee compensation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(comp, HttpStatus.OK);
    }
    /**
    This is compensation REST endpoint to read by employeeId. 
    */
    @GetMapping("/employee/{id}/compensation")
    public ResponseEntity<Compensation> read(@PathVariable String id) {
        LOG.debug("Received read compensation request for id [{}]", id);

        try {
            Compensation comp = compensationService.read(id);
            return new ResponseEntity<>(comp, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.warn("Failure while reading employee compensation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}