package com.mitocode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.model.VitalSigns;

public interface IVitalSignsService extends ICRUD<VitalSigns, Integer> {

    Page<VitalSigns> listPage(Pageable pageable);

}
