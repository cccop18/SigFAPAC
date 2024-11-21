import { AbstractControl, ValidatorFn } from "@angular/forms";

export const validTimeValidator: ValidatorFn = (control: AbstractControl): { [key: string]: boolean } | null => {
    const timeRegex = /^([01]\d|2[0-3]):([0-5]\d)$/;
    const timeValue = control.value;
  
    return timeRegex.test(timeValue) ? null : { 'invalidTimeFormat': true };
  };