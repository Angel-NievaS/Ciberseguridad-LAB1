package cl.lab.inventario.crud.controllers;

import cl.lab.inventario.crud.exceptions.CrudException;
import cl.lab.inventario.crud.model.ResponseModel;
import cl.lab.inventario.crud.model.UsuarioModel;
import cl.lab.inventario.crud.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseModel<UsuarioModel> login(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password) {
        ResponseModel<UsuarioModel> result = new ResponseModel<>();

        try {
            result.setData(this.loginService.login(usuario, password));
            result.setSuccess(Boolean.TRUE);
        } catch (CrudException e) {
            result.setError(e.getMessage());
            result.setMessage(e.getMessage());
            result.setSuccess(Boolean.FALSE);
        }
        return result;
    }

}
