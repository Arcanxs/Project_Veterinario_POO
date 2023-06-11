class Cachorro extends Animal {
    public Cachorro(String nome, int idade, String cor) {
        super(nome, idade, cor);
    }

    @Override
    public void emitirSom() {
        System.out.println("O cachorro está latindo.");
    }

    public void abanarRabo() {
        System.out.println("O cachorro está abanando o rabo.");
    }
}