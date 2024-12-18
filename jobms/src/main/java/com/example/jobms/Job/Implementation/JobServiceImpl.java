package com.example.jobms.Job.Implementation;

import com.example.jobms.DTO.JobDTO;
import com.example.jobms.Job.External.Company;
import com.example.jobms.Job.External.Review;
import com.example.jobms.Job.JobRepository;
import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobService;
import com.example.jobms.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service                                                                   //It tells that this class is a service
public class JobServiceImpl implements JobService {
    
//    private final List<Job> jobs= new ArrayList<>();

    //Define repository object
    JobRepository jobRepository;

    @Autowired //this means provide us the instance of restTemplate on run time
    RestTemplate restTemplate;

    //Contractor
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

//    private long nextId = 1L;         //This variable which used to keep track of job_id that I have in my application.
                                      //Also, this will help with no duplicate job_id in my job[every I create new job, job_id will be unique].


    //Implement find all jobs
    @Override
//    public List<Job> findall() {
    public List<JobDTO> findall() {
//        RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
//        System.out.println("Company: "+company.getName());
//        System.out.println("Company: "+company.getId());
//        return jobRepository.findAll();

        //change the code for show every job with company with the help of DTO class
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        //use ConvertToDTO method using stream
        return jobs.stream() //convert the list into a stream
                .map(this::ConvertToDTO) //applying ConvertToDTO function with the help of Map() to every object in job
                .collect(Collectors.toList());//collect operation used to collect element of the stream into a new collection
    }


    private JobDTO ConvertToDTO(Job job){
        //add a for loop because, for every job I have in this list I need the company details
        //every job has a companyId, so with the help of loop I iterate list of job and fetch companyId from job
        //using RestTemplate call company microservice and get the company object and add it to the DTO

//        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//        jobWithCompanyDTO.setJob(job);
//        RestTemplate restTemplate = new RestTemplate();

        //call company api
        Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/"+job.getCompanyId(), Company.class);
        //call review api
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEWMS:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
        });

        List<Review> reviews = reviewResponse.getBody();

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
//        jobDTO.setCompany(company);

        return jobDTO;
    }

    //Implement create jobs
    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);      //It will do is, set current id to job object then it will increment by 1.
//        jobs.add(job);
        jobRepository.save(job);
    }


    //Implement getJobById
    //I need to add company object to the response, for that I use JobWithCompanyDTO class
    @Override
    public JobDTO getJobById(Long id) {
//        for(Job job: jobs){
//            if(job.getId().equals(id)) {
//                return job;
//            }
//        }
//        return null;
        Job job = jobRepository.findById(id).orElse(null);
        //Now convert this job into JobWithCompanyDTO object
        return ConvertToDTO(job);
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
