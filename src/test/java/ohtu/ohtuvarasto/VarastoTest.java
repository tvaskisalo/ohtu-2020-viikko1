package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusKonstruktorissaToimiiOikein() {
        Varasto alkuSaldoVarasto = new Varasto(-10, 10);
        assertEquals(0, alkuSaldoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusKonstruktorissaToimiiOikein2() {
        Varasto alkuVarasto = new Varasto(-10);
        assertEquals(0, alkuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void nollaSaldonaToimiiOikein() {
        Varasto alkuSaldoVarasto = new Varasto(10, 0);
        assertEquals(0, alkuSaldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test 
    public void negatiivinenSaldoOnNollaSaldoKonstruktorissa() {
        Varasto alkuVarasto = new Varasto(10, -10);
        assertEquals(0, alkuVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void liianSuuriSaldoTäyttääTilavuudenOikein() {
        Varasto alkuSaldoVarasto = new Varasto(10, 100);
        assertEquals(10, alkuSaldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonAlkusaldolla() {
        Varasto alkuSaldoVarasto = new Varasto(10, 10);
        assertEquals(10, alkuSaldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    
    public void negatiivinenLisäysEiTeeMitaan() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);

        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaminenEiKasvataSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(10000);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test

    public void toStringToimiiOikein() {
        assertTrue(varasto.toString().equals("saldo = " + 0.0 + ", vielä tilaa "+10.0));
    }

    @Test
    public void otaNegatiivinenMaaraEiTeeMitaan() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-100);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kaikenOttaminenPalauttaaSaldon() {
        double saldo = varasto.otaVarastosta(100000);
        assertEquals(0, saldo, vertailuTarkkuus);
    }

    @Test 
    public void kaikenOttaminenNollaaSaldon() {
        varasto.otaVarastosta(1099999);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
}