package com.example.companyms.company;

import java.util.*;

public interface CompanyService {

    //Define methods here...
    List<Company> getallCompanies();
    void createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
