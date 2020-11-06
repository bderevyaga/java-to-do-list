package com.example.toDo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoController {
    private static final List<ToDo> toDoList = new ArrayList<>();

    static {
        toDoList.add(new ToDo("First task", ToDoStatus.Init));
        toDoList.add(new ToDo("Next task", ToDoStatus.Init));
    }

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/toDoList")
    public String toDoList(Model model) {
        model.addAttribute("toDoList", toDoList);

        return "toDoList";
    }

    @GetMapping("/addToDo")
    public String addToDo(Model model) {
        model.addAttribute("toDoForm", new ToDoForm());

        return "addToDo";
    }

    @PostMapping("/addToDo")
    public String saveToDo(Model model, @ModelAttribute("toDoForm") ToDoForm toDoForm) {
        String text = toDoForm.getText();

        if (text != null && text.length() > 0) {
            toDoList.add(new ToDo(text, ToDoStatus.Init));

            return "redirect:/toDoList";
        }

        model.addAttribute("errorMessage", errorMessage);

        return "addToDo";
    }

    @GetMapping("/deleteToDo/{id}")
    public String deleteToDo(@PathVariable(value="id") String id) {
        toDoList.remove(Integer.parseInt(id));

        return "redirect:/toDoList";
    }

    @GetMapping("/doneToDo/{id}")
    public String doneToDo(@PathVariable(value="id") String id) {
        toDoList.get(Integer.parseInt(id)).setStatus(ToDoStatus.Done);

        return "redirect:/toDoList";
    }
}