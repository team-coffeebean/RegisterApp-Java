package edu.uark.registerapp.commands.employees;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeQuery implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(this.recordId);
        if (employeeEntity.isPresent()) {
			return new Employee(employeeEntity.get());
        } else {
            throw new NotFoundException("Employee");
        }
	}

    // Properties
    private UUID recordId;
	public EmployeeQuery setRecordId(final UUID id) {
		this.recordId= id;
		return this;
	}

	@Autowired
	private EmployeeRepository employeeRepository;
}