package net.atos.si.ma.mt.astreinte.service.impl;

import net.atos.si.ma.mt.astreinte.dao.RessourceDAO;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.service.RessourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RessourceServiceImpl extends
		GenericServiceImpl<Ressource, RessourceDAO> implements RessourceService {

	@Autowired
	@Qualifier("ressourceDAOImpl")
	@Override
	public void setDao(RessourceDAO dao) {
		super.setDao(dao);
	}

	public Ressource find(long id) {
		Ressource objRegister = getDao().find(id);
		return objRegister;
	}

}
