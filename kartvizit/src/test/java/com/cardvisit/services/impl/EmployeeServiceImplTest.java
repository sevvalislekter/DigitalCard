package com.cardvisit.services.impl;

import com.cardvisit.dto.EmployeeDTO;
import com.cardvisit.dto.EmployeeIUDTO;
import com.cardvisit.entity.EmployeeEntity;
import com.cardvisit.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeRepository employeeRepository;
    private EmployeeServiceImpl employeeService;

    private List<EmployeeEntity> fakeDb;

    @BeforeEach
    void setUp() {
       
        fakeDb = new ArrayList<>();

        employeeRepository = new EmployeeRepository() {
            @Override
            public EmployeeEntity findByRandomCode(String randomCode) {
                return fakeDb.stream()
                        .filter(emp -> randomCode.equals(emp.getRandomCode()))
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public EmployeeEntity save(EmployeeEntity entity) {
                if (!fakeDb.contains(entity)) {
                    fakeDb.add(entity);
                }
                return entity;
            }

            @Override
            public List<EmployeeEntity> findByQrActiveTrue() {
                return fakeDb.stream()
                        .filter(EmployeeEntity::getQrActive)
                        .toList();
            }

            @Override
            public List<EmployeeEntity> findByQrActiveFalse() {
                return fakeDb.stream()
                        .filter(emp -> !emp.getQrActive())
                        .toList();
            }

			@Override
			public <S extends EmployeeEntity> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <S extends EmployeeEntity> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <S extends EmployeeEntity> S saveAndFlush(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void deleteAllInBatch(Iterable<EmployeeEntity> entities) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void deleteAllByIdInBatch(Iterable<Integer> ids) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void deleteAllInBatch() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public EmployeeEntity getOne(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EmployeeEntity getById(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EmployeeEntity getReferenceById(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<EmployeeEntity> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<EmployeeEntity> findAllById(Iterable<Integer> ids) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<EmployeeEntity> findById(Integer id) {
				// TODO Auto-generated method stub
				return Optional.empty();
			}

			@Override
			public boolean existsById(Integer id) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void deleteById(Integer id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void delete(EmployeeEntity entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void deleteAllById(Iterable<? extends Integer> ids) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void deleteAll(Iterable<? extends EmployeeEntity> entities) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public List<EmployeeEntity> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Page<EmployeeEntity> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return Optional.empty();
			}

			@Override
			public <S extends EmployeeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends EmployeeEntity, R> R findBy(Example<S> example,
					Function<FetchableFluentQuery<S>, R> queryFunction) {
				// TODO Auto-generated method stub
				return null;
			}

         
        };

        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void deactivateEmployeeByRandomCode() {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setRandomCode("TEST123");
        emp.setQrActive(true);
        fakeDb.add(emp);

        employeeService.deactivateEmployeeByRandomCode("TEST123");

        assertFalse(emp.getQrActive());
        assertNotNull(emp.getExitDate());
    }

    @Test
    void createEmployeeWithPhoto_shouldSaveEmployee() throws IOException {
        EmployeeIUDTO dto = new EmployeeIUDTO();
        dto.setFirstName("Ali");
        dto.setLastName("Veli");
        dto.setEmail("ali@example.com");

        MockMultipartFile mockPhoto = new MockMultipartFile(
                "photo",
                "photo.jpg",
                "image/jpeg",
                "dummy image".getBytes()
        );
        dto.setPhoto(mockPhoto);

        employeeService.createEmployeeWithPhoto(dto);

        assertTrue(fakeDb.stream().anyMatch(emp -> emp.getFirstName().equals("Ali")));
    }

    @Test
    void updateEmployeeForm_shouldUpdateEmployee() {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setRandomCode("ABC123");
        fakeDb.add(emp);

        EmployeeIUDTO dto = new EmployeeIUDTO();
        dto.setRandomCode("ABC123");
        dto.setFirstName("Zeynep");
        dto.setLastName("Demir");
        dto.setEmail("zeynep@gmail.com");
        dto.setPhoneNumber("123456");
        dto.setTitle("Engineer");
        dto.setLinkedinUrl("linkedin.com/zeynep");

        employeeService.updateEmployeeForm(dto);

        assertEquals("Zeynep", emp.getFirstName());
        assertEquals("Demir", emp.getLastName());
        assertEquals("zeynep@gmail.com", emp.getEmail());
        assertEquals("123456", emp.getPhoneNumber());
        assertEquals("Engineer", emp.getTitle());
        assertEquals("linkedin.com/zeynep", emp.getLinkedinUrl());
    }

    @Test
    void getAllEmployees_shouldReturnOnlyActive() {
        EmployeeEntity emp1 = new EmployeeEntity();
        emp1.setFirstName("Active1");
        emp1.setQrActive(true);

        EmployeeEntity emp2 = new EmployeeEntity();
        emp2.setFirstName("Inactive1");
        emp2.setQrActive(false);

        fakeDb.add(emp1);
        fakeDb.add(emp2);

        List<EmployeeEntity> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        assertTrue(result.get(0).getQrActive());
        assertEquals("Active1", result.get(0).getFirstName());
    }

    @Test
    void findByRandomCode_shouldReturnEmployee() {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setRandomCode("XYZ789");
        emp.setFirstName("Mert");
        fakeDb.add(emp);

        EmployeeEntity result = employeeService.findByRandomCode("XYZ789");

        assertNotNull(result);
        assertEquals("Mert", result.getFirstName());
    }

    @Test
    void generateVCard_shouldReturnVCardContent() {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setRandomCode("CARD99");
        emp.setFirstName("Ayşe");
        emp.setLastName("Kaya");
        emp.setEmail("ayse@example.com");
        fakeDb.add(emp);

        ResponseEntity<byte[]> response = employeeService.generateVCard("CARD99");

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());

        String content = new String(response.getBody());
        assertTrue(content.contains("FN:Ayşe"));
        assertTrue(content.contains("email:ayse@example.com"));
    }

    @Test
    void findByQrActiveFalse_shouldReturnOnlyInactive() {
        EmployeeEntity emp1 = new EmployeeEntity();
        emp1.setFirstName("InactiveOne");
        emp1.setQrActive(false);

        EmployeeEntity emp2 = new EmployeeEntity();
        emp2.setFirstName("InactiveTwo");
        emp2.setQrActive(false);

        fakeDb.add(emp1);
        fakeDb.add(emp2);

        List<EmployeeEntity> inactives = employeeRepository.findByQrActiveFalse();

        assertEquals(2, inactives.size());
        assertEquals("InactiveOne", inactives.get(0).getFirstName());
        assertFalse(inactives.get(0).getQrActive());
    }

    @Test
    void saveEmployee_shouldCreateEmployeeWithRandomCode() {
        EmployeeIUDTO dto = new EmployeeIUDTO();
        dto.setFirstName("Mete");
        dto.setLastName("Yılmaz");
        dto.setEmail("mete@gmail.com");

        EmployeeDTO saved = employeeService.saveEmployee(dto);

        assertEquals("Mete", saved.getFirstName());
        assertNotNull(saved.getRandomCode());

        boolean exists = fakeDb.stream().anyMatch(e -> e.getFirstName().equals("Mete"));
        assertTrue(exists);
    }
}
