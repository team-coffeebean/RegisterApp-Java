package edu.uark.registerapp.commands.employees;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeCreateCommand implements ResultCommandInterface<Employee> {
    @Transactional
	@Override
	public Employee execute() {
        this.validateEmployee();
        
        if (this.isInitialEmployee) {
            if (this.apiEmployee.getClassification() 
                    == EmployeeClassification.NOT_DEFINED.getClassification()) {
                this.apiEmployee.setClassification(EmployeeClassification.GENERAL_MANAGER.getClassification());
            }
        }
		EmployeeEntity employee = this.employeeRepository.save(
			new EmployeeEntity(this.apiEmployee));
        return this.apiEmployee;
	}

	// Helper methods
	private void validateEmployee() {
        if (StringUtils.isBlank(this.apiEmployee.getFirstName())
                || StringUtils.isBlank(this.apiEmployee.getLastName())
                    || StringUtils.isBlank(this.apiEmployee.getPassword())) {
			throw new UnprocessableEntityException("employee");
		}
	}

	// Properties
    private Employee apiEmployee;
    private boolean isInitialEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeCreateCommand setApiEmployee(final Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	public EmployeeCreateCommand setIsInitiaEmployee(final boolean isInitialEmployee) {
		this.isInitialEmployee = isInitialEmployee;
		return this;
	}

	@Autowired
	private EmployeeRepository employeeRepository;
}