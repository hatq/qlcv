package info.dt.qlcv.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DepartmentRequest {

    private int idDepartment;

    @NotEmpty
    private String nameDepartment;

    private int status;

}
