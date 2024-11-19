package com.example.jobms.Job.Implementation;

import com.example.jobms.Job.External.Company;
import com.example.jobms.Job.JobRepository;
import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service                                                                   //It tells that this class is a service
public class JobServiceImpl implements JobService {
    
//    private final List<Job> jobs= new ArrayList<>();

    //Define repository object
    JobRepository jobRepository;

    //Contractor
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

//    private long nextId = 1L;         //This variable which used to keep track of job_id that I have in my application.
                                      //Also, this will help with no duplicate job_id in my job[every I create new job, job_id will be unique].


    //Implement find all jobs
    @Override
    public List<Job> findall() {
//        return jobs;
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
        System.out.println("Company: "+company.getName());
        System.out.println("Company: "+company.getId());
        return jobRepository.findAll();
    }


    //Implement create jobs
    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);      //It will do is, set current id to job object then it will increment by 1.
//        jobs.add(job);
        jobRepository.save(job);
    }


    //Implement getJobById
    @Override
    public Job getJobById(Long id) {
//        for(Job job: jobs){
//            if(job.getId().equals(id)) {
//                return job;
//            }
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }


    //Implement DeleteJobById
    @Override
    public boolean DeleteJobById(Long id) {
//        Iterator<Job> it = jobs.iterator(); //with the help of Iterator I travers through the list and fetching every
//        while (it.hasNext()){               //object present in the list.
//            Job job = it.next();            //Then assign it to job object
//            if(job.getId().equals(id)){     //compare the ID
//                it.remove();                //if ID matched then delete and return TRUE...
//                return true;
//            }
//        }
//        return false;

        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //Implement UpdateJobById
    @Override
    public boolean UpdateJobById(Long id, Job updatedJob) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) {
//                //Update Logic
//                //job.setId(updatedJob.getId()); not updating cause it's a unique identifier so it not suppose to update
//                job.setCompany(updatedJob.getCompany());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//        }
//        return false;

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            //Update Logic
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
