package io.oasp.gastronomy.restaurant.offermanagement.common.api;

import java.time.DayOfWeek;

public interface WeeklyPeriod {

  /**
   * @return startingDay
   */
  DayOfWeek getStartingDay();

  /**
   * @param startingDay new value of {@link #getstartingDay}.
   */
  void setStartingDay(DayOfWeek startingDay);

  /**
   * @return startingHour
   */
  int getStartingHour();

  /**
   * @param startingHour new value of {@link #getstartingHour}.
   */
  void setStartingHour(int startingHour);

  /**
   * @return endingDay
   */
  DayOfWeek getEndingDay();

  /**
   * @param endingDay new value of {@link #getendingDay}.
   */
  void setEndingDay(DayOfWeek endingDay);

  /**
   * @return endingHour
   */
  int getEndingHour();

  /**
   * @param endingHour new value of {@link #getendingHour}.
   */
  void setEndingHour(int endingHour);
}
