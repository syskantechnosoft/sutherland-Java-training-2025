package com.example;

import java.util.Scanner;

import com.example.dao.EmployeeCrud;
import com.example.entity.Employee;

public class CrudDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EmployeeCrud crud = new EmployeeCrud();
		Scanner input = new Scanner(System.in);
		int option = 0;
		int id = 0;
		int status = 0;
		String name = null;
		String email = null;

		while (option < 5) {
			System.out.println("Welcome to Employee Management System");
			System.out.println("\t\t\t 1. View All Employees ");
			System.out.println("\t\t\t 2. Add New Employee ");
			System.out.println("\t\t\t 3. Update An Employee ");
			System.out.println("\t\t\t 4. Remove An Employee ");
			System.out.println("\t\t\t 5.Exit ");

			System.out.print("Enter your choice: ");
			option = input.nextInt();

			switch (option) {
			case 1:
				System.out.println("Viewing All Employees");
				System.out.println(crud.readAll());
				break;
			case 2:
				System.out.println("Adding New Employee");
				status = 0;
				System.out.print("Enter ID of Employee:");
				id = input.nextInt();
				System.out.print("Enter Name of Employee:");
				name = input.next();
				System.out.print("Enter Email of Employee:");
				email = input.next();
				status = crud.save(new Employee(id, name, email));
				if (status != 0)
					System.out.println("One New Employee Added successfully");
				else
					System.out.println("Error in adding employee!!!!");
				break;
			case 3:
				System.out.println("Updating An Employee");
				status = 0;
				System.out.print("Enter ID of Employee:");
				id = input.nextInt();
				System.out.print("Enter Name of Employee:");
				name = input.next();
				System.out.print("Enter Email of Employee:");
				email = input.next();
				status = crud.update(id, new Employee(id, name, email));
				if (status != 0)
					System.out.println("One New Employee Updated successfully");
				else
					System.out.println("Error in updating employee!!!!");
				break;
			case 4:
				System.out.println("Removing An Employee");
				status = 0;
				System.out.print("Enter ID of Employee:");
				id = input.nextInt();
				status = crud.delete(id);
				if (status != 0)
					System.out.println("One New Employee Deleted successfully");
				else
					System.out.println("Error in Deleting employee!!!!");
				break;
			case 5:
				System.out.println("Thanks for Using our App");
				System.exit(0);

			default:
				System.out.println("Select any number between 1 and 5 only!!!");
				break;
			}
		}

		input.close();

	}

}
