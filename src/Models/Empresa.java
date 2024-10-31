package Models;

public class Empresa {
    String nomeEmpresa;
    String inscricaoEmpresa;

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public String getInscricaoEmpresa() {
        return inscricaoEmpresa;
    }

    public void setInscricaoEmpresa(String inscricaoEmpresa) {
        this.inscricaoEmpresa = inscricaoEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    double valorMercado;

    public Empresa(String nomeEmpresa, String inscricaoEmpresa, double valorMercado) {
        this.nomeEmpresa = nomeEmpresa;
        this.inscricaoEmpresa = inscricaoEmpresa;
        this.valorMercado = valorMercado;
    }

    @Override
    public String toString() {
        return nomeEmpresa + " | " + inscricaoEmpresa + " | " + valorMercado;
    }
}
