package com.example.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.VfData;

@Service
public class ReportService {
    @Autowired
    private VfData vfData;
}
