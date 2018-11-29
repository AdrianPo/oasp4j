package io.oasp.gastronomy.restaurant.suppliermanagement.service.impl.rest;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;
import io.oasp.gastronomy.restaurant.suppliermanagement.service.api.rest.SuppliermanagementRestService;
import io.oasp.module.service.common.api.client.ServiceClientFactory;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement",
"service.client.app.restaurant.user.login=manager" })
public class SuppliermanagementRestServiceTest extends AbstractRestServiceTest {

  private SuppliermanagementRestService service;

  @Inject
  private ServiceClientFactory serviceClientFactory;

  @Before
  public void initialize() {

    this.service = this.serviceClientFactory.create(SuppliermanagementRestService.class);
  }

  @Test
  public void testFindSupplier() {

    // given
    Long id = 3l;

    // when
    SupplierEto supplier = this.service.getSupplier(id);

    // then
    assertThat(supplier).isNotNull();
    assertThat(supplier.getName()).isEqualTo("Albafrost");
  }

  @After
  public void end() {

    this.service = null;
  }
}
