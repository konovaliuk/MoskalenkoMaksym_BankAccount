package main.java.controllers;

import main.java.dao.ProfileDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Profile;
import main.java.utils.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

public class ProfileByIdController extends BaseController {
    protected Command getCommand(String commandClassName) {
        return new ProfileByIdCommand();
    }

    private static class ProfileByIdCommand implements Command {
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String id = request.getParameter("id");
            assert !Objects.equals(id, "");

            ProfileDao profileDao = DaoFactory.getProfileDao();
            Profile profile = profileDao.getById(UUID.fromString(id));

            request.setAttribute("profile", profile);
            return "profile.jsp";
        }
    }
}
