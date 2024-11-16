package com.example.jobms.Job;
import jakarta.persistence.*;

@Entity                       //This @annotation tells Spring-Boot and JPA that this is the class this supposed to be mapped to a table.
//@Table(name ="Job_Table")     //Instead of having same name as class name this @Table annotation change the table name.
public class Job {

    //Need to define what we need in this class, and what all info we need about the job...
    //fields for every jobs

    @Id      //this annotation tells this the ID or Primary key. No need manage Primary Key so far I'm doing this by [nextId] variable.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Using this annotation it will be automatically managed by JPA
    private Long id; //unique id for each job
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId; //Instead of Company entity store company ID.

    //define the relationship between Job and Company...
//    @ManyToOne //many job s can be associated with one company
//    private Company company; // No Longer access the company


    //Default constructor
    public Job() {
        /* needed because, Entities are objects that represent the data in the relational database
           Requirement for JPA, Because JPA needs to create instances of Entity class during the retrieval of data
           From the database.*/
    }

    //constructor
    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location, String company){
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
