package com.mindex.challenge.service.impl;

/** 
 *EmployeeServiceImpl implements EmployeeService.
 *@author Kunal Joshi
 *version 1.0
 *@since 2021-04-25
 */
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeNotFoundException;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    /**
	This method is for determining the number of reports under a given employee such as the number of directReports for an employee and all of their distinct reports.
    @return ReportingStructure.
	*/
    @Override
    public ReportingStructure reports(String id) throws Exception {
        LOG.debug("Employee reports for id [{}]", id);

        int numReports = 0;
        Employee root = employeeRepository.findByEmployeeId(id);
        if (root == null) {
            throw new EmployeeNotFoundException("employee with id: " + id + " not found");
        }
        Queue<String> eQueue = new LinkedList<>();
        eQueue.add(id);
        while (!eQueue.isEmpty()) {
            Employee e = employeeRepository.findByEmployeeId(eQueue.remove());
            numReports++;
            if (e.getDirectReports() == null) {
                continue;
            }
            for (Employee directReport : e.getDirectReports()) {
                eQueue.add(directReport.getEmployeeId());
            }
        }
        // subtract 1 so that we don't count the root as its own report
        return new ReportingStructure(root, numReports - 1);
    }
}