package com.rent.rest_api_impl;

import com.rent.model.dto.UserDto;
import com.rent.model.entity.Users;
import com.rent.rest_api.UsersController;
import com.rent.service_api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UsersControllerImpl implements UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("general/login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Users user = new Users();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("general/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Users userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("general/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("general/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home_admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + (user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")"));
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value="/general/home", method = RequestMethod.GET)
    public ModelAndView home_general(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + (user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")"));
        modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
        modelAndView.setViewName("general/home");
        return modelAndView;
    }

//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth.getAuthorities().toString() == "USER") {
//            modelAndView.setViewName("general/home");
//        } else if(auth.getAuthorities().toString() == "ADMIN"){
//            modelAndView.setViewName("admin/home");
//        }
//        return modelAndView;
//    }

//    --------------------------------------------------------------------------------


//    private Logger log = LoggerFactory.getLogger(UsersControllerImpl.class);
//
//    @Override
//    @GetMapping("/login")
//    public String login(HttpServletRequest request) {
//        log.debug("REST request for user login");
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        return userName;
//    }
//
//    @Override
//    @GetMapping("/logout")
//    public String logout(HttpServletResponse response, HttpServletRequest request) {
//        log.debug("REST request for get all users from DB");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null) {
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//        }
//        return "logout";
//    }
//
//    @Override
//    public UserDto getLoggedUser() {
//        return null;
//    }
//
//    /*
//        example
//        {
//            "firstName": "first",
//            "lastName": "last",
//            "identNumber": "aefneklgnlseingl",
//            "age": 34,
//            "userName": "f_l"
//        }
//         */
//    @Override
//    @PostMapping("/user/add")
//    public ResponseEntity addUser(@RequestBody UserDto user) {
//        if(user != null) {
//            userService.saveUser(user);
//            return new ResponseEntity<String>("User added", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    @PutMapping("/user/edit/{id}")
//    public ResponseEntity editUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
//        if(userService.existsUserWithId(id)) {
//            userService.updateUser(id, updatedUser);
//            return new ResponseEntity<String>("User updated", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    @DeleteMapping("/admin/user/delete/{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id) {
//        if(userService.existsUserWithId(id)) {
//            userService.deleteUser(id);
//            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
//    }
}
