package com.example;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.example.dao.TrainerCrud;
import com.example.model.Trainer;
import com.example.util.DateUtil;

public class Starter {
	
	public static void printTrainersTable(List<Trainer> trainers) {
		// Table header
		String format = "| %-5s | %-12s | %-12s | %-8s | %-40s | %-12s | %-12s |%n";
		System.out.format(
				"+-------+--------------+--------------+----------+------------------------------------------+--------------+--------------+%n");
		System.out.format(
				"| ID    | First Name   | Last Name    | Gender   | Email                     		    |  Mobile      | DOB          |%n");
		System.out.format(
				"+-------+--------------+--------------+----------+------------------------------------------+--------------+--------------+%n");
		// Table rows
		for (Trainer t : trainers) {
			System.out.format(format, t.getId(), t.getFirstName(), t.getLastName(), t.getGender(), t.getEmail(),
					t.getMobile(), t.getDateOfBirth());
		}
		System.out.format(
				"+-------+--------------+--------------+----------+------------------------------------------+--------------+--------------+%n");
	}
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TrainerCrud crud = new TrainerCrud();
		Scanner input = new Scanner(System.in);
		int option = 0;
		int id = 0;
		int status = 0;
		String firstName = null;
		String lastName = null;
		String gender = null;
		String email = null;
		Long mobile = null;
		Date dateOfBirth = null;

		while (option < 5) {
			System.out.println("Welcome to Trainer Management System");
			System.out.println("\t\t\t 1. View All Trainers ");
			System.out.println("\t\t\t 2. Add New Trainer ");
			System.out.println("\t\t\t 3. Update A Trainer ");
			System.out.println("\t\t\t 4. Remove A Trainer ");
			System.out.println("\t\t\t 5.Exit ");

			System.out.print("Enter your choice: ");
			option = input.nextInt();

			switch (option) {
			case 1:
				System.out.println("Viewing All Trainers");
				printTrainersTable(crud.readAll());
				break;
			case 2:
				System.out.println("Adding New Trainer");
				status = 0;
				System.out.print("Enter ID of Trainer:");
				id = input.nextInt();
				System.out.print("Enter First Name of Trainer:");
				firstName = input.next();
				System.out.print("Enter Last Name of Trainer:");
				lastName = input.next();
				System.out.print("Enter Gender of Trainer(M/F/O):");
				gender = input.next();
				System.out.print("Enter Email of Trainer:");
				email = input.next();
				System.out.print("Enter Mobile Number of Trainer:");
				mobile = input.nextLong();
				System.out.print("Enter Trainer's Date of Birth (dd-MM-yyyy) :");
				String dob = input.next();
				dateOfBirth = DateUtil.convertStringToSqlDate(dob,"dd-MM-yyyy");
				status = crud.save(new Trainer(id, firstName, lastName, gender, email, mobile, dateOfBirth));
				if (status != 0)
					System.out.println("One New Trainer Added successfully");
				else
					System.out.println("Error in adding Trainer!!!!");
				break;
			case 3:
				System.out.println("Updating An Trainer");
				status = 0;
				System.out.print("Enter ID of Trainer:");
				id = input.nextInt();
				System.out.print("Enter First Name of Trainer:");
				firstName = input.next();
				System.out.print("Enter Last Name of Trainer:");
				lastName = input.next();
				System.out.print("Enter Gender of Trainer(M/F/O):");
				gender = input.next();
				System.out.print("Enter Email of Trainer:");
				email = input.next();
				System.out.print("Enter Mobile Number of Trainer:");
				mobile = input.nextLong();
				System.out.print("Enter Trainer's Date of Birth (dd-MM-yyyy) :");
				dob = input.next();
				dateOfBirth = DateUtil.convertStringToSqlDate(dob,"dd-MM-yyyy");
				status = crud.update(id, new Trainer(id, firstName, lastName, gender, email, mobile, dateOfBirth));
				if (status != 0)
					System.out.println("One New Trainer Updated successfully");
				else
					System.out.println("Error in updating Trainer!!!!");
				break;
			case 4:
				System.out.println("Removing A Trainer");
				status = 0;
				System.out.print("Enter ID of Trainer:");
				id = input.nextInt();
				status = crud.delete(id);
				if (status != 0)
					System.out.println("One Trainer Deleted successfully");
				else
					System.out.println("Error in Deleting Trainer!!!!");
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
