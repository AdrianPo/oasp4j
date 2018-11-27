package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import java.time.LocalDateTime;

import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

public class SpecialSearchCriteriaTo extends SearchCriteriaTo {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  private Long offerNumber;

  private LocalDateTime startingDate;

  /**
   * @return offerNumber
   */
  public Long getOfferNumber() {

    return this.offerNumber;
  }

  /**
   * @param offerNumber new value of {@link #getofferNumber}.
   */
  public void setOfferNumber(Long offerNumber) {

    this.offerNumber = offerNumber;
  }

  /**
   * @return date
   */
  public LocalDateTime getStartingDate() {

    return this.startingDate;
  }

  /**
   * @param date new value of {@link #getdate}.
   */
  public void setStartingDate(LocalDateTime startingDate) {

    this.startingDate = startingDate;
  }

}
