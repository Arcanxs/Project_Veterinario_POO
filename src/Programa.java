import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;

public class Programa {
    public static void main(String[] args) {
        ArrayList<Animal> animais = new ArrayList<>();

        Cachorro cachorro = new Cachorro("Panqueca", 2, "Preta com Patas Brancas");
        Gato gato = new Gato("Laranjinho", 1, "Lajanja Listrado");

        animais.add(cachorro);
        animais.add(gato);

        for (Animal animal : animais) {
            animal.emitirSom();

            if (animal instanceof Cachorro) {
                ((Cachorro) animal).abanarRabo();
            } else if (animal instanceof Gato) {
                ((Gato) animal).arranhar();
            }

            Veterinario veterinario = new Veterinario();
            veterinario.examinarAnimal(animal);
        }

         // Criação da janela principal
         JFrame frame = new JFrame("Lista para Adoção");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         // Criação do painel para exibição dos animais
         JPanel panel = new JPanel();
 
         // Criação do botão para exibir os animais
         JButton button = new JButton("Exibir Pets");
 
         // Definição da ação do botão
         button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 StringBuilder sb = new StringBuilder();
                 for (Animal animal : animais) {
                     sb.append("Nome: " + animal.getNome() + "\n");
                     sb.append("Idade: " + animal.getIdade() + "\n");
                     sb.append("Cor: " + animal.getCor() + "\n");
                     sb.append("\n");
                 }
                 JOptionPane.showMessageDialog(frame, sb.toString());
             }
         });

         // Adição do botão e do painel à janela principal
        frame.getContentPane().add(button, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Configuração da janela principal
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}