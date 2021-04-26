package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeNotFoundException;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/** 
 *EmployeeController class contains Post, Get, Put methods for the employee and Get method for reports.
 *@author Kunal Joshi
 *version 1.0
 *@since 2021-04-25
 */
@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    /**
    This is employee REST endpoint to create employee. 
    */
    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }
    /**
    This is employee REST endpoint to read by employeeId. 
    */
    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }
    /**
    This is employee REST endpoint to put by employeeId. 
    */
    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    /**
    This is reports REST endpoint to read by employeeId. 
    */
    @GetMapping("/employee/{id}/reports")
    public ResponseEntity<ReportingStructure> reports(@PathVariable String id) {
        LOG.debug("Received employee reports request for id [{}]", id);

        try {
            ReportingStructure reportingStructure = employeeService.reports(id);
            return new ResponseEntity<>(reportingStructure, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.warn("Failure while generating employee reports", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}