import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function ConfirmEqualValidator(main: string, confirm: string):ValidatorFn{
  return (ctrl: AbstractControl): ValidationErrors | null => {
    const mainControl = ctrl.get(main);
    const confirmControl = ctrl.get(confirm);

    if (!mainControl || !confirmControl) {
      return null; // Ne pas valider si l'un des champs est absent
    }

    const mainValue = mainControl.value;
    const confirmValue = confirmControl.value;

    return mainValue === confirmValue ? null : {
      confirmEqual: {
        main: mainValue,
        confirm: confirmValue
      }
    };
  };

}
