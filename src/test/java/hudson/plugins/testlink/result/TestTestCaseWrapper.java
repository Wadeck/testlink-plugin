/* 
 * The MIT License
 * 
 * Copyright (c) 2010 Bruno P. Kinoshita <http://www.kinoshita.eti.br>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.plugins.testlink.result;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 2.0
 */
public class TestTestCaseWrapper 
extends junit.framework.TestCase
{

	protected TestCase testCase;
	protected Build build;
	protected TestPlan testPlan;
	
	protected TestCaseWrapper<TestSuite> testResult;
	
	private final String suiteName = "Sample junit test suite.";
	
	private String[] customFieldsNames = new String[]{"nz", "au"};
	
	protected void setUp()
	{
		testCase = new TestCase();
		testCase.setId( 100 );
		build = new Build();
		build.setId( 100 );
		testPlan = new TestPlan();
		testPlan.setId( 100 );
		
		TestSuite junitTestSuite = new TestSuite();
		
		junitTestSuite.setName( suiteName );
		
		testResult = new TestCaseWrapper<TestSuite>(testCase, customFieldsNames, junitTestSuite );
	}
	
	public void testTestResultTestCaseId()
	{
		assertTrue( testResult.getId() == 100 );
	}

	public void testTestResultNotes()
	{
		assertNotNull( this.testResult.getNotes() );
		
		String newNotes = "Home sweet home";
		this.testResult.appendNotes( newNotes );
		
		assertNotNull( this.testResult.getNotes() );
	}
	
	public void testTestResultAttachments()
	{
		assertNotNull( testResult.getAttachments() );
		
		assertEquals ( testResult.getAttachments().size(), 0 );
		
		Attachment attachment = new Attachment();
		testResult.getAttachments().add( attachment );
		
		assertEquals( testResult.getAttachments().size(), 1 );
		
		attachment = new Attachment();
		testResult.addAttachment( attachment );
		
		assertEquals( testResult.getAttachments().size(), 2 );
	}
	
	public void testTestResultOrigin()
	{
		assertNotNull( testResult.getOrigin() );
		
		assertTrue( testResult.getOrigin() instanceof TestSuite );
		
		TestSuite origin = testResult.getOrigin();
		
		assertEquals( origin.getName(), suiteName );
	}
	
	public void testTestResultsCustomFields()
	{
		assertNotNull( testResult.getCustomFieldsNames() );
		
		assertEquals( testResult.getCustomFieldsNames().length, 2 );
	}
	
	public void testTestResultCustomFieldAndStatus()
	{
		assertNotNull( testResult.getCustomFieldAndStatus() );
		
		assertEquals( testResult.getCustomFieldAndStatus().size(), 0 );
	}

}
