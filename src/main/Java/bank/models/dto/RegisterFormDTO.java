package bank.models.dto;

import javax.validation.constraints.NotBlank;

public class RegisterFormDTO extends LoginFormDTO {

    @NotBlank(message = "* Required")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
