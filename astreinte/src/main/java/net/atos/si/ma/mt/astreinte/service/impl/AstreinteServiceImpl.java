package net.atos.si.ma.mt.astreinte.service.impl;

import java.util.Calendar;
import java.util.List;

import net.atos.si.ma.mt.astreinte.dao.AstreinteDAO;
import net.atos.si.ma.mt.astreinte.dao.JourFerierDAO;
import net.atos.si.ma.mt.astreinte.dao.TicketDAO;
import net.atos.si.ma.mt.astreinte.model.Astreinte;
import net.atos.si.ma.mt.astreinte.model.Ressource;
import net.atos.si.ma.mt.astreinte.model.StatutAstreinte;
import net.atos.si.ma.mt.astreinte.model.TypeAstreinte;
import net.atos.si.ma.mt.astreinte.service.AstreinteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AstreinteServiceImpl extends
		GenericServiceImpl<Astreinte, AstreinteDAO> implements AstreinteService {

	@Autowired
	@Qualifier("astreinteDAOImpl")
	@Override
	public void setDao(AstreinteDAO dao) {
		super.setDao(dao);
	}

	private JourFerierDAO jourFerierDAO;
	private TicketDAO ticketDAO;

	@Autowired
	@Qualifier("jourFerierDAOImpl")
	public void setDaoJourFerier(JourFerierDAO jourFerierDAO) {
		this.jourFerierDAO = jourFerierDAO;
	}

	@Autowired
	@Qualifier("ticketDAOImpl")
	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

	public Astreinte find(long id) {
		Astreinte objRegister = getDao().find(id);
		return objRegister;
	}

	@Override
	public void save(Astreinte astreinte) {
		super.save(astreinte);
	}

	public void process(List<Astreinte> astreintes) {
		if (astreintes != null)
			for (Astreinte astreinte : astreintes) {
				process(astreinte);
			}

	}

	public void process(Astreinte astreinte) {
		if (astreinte != null && astreinte.getDateAstreinte() != null
				&& astreinte.getHdebut() != null && astreinte.getHfin() != null) {
			if (astreinte.getTypeAstreinte().getId() == 1) {
				astreinte.setK(TypeAstreinte.K_0_33);
			} else {
				if (jourFerierDAO.isJourFerier(astreinte.getDateAstreinte())) {
					astreinte.setK(TypeAstreinte.K_2);
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(astreinte.getDateAstreinte());
					int jour = calendar.get(Calendar.DAY_OF_WEEK);
					switch (jour) {
					case Calendar.SUNDAY:
						astreinte.setK(TypeAstreinte.K_2);
						break;

					default:
						astreinte.setK(TypeAstreinte.K_1_5);
						break;
					}

				}
			}
			astreinte.setCharge(astreinte.getK()
					* (astreinte.getHfin().getTime() - astreinte.getHdebut()
							.getTime()) / (1000 * 60 * 60 * 8));

		}

	}

	public List<Astreinte> checkChevechment(Astreinte astreinte) {
		return getDao().checkChevechment(astreinte);
	}

	@Override
	public List<Astreinte> getByRessource(long idressource, long idqc) {
		// TODO Auto-generated method stub
		return getDao().getByRessource(idressource, idqc);
	}

	@Override
	public void changeStaut(Astreinte astreinte, long idStatut) {
		StatutAstreinte statutAstreinte = new StatutAstreinte();
		statutAstreinte.setId(idStatut);
		astreinte.setStatutAstreinte(statutAstreinte);
		getDao().saveOrUpdate(astreinte);

	}

}
