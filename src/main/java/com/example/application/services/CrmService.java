package com.example.application.services;

import com.example.application.data.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CrmService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository, CompanyRepository companyRepository, StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContacts(String stringFilter)
    {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    //Find All Companies
    public List<Company> findAllCompanies()
    {
        return companyRepository.findAll();
    }

    //Find All Statuses
    public List<Status> findAllStatuses()
    {
        return statusRepository.findAll();
    }

    //Count Contacts
    public long countContacts() {
        return contactRepository.count();
    }

    //Delete Contact
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    //Save Contact
    public void saveContact(Contact contact) {
        if (contact == null) {
            return;
        }
        contactRepository.save(contact);
    }
}
