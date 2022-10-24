package com.thuong.controller;

import com.thuong.model.Expenditure;
import com.thuong.model.ExpenditureForm;
import com.thuong.service.ExpenditureService;
import com.thuong.service.IExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenditureController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IExpenditureService expenditureService;

    @GetMapping("/create-expenditure")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/expenditure/create");
        modelAndView.addObject("expenditureForm", new ExpenditureForm());
        return modelAndView;
    }
    @PostMapping("/create-expenditure")
    public  ModelAndView saveExpenditure(Model model, @ModelAttribute("expenditure") ExpenditureForm expenditureForm){
        MultipartFile multipartFile = expenditureForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(expenditureForm.getImage().getBytes(), new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expenditure expenditure= new Expenditure(expenditureForm.getId(),
                expenditureForm.getName(),
                expenditureForm.getPrice(),
                expenditureForm.getDescription(),
                expenditureForm.getListSpend(),fileName);
        expenditureService.save(expenditure);
//        List<String> listSpend=new ArrayList<>();
//        listSpend.add("Market");
//        listSpend.add("Cinema");
//        listSpend.add("Travel");
//        listSpend.add("Learning");
        ModelAndView modelAndView= new ModelAndView("/expenditure/create");
//        model.addAttribute("spend", listSpend);
        modelAndView.addObject("expenditureForm", new ExpenditureForm());
        modelAndView.addObject("message", "New Expenditure create successfully");
        return  modelAndView;
    }
    @GetMapping("/expenditures")
    public ModelAndView listExpenditures(){
        List<Expenditure> expenditures=expenditureService.findAll();
        ModelAndView modelAndView=new ModelAndView("/expenditure/list");
        modelAndView.addObject("expenditures", expenditures);
        return modelAndView;
    }
    @GetMapping("/edit-expenditure/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Expenditure expenditure=expenditureService.findById(id);
        if (expenditure!=null){
            ModelAndView modelAndView=new ModelAndView("/expenditure/edit");
            modelAndView.addObject("expenditure", expenditure);
            return modelAndView;
        }else {
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-expenditure")
public  ModelAndView updateExpenditure(@ModelAttribute("expenditure") ExpenditureForm expenditureForm){
        MultipartFile multipartFile = expenditureForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(expenditureForm.getImage().getBytes(), new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expenditure expenditure= new Expenditure(expenditureForm.getId(),
                expenditureForm.getName(),
                expenditureForm.getPrice(),
                expenditureForm.getDescription(),
                expenditureForm.getListSpend(),fileName);
        expenditureService.save(expenditure);
        ModelAndView modelAndView= new ModelAndView("/expenditure/edit");
        modelAndView.addObject("expenditureForm",expenditureForm);
        modelAndView.addObject("message", "New Expenditure edit successfully");
        return  modelAndView;
    }
    @GetMapping("/delete-expenditure/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Expenditure expenditure= expenditureService.findById(id);
        if (expenditure!=null){
            ModelAndView modelAndView=new ModelAndView("/expenditure/delete");
            modelAndView.addObject("expenditure", expenditure);
            return modelAndView;
        }else {
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-expenditure")
    public String deleteExpenditure(@ModelAttribute("expenditure") Expenditure expenditure){
        expenditureService.remove(expenditure.getId());
        return "redirect:expenditures";
    }
}
