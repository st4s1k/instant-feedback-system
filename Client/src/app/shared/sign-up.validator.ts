import { FormGroup } from '@angular/forms';

export class SignUpValidator {
    static validate(signUpFormGroup: FormGroup) {
        let password = signUpFormGroup.controls.password.value;
        let repeatPassword = signUpFormGroup.controls.confirm_password.value;
 
        if (repeatPassword.length <= 0) {
            return null;
        }
 
        if (repeatPassword !== password) {
            return {
                doesMatchPassword: true
            };
        }
 
        return null;
 
    }
}