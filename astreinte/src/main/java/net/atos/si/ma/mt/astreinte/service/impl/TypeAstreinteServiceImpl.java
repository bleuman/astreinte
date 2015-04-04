package net.atos.si.ma.mt.astreinte.service.impl;

import net.atos.si.ma.mt.astreinte.dao.TypeAstreinteDAO;
import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;
import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;
import net.atos.si.ma.mt.astreinte.service.TypeAstreinteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TypeAstreinteServiceImpl extends
		GenericServiceImpl<TypeAstreinte, TypeAstreinteDAO> implements
		TypeAstreinteService {
	

	@Autowired
	@Qualifier("typeAstreinteDAOImpl")
	@Override
	public void setDao(TypeAstreinteDAO dao) {
		super.setDao(dao);
	}

	public TypeAstreinte find(long id) {
		TypeAstreinte objRegister = getDao().find(id);
		return objRegister;
	}

}
