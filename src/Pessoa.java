public class Pessoa {

    private static int contador = 1;

    private int id;
    private String nome;
    private double altura;
    private double peso;
    private double imc;

    public Pessoa(String nome, double altura, double peso) {
        this.id = contador++;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        calcularIMC();
    }

    private void calcularIMC() {
        imc = peso / (altura * altura);
    }

    public String getClassificacao() {

        if (imc < 18.5) return "Abaixo do Peso";
        if (imc < 24.9) return "Peso Normal";
        if (imc < 29.9) return "Sobrepeso";
        if (imc < 34.9) return "Obesidade Grau 1";
        if (imc < 39.9) return "Obesidade Grau 2";

        return "Obesidade Grau 3";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public double getImc() {
        return imc;
    }
}