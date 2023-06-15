import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class TelaAnimais extends JFrame {
    private static final String NOME_ARQUIVO = "animaiscadastrados.txt";
    private ArrayList<Animal> animaisCadastrados;

    public TelaAnimais() {
        setTitle("Cadastro de Animais");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        JButton btnCadastrar = new JButton("Resgastar Animal");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAnimal();
            }
        });

        JButton btnExibir = new JButton("Exibir Animais Resgatados");
        btnExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirAnimaisCadastrados();
            }
        });

        painel.add(btnCadastrar);
        painel.add(btnExibir);

        add(painel);

        animaisCadastrados = new ArrayList<>();
        carregarAnimaisDoArquivo();
    }

    public void cadastrarAnimal() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do animal:");
        String idadeStr = JOptionPane.showInputDialog(this, "Digite a idade do animal:");
        String cor = JOptionPane.showInputDialog(this, "Digite a cor do animal:");

    if (nome != null && !nome.isEmpty() && idadeStr != null && !idadeStr.isEmpty() && cor != null && !cor.isEmpty()) {
        try {
            int idade = Integer.parseInt(idadeStr);
            String[] opcoesAnimais = {"Cachorro", "Gato"};
            String tipoAnimal = (String) JOptionPane.showInputDialog(this,
                    "Selecione o tipo de animal:",
                    "Resgate do Tipo de Animal",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoesAnimais,
                    opcoesAnimais[0]);

            Animal animal;

            if (tipoAnimal.equals("Cachorro")) {
                animal = new Cachorro(nome, idade, cor);
            } else {
                animal = new Gato(nome, idade, cor);
            }

            animaisCadastrados.add(animal);
            salvarAnimaisNoArquivo();
            JOptionPane.showMessageDialog(this, "Animal resgatado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite uma idade válida para o animal.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
    }

}

    public void exibirAnimaisCadastrados() {
        if (animaisCadastrados.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nenhum animal resgatado.");
    } else {
        String[] opcoesAnimais = new String[animaisCadastrados.size()];
        for (int i = 0; i < animaisCadastrados.size(); i++) {
            opcoesAnimais[i] = animaisCadastrados.get(i).getNome();
        }

        String animalSelecionado = (String) JOptionPane.showInputDialog(this,
                "Selecione um animal:",
                "Animais Resgatados",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesAnimais,
                opcoesAnimais[0]);

        if (animalSelecionado != null) {
            Animal animal = buscarAnimalPorNome(animalSelecionado);
            if (animal != null) {
                exibirDetalhesAnimal(animal);
            }
        }
    }
}

    private Animal buscarAnimalPorNome(String nome) {
         for (Animal animal : animaisCadastrados) {
             if (animal.getNome().equals(nome)) {
                 return animal;
        }
    }
    return null;
}

    private void exibirDetalhesAnimal(Animal animal) {
        StringBuilder mensagem = new StringBuilder("Detalhes do animal:\n");
             mensagem.append("Nome: ").append(animal.getNome()).append("\n");
             mensagem.append("Idade: ").append(animal.getIdade()).append(" anos\n");
             mensagem.append("Cor: ").append(animal.getCor());
             
        JOptionPane.showMessageDialog(this, mensagem.toString());
}

    private void salvarAnimaisNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Animal animal : animaisCadastrados) {
                writer.write(animal.getNome() + "," + animal.getIdade() + "," + animal.getCor());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os animais resgatados. Tente novamente.");
        }
    }

    private void carregarAnimaisDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    String nome = dados[0];
                    int idade = Integer.parseInt(dados[1]);
                    String cor = dados[2];
                    Animal animal = new Animal(nome, idade, cor);
                    animaisCadastrados.add(animal);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os animais resgatados. Tente novamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaAnimais tela = new TelaAnimais();
                tela.setVisible(true);
            }
        });
    }
}

class Animal {
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
}
