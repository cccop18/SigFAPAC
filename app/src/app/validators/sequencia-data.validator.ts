import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function sequentialDateValidator(...dateFields: string[]): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      let previousDate: Date | null = null;
      for (const dateField of dateFields) {
        const currentValue = formGroup.get(dateField)?.value;
        if (currentValue) {
          const currentDate = new Date(currentValue);
          if (previousDate && currentDate.getTime() < previousDate.getTime()) {
            formGroup.get(dateField)?.setErrors({ sequentialDateInvalid: true });
            return { sequentialDateInvalid: true };
          }
          previousDate = currentDate;
        }
      }
      return null;
    };
  }