package com.ds;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Prueba unitaria simple para la clase App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Crea la prueba con el nombre especificado.
     *
     * @param testName nombre del caso de prueba
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * Devuelve el conjunto de pruebas que deben ejecutarse.
     *
     * @return suite de pruebas para esta clase
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Prueba de ejemplo que siempre pasa.
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
