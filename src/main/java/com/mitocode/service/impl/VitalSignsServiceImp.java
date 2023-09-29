package com.mitocode.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.VitalSigns;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVitalSigns;
import com.mitocode.service.IVitalSignsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VitalSignsServiceImp extends CRUDImpl<VitalSigns, Integer> implements IVitalSignsService {

    private final IVitalSigns vitalSignsRepo;

    @Override
    protected IGenericRepo<VitalSigns, Integer> getRepo() {
        return vitalSignsRepo;
    }

    @Override
    public Page<VitalSigns> listPage(Pageable pageable) {
        return vitalSignsRepo.findAll(pageable);
    }

}
