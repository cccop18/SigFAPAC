import { AbstractControl, ValidatorFn } from "@angular/forms";

// Validador customizado
export const faixaFinanciamentoValidator: ValidatorFn = (form: AbstractControl): { [key: string]: boolean } | null => {
    const min = form.get('minFaixaFinanciamento')?.value;
    const max = form.get('maxFaixaFinanciamento')?.value;
  
    if (min !== null && max !== null && max < min) {
      return { faixaInvalida: true };
    }
    return null;
  };