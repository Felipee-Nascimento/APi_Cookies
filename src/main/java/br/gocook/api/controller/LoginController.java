package br.gocook.api.controller;

import br.gocook.api.model.User;
import br.gocook.api.repository.UserRepository;
import br.gocook.api.service.CookieService;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    private UserRepository ur;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        //model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        return "redirect:http://127.0.0.1:5500/app/Home/home.html";
    }

    @GetMapping("/login")
    public String login(){
        return "redirect:http://127.0.0.1:5500/app/telaLogin/login.html";
    }

    @PostMapping("/logar")
    public String logar(User user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        User usuario = this.ur.login(user.getEmail(), user.getPassword());
        if(usuario != null){
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuario.getId()), 10000);
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuario.getName()), 10000);
            return "redirect:http://127.0.0.1:5500/app/telaLogin/login.html";
        }

        //model.addAttribute("erro", "Usuario Invalido!");
        return "redirect:http://127.0.0.1:5500/app/telaLogin/login.html";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "redirect:http://127.0.0.1:5500/app/telaCadastro/cadastro.html";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String Cadastrar(@Valid User user, BindingResult result){

        if(result.hasErrors()){
            return "redirect:http://127.0.0.1:5500/app/telaCadastro/cadastro.html";
        }

        ur.save(user);
        return "redirect:http://127.0.0.1:5500/app/telaLogin/login.html";
    }
}
