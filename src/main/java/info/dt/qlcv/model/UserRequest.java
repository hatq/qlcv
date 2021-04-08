package info.dt.qlcv.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserRequest {

    private int idUser;

    @NotEmpty
    private String userName;
    
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String phone;
    
    @NotEmpty
    @Email
    private String email;

    private Integer roleId;
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String confirmPassword;
    
    private String[] idDonVi;


}
