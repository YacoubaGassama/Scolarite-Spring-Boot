package uahb.m1gl.gestionscolarite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.helper.ClasseHelper;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.service.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClasseController {

    private final ClasseHelper classeHelper;

    public ClasseController(ClasseHelper classeHelper) {
        this.classeHelper = classeHelper;
    }

    @GetMapping
    public @ResponseBody List<ClasseResponse> allClasses(){
        return classeHelper.findAllClasses();
    }
}