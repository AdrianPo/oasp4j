package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.dataaccess.api.ApplicationPersistenceEntity;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.Special;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.WeeklyPeriod;

@Entity
@Table(name = "Special")
public class SpecialEntity extends ApplicationPersistenceEntity implements Special {

  private String name;

  private OfferEntity offer;

  private WeeklyPeriod activePeriod;

  private Money specialPrice;

  /**
   * @return name
   */
  @Column(unique = true)
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return offer
   */

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "offerId")
  public OfferEntity getOffer() {

    return this.offer;
  }

  /**
   * @param offer new value of {@link #getoffer}.
   */
  public void setOffer(OfferEntity offer) {

    this.offer = offer;
  }

  /**
   * @return activePeriod
   */
  @Embedded
  public WeeklyPeriod getActivePeriod() {

    return this.activePeriod;
  }

  /**
   * @param activePeriod new value of {@link #getactivePeriod}.
   */
  public void setActivePeriod(WeeklyPeriod activePeriod) {

    this.activePeriod = activePeriod;
  }

  /**
   * @return specialPrice
   */
  public Money getSpecialPrice() {

    return this.specialPrice;
  }

  /**
   * @param specialPrice new value of {@link #getspecialPrice}.
   */
  public void setSpecialPrice(Money specialPrice) {

    this.specialPrice = specialPrice;
  }

  @Override
  @Transient
  public Long getOfferId() {

    if (this.offer == null) {
      return null;
    }
    return this.offer.getId();
  }

  @Override
  public void setOfferId(Long offerId) {

    if (offerId == null) {
      this.offer = null;
    } else {
      OfferEntity offerEntity = new OfferEntity();
      offerEntity.setId(offerId);
      this.offer = offerEntity;
    }

  }
}
