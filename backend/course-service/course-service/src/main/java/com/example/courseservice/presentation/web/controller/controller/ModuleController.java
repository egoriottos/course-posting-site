package com.example.courseservice.presentation.web.controller.controller;

import com.example.courseservice.application.commands.module.ModuleResponse;
import com.example.courseservice.application.commands.module.query.ModuleQuery;
import com.example.courseservice.application.commands.module.CreateModuleCommand;
import com.example.courseservice.application.commands.module.UpdateModuleCommand;
import com.example.courseservice.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/module")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;
    private final ModelMapper modelMapper;

    @PostMapping("/search")
    public List<ModuleResponse> search(@RequestBody ModuleQuery query) {
        return moduleService.search(query);
    }
    @PostMapping("/create/{id}")
    public String create(@PathVariable Long id, @RequestBody CreateModuleCommand command){
        moduleService.createModule(id, command);
        return "Module created " + id + " successfully "
                + command.getTitle() + " " + command.getDescription()
                +" "+ command.getCourse().getId();
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        moduleService.deleteModule(id);
        return "Module deleted " + id + " successfully ";
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody UpdateModuleCommand command){
        moduleService.updateModule(id, command);
        return "Module updated successfully " + command.getTitle() + " " + command.getDescription();
    }
    @PutMapping("/move/{moduleId}")
    public String move(@PathVariable Long moduleId, @RequestParam int newOrder){
        moduleService.moveModule(moduleId, newOrder);
        return "Module moved successfully " + moduleId + " " + newOrder;
    }
    @GetMapping("/getLessonsId/{id}")
    public List<Integer> getLessonsId(@PathVariable Long id){
       return moduleService.getLessonsIdFromModule(id);

    }
    @GetMapping("/all")
    public List<ModuleResponse> getAll(){
        return moduleService.getAll().stream().map(i->modelMapper.map(i, ModuleResponse.class)).collect(Collectors.toList());
    }
    @GetMapping("/modules/fromCourse/{id}")
    public List<ModuleResponse> getAllModulesFromCourse(@PathVariable Long id){
        return moduleService.getAllModulesFromCourse(id).stream().map(i->modelMapper.map(i, ModuleResponse.class)).collect(Collectors.toList());
    }

}
