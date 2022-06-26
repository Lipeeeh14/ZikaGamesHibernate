package br.com.fateczl.zikagames.control.Validations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AluguelValidations {
	public static Validator validarDataVencimento(LocalDate dataVencimento) {
		Validator validator = new Validator();
		
		if (ChronoUnit.DAYS.between(dataVencimento, LocalDate.now()) > 0)
			validator.setErrorMessage("A data de vencimento não pode ser menor ou igual que a data de hoje, tente novamente!");
			
		return validator;
	}
}
