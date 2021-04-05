package info.dt.qlcv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.dao.DepartmentDAO;
import info.dt.qlcv.dao.TopicDAO;
import info.dt.qlcv.dao.TypeTopicDAO;
import info.dt.qlcv.dao.UserDAO;
import info.dt.qlcv.entity.Department;
import info.dt.qlcv.entity.Raci;
import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.TypeTopic;
import info.dt.qlcv.entity.User;
import info.dt.qlcv.model.TopicRequest;
import info.dt.qlcv.repository.RaciRepository;

@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicDAO topicDAO;
    @Autowired
    private TypeTopicDAO typeTopicDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Autowired
    private RaciRepository raciRepo;

    @RequestMapping(value = "")
    private ModelAndView index(@CookieValue(value = "token", defaultValue = "") String token,
                               @CookieValue(value = "userId", defaultValue = "-1") String idUser){
        ModelAndView mav = new ModelAndView();

        if(userDAO.checkToken(idUser,token)){
            User user = userDAO.getUserById(idUser);
            mav.addObject("userLogin", user);
            List<TypeTopic> lstTypeTopic = typeTopicDAO.getAllTypeTopic();
            mav.addObject("lstTypeTopic", lstTypeTopic);
            List<Topic> lstTopic = topicDAO.getAllTopic();
            mav.addObject("lstTopic", lstTopic);
            List<Raci> lstRaci = raciRepo.findAll();
            mav.addObject("lstRaci", lstRaci);
            List<Department> lstDepartment = departmentDAO.getAllDepartment();
            mav.addObject("lstDepartment", lstDepartment);
            mav.setViewName("/topic/index");
        }
        else {
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    private ModelAndView insert(@ModelAttribute("topicRequest") TopicRequest topicRequest){
        ModelAndView mav = new ModelAndView();

        topicDAO.insertTopic(topicRequest);
        mav.setViewName("redirect:/topic");
        return mav;
    }

    @RequestMapping(value = "/getTopic/{idTopic}", method = RequestMethod.GET)
    public ResponseEntity<?> getTopic(@PathVariable("idTopic") int idTopic){

        Topic result = topicDAO.getTopicById(idTopic);
        if(result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    public ResponseEntity<?> deleteTopic(@RequestParam("idTopic") int idTopic){

        boolean result = topicDAO.deleteTopic(idTopic);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
