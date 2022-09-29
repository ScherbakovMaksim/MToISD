package ru.rsatu;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.joda.time.LocalTime;
import ru.rsatu.ParentClass;
import ru.rsatu.Child1;
import ru.rsatu.Child2;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	 @Test
    public void checkTime()
    {
		LocalTime check1 = new LocalTime();

        assertTrue( check1 != null );

    }
	
	@Test
    public void checkClasses1()
    {
		String check2 = ParentClass.Mess;

		assertTrue( check2 != null );
    }
	
	 @Test
    public void checkClasses2()
    {
		String check3 = ParentClass.Mess2;

		assertTrue( check3 != null );
    }	
	
}
