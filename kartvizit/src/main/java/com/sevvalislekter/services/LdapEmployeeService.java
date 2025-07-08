package com.sevvalislekter.services;

import com.sevvalislekter.entities.Employee;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import org.springframework.ldap.core.DirContextAdapter;
import java.io.IOException;

@Service
public class LdapEmployeeService {

    private final LdapTemplate ldapTemplate;

    public LdapEmployeeService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void addEmployee(Employee employee, MultipartFile photoFile) {
        // Distinguished Name: cn=Ad Soyad,ou=employees,dc=example,dc=com
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "employees")
                .add("cn", employee.getFirstName() + " " + employee.getLastName())
                .build();

        DirContextAdapter context = new DirContextAdapter(dn);

        context.setAttributeValues("objectclass", new String[]{"inetOrgPerson"});

        // Temel alanlar
        context.setAttributeValue("cn", employee.getFirstName() + " " + employee.getLastName());
        context.setAttributeValue("sn", employee.getLastName());
        context.setAttributeValue("mail", employee.getEmail());

        // Opsiyonel alanlar
        if (employee.getTitle() != null) {
            context.setAttributeValue("title", employee.getTitle());
        }

        if (employee.getPhoneNumber() != null) {
            context.setAttributeValue("telephoneNumber", employee.getPhoneNumber());
        }

        if (employee.getLinkedinUrl() != null) {
            context.setAttributeValue("labeledURI", employee.getLinkedinUrl());
        }

        // Fotoğrafı jpegPhoto olarak ekle (LDAP sadece byte[] kabul eder)
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                byte[] photoBytes = photoFile.getBytes();
                context.setAttributeValue("jpegPhoto", photoBytes);
            } catch (IOException e) {
                System.err.println("Fotoğraf LDAP'a yüklenirken hata: " + e.getMessage());
                // İstersen burada loglama veya hata fırlatma yapabilirsin
            }
        }

        // LDAP dizinine kaydet
        ldapTemplate.bind(context);
    }

    // İstersen silme veya güncelleme gibi işlemleri de buraya ekleyebilirsin.
}
