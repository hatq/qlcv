package info.dt.qlcv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import info.dt.qlcv.common.Common;
import info.dt.qlcv.entity.AccessToken;
import info.dt.qlcv.entity.Role;
import info.dt.qlcv.entity.User;
import info.dt.qlcv.model.UserRequest;
import info.dt.qlcv.repository.AccessTokenRepository;
import info.dt.qlcv.repository.RoleRepository;
import info.dt.qlcv.repository.UserRepository;

@Service
public class UserDAO {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AccessTokenRepository accessTokenRepository;

	public User getUserById(String strIdUser) {
		int idUser = Integer.parseInt(strIdUser);
		Optional<User> userOptional = userRepository.findById(idUser);
		return userOptional.orElse(null);

	}
	
	public List<User> findByRoleGreaterThanEqual(Integer roleId) {
		return this.userRepository.findByRoleIdGreaterThanEqual(roleId);
	}

	public User getUserById(int idUser) {
		Optional<User> userOptional = userRepository.findById(idUser);
		return userOptional.orElse(null);

	}

	public User getCheckLevel4(int idUser) {
		// User level = userRepository.getCheckUserLevel4(idUser);
		return new User();

	}

	public String addUser(UserRequest userRequest) {
		try {
			if (!userRepository.existsByUserName(userRequest.getUserName())) {

				User user = new User();
				user.setUserName(userRequest.getUserName());
				user.setFirstName(userRequest.getFirstName());
				user.setLastName(userRequest.getLastName());
				user.setPhone(userRequest.getPhone());
				user.setEmail(userRequest.getEmail());
				user.setPassword(Common.hashPass(userRequest.getPassword()));
				user.setRoleId(userRequest.getRoleId());
				user.setDonVi(convertDonVi(userRequest.getIdDonVi()));

				this.userRepository.save(user);
				return "True";
			} else {
				return "Email or User name exist";
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	private String convertDonVi(String[] arrDonVi) {
		return String.join(",", arrDonVi);
	}

	public String editUser(UserRequest userRequest) {
		try {
			Optional<User> userOptional = userRepository.findById(userRequest.getIdUser());
			if (userOptional.isPresent()) {
				Role role = roleRepository.findByLevelAndStatus(userRequest.getRoleId(), 0);
				if (role == null)
					return "Role don't exist";

				User user = userOptional.get();
				if (!user.getEmail().equals(userRequest.getEmail())
						&& !user.getUserName().equals(userRequest.getUserName())
						&& userRepository.existsByUserNameOrEmail(userRequest.getUserName(), userRequest.getEmail())) {
					ModelAndView mav = new ModelAndView("user/manager");
					mav.addObject("messages", "Email or User name exist");
				}
				user.setUserName(userRequest.getUserName());
				user.setEmail(userRequest.getEmail());
				user.setRole(role);
				if (!userRequest.getPassword().equals(user.getPassword())) {
					String password = Common.hashPass(userRequest.getPassword());
					user.setPassword(password);
				} else {
					ModelAndView mav = new ModelAndView("user/manager");
					mav.addObject("messages", "Password not entered");
				}
				this.userRepository.save(user);
				return "True";
			} else {
				return "Account not found";
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	public User login(String userName, String password) {

		Optional<User> userOptional = userRepository.findTopByUserNameOrEmailAndPassword(userName, userName, password);
		if (userOptional.isPresent()) {
			AccessToken accessToken = accessTokenRepository.findByUser(userOptional.get()).orElse(new AccessToken());
			accessToken.setStatus(0);
			String token = Common.createToken(userOptional.get().getEmail(), userOptional.get().getPassword());
			if (token.isEmpty())
				return null;
			accessToken.setToken(token);
			accessToken.setUser(userOptional.get());
			AccessToken accessTokenInsert = accessTokenRepository.save(accessToken);
			userOptional.get().setAccessToken(accessTokenInsert);
			userOptional.get().setPassword("");

			return userOptional.get();
		} else {
			return null;
		}
	}

	public List<User> getAllUsert() {
		List<User> lstUser = (List<User>) userRepository.findAll();
		if (lstUser != null && lstUser.size() > 0)
			return lstUser;
		return new ArrayList<>();
	}

	public boolean checkToken(String strIdUser, String token) {
		int idUser = Integer.parseInt(strIdUser);
		Optional<User> user = userRepository.findById(idUser);
		return accessTokenRepository.existsByUserAndToken(user.orElse(null), token);
	}

	public void logout(String strIdUser, String token) {
		int idUser = Integer.parseInt(strIdUser);
		Optional<User> user = userRepository.findById(idUser);
		Optional<AccessToken> accessToken = accessTokenRepository.findByUserAndToken(user.orElse(null), token);
		accessToken.ifPresent(accessToken1 -> accessTokenRepository.delete(accessToken1));
	}

	public boolean delete(int idUser) {
		try {
			Optional<User> user = userRepository.findById(idUser);
			if (user.isPresent()) {
				userRepository.delete(user.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
