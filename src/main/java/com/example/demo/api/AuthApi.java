package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.dto.SigninInfoDto;
import com.example.demo.entity.ERole;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TokenRefreshTokenPairRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthApi {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private TokenRefreshTokenPairRepository tokenRefreshTokenPairRepository;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		ReturnObject returnObject = new ReturnObject();
		try {
			// Xác thực thông tin đăng nhập của người dùng
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			// Đặt đối tượng xác thực vào bảo mật context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Tạo mã thông báo truy cập (JWT)
			String jwt = jwtUtils.generateJwtToken(authentication);


			/* new */
//			// Tạo mã thông báo làm mới
//			String refreshToken = jwtUtils.generateRefreshToken(authentication);
//
//			// Lưu trữ mã thông báo làm mới vào cơ sở dữ liệu
//			tokenRefreshTokenPairRepository.save(new RefreshJwtToken(refreshToken));
//
//			// Thiết lập các thông tin JWT vào header của phản hồi
//			HttpHeaders responseHeaders = new HttpHeaders();
//			responseHeaders.set("Authorization", "Bearer " + jwt);
//			responseHeaders.set("RefreshToken", refreshToken);
			/*end new*/

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			returnObject.setStatus(ReturnObject.SUCCESS);
			returnObject.setMessage("200");

			SigninInfoDto results = new SigninInfoDto();
			results.setJwt(jwt);
			results.setRoles(roles);
			results.setUserDetails(userDetails);

			returnObject.setRetObj(results);
		}
		catch (Exception ex){
			returnObject.setStatus(ReturnObject.ERROR);
//			returnObject.setMessage(ex.getMessage());
			String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
			returnObject.setMessage(errorMessage);
		}

		return ResponseEntity.ok(returnObject);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signOut(HttpServletRequest request) {
		ReturnObject returnObject = new ReturnObject();
		try {

			// Lấy mã thông báo làm mới từ request
//			String refreshToken = jwtUtils.getRefreshTokenFromRequest(request);

			// Xóa mã thông báo làm mới khỏi cơ sở dữ liệu
//			tokenRefreshTokenPairRepository.deleteByRefreshToken(refreshToken);

			// Get the current user's token
//			HttpServletRequest requestJWT = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//			String authorizationHeader = request.getHeader("Authorization");
//			String token = authorizationHeader.substring("Bearer ".length());
//			jwtUtils.deleteJwtTokens(token);
//
//
//			String authToken = jwtUtils.getTokenFromRequest(request);
//
//			if (authToken != null) {
//				String username = jwtUtils.getUsernameFromToken(authToken);
//				UserEntity user = userRepository.findByUsername(username).get();
//
//				if (user != null) {
////					token.remove(authToken);
////					userRepository.save(user);
//					jwtUtils.invalidateToken(authToken);
//				}
//			}

			// Xóa đối tượng xác thực trong bảo mật context
			SecurityContextHolder.getContext().setAuthentication(null);

			SecurityContextHolder.clearContext();

			returnObject.setStatus(ReturnObject.SUCCESS);
			returnObject.setMessage("Đăng xuất thành công");
		} catch (Exception ex) {
			returnObject.setStatus(ReturnObject.ERROR);
//			returnObject.setMessage(ex.getMessage());
			String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
			returnObject.setMessage(errorMessage);
		}

		return ResponseEntity.ok(returnObject);
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()), null);

		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleEntity> roles = new HashSet<>();

		if (strRoles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
//				case "mod":
//					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
