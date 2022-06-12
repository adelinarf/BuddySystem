import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import BuddySystem.*

//Este es el archivo que se utilizo para la realizacion del Unit Test
//En los test se considero la lista mutable de bloques libres, las pruebas que incluyen assertEquals
//Y las pruebas acerca de si puede o no dividir en mas bloques, con las pruebas que incluyen assertTrue y assertFalse

internal class SampleTest {

    private val testSample: Sample = Sample()

    @Test
    fun testBuddy() {
        var b = BuddySystem(16)
        assertTrue(b.reservar("primero",4))
        assertFalse(b.reservar("segundo",9))
        b.liberar("primero")
        assertTrue(b.reservar("segundo",9))
        var c = BuddySystem(64)
        assertTrue(c.reservar("primero",4))
        assertTrue(c.reservar("segundo",9))
        assertFalse(c.reservar("segundo",9))
        assertFalse(c.reservar("segundo",19))
        assertTrue(c.reservar("tercero",7))
        assertTrue(c.reservar("cuarto",10))
        assertTrue(c.reservar("quinto",5))
        assertFalse(c.reservar("quinto",9))
        assertFalse(c.reservar("sexto",20))
        var f = BuddySystem(4)
        assertFalse(f.reservar("bloque1",20))
        assertFalse(f.reservar("bloque2",10))
        assertFalse(f.reservar("bloque3",5))
        assertTrue(f.reservar("bloque4",1))
        assertFalse(f.reservar("bloque4",2))
        assertFalse(f.reservar("bloque5",3))

        var d = BuddySystem(64)
        assertTrue(d.reservar("primero",4))
        assertTrue(d.reservar("segundo",9))
        assertTrue(d.reservar("tercero",7))
        assertTrue(d.reservar("cuarto",10))
        assertTrue(d.reservar("quinto",5))
        assertFalse(d.reservar("sexto",20))
        assertEquals(setOf(d.liberar("quinto")),setOf(mutableListOf(16,4)))

        var e = BuddySystem(16)
        assertTrue(e.reservar("bloque1",4))
        assertTrue(e.reservar("bloque2",8))
        assertFalse(e.reservar("bloque3",7))
        assertEquals(setOf(e.liberar("bloque1")),setOf(mutableListOf(8)))
    }
}
