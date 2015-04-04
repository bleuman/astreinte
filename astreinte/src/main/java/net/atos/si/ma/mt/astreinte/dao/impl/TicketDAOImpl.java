
			package net.atos.si.ma.mt.astreinte.dao.impl;

				import net.atos.si.ma.mt.astreinte.dao.TicketDAO;
				import net.atos.si.ma.mt.astreinte.model.Ticket;
				import net.atos.si.ma.mt.astreinte.model.Ticket;

				import org.springframework.stereotype.Repository;

				@Repository
				public class TicketDAOImpl extends GenericHibernateDAO<Ticket> implements
						TicketDAO {

					public TicketDAOImpl() {
						super(Ticket.class);
					}

					public Ticket find(long id) {
						Ticket registerFound = (Ticket) getCurrentSession().get(
								Ticket.class, id);

						return registerFound;
					}

				}
		
	