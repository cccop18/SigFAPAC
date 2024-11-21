import { AbstractControl, ValidationErrors } from "@angular/forms";

export function noPastDateValidator(control: AbstractControl): ValidationErrors | null {
    const date = control.value;
    if (date && new Date(date).getTime() < new Date().setHours(0, 0, 0, 0)) {
      return { noPastDate: true };
    }
    return null;
  }