package ddsGrupo2.wicket;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import ddsGrupo2.wicket.EntradaPage;
import ddsGrupo2.wicket.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(EntradaPage.class);

		//assert rendered page class
		tester.assertRenderedPage(EntradaPage.class);
	}
}
