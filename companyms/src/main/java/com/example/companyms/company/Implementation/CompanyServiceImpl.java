package com.example.companyms.company.Implementation;
import com.example.companyms.company.Company;
import com.example.companyms.company.CompanyRepository;
import com.example.companyms.company.CompanyService;
import com.example.companyms.company.External.Review;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getallCompanies() {

        RestTemplate restTemplate = new RestTemplate();
        Review review = restTemplate.getForObject("http://localhost:8083/reviews/1", Review.class);
        System.out.println("Review "+ review.getTitle());
        System.out.println("Description "+review.getDescription());


        return companyRepository.findAll();  //this method returns all the instance of the company in database
    }

    @Override
    public void createCompany(Company company) { //create company
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> jobOptional = companyRepository.findById(id);
        if (jobOptional.isPresent()) {
            Company companyToUpdate = jobOptional.get();
            //Update Logic
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
//            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        //Delete logic...
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
