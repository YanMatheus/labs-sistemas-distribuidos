package jtree;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class Main implements Runnable {

    private static final String rootDir = "/home/micael/Documentos";
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;

    @Override
    public void run() {
        JFrame frame = new JFrame("File Browser Example");
        Container panel = frame.getContentPane();

        JButton btnSelecionar = new JButton("Selecionar");
        File fileRoot = new File(Main.rootDir);
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);

        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        JScrollPane scrollPane = new JScrollPane(tree);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocationByPlatform(true);
        frame.setSize(480, 480);
        frame.setVisible(true);
        panel.setLayout(new BoxLayout(panel, 0));
        panel.add(scrollPane);
        panel.add(btnSelecionar);


        // listener para o clique no botão
        btnSelecionar.addActionListener((ActionEvent ae) -> {
            atuarSobreDiretorioSelecionado( tree.getSelectionPath() );
        });

        // listener para escutar dois cliques com o botão esquerdo do mouse na árvore
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

                if ((selRow >= 0) && (e.getClickCount() == 2)) {
                    atuarSobreDiretorioSelecionado(selPath);
                }
            }
        });


        ChildNode ccn = new ChildNode(fileRoot, root);
        new Thread(ccn).start();
    }

    public static void atuarSobreDiretorioSelecionado(TreePath tp) {
        if (tp == null) return;
        TreeNode node = (TreeNode) tp.getLastPathComponent();
        if (!node.isLeaf())
            JOptionPane.showMessageDialog(null, "Caminho do diretório selecionado:\n" + tp);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

}
