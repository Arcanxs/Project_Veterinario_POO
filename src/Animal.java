abstract class Animal {
    private String nome;
    private int idade;
    private String cor;

    public Animal(String nome, int idade, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.cor = cor;
    }

    public abstract void emitirSom();

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void dormir() {
        System.out.println("O animal está dormindo.");
    }
}