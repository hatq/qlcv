package info.dt.qlcv.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.dao.WorkDAO;
import info.dt.qlcv.entity.Work;
import info.dt.qlcv.model.WorkRequest;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkDAO workDao;
    
	@PostMapping("/insert")
    private ModelAndView insert(@ModelAttribute("work") WorkRequest workRequest,
			@CookieValue(value = "userId", defaultValue = "-1") Integer idUser) throws ParseException{
        ModelAndView mav = new ModelAndView();
        
        //set user login
        workRequest.setIdUser(idUser);
        
        workDao.save(workRequest);
        mav.setViewName("redirect:/");
        return mav;
    }
	
	@PostMapping("/edit")
	private ModelAndView edit(@ModelAttribute("work")  WorkRequest workRequest) {
		ModelAndView mav = new ModelAndView();
		this.workDao.edit(workRequest);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value = "/get/{idWork}", method = RequestMethod.GET)
	public ResponseEntity<?> getAction(@PathVariable("idWork") Integer id) {

		Optional<Work> result = workDao.findById(id);
		if (result.isPresent())
			return new ResponseEntity<>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteAction(@RequestParam("workId") Integer workId) {

		boolean result = workDao.delete(workId);
		if (result)
			return new ResponseEntity<>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
