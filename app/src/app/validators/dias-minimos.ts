import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function minimumDaysBetweenValidator(dateInicio: string, dateFim: string, minimumDays: number): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const inicio = formGroup.get(dateInicio)?.value;
      const fim = formGroup.get(dateFim)?.value;
  
      if (!inicio || !fim) {
        return null;
      }
  
      const inicioDate = new Date(inicio);
      const fimDate = new Date(fim);
  
      const daysDifference = (fimDate.getTime() - inicioDate.getTime()) / (1000 * 3600 * 24);
  
      if (daysDifference < minimumDays) {
        formGroup.get(dateFim)?.setErrors({ minimumDaysInvalid: true });
        return { minimumDaysInvalid: true };
      }
  
      return null;
    };
  }