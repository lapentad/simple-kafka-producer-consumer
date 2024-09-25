package com.demo.kafkaproducer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.demo.kafkaproducer.model.Job;

import lombok.Data;

@Data
@Service
public class JobService {

    public List<Job> jobList = new ArrayList<>();

}
