package io.oasp.gastronomy.restaurant.offermanagement.common.api;

import io.oasp.gastronomy.restaurant.general.common.api.ApplicationEntity;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;

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
   * @return offer
   */
  OfferEntity getOffer();

  /**
   * @param offer new value of {@link #getoffer}.
   */
  void setOffer(OfferEntity offer);

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
