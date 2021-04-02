package info.dt.qlcv.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.common.Common;
import info.dt.qlcv.dao.ActionDetailDAO;
import info.dt.qlcv.dao.DepartmentDAO;
import info.dt.qlcv.dao.RoleDAO;
import info.dt.qlcv.dao.TopicDAO;
import info.dt.qlcv.dao.TypeTopicDAO;
import info.dt.qlcv.dao.UserDAO;
import info.dt.qlcv.entity.ActionDetail;
import info.dt.qlcv.entity.Department;
import info.dt.qlcv.entity.Role;
import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.TypeTopic;
import info.dt.qlcv.entity.User;
import info.dt.qlcv.model.ActionDetailRequest;
import info.dt.qlcv.model.UserRequest;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private TopicDAO topicDAO;
    @Autowired
    private TypeTopicDAO typeTopicDAO;
    @Autowired
    public ActionDetailDAO actionDetailDao;
    @Autowired
    public RoleDAO roleDao;

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletResponse response, @CookieValue(value = "token", defaultValue = "") String token,
                              @CookieValue(value = "userId", defaultValue = "-1") String idUser){
        ModelAndView mav = new ModelAndView();

        if(userDAO.checkToken(idUser,token)){
            User user = userDAO.getUserById(idUser);
            
            List<TypeTopic> lstTypeTopic = typeTopicDAO.getAllTypeTopic();
            mav.addObject("lstTypeTopic", lstTypeTopic);
            List<Topic> lstTopic = topicDAO.getAllTopic();
            mav.addObject("lstTopic", lstTopic);
            List<Department> lstDepartment = departmentDAO.getAllDepartment();
            mav.addObject("lstDepartment", lstDepartment);
            
            List<ActionDetail> lstAction = actionDetailDao.getAllActionDetail();
            mav.addObject("lstAction", lstAction);
            
            
            
            List<User> lstUser = userDAO.getAllUsert();
            mav.addObject("lstUser", lstUser);

            mav.addObject("userLogin", user);
            mav.setViewName("index");
        }
        else {
            mav.setViewName("user/login");

            resetCookie(response);

            mav.addObject("messages","");
        }
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("user/login");
        mav.addObject("messages","");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletResponse response, @ModelAttribute("userName") String userName,
                              @ModelAttribute("password") String password ){
        ModelAndView mav = new ModelAndView("user/login");
        try {
            String passwordCheck = Common.hashPass(password);
            User userLogin = userDAO.login(userName, passwordCheck);
            if(userLogin != null && userLogin.getRole() != null) {

                String token = userLogin.getAccessToken().getToken();
                Cookie cookie = new Cookie("token", URLEncoder.encode(token, "UTF-8"));
                cookie.setMaxAge(3600);
                response.addCookie(cookie);

                Cookie cookie2 = new Cookie("userId", URLEncoder.encode(userLogin.getIdUser()+"", "UTF-8"));
                cookie2.setMaxAge(3600);
                response.addCookie(cookie2);

                mav.addObject("userLogin", userLogin);
                
                List<TypeTopic> lstTypeTopic = typeTopicDAO.getAllTypeTopic();
                mav.addObject("lstTypeTopic", lstTypeTopic);
                List<Topic> lstTopic = topicDAO.getAllTopic();
                mav.addObject("lstTopic", lstTopic);
                List<Department> lstDepartment = departmentDAO.getAllDepartment();
                mav.addObject("lstDepartment", lstDepartment);
                List<ActionDetail> lstAction = actionDetailDao.getAllActionDetail();
                mav.addObject("lstAction", lstAction);
                mav.setViewName("index");
            }
            else {
                resetCookie(response);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response, @CookieValue(value = "token", defaultValue = "") String token,
                         @CookieValue(value = "userId", defaultValue = "") String idUser){

        resetCookie(response);

        userDAO.logout(idUser,token);
        return "redirect:/";
    }

    @RequestMapping(value = "/user/register")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject("messages", "");
        mav.addObject("userRequest", new UserRequest());
        return mav;
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestParam("idUser") int idUser){
        boolean check = false;
        if (idUser > -1) {
            check = userDAO.delete(idUser);
        }
        if (check)
            return ResponseEntity.ok("Success");
        else
            return ResponseEntity.ok("Fails");

    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid UserRequest userRequest, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("user/register");

        boolean flagInsert = true;
        String message = "";

        if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())){
            message = "Confirm password and Password incorrect";
            flagInsert = false;
        }

        if (bindingResult.hasErrors() || !flagInsert) {
            mav.addObject("messages", message);
        }
        else {
            String result = userDAO.addUser(userRequest);
            if(result.equals("True"))
                return new ModelAndView("redirect:/");
            else {
                mav.addObject("messages", result);
            }
        }

        return mav;
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid UserRequest userRequest, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("user/manager");

        String message = "";
        boolean flagInsert = true;


        if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())){
            message = "Confirm password and Password incorrect";
            mav.addObject("messages",message);
            flagInsert = false;
        }

        if (bindingResult.hasErrors() || !flagInsert) {
            mav.addObject("messages", message);
            return mav;
        }
        else {
            String result = userDAO.editUser(userRequest);
            if(result.equals("True"))
                return new ModelAndView("redirect:/user/manager");
            else {
                mav.addObject("messages", result);
            }
        }

        return mav;
    }
    @RequestMapping(value = "/user/manager")
    public ModelAndView userManager(HttpServletResponse response, @CookieValue(value = "token", defaultValue = "") String token,
                                    @CookieValue(value = "userId", defaultValue = "-1") String idUser){
        ModelAndView mav = new ModelAndView();

        if(userDAO.checkToken(idUser,token)){
            User user = userDAO.getUserById(idUser);
            if(user.getRole().getLevel() != 1){
                return new ModelAndView("redirect:/");
            }

            List<Role> lstRoles = roleDao.getAllRole();
            mav.addObject("lstRoles",lstRoles);
            mav.addObject("userLogin", user);
            List<Department> lstDepartment = departmentDAO.getAllDepartment();
            mav.addObject("lstDepartment", lstDepartment);
            List<User> lstUser = userDAO.getAllUsert();
            if(lstUser.size()> 0){
                mav.addObject("lstUser", lstUser);
            }
            mav.setViewName("user/manager");
        }
        else {
            mav.setViewName("redirect:/");
            resetCookie(response);
        }
        return mav;
    }

    @RequestMapping(value="/user/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByID(@PathVariable("id") int idUser){
        User user = userDAO.getUserById(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private void resetCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("token", ""); // Not necessary, but saves bandwidth.
        cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("userId", ""); // Not necessary, but saves bandwidth.
        cookie1.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        response.addCookie(cookie1);
    }
    
    @RequestMapping(value = "/actiondetail/insert", method = RequestMethod.POST)
    private ModelAndView insert(@ModelAttribute("actiondetailRequest") ActionDetailRequest actionDetailRequest){
        ModelAndView mav = new ModelAndView();

        actionDetailDao.insertActionDetail(actionDetailRequest);
        mav.setViewName("redirect:/");
        return mav;
    }
    @RequestMapping(value = "/user/insert", method = RequestMethod.POST)
    private ModelAndView userinsert(@ModelAttribute("ueserRequest") UserRequest userRequest){
        ModelAndView mav = new ModelAndView();

        userDAO.addUser(userRequest);
        mav.setViewName("redirect:manager");
        return mav;
    }
    
    @RequestMapping(value = "/getAction/{idAction}", method = RequestMethod.GET)
    public ResponseEntity<?> getAction(@PathVariable("idAction") int idAction){

        ActionDetail result = actionDetailDao.getActionById(idAction);
        if(result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/deleteAction", method = RequestMethod.POST)
    public ResponseEntity<?> deleteAction(@RequestParam("idAction") int idAction){

        boolean result = actionDetailDao.deleteAction(idAction);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
