package fapacapi.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import fapacapi.controller.dto.EditalChamadasDto;

public class DataFechamentoEditalValidator implements ConstraintValidator<DataFechamentoEdital, LocalDate> {

    private EditalChamadasDto dto;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Verificar se as datas de abertura e fechamento são nulas
        LocalDate dataAbertura = dto.dataAberturaEdital();
        LocalDate dataFechamento = dto.dataFechamentoEdital();
        // Verificar se a data de fechamento é maior que a data de abertura
        return dataFechamento.isAfter(dataAbertura);
    }
}