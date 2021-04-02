package info.dt.qlcv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/role")
public class RoleController {
	
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getRoleByID(){

        return "index";
    }
}
