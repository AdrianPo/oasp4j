package io.oasp.gastronomy.restaurant.offermanagement.common.api;

import io.oasp.gastronomy.restaurant.general.common.api.ApplicationEntity;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;

public interface Special extends ApplicationEntity {

  /**
   * @return name
   */
  String getName();

  /**
   * @param name new value of {@link #getname}.
   */
  void setName(String name);

  /**
   * @return offer Id
   */
  Long getOfferId();

  /**
   * @param name new value of {@link #getOfferId}.
   */
  void setOfferId(Long offerId);

  /**
   * @return activePeriod
   */
  WeeklyPeriod getActivePeriod();

  /**
   * @param activePeriod new value of {@link #getactivePeriod}.
   */
  void setActivePeriod(WeeklyPeriod activePeriod);

  /**
   * @return specialPrice
   */
  Money getSpecialPrice();

  /**
   * @param specialPrice new value of {@link #getspecialPrice}.
   */
  void setSpecialPrice(Money specialPrice);

}
