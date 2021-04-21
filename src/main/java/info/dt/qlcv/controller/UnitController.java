package info.dt.qlcv.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.dao.ActionDetailDAO;
import info.dt.qlcv.dao.RoleDAO;
import info.dt.qlcv.dao.TopicDAO;
import info.dt.qlcv.dao.UnitDAO;
import info.dt.qlcv.dao.UserDAO;
import info.dt.qlcv.entity.ActionDetail;
import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.Unit;
import info.dt.qlcv.entity.User;
import info.dt.qlcv.model.ActionDetailRequest;
import info.dt.qlcv.model.UnitRequest;

@RestController
@RequestMapping("/unit")
public class UnitController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TopicDAO topicDAO;
	
	@Autowired
	public ActionDetailDAO actionDetailDao;
	
	@Autowired
	public RoleDAO roleDao;
	
	@Autowired
	public UnitDAO unitDao;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createDepartment(@Valid UnitRequest unit, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("department/manager");

		String result = unitDao.addUnit(unit);
		if (result.equals("True"))
			return new ModelAndView("redirect:/unit/manager");
		else {
			mav.addObject("messages", result);
		}

		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editDepartment(@Valid UnitRequest unit, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("department/manager");

		String result = unitDao.editUnit(unit);
		if (result.equals("True"))
			mav.setViewName("redirect:/unit/manager");
		else {
			mav.setViewName("redirect:/unit/manager");
			mav.addObject("messages", result);
		}

		return mav;
	}

	@RequestMapping(value = "/manager")
	public ModelAndView loadDepartment(HttpServletResponse response,
			@CookieValue(value = "token", defaultValue = "") String token,
			@CookieValue(value = "userId", defaultValue = "-1") String idUser) {
		ModelAndView mav = new ModelAndView();
		if (userDAO.checkToken(idUser, token)) {
			User user = userDAO.getUserById(idUser);
			mav.addObject("userLogin", user);
			List<Unit> lstDonvi = unitDao.findAll();
			mav.addObject("lstDonvi", lstDonvi);
			mav.addObject("maDonViTuDong", this.unitDao.taoMaDonVi());
			mav.setViewName("department/manager");
		} else {
			mav.setViewName("redirect:/unit/manager");
		}
		return mav;
	}

	@RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
	public ModelAndView getDepartmentDetailByID(@PathVariable("id") String id, HttpServletResponse response,
			@CookieValue(value = "token", defaultValue = "") String token,
			@CookieValue(value = "userId", defaultValue = "-1") String idUser) {
		ModelAndView mav = new ModelAndView();

		if (userDAO.checkToken(idUser, token)) {
			int idDeparment = Integer.parseInt(id);
			List<ActionDetail> listactionDeparment = actionDetailDao.getActionDetailByIdDepartment(idDeparment);
			User user = userDAO.getUserById(idUser);
			mav.addObject("userLogin", user);
			List<Topic> lstTopic = topicDAO.getTopicByIdDepartment(idDeparment);
			mav.addObject("lstTopic", lstTopic);
//			List<Department> lstDepartment = departmentDAO.getAllDepartment();
//			mav.addObject("lstDepartment", lstDepartment);

			mav.addObject("listactionDeparment", listactionDeparment);
			mav.setViewName("department/library");

			int level = userDAO.getUserById(idUser).getRole().getLevel();
			if (level == 4) {

			}

			List<User> lstUser = userDAO.getAllUsert();
			mav.addObject("lstUser", lstUser);
		} else {
			mav.setViewName("redirect:/");

			mav.addObject("messages", "");
		}
		return mav;
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDepartmentByID(@PathVariable("id") Integer id) {
		Optional<Unit> unit = this.unitDao.findById(id);
		if (unit.isPresent()) {
			return new ResponseEntity<>(unit.get(), HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestParam("unitId") int id) {
		boolean check = false;
		if (id > -1) {
			check = unitDao.delete(id);
		}
		if (check)
			return ResponseEntity.ok("Success");
		else
			return ResponseEntity.ok("Fails");

	}

	@RequestMapping(value = "/topicdepartment/insert", method = RequestMethod.POST)
	private ModelAndView insert(@ModelAttribute("actiondetailRequest") ActionDetailRequest actionDetailRequest) {
		ModelAndView mav = new ModelAndView();

		int department = actionDetailDao.insertActionDetail(actionDetailRequest);

		mav.setViewName("redirect:/unit/library/" + department);
		return mav;
	}
}
