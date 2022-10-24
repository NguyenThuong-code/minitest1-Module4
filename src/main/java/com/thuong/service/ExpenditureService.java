package com.thuong.service;

import com.thuong.model.Expenditure;
import com.thuong.repository.IExpenditureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpenditureService implements IExpenditureService{
    @Autowired
    private IExpenditureRepository expenditureRepository;
    @Override
    public List<Expenditure> findAll() {
        return expenditureRepository.findAll();
    }

    @Override
    public Expenditure findById(Long id) {
        return expenditureRepository.findById(id);
    }
    @Override
    public void save(Expenditure expenditure) {
       expenditureRepository.save(expenditure);
    }


    @Override
    public void remove(Long id) {
        expenditureRepository.remove(id);
    }
}
