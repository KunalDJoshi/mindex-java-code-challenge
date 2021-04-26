package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(String id, Compensation comp) throws Exception {
        LOG.debug("Creating compensation [{}]", comp);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with Id: " + comp.getEmployeeId());
        }
        comp.setEffectiveDate(LocalDate.now());
        employee.setCompensation(comp);
        employeeRepository.save(employee);
        return comp;
    }

    @Override
    public Compensation read(String id) throws Exception {
        LOG.debug("Getting compensation for employee ID [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with Id: " + id);
        }
        return employee.getCompensation();
    }
}