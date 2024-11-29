package com.example.jobms.Job;

import com.example.jobms.DTO.JobDTO;

import java.util.*;

//Created this as an Interface to promote loss coupling.
//Loss Coupling: Loose coupling is a design principle that aims to reduce the dependencies between different components or modules of a software system.
// Why we use? Because, have some modularity in my code-base, so I can separate the interface and the implementation.
public interface JobService {


    //defining methods here...
//    List<Job>findall();
    List<JobDTO> findall();
    void createJob(Job job);
    JobDTO getJobById(Long id);
    boolean DeleteJobById(Long id);
    boolean UpdateJobById(Long id, Job updatedJob);
}
