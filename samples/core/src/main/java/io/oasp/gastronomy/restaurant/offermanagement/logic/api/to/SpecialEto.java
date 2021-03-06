package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.Special;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.WeeklyPeriod;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.WeeklyPeriodEmbeddable;
import io.oasp.module.basic.common.api.to.AbstractEto;

public class SpecialEto extends AbstractEto implements Special {

  private static final long serialVersionUID = 1L;

  private String name;

  private Long offerId;

  private WeeklyPeriodEmbeddable activePeriod;

  private Money specialPrice;

  public SpecialEto() {

    super();
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  @Override
  public WeeklyPeriodEmbeddable getActivePeriod() {

    return this.activePeriod;
  }

  @Override
  public void setActivePeriod(WeeklyPeriod activePeriod) {

    WeeklyPeriodEmbeddable embeddable = new WeeklyPeriodEmbeddable();
    embeddable.setEndingDay(activePeriod.getEndingDay());
    embeddable.setStartingDay(activePeriod.getStartingDay());
    embeddable.setEndingHour(activePeriod.getEndingHour());
    embeddable.setStartingHour(activePeriod.getStartingHour());
    this.activePeriod = embeddable;
  }

  @Override
  public Money getSpecialPrice() {

    return this.specialPrice;
  }

  @Override
  public void setSpecialPrice(Money specialPrice) {

    this.specialPrice = specialPrice;
  }

  @Override
  public Long getOfferId() {

    return this.offerId;
  }

  @Override
  public void setOfferId(Long offerId) {

    this.offerId = offerId;
  }

}
