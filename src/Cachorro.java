public class Cachorro extends Animal {
    public Cachorro(String nome, int idade, String cor) {
        super(nome, idade, cor);
    }
   
    @Override
    public String getNome() {
    
        return "O Cachorro " + super.getNome();
    }
    
    @Override
    public void emitirSom() {
        System.out.println(this.getNome() + " expressar gratidão latindo com animação");
        
    }

}