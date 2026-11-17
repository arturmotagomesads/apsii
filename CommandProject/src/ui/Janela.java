package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Janela extends JFrame {

    private static final Color FUNDO = new Color(29, 30, 40);
    private static final Color BORDA = Color.WHITE;
    private static final Font FONTE = new Font("Bahnschrift", Font.BOLD, 14);

    private final JLabel ultimaTecla;

    public Janela() {
        super("Atalhos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel raiz = new JPanel(null);
        raiz.setBackground(FUNDO);
        raiz.setPreferredSize(new Dimension(763, 529));

        JLabel titulo = new JLabel("ATALHOS", SwingConstants.CENTER);
        titulo.setFont(FONTE);
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(0, 8, 763, 20);
        raiz.add(titulo);

        String[] descricoes = {
                "Abrir windows explorer",         // F1
                "Abrir Microsoft Edge",           // F2
                "Abrir paint",             // F3
                "Abrir calculadora",              // F4
                "Abrir bloco de notas",           // F5
                "Abrir configurações do windows", // F6
                "Aumentar volume do som",         // F7
                "Diminuir volume do som",         // F8
                "Desativar som (mudo)",           // F9
                "Ir para Área de Trabalho (minimizar tudo)",       // F10
                "Desfazer última ação",         // F11
                "Sair"                 // F12
            };

        int[] colX = {60, 444};
        for (int i = 0; i < 12; i++) {
            int coluna = i / 6;
            int linha = i % 6;
            raiz.add(criarAtalho(colX[coluna], 78 + linha * 62, "F" + (i + 1), descricoes[i]));
        }

        ultimaTecla = new JLabel("ÚLTIMA TECLA DIGITADA:", SwingConstants.CENTER);
        ultimaTecla.setFont(FONTE);
        ultimaTecla.setForeground(Color.WHITE);
        ultimaTecla.setBorder(new LineBorder(BORDA, 2));
        ultimaTecla.setBounds(188, 470, 322, 42);
        raiz.add(ultimaTecla);

        setContentPane(raiz);
        pack();
        setLocationRelativeTo(null);
    }

    /** Atualiza o texto da última tecla (1 a 12). */
    public void setUltimaTecla(int numeroF) {
        ultimaTecla.setText("ULTIMA TECLA DIGITADA: F" + numeroF);
    }

    private JPanel criarAtalho(int x, int y, String tecla, String descricao) {
        JPanel caixa = new JPanel(new BorderLayout());
        caixa.setBackground(FUNDO);
        caixa.setBorder(new LineBorder(BORDA, 2));
        caixa.setBounds(x, y, 258, 42);

        JLabel lblTecla = new JLabel(tecla, SwingConstants.CENTER);
        lblTecla.setFont(FONTE);
        lblTecla.setForeground(Color.WHITE);
        lblTecla.setPreferredSize(new Dimension(65, 42));
        lblTecla.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, BORDA));

        JLabel lblDesc = new JLabel(
                "<html><div style='text-align:center'>" + descricao + "</div></html>",
                SwingConstants.CENTER);
        lblDesc.setFont(FONTE);
        lblDesc.setForeground(Color.WHITE);

        caixa.add(lblTecla, BorderLayout.WEST);
        caixa.add(lblDesc, BorderLayout.CENTER);
        return caixa;
    }
}