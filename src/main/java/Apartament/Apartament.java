package Apartament;
public class Apartament {
    private int codApartament;
    private int etaj;
    private int nrCamere;
    private double pret;
    private double metriPatrati;
    private int codAgent;

    public Apartament(int codApartament, int etaj, int nrCamere, double pret, double metriPatrati, int codAgent) {
        this.codApartament = codApartament;
        this.etaj = etaj;
        this.nrCamere = nrCamere;
        this.pret = pret;
        this.metriPatrati = metriPatrati;
        this.codAgent = codAgent;
    }

    public int getCodApartament() {
        return codApartament;
    }

    public void setCodApartament(int codApartament) {
        this.codApartament = codApartament;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }

    public int getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(int nrCamere) {
        this.nrCamere = nrCamere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public double getMetriPatrati() {
        return metriPatrati;
    }

    public void setMetriPatrati(double metriPatrati) {
        this.metriPatrati = metriPatrati;
    }

    public int getCodAgent() {
        return codAgent;
    }

    public void setCodAgent(int codAgent) {
        this.codAgent = codAgent;
    }
}
