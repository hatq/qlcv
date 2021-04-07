package info.dt.qlcv.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.dao.WorkDAO;
import info.dt.qlcv.model.WorkRequest;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkDAO workDao;
    
	@PostMapping("/insert")
    private ModelAndView insert(@ModelAttribute("work") WorkRequest workRequest) throws ParseException{
        ModelAndView mav = new ModelAndView();

        workDao.save(workRequest);
        mav.setViewName("redirect:/");
        return mav;
    }
}
