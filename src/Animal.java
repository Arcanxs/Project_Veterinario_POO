public abstract class Animal {
    private String nome;
    private int idade;
    private String cor;

    public Animal(String nome, int idade, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }
    

    public abstract void emitirSom();

    public String getApelido(){
        return nome;
    }
}