/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CMD;

/**
 *
 * @author jerem
 */
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CMDGUI extends JFrame {

    private final JTextArea area;
    private int inicioEntrada = 0;
    private final Comandos2 comando;

    public CMDGUI() {
        super("CMD JEREMY - RAFAEL - DIEGO");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);

        String dirUsuario = System.getProperty("user.dir");
        comando = new Comandos2(dirUsuario);

        area = new JTextArea();
        area.setEditable(true);
        area.setBackground(Color.BLACK);
        area.setForeground(new Color(0, 170, 255));
        area.setCaretColor(new Color(0, 170, 255));
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);
        add(scroll);

        appendText("Microsoft Windows [Versión 10.0.22621.521]\n");
        appendText("(c) Microsoft Corporation. Todos los derechos reservados.\n");
        appendText("Realizado por: Jeremy, Rafael y Diego.\n");
        appendText("Escribe 'Exit' para salir.\n");

        writePrompt();

        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int caretPos = area.getCaretPosition();

                if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_HOME) && caretPos <= inicioEntrada) {
                    e.consume();
                    area.setCaretPosition(inicioEntrada);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && caretPos <= inicioEntrada) {
                    e.consume();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE && caretPos < inicioEntrada) {
                    e.consume();
                    return;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    String command;
                    try {
                        int len = area.getDocument().getLength();
                        command = area.getText(inicioEntrada, len - inicioEntrada).trim();
                    } catch (BadLocationException ex) {
                        appendText("\nError leyendo la entrada: " + ex.getMessage() + "\n");
                        writePrompt();
                        return;
                    }
                    appendText("\n");
                    processCommand(command);
                    writePrompt();
                }
            }
        });

        setVisible(true);
    }

    private void appendText(String s) {
        area.append(s);
        area.setCaretPosition(area.getDocument().getLength());
    }

    private void writePrompt() {
        File actual = comando.getPathActual();
        String ruta = actual != null ? actual.getAbsolutePath() : "";
        appendText(ruta + ">");
        inicioEntrada = area.getDocument().getLength();
    }

    private void processCommand(String raw) {
        if (raw == null || raw.isEmpty()) {
            return;
        }

        String[] parts = raw.split("\\s+");
        String cmd = parts[0].toLowerCase();

        try {
            switch (cmd) {
                case "help":
                    appendText("Comandos disponibles:\n");
                    appendText("  cd <ruta>\n");
                    appendText("  ...\n");
                    appendText("  mkdir <nombre>\n");
                    appendText("  mfile <nombre>\n");
                    appendText("  rm <nombre>\n");
                    appendText("  dir [ruta]\n");
                    appendText("  wr <archivo> <texto>\n");
                    appendText("  rd <archivo>\n");
                    appendText("  time\n");
                    appendText("  date\n");
                    appendText("  cls\n");
                    appendText("  exit\n");
                    break;

                case "cd":
                    if (parts.length < 2) {
                        File actual = comando.getPathActual();
                        if (actual != null) {
                            appendText(actual.getAbsolutePath() + "\n");
                        }
                    } else {
                        String ruta = raw.substring(raw.indexOf(' ') + 1).trim();
                        File base = comando.getPathActual();
                        File nueva;
                        if ("..".equals(ruta)) {
                            nueva = base != null ? base.getParentFile() : null;
                        } else {
                            File posible = new File(ruta);
                            if (posible.isAbsolute()) {
                                nueva = posible;
                            } else {
                                nueva = base != null ? new File(base, ruta) : posible;
                            }
                        }
                        if (comando.cd(nueva)) {
                            File actual = comando.getPathActual();
                            if (actual != null) {
                                appendText(actual.getAbsolutePath() + "\n");
                            }
                        } else {
                            appendText("No existe la ruta.\n");
                        }
                    }
                    break;

                case "...":
                    if (comando.cdBack()) {
                        File actual = comando.getPathActual();
                        if (actual != null) {
                            appendText(actual.getAbsolutePath() + "\n");
                        }
                    } else {
                        appendText("No se puede subir más.\n");
                    }
                    break;

                case "mkdir":
                    if (parts.length < 2) {
                        appendText("Uso: mkdir <carpeta>\n");
                    } else {
                        String msg = comando.mkdir(parts[1]);
                        appendText(msg + "\n");
                    }
                    break;

                case "mfile":
                    if (parts.length < 2) {
                        appendText("Uso: mfile <archivo>\n");
                    } else {
                        String msg = comando.mfile(parts[1]);
                        appendText(msg + "\n");
                    }
                    break;

                case "rm":
                    if (parts.length < 2) {
                        appendText("Uso: rm <nombre>\n");
                    } else {
                        String msg = comando.rm(parts[1]);
                        appendText(msg + "\n");
                    }
                    break;

                case "dir":
                    String argDir;
                    if (parts.length < 2) {
                        argDir = ".";
                    } else {
                        argDir = raw.substring(raw.indexOf(' ') + 1).trim();
                    }
                    String resultadoDir = comando.dir(argDir);
                    appendText(resultadoDir + "\n");
                    break;

                case "wr":
                    if (parts.length < 2) {
                        appendText("Uso: wr <archivo>\n");
                    } else {
                        String nombre = parts[1];
                        appendText("Modo escritura activado. Escribe texto y termina con EXIT en mayúsculas:\n");

                        new Thread(() -> {
                            StringBuilder sb = new StringBuilder();
                            boolean escribiendo = true;

                            while (escribiendo) {
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    break;
                                }

                                SwingUtilities.invokeLater(() -> {
                                    int len = area.getDocument().getLength();
                                    try {
                                        String linea = area.getText(inicioEntrada, len - inicioEntrada).trim();
                                        if (linea.endsWith("EXIT")) {
                                            sb.append(linea.replace("EXIT", "")).append("\n");
                                            String msg = comando.escribirTexto(nombre, sb.toString());
                                            appendText(msg + "\n");
                                            inicioEntrada = area.getDocument().getLength();
                                        } else {
                                            sb.append(linea).append("\n");
                                        }
                                    } catch (BadLocationException ex) {
                                        appendText("Error en WR: " + ex.getMessage() + "\n");
                                    }
                                });

                                escribiendo = false;
                            }
                        }).start();
                    }
                    break;

                case "rd":
                    if (parts.length < 2) {
                        appendText("Uso: rd <archivo>\n");
                    } else {
                        String contenido = comando.leerTexto(parts[1]);
                        appendText(contenido + "\n");
                    }
                    break;

                case "time":
                appendText(comando.time() + "\n");
                break;

                case "date":
                    appendText(comando.date() + "\n");
                    break;

                case "cls":
                    area.setText("");
                    appendText("Microsoft Windows [Versión 10.0.22621.521]\n");
                    appendText("(c) Microsoft Corporation. Todos los derechos reservados.\n");
                    appendText("Si ocupas ayuda usa el comando 'help'.\n");
                    break;

                case "exit":
                    dispose();
                    break;

                default:
                    appendText("Comando no reconocido: " + cmd + "\n");
                    break;
            }
        } catch (Exception ex) {
            appendText("Error al ejecutar comando: " + ex.getMessage() + "\n");
        }
    }
}
