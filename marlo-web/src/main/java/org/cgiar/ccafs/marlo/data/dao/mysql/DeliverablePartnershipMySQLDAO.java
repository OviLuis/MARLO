/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.DeliverablePartnershipDAO;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnership;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

@Named
public class DeliverablePartnershipMySQLDAO extends AbstractMarloDAO<DeliverablePartnership, Long>
  implements DeliverablePartnershipDAO {


  @Inject
  public DeliverablePartnershipMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }


  @Override
  public void deleteDeliverablePartnership(long deliverablePartnershipId) {
    DeliverablePartnership deliverablePartnership = this.find(deliverablePartnershipId);
    deliverablePartnership.setActive(false);
    super.delete(deliverablePartnership);

  }


  @Override
  public boolean existDeliverablePartnership(long deliverablePartnershipID) {
    DeliverablePartnership deliverablePartnership = this.find(deliverablePartnershipID);
    if (deliverablePartnership == null) {
      return false;
    }
    return true;

  }

  @Override
  public DeliverablePartnership find(long id) {
    return super.find(DeliverablePartnership.class, id);

  }

  @Override
  public List<DeliverablePartnership> findAll() {
    String query = "from " + DeliverablePartnership.class.getName() + " where is_active=1";
    List<DeliverablePartnership> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }


  @Override
  public List<DeliverablePartnership> findByDeliverablePhasePartnerAndPartnerperson(long deliverableID, Long phase,
    Long projectPartnerId, Long projectPartnerPersonId, Long partnerDivisionId, String partnerType) {
    StringBuilder query = new StringBuilder();
    query.append("SELECT DISTINCT  ");
    query.append("dp.id as id ");
    query.append("FROM ");
    query.append("deliverable_partnerships dp ");
    query.append("WHERE ");
    query.append("dp.deliverable_id = " + deliverableID);
    query.append(" and dp.id_phase = " + phase);
    query.append(" and dp.partner_type = '" + partnerType + "'");
    query.append(" and dp.project_partner_id = " + projectPartnerId);
    if (projectPartnerPersonId != null) {
      query.append(" and dp.partner_person_id = " + projectPartnerPersonId);
    } else {
      query.append(" and dp.partner_person_id IS NULL");
    }
    if (partnerDivisionId != null) {
      query.append(" and dp.division_id = " + partnerDivisionId);
    } else {
      query.append(" and dp.division_id IS NULL");
    }

    List<Map<String, Object>> rList = super.findCustomQuery(query.toString());

    List<DeliverablePartnership> DeliverablePartnerships = new ArrayList<>();

    if (rList != null) {
      for (Map<String, Object> map : rList) {
        DeliverablePartnership DeliverablePartnership = this.find(Long.parseLong(map.get("id").toString()));
        DeliverablePartnerships.add(DeliverablePartnership);
      }
    }
    return DeliverablePartnerships;
  }

  @Override
  public List<DeliverablePartnership> findForDeliverableIdAndPartnerTypeOther(long deliverableId) {
    String query = "select dp from DeliverablePartnership as dp " + "inner join dp.deliverable as d "
      + "where dp.active is true " + "and dp.partnerType = 'Other' " + "and d.id = :deliverableId ";

    Query createQuery = this.getSessionFactory().getCurrentSession().createQuery(query);
    createQuery.setParameter("deliverableId", deliverableId);

    List<DeliverablePartnership> deliverablePartnerships = createQuery.list();

    return deliverablePartnerships;
  }

  @Override
  public List<DeliverablePartnership> findForDeliverableIdAndProjectPersonIdPartnerTypeOther(long deliverableId,
    long projectPersonId) {
    String query = "select dp from DeliverablePartnership as dp " + "inner join dp.projectPartnerPerson as ppp "
      + "inner join dp.deliverable as d " + "where dp.active is true " + "and dp.partnerType = 'Other' "
      + "and d.id = :deliverableId " + "and ppp.id = :projectPersonId";

    Query createQuery = this.getSessionFactory().getCurrentSession().createQuery(query);
    createQuery.setParameter("deliverableId", deliverableId);
    createQuery.setParameter("projectPersonId", projectPersonId);

    List<DeliverablePartnership> deliverablePartnerships = createQuery.list();

    return deliverablePartnerships;
  }


  @Override
  public DeliverablePartnership save(DeliverablePartnership deliverablePartnership) {
    if (deliverablePartnership.getId() == null) {
      super.saveEntity(deliverablePartnership);
    } else {
      deliverablePartnership = super.update(deliverablePartnership);
    }
    return deliverablePartnership;
  }


}