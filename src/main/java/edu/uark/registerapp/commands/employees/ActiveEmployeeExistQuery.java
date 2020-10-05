// editted by Takeshi
package edu.uark.registerapp.commands.employees;

import java.lang.Boolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class ActiveEmployeeExistQuery implements VoidCommandInterface {
	@Override
	public void execute() {
        if (!this.employeeRepository.existsByIsActive(true)) {
            throw new NotFoundException("ActiveEmployee");
        }
	}


	@Autowired
	private EmployeeRepository employeeRepository;
}
