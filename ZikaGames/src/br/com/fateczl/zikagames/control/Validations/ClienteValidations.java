package br.com.fateczl.zikagames.control.Validations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteValidations {
	public static Validator validarDataNascimento(LocalDate dataNascimento) {
		Validator validator = new Validator();
		
		if (ChronoUnit.DAYS.between(dataNascimento, LocalDate.now()) < 18)
			validator.setErrorMessage("O cliente não pode ser menor de 18 anos, tente novamente!");
			
		return validator;
	}
}
