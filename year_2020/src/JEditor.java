import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class JEditor {
    /**
     *编辑器类
     */
    private JFrame jFrame;
    private JTextPane jTextPane=new JTextPane();
    private JFileChooser jFileChooser=new JFileChooser();

    public static void main(String[] args) {
        JEditor jEditor1 =new JEditor();
        jEditor1.go();
    }


    public void go(){
        //实例化一个框架
        jFrame=new JFrame("wyh's editor");
        //实例化一个菜单栏
        JMenuBar jMenuBar=new JMenuBar();
        //实例化三个菜单
        JMenu jMenuFile=new JMenu("文件");
        JMenu jMenuEdit=new JMenu("编辑");
        JMenu jMenuHelp=new JMenu("帮助");

        //实例化菜单项的监听器
        Action actions[]={
                new OpenFile(),
                new NewFile(),
                new SaveFile(),

                new Cut(),
                new Copy(),
                new Paste(),

                new Help(),
                new About()
        };
        //在每个菜单下面添加菜单项
        //文件菜单项
        jMenuFile.add(new JMenuItem(actions[0]));
        jMenuFile.add(new JMenuItem(actions[1]));
        jMenuFile.add(new JMenuItem(actions[2]));
        //编辑菜单项
        jMenuEdit.add(new JMenuItem(actions[3]));
        jMenuEdit.add(new JMenuItem(actions[4]));
        jMenuEdit.add(new JMenuItem(actions[5]));
        //帮助菜单项
        jMenuHelp.add(new JMenuItem(actions[6]));
        jMenuHelp.add(new JMenuItem(actions[7]));

        //将菜单添加到菜单栏
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuHelp);

        //将菜单栏添加到框架
        jFrame.setJMenuBar(jMenuBar);
        jFrame.getContentPane().add(jTextPane, BorderLayout.CENTER);
        //设置一些框架的信息
        jFrame.setSize(1400,1400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    //编写菜单项的监听器
    //打开
    class OpenFile extends AbstractAction{
        public OpenFile(){
            super("打开");
        }

        //重写遇到事件发生时对应的方法
        @Override
        public void actionPerformed(ActionEvent e) {
            int status=jFileChooser.showOpenDialog(null);
            if ((status==JFileChooser.APPROVE_OPTION)){
                File file=jFileChooser.getSelectedFile();
                try{
                    InputStream fileInputStream=new FileInputStream(file);
                    BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
                    jTextPane.read(bufferedInputStream,"Fill the pane");

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //新建
    class NewFile extends AbstractAction{
        //以下均同上
        public NewFile(){
            super("新建");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jTextPane.setDocument(new DefaultStyledDocument());
        }
    }

    //保存
    class SaveFile extends AbstractAction{

        public SaveFile() {
            super("保存");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int status=jFileChooser.showSaveDialog(null);
            if (status==JFileChooser.APPROVE_OPTION){
                File f=jFileChooser.getSelectedFile();
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    f.createNewFile();
                    OutputStream fileOutputStream=new FileOutputStream(f);
                    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
                    bufferedOutputStream.write(jTextPane.getText().getBytes());
                    bufferedOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //剪切
    class Cut extends AbstractAction{
        public Cut() {
            super("剪切");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jTextPane.cut();
        }
    }

    //复制
    class Copy extends AbstractAction{
        public Copy() {
            super("复制");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jTextPane.copy();
        }
    }

    //粘贴
    class Paste extends AbstractAction{
        public Paste() {
            super("粘贴");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jTextPane.paste();
        }
    }

    //帮助
    class Help extends AbstractAction{
        public Help() {
            super("帮助");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Desktop desktop=Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)){
                try {
                    URI uri=new URI("https://www.baidu.com");
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //关于
    class About extends AbstractAction{
        public About() {
            super("关于");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"开发者:吴义虎","关于",JOptionPane.PLAIN_MESSAGE);
        }
    }

}

