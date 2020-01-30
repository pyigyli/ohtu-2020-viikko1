package ohtu.ohtuvarasto;

public class Varasto {

  private double tilavuus;
  private double saldo;

  public Varasto(double tilavuus) {
    this.tilavuus = Math.max(tilavuus, 0.0);
    saldo = 0;
  }

  public Varasto(double tilavuus, double alkuSaldo) {
    this.tilavuus = Math.max(tilavuus, 0.0);
    this.saldo = Math.max(alkuSaldo, 0.0);
    if (this.saldo > this.tilavuus) {
      this.saldo = this.tilavuus;
    }
  }

  public double getSaldo() {
    return saldo;
  }

  public double getTilavuus() {
    return tilavuus;
  }

  public double paljonkoMahtuu() {
    return tilavuus - saldo;
  }

  public void lisaaVarastoon(double maara) {
    if (maara < 0) {
      return;
    }
    if (maara <= paljonkoMahtuu()) {
      saldo = saldo + maara;
    } else {
      saldo = tilavuus;
    }
  }

  public double otaVarastosta(double maara) {
    if (maara < 0) {
      return 0.0;
    }
    if (maara > saldo) {
      double kaikkiMitaVoidaan = saldo;
      saldo = 0.0;
      return kaikkiMitaVoidaan;
    }
    saldo = saldo - maara;
    return maara;
  }

  public String toString() {
    return ("saldo = " + saldo + ", viela tilaa " + paljonkoMahtuu());
  }
}