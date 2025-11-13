package app.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.components.UserServiceComponent;
import app.dto.UserDto;
import app.entities.User;

@Path("/eateneo")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceComponent userService;

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new WebApplicationException("{\"error\": \"Name is required\", \"status\": 400}", 400);
        }
        if (userDto.getIdNumber() == null || !userDto.getIdNumber().matches("\\d{6}")) {
            throw new WebApplicationException("{\"error\": \"Invalid ID number (must be 6 digits)\", \"status\": 400}", 400);
        }
        if (userDto.getPhoneNumber() == null || !userDto.getPhoneNumber().startsWith("+63") || userDto.getPhoneNumber().length() != 13) {
            throw new WebApplicationException("{\"error\": \"Phone number must start with +63 and be 13 digits\", \"status\": 400}", 400);
        }

        return userService.addNewUser(userDto);
    }
}
