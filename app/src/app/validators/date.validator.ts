import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function dateRangeValidator(dataInicio: string, dataFim: string): ValidatorFn {
  return (formGroup: AbstractControl): ValidationErrors | null => {
    const inicio = formGroup.get(dataInicio)?.value;
    const fim = formGroup.get(dataFim)?.value;

    if (!inicio || !fim) {
      return null; // Se uma das datas não estiver preenchida, não há erro
    }

    const inicioDate = new Date(inicio);
    const fimDate = new Date(fim);

    if (inicioDate.getTime() > fimDate.getTime()) {
      // Se a data de início for maior que a de fim, retorna o erro
      formGroup.get(dataFim)?.setErrors({ dateRangeInvalid: true });
      return { dateRangeInvalid: true };
    }

    return null; // Tudo ok, remove o erro se houver
  };
}
