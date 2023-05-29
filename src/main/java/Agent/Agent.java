package Agent;

public class Agent {
    private int codAgent;
    private String nume;
    private String prenume;
    private int varsta;
    private String telefon;

    public Agent(int codAgent, String nume, String prenume, int varsta, String telefon) {
        this.codAgent = codAgent;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.telefon = telefon;
    }

    public int getCodAgent() {
        return codAgent;
    }

    public void setCodAgent(int codAgent) {
        this.codAgent = codAgent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
