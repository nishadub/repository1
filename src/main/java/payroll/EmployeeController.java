package payroll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

  private final EmployeeRepository repository;

  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  List<Employee> all() {
    return repository.findAll();
  }
  @GetMapping("/employeesNaman")
  List<Employee> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]
  @GetMapping("/employees/getByName")
  List<Employee> giveEmployee(@RequestParam("name") String name,@RequestParam("role") String role){
	  List<Employee> allEmployees=repository.findAll();
	  List<Employee> employeeWithName=new ArrayList<Employee>();
	  System.out.println("Hi ");
	  for(int i=0;i<allEmployees.size();i++)
	  {
		  if(allEmployees.get(i).getName().equals(name)  && allEmployees.get(i).getRole().equals(role))
		  {
			  employeeWithName.add(allEmployees.get(i));
		  }
	  }
	  if(employeeWithName.size()==0)
	  {
		  throw new EmployeeNotFoundException(name);
	  }
	  return employeeWithName;
	  
	
	  
		
		
  }
  
  @GetMapping("/calculator/add")
  int add(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c, @RequestParam("d") int d)
  {
	  int sum=a+b+c+d;
	  return sum;
  }
  
 
  
  @GetMapping("/calculator/subtract")
  int subtract(@RequestParam("a") int a, @RequestParam("b") int b)
  {
	  int diff=a-b;
	  return diff;
  }
  
  @GetMapping("/calculator/product")
  int multiply(@RequestParam("a") int a, @RequestParam("b") int b)
  {
	  int ans=a*b;
	  return ans;
  }
  
  @GetMapping("/calculator/modulus")
  int modulus(@RequestParam("a") int a, @RequestParam("b") int b)
  {
	  if(b==0)
		  return -1;
	  int mod=a%b;
	  return mod;
  }
  
  
  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
