// 1. Single Responsibility Principle
console.log("\nSingle Responsibility Principle:")
class Employee_S {
  constructor(id, firstName, lastName, age, type) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.type = type;
  }

  getID() {
    return this.id;
  }

  getName() {
    return this.firstName + " " + this.lastName;
  }

  getAge() {
    return this.age;
  }

  getType() {
    return this.type;
  }
}

class SalaryCalculator {
  calculateSalary(employee) {
    let hourly = 0;

    if (employee.getType() === 'c') {
      hourly = 25.0;
    } else if (employee.getType() === 'p') {
      hourly = 30.0;
    } else if (employee.getType() === 'f') {
      hourly = 38.0;
    }

    return "Hourly Rate: $" + hourly;
  }
}

const employee = new Employee_S("45A", "James", "Bond", 20, 'c');
const types = ['c', 'p', 'f'];

console.log("ID: " + employee.getID());
console.log("Name: " + employee.getName());
console.log("Age: " + employee.getAge());

for (const t of types) {
  if (employee.getType() === t) {
    console.log("Type: " + employee.getType());
  }
}

const salaryCalculator = new SalaryCalculator();
const salary = salaryCalculator.calculateSalary(employee);
console.log("Salary: $" + salary);


console.log("\nOpen Closed Principle:");
// 2. Open Closed Principle
class Employee {
constructor(name) {
this.name = name;
}


calculateSalary() {
console.log("Calculating salary for the default employee.");
return 0;
}
}


class CasualEmployee extends Employee {
constructor(name, hourlyRate) {
super(name);
this.hourlyRate = hourlyRate;
}


calculateSalary() {
console.log(`Calculating 40-hour weekly salary for Casual Employee: ${this.name}`);
return this.hourlyRate * 40; // Assuming 40 hours per week
}
}


class FullTimeEmployee extends Employee {
constructor(name, annualSalary) {
super(name);
this.annualSalary = annualSalary;
}


calculateSalary() {
console.log(`Calculating monthly salary for Full-Time Employee: ${this.name}`);
return this.annualSalary / 12; // Monthly salary
}
}


class PartTimeEmployee extends Employee {
constructor(name, hourlyRate, hoursWorked) {
super(name);
this.hourlyRate = hourlyRate;
this.hoursWorked = hoursWorked;
}


calculateSalary() {
console.log(`Calculating weekly salary for Part-Time Employee: ${this.name}`);
return this.hourlyRate * this.hoursWorked;
}
}


const employees = [
new CasualEmployee("John", 22),
new FullTimeEmployee("Alice", 60000),
new PartTimeEmployee("Bob", 19, 30),
];


for (const employee of employees) {
console.log("Salary:", employee.calculateSalary());
}


console.log("\nLinkov Substitution Principle: ");
//3. Linkov Substitution Principle:
class Employee_L {
  constructor(salary) {
    this.salary = salary;
  }

  details() {
    console.log("This is a default employee type.");
  }
}

class Casual extends Employee_L {
  constructor() {
    super(20);
  }

  details() {
    console.log("This is a Casual employee-type.");
    console.log("Hourly Rate: $" + this.salary);
  }
}

class PartTime extends Employee_L {
  constructor() {
    super(23);
  }

  details() {
    console.log("This is a Part-Time employee-type.");
    console.log("Hourly Rate: $" + this.salary);
  }
}

class FullTime extends Employee_L {
  constructor() {
    super(30);
  }

  details() {
    console.log("This is a Full-Time employee-type.");
    console.log("Hourly Rate: $" + this.salary);
  }
}

class Intern extends Employee_L {
  constructor() {
    super(0);
  }

  details() {
    console.log("This is an Intern employee-type.");
    console.log("Hourly Rate: $" + this.salary);
  }
}

const employees_L = [];
employees_L[0] = new Casual();
employees_L[1] = new PartTime();
employees_L[2] = new FullTime();
employees_L[3] = new Intern();

for (const e of employees_L) {
  e.details();
}


console.log("\nIntegrated Interface Principle:");
// 4. Integrated Interface Principle
/**
 * @interface
 */
class Coder {
  /**
   * @abstract
   */
  code() {}
}

/**
 * @interface
 */
class Manager {
  /**
   * @abstract
   */
  manageProject() {}
}

class Programmer {
  constructor(name) {
    this.name = name;
  }

  code() {
    console.log(this.name + " is coding.");
  }
}

class ManagerEmployee {
  constructor(name) {
    this.name = name;
  }

  manageProject() {
    console.log(this.name + " is managing a project.");
  }
}

const programmer = new Programmer("Sophie");
const manager = new ManagerEmployee("George");

programmer.code(); 
manager.manageProject(); 


console.log("\nDependency Inversion Principle:");
// 5. Dependency Inversion Principle:
class Device {
  turnOn() {
    console.log("Device is on.");
  }

  turnOff() {
    console.log("Device is off.");
  }
}

class LED extends Device {
  turnOn() {
    console.log("LED is on.");
  }

  turnOff() {
    console.log("LED is off.");
  }
}

class Fan extends Device {
  turnOn() {
    console.log("Fan is on.");
  }

  turnOff() {
    console.log("Fan is off.");
  }
}

class Switch {
  constructor(device) {
    this.device = device;
  }

  turnOn() {
    this.device.turnOn();
  }

  turnOff() {
    this.device.turnOff();
  }
}

// Main
const LEDDevice = new LED();
const fanDevice = new Fan();

const LEDSwitch = new Switch(LEDDevice);
const fanSwitch = new Switch(fanDevice);

LEDSwitch.turnOn();
LEDSwitch.turnOff();
LEDSwitch.turnOn();

fanSwitch.turnOn();
fanSwitch.turnOff();
fanSwitch.turnOn();
fanSwitch.turnOff();
