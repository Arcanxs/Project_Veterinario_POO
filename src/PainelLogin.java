import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PainelLogin extends JFrame {
    private static final String NOME_ARQUIVO = "usuarios.txt";
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private TelaAnimais telaAnimais;

    public PainelLogin() {
        
        setTitle("Sistema de Resgate Animal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        // Criação dos componentes da interface
        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField(15);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(15);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        // Adiciona os componentes ao painel
        painel.add(lblUsuario);
        painel.add(txtUsuario);
        painel.add(lblSenha);
        painel.add(txtSenha);
        painel.add(btnEntrar);
        painel.add(btnCadastrar);

        // Adiciona o painel ao JFrame
        add(painel);
    }

    private void cadastrarUsuario() {
        String nomeUsuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        // Verifica se o usuário já está cadastrado
        if (verificarUsuarioExistente(nomeUsuario)) {
            JOptionPane.showMessageDialog(this, "Usuário já cadastrado. Tente novamente.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            // Grava os dados de usuário no arquivo
            writer.write(nomeUsuario + "," + senha);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o cadastro. Tente novamente.");
        }
    }

    private boolean verificarUsuarioExistente(String nomeUsuario) {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;

            // Lê o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String usuario = dados[0];

                // Verifica se o usuário já existe no arquivo
                if (usuario.equals(nomeUsuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    private void realizarLogin() {
        String nomeUsuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        // Verifica se o usuário existe
        if (!verificarUsuarioExistente(nomeUsuario)) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado. Tente novamente.");
            return;
        }

        // Verifica as credenciais de login
        if (verificarCredenciais(nomeUsuario, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            telaAnimais = new TelaAnimais();
            telaAnimais.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas. Tente novamente.");
        }
    }

    private boolean verificarCredenciais(String nomeUsuario, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;

            // Lê o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String usuario = dados[0];
                String senhaArmazenada = dados[1];

                // Verifica se o usuário e a senha correspondem aos dados armazenados
                if (usuario.equals(nomeUsuario) && senhaArmazenada.equals(senha)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PainelLogin painel = new PainelLogin();
                painel.setVisible(true);
            }
        });
    }
}