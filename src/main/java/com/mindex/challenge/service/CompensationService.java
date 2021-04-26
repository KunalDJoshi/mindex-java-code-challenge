package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

public interface CompensationService {
    Compensation create(String id, Compensation comp) throws Exception;
    Compensation read(String id) throws Exception;
}