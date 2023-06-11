class Gato extends Animal {
    public Gato(String nome, int idade, String cor) {
        super(nome, idade, cor);
    }

    @Override
    public void emitirSom() {
        System.out.println("O gato está miando.");
    }

    public void arranhar() {
        System.out.println("O gato está arranhando seu brinquedo.");
    }
}