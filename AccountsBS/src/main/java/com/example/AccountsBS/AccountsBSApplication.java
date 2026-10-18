package com.example.AccountsBS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountsBSApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsBSApplication.class, args);
	}

    //Perguntar sobre a função do Create no mapper sendo que ele não é usado em nenhum controller e agora estamos a criar as contas pelo register
}
