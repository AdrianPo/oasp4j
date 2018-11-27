package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import static com.querydsl.core.alias.Alias.$;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.querydsl.core.alias.Alias;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;

@Named
public class SpecialDaoImpl extends ApplicationMasterDataDaoImpl<SpecialEntity> implements SpecialDao {

  /**
   * The constructor.
   */
  public SpecialDaoImpl() {

    super();
  }

  @Override
  protected Class<SpecialEntity> getEntityClass() {

    return SpecialEntity.class;
  }

  @Override
  public List<SpecialEntity> getSpecialForSearchCriteria(SpecialSearchCriteriaTo searchCriteriaTo) {

    if (searchCriteriaTo == null) {
      return new ArrayList<>(0);
    }

    SpecialEntity special = Alias.alias(SpecialEntity.class);
    JPQLQuery<SpecialEntity> query = new JPAQuery<SpecialEntity>(getEntityManager()).from($(special));

    if (searchCriteriaTo.getOfferNumber() != null && searchCriteriaTo.getOfferNumber() > 0) {
      OfferEntity offer = Alias.alias(OfferEntity.class);
      query = query.join($(special.getOffer()), $(offer));
      query.where($(offer.getId()).eq(searchCriteriaTo.getOfferNumber()));
    }

    if (searchCriteriaTo.getStartingDate() != null) {
      LocalDateTime searchDate = searchCriteriaTo.getStartingDate();
      query.where($(special.getActivePeriod().getStartingDay()).eq(searchDate.getDayOfWeek()));
      query.where($(special.getActivePeriod().getStartingHour()).gt(searchDate.getHour()));
      query.orderBy($(special.getActivePeriod().getStartingHour()).asc());
    }
    List<SpecialEntity> result = query.fetch();
    return result;
  }

}
