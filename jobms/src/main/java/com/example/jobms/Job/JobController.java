package com.example.jobms.Job;

import com.example.jobms.DTO.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@RequestMapping("/jobs")
public class JobController {

    //Defining job controller here...

//    private List<Job> jobs= new ArrayList<>(); //List of jobs

    private final JobService jobservice;  //job_service will be available at runtime, and inject it to job_controller

    //constructor
    public JobController(JobService jobservice) {
        this.jobservice = jobservice;
    }

    //Get list of all jobs
    @GetMapping("/jobs")
    //this the endpoint we are creating here which return all the jobs with company
//    public ResponseEntity<List<Job>> findAll(){
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity.ok(jobservice.findall());
    }

    //Create a new job
    @PostMapping("/create_jobs")
    public ResponseEntity<String> CreateJob(@RequestBody Job job){
         //this the endpoint we are creating here which return all the jobs
        jobservice.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.OK);
    }

    //Get specific job by ID
//    @GetMapping("/jobsById/{id}") // {} -> dynamic, id -> variable name
//    public Job getJobById(@PathVariable Long id) {
//
//        //public Job, why? Because fetching a particular job, this will return a Job Object.
//        //@PathVariable ->  this is used to extract the value from the URL.
//
//        Job job = jobservice.getJobById(id);
//
//        //add validation, if given id is not present then show this dummy job
//        if (job != null){
//            return job;
//        }
//        return new Job(0L,
//                "dummy job",
//                "Their no job associated with this id",
//                "000",
//                "000",
//                "India"
//        );
//    }

    //Get specific job by ID using ResponseEntity for batter readability and HTTP response
    @GetMapping("/jobsById/{id}") // {} -> dynamic, id -> variable name
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobservice.getJobById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Delete a specific job by ID

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id){
        /* Create a boolean variable Because, I am passing the [id] to the JobService, and this will take care of the
           deletion, if the deletion is successful it will return True if not then it will return False */

        boolean deleted = jobservice.DeleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Update a specific job by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> UpdateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated =   jobservice.UpdateJobById(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


