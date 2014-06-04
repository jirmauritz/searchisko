package org.searchisko.ftest.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.searchisko.ftest.DeploymentHelpers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.searchisko.ftest.rest.RestTestHelpers.givenJsonAndLogIfFails;

/**
 * Integration test for /auth/status REST API.
 * <p/>
 * http://docs.jbossorg.apiary.io/#userauthenticationstatusapi
 *
 * @author Lukas Vlcek
 * @author Libor Krzyzanek
 */
@RunWith(Arquillian.class)
public class AuthStatusRestServiceTest {

	@ArquillianResource
	protected URL context;

	@Deployment(testable = false)
	public static WebArchive createDeployment() throws IOException {
		return DeploymentHelpers.createDeploymentMinimalWebXML();
	}

	@Test
	@InSequence(0)
	public void assertSSOServiceNotAvailable() throws MalformedURLException {
		givenJsonAndLogIfFails().
				when().get(new URL(context, DeploymentHelpers.DEFAULT_REST_VERSION + "auth/status").toExternalForm()).
				then().
				statusCode(200).
				header("WWW-Authenticate", nullValue()).
				body("authenticated", is(false));
	}
}
