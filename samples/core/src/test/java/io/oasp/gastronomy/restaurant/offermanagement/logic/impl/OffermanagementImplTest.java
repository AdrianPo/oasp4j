package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferCto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.beanmapping.common.api.BeanMapper;
import io.oasp.module.test.common.base.ModuleTest;

/**
 * This class tests the correct execution of the methods findOffer and findOfferCto belonging to the
 * {@link OffermanagementImpl}
 *
 */

public class OffermanagementImplTest extends ModuleTest {

  private static final long ID = 1;

  /**
   * The System Under Test (SUT)
   */

  private OffermanagementImpl offerManagementImpl;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Mock
  private OfferDao offerDao;

  @Mock
  private SpecialDao specialDao;

  @Mock
  private BeanMapper beanMapper;

  /**
   * This method initializes the object {@link OffermanagementImpl} and assigns the mocked objects of the classes
   * {@link OfferDao} and {@link BeanMapper} to the attributes of the {@link OffermanagementImpl} object before tests,
   * if they are not null.
   */
  @Before
  public void init() {

    this.offerManagementImpl = new OffermanagementImpl();
    this.offerManagementImpl.setOfferDao(this.offerDao);
    this.offerManagementImpl.setBeanMapper(this.beanMapper);
    this.offerManagementImpl.setSpecialDao(this.specialDao);
  }

  /**
   * This method dereferences all object after each test
   */
  @After
  public void clean() {

    this.beanMapper = null;
    this.offerDao = null;
    this.offerManagementImpl = null;
    this.specialDao = null;
  }

  /**
   * This method tests the execution of the findOffer method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOffer() {

    // given
    OfferEntity offerEntity = mock(OfferEntity.class);
    OfferEto offerEto = new OfferEto();

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferEto responseOfferEto = this.offerManagementImpl.findOffer(ID);

    // then
    assertThat(responseOfferEto).isNotNull();
    assertThat(responseOfferEto).isEqualTo(offerEto);
  }

  /**
   * This method tests the execution of the findOfferCto method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOfferCto() {

    // given
    OfferCto offerCto = new OfferCto();
    OfferEto offerEto = new OfferEto();

    offerCto.setOffer(offerEto);
    OfferEntity offerEntity = mock(OfferEntity.class);

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferCto responseOfferCto = this.offerManagementImpl.findOfferCto(ID);

    // then
    assertThat(responseOfferCto).isNotNull();
    assertThat(responseOfferCto.getOffer()).isEqualTo(offerEto);

  }

  /**
   * This method tests the execution of the findOffer method belonging to the {@link OffermanagementImpl} class. It
   * includes the search for Special Offers
   */
  @Test
  public void findOfferWithSpecial() {

    // given
    Money testSpecialprice = new Money(2.22);
    OfferEntity offerEntity = mock(OfferEntity.class);
    OfferEto offerEto = new OfferEto();
    offerEto.setSpecialPrice(testSpecialprice);

    SpecialEntity specialEntity = new SpecialEntity();
    specialEntity.setSpecialPrice(testSpecialprice);

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.specialDao.getFirstSpecialForSearchCriteria(any(SpecialSearchCriteriaTo.class)))
        .thenReturn(specialEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferEto responseOfferEto = this.offerManagementImpl.findOffer(ID);

    // then
    assertThat(responseOfferEto).isNotNull();
    assertThat(responseOfferEto).isEqualTo(offerEto);
    assertThat(responseOfferEto.getSpecialPrice()).isEqualTo(testSpecialprice);
  }

  /**
   * This method tests if a special offer can be saved.
   */
  @Test
  public void shouldSaveSpecial() {

    // given
    SpecialEto specialEto = new SpecialEto();
    SpecialEntity specialEntity = new SpecialEntity();

    when(this.specialDao.save(specialEntity)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEntity, SpecialEto.class)).thenReturn(specialEto);
    when(this.beanMapper.map(specialEto, SpecialEntity.class)).thenReturn(specialEntity);

    // when
    SpecialEto responseSpecialEto = this.offerManagementImpl.saveSpecial(specialEto);

    // then
    assertThat(responseSpecialEto).isNotNull();
    assertThat(responseSpecialEto).isEqualTo(specialEto);
  }

  /**
   * This method tests loading of a Special.
   */
  @Test
  public void shouldLoadSpecial() {

    // given
    SpecialEto specialEto = new SpecialEto();
    specialEto.setId(ID);
    SpecialEntity specialEntity = new SpecialEntity();

    when(this.specialDao.findOne(ID)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEntity, SpecialEto.class)).thenReturn(specialEto);
    when(this.beanMapper.map(specialEto, SpecialEntity.class)).thenReturn(specialEntity);

    // when
    SpecialEto responseSpecialEto = this.offerManagementImpl.findSpecialEto(ID);

    // then
    assertThat(responseSpecialEto).isNotNull();
    assertThat(responseSpecialEto).isEqualTo(specialEto);
    assertThat(responseSpecialEto.getId()).isEqualTo(ID);
  }

  /**
   * This method tests loading of a 2 Special.
   */
  @Test
  public void shouldLoadAllSpecial() {

    // given
    SpecialEto firstSpecialEto = new SpecialEto();
    SpecialEto secondSpecialEto = new SpecialEto();
    List<SpecialEto> specialEtos = new ArrayList<>(2);
    specialEtos.add(firstSpecialEto);
    specialEtos.add(secondSpecialEto);

    List<SpecialEntity> specialEntities = new ArrayList<>(2);
    SpecialEntity firstSpecialEntity = new SpecialEntity();
    SpecialEntity secondSpecialEntity = new SpecialEntity();
    specialEntities.add(firstSpecialEntity);
    specialEntities.add(secondSpecialEntity);

    when(this.specialDao.findAll()).thenReturn(specialEntities);
    when(this.beanMapper.mapList(specialEntities, SpecialEto.class)).thenReturn(specialEtos);

    // when
    List<SpecialEto> responseSpecialEtos = this.offerManagementImpl.findAllSpecials();

    // then
    assertThat(responseSpecialEtos).isNotNull();
    assertThat(responseSpecialEtos.size()).isEqualTo(2);
    assertThat(responseSpecialEtos.get(0)).isEqualTo(firstSpecialEto);
    assertThat(responseSpecialEtos.get(1)).isEqualTo(secondSpecialEto);
  }
}
