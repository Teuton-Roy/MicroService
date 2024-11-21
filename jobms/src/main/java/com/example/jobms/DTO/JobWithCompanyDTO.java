package com.example.jobms.DTO;
import com.example.jobms.Job.External.Company;
import com.example.jobms.Job.Job;

//implement DTO Class

public class JobWithCompanyDTO {
    private Job job;
    private Company company;


    //Getter and Setter
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
