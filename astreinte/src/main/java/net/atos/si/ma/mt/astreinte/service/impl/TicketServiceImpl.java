package net.atos.si.ma.mt.astreinte.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.atos.si.ma.mt.astreinte.dao.TicketDAO;
import net.atos.si.ma.mt.astreinte.model.Ticket;
import net.atos.si.ma.mt.astreinte.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends GenericServiceImpl<Ticket, TicketDAO>
		implements TicketService {

	@Autowired
	@Qualifier("ticketDAOImpl")
	@Override
	public void setDao(TicketDAO dao) {
		super.setDao(dao);
	}

	public Ticket find(long id) {
		Ticket objRegister = getDao().find(id);
		return objRegister;
	}

	@Override
	public List<Ticket> recherche(String qc) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("qc:string", qc);
		List<Ticket> list = getDao().listByCriteres("from Ticket where qc like'"+qc+"'",null,null);
		return list;
	}

}
