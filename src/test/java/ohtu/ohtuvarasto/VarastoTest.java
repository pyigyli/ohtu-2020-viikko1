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
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pit‰isi olla sama kun lis‰tty m‰‰r‰
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pit‰isi viel‰ olla tilavuus-lis‰tt‰v‰ m‰‰r‰ eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pit‰isi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastossaOnAlkusaldoa() {
        Varasto v = new Varasto(10, 10);
        v.otaVarastosta(5);
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellisenVarastonLuonti1() {
        Varasto v = new Varasto(-1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellisenVarastonLuonti2() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiLisataNegatiivisiaMaaria() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiLisataLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTest() {
        assertEquals("saldo = 0.0, viela tilaa 10.0", varasto.toString());
    }

    @Test
    public void varastoaEiLuodaTilavuudenYlittallaSaldolla() {
        Varasto v = new Varasto(1, 2);
        assertEquals(v.getTilavuus(), v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEiOtaNegatiivisellaArvolla() {
        Varasto v = new Varasto(10, 10);
        v.otaVarastosta(-1);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEiOtaLiikaa() {
        Varasto v = new Varasto(10, 10);
        v.otaVarastosta(11);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
}