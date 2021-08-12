package payroll;

class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
  EmployeeNotFoundException(String name) {
	    super("Could not find employeewithname " + name);
	  }
}
