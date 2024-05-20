package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.UserService;

public class SignUpController {

	@FXML
	private TextField firstNameLabel;

	@FXML
	private TextField lastNameLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField usernameRegistrationLabel;

	public void signUpButton(ActionEvent event) throws Exception {
		UserService userService = new UserService();
		User userToBeInserted = new User(firstNameLabel.getText(),lastNameLabel.getText(), passwordField.getText(),
				usernameRegistrationLabel.getText());	
		userService.addUser(userToBeInserted);
		System.out.println("User registered successfuly!");
		}

	}

