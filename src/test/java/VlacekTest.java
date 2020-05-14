import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VlacekTest {
    Vlacek vlacek;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vlacek = new Vlacek();
    }

    @Test
    void test1() {
        assertEquals(2, vlacek.getDelka());
        assertTrue(this::isPostovniVagonLast);
    }

    @Test
    void test2() {
        vlacek.pridatVagonek(VagonekType.DREVO);
        vlacek.pridatVagonek(VagonekType.DREVO);
        assertTrue(this::isPostovniVagonLast);
        Vagonek vagonek = vlacek.getVagonekByIndex(2);
        assertEquals(VagonekType.DREVO, vagonek.getType());
        vlacek.pridatVagonek(VagonekType.UHLI);
        vagonek = vlacek.getVagonekByIndex(2);
        assertEquals(VagonekType.UHLI, vagonek.getType());
        vagonek = vlacek.getVagonekByIndex(3);
        assertEquals(VagonekType.DREVO, vagonek.getType());
        assertEquals(3, vagonek.getUmisteni());
        assertTrue(this::isPostovniVagonLast);
    }

    @Test
    void test3() {
        test2(); // L,U,D,D,P
        assertTrue(vlacek.getVagonkyTekaveLatky().isEmpty());
        assertEquals(2, vlacek.getPocetVagonkuByType(VagonekType.DREVO));
        assertEquals(1, vlacek.getPocetVagonkuByType(VagonekType.UHLI));
        assertEquals(0, vlacek.getPocetVagonkuByType(VagonekType.AUTA));
    }

    @Test
    void test4() {
        test2(); // L,U,D,D,P
        vlacek.pridatVagonek(VagonekType.NAFTA);
        assertTrue(this::isPostovniVagonLast);
        vlacek.pridatVagonekTekaveLatky();
        Vagonek vagonek = vlacek.getVagonekByIndex(6);
        assertEquals(VagonekType.TEKAVE_LATKY, vagonek.getType());
        vagonek = vlacek.getVagonekByIndex(5);
        assertEquals(VagonekType.NAFTA, vagonek.getType());
        assertTrue(this::isPostovniVagonLast);
    }

    @Test
    void test5() {
        test4(); // L,U,D,D,N,T,P
        List<Vagonek> tekaveLatky = vlacek.getVagonkyTekaveLatky();
        assertEquals(1, tekaveLatky.size());
    }

    @Test
    void test6() {
        test4(); // L,U,D,D,N,T,P
        vlacek.pridatVagonek(VagonekType.AUTA);
        vlacek.pridatVagonekTekaveLatky(); // L,U,D,D,N,T,P,A,T
        Vagonek vagonek = vlacek.getVagonekByIndex(9);
        assertEquals(VagonekType.TEKAVE_LATKY, vagonek.getType());
        assertFalse(vlacek.getVagonkyTekaveLatky().isEmpty());
        vagonek = vlacek.getLastVagonekByType(VagonekType.DREVO);
        assertEquals(4, vagonek.getUmisteni());
    }

    @Test
    void test7() {
        test4(); // L,U,D,D,N,T,P
        vlacek.pridatVagonek(VagonekType.UHLI);
        vlacek.pridatVagonek(VagonekType.UHLI); // L,U,U,U,D,D,N,T,P
        vlacek.pridatVagonek(VagonekType.AUTA);
        vlacek.pridatVagonek(VagonekType.AUTA); // L,U,U,U,D,D,N,T,P,A,A
        Vagonek vagonek = vlacek.getVagonekByIndex(5);
        assertEquals(VagonekType.DREVO, vagonek.getType());
        vagonek = vlacek.getVagonekByIndex(9);
        assertEquals(VagonekType.POSTOVNI, vagonek.getType());
        assertFalse(this::isPostovniVagonLast);
    }

    @Test
    void test8() {
        test7(); // L,U,U,U,D,D,N,T,P,A,A
        assertFalse(this::isPostovniVagonLast);
        List<Vagonek> vagonkyTekaveLatky = vlacek.getVagonkyTekaveLatky();
        assertEquals(1, vagonkyTekaveLatky.size());
        assertEquals(8, vagonkyTekaveLatky.get(0).getUmisteni());
        Vagonek vagonek = vlacek.getVagonekByIndex(10);
        assertEquals(VagonekType.AUTA, vagonek.getType());
        vlacek.odebratPosledniVagonekByType(VagonekType.DREVO);
        vlacek.odebratPosledniVagonekByType(VagonekType.AUTA); // L,U,U,U,D,N,T,P,A
        assertEquals(9, vlacek.getDelka());
        assertFalse(this::isPostovniVagonLast);
    }

    @Test
    void test9() {
        test8();  // L,U,U,U,D,N,T,P,A
        vlacek.odebratPosledniVagonekByType(VagonekType.UHLI);
        vlacek.odebratPosledniVagonekByType(VagonekType.UHLI); // L,U,D,N,T,P,A
        assertEquals(1, vlacek.getPocetVagonkuByType(VagonekType.NAFTA));
        vlacek.odebratPosledniVagonekByType(VagonekType.AUTA);
        assertTrue(this::isPostovniVagonLast);
        vlacek.pridatVagonek(VagonekType.AUTA);
        vlacek.pridatVagonek(VagonekType.NAFTA);
        vlacek.pridatVagonek(VagonekType.NAFTA); // L,U,D,N,T,N,N,P,A
    }

    @Test
    void test10() {
        test9(); // L,U,D,N,T,N,N,P,A
        Vagonek vagonek = vlacek.getVagonekByIndex(4);
        assertEquals(VagonekType.NAFTA, vagonek.getType());
        vagonek = vlacek.getLastVagonekByType(VagonekType.POSTOVNI);
        assertEquals(8, vagonek.getUmisteni());
        vlacek.pridatVagonek(VagonekType.UHLI);
        vlacek.pridatVagonek(VagonekType.UHLI); // L,U,U,U,D,N,T,N,N,P,A
        vlacek.pridatVagonekTekaveLatky(); // L,U,U,U,D,N,T,N,N,T,P,A
        assertFalse(this::isPostovniVagonLast);
    }

    @Test
    void test11() {
        test10(); // L,U,U,U,D,N,T,N,N,T,P,A
        vlacek.pridatVagonek(VagonekType.DREVO);
        vlacek.pridatVagonek(VagonekType.AUTA); // L,U,U,U,D,D,N,T,N,N,T,P,A,A
        assertEquals(3, vlacek.getPocetVagonkuByType(VagonekType.UHLI));
        Vagonek vagonek = vlacek.getLastVagonekByType(VagonekType.POSTOVNI);
        assertEquals(12, vagonek.getUmisteni());
    }

    @Test
    void test12() {
        test11(); // L,U,U,U,D,D,N,T,N,N,T,P,A,A
        assertEquals(14, vlacek.getDelka());
        vlacek.pridatVagonekTekaveLatky();
        Vagonek vagonek = vlacek.getVagonekByIndex(6);
        assertEquals(VagonekType.DREVO, vagonek.getType());
        vagonek = vlacek.getVagonekByIndex(15);
        assertEquals(VagonekType.TEKAVE_LATKY, vagonek.getType());
    }

    @Test
    void test13() {
        test11(); // L,U,U,U,D,D,N,T,N,N,T,P,A,A,T
        vlacek.odebratPosledniVagonekByType(VagonekType.TEKAVE_LATKY);
        vlacek.odebratPosledniVagonekByType(VagonekType.TEKAVE_LATKY); // L,U,U,U,D,D,N,T,N,N,P,A,A
        vlacek.odebratPosledniVagonekByType(VagonekType.UHLI);
        vlacek.pridatVagonek(VagonekType.NAFTA); // L,U,U,U,D,D,N,T,N,N,N,P,A,A
        Vagonek vagonek = vlacek.getVagonekByIndex(4);
        assertEquals(VagonekType.UHLI, vagonek.getType());

    }

    private boolean isPostovniVagonLast() {
        Vagonek vagonek = vlacek.getVagonekByIndex(vlacek.getDelka());
        return VagonekType.POSTOVNI == vagonek.getType();
    }
}