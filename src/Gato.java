public class Gato extends Animal {
    public Gato(String nome, int idade, String cor) {
        super(nome, idade, cor);
    }

    @Override
    public String getNome() {
    
        return "O Gato " + super.getNome();
    }


     @Override
    public void emitirSom() {
        System.out.println(this.getNome() +" mia de felicidade");
        
    }
}