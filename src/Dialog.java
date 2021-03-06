
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class Dialog extends JFrame {

    private static final long serialVersionUID = 1L;

    // Messages
    private static String segDownloadedString = "Amount of segments downloaded: ";
    private static String downloadFinishedMessage = "Download finished.\n" +
            "If file not in root directory then download has failed.";
    private static String siteErrorMessage = "Site could not be reached";
    private static String downloadTitle = "Download";
    private static String stopDownloadTitle = "Stop downloading";

    // Site url
    private static String siteUrl = "http://github.com/RubenPants/Toledo-Lecture-Downloader";

    // Dialog members
    private ValuePanel fileName;
    private JLabel urlTitle;
    private JTextArea urlInput;
    private JLabel segmentNr;
    private JButton helpButton;
    private JButton downloadButton;

    // State-boolean
    private boolean downloading = false;

    // Download thread
    private Thread downloadThread;

    public static void main(String[] args) {
        new Dialog().setVisible(true);
    }

    /**
     * Create the dialog which is used to enter input-data
     */
    private Dialog() {
        super("Toledo Video Downloader");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setup dialogs
        fileName = new ValuePanel("File Name", "");
        urlTitle = new JLabel("URL of first segment");
        urlInput = new JTextArea();
        urlInput.setLineWrap(true);
        JScrollPane sp = new JScrollPane(urlInput);
        segmentNr = new JLabel(segDownloadedString + "0");
        helpButton = new JButton("Help");
        downloadButton = new JButton(downloadTitle);

        // Add listener to button
        downloadButton.addActionListener(e -> {
            if (downloading) {
                toggleDownload();
                downloadThread.stop();
            } else {
                toggleDownload();
                String name = fileName.getValue();
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in a filename");
                    toggleDownload();
                    return;
                }

                String url = urlInput.getText();
                if (url.equals("") || !url.contains("https://") || !(url.contains("seg") || url.contains("[i]") ||
                        url.contains("[x"))) {
                    JOptionPane.showMessageDialog(null, "Please fill in a valid segment url");
                    toggleDownload();
                    return;
                }

                Runnable downloadRunnable = () -> {
                    try {
                        Manager.useManager(Dialog.this, name, url);
                    } catch (Exception manager_exception) {
                        JOptionPane.showMessageDialog(null, downloadFinishedMessage);
                        toggleDownload();
                    }
                };
                downloadThread = new Thread(downloadRunnable);
                downloadThread.start();
            }
        });

        helpButton.addActionListener(e -> {
            try {
                URI site = new URI(siteUrl);
                java.awt.Desktop.getDesktop().browse(site);
            } catch (Exception uri_exception) {
                JOptionPane.showMessageDialog(null, siteErrorMessage);
            }
        });

        // Add elements to frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 0, 10);  //top padding
        c.gridx = 0;
        c.gridy = 0;
        add(fileName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 0, 10);  //top padding
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        add(urlTitle, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 10, 0, 10);  //top padding
        c.ipady = 50;      //make this component tall
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 2;
        add(sp, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 3;
        add(segmentNr, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;   //request any extra vertical space
        c.insets = new Insets(10, 10, 10, 10);  // padding
        c.gridx = 0;       //aligned with button 2
        c.gridy = 4;       //fifth row
        add(downloadButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(0, 10, 10, 10);  // padding
        c.gridx = 0;       //aligned with button 2
        c.gridy = 5;       //sixth row
        add(helpButton, c);

        pack();
        setSize(400, getHeight());
    }

    void setSegmentNr(String i) {
        segmentNr.setText(segDownloadedString + i);
    }

    private void toggleDownload() {
        if (downloading) {
            downloading = false;
            downloadButton.setText(downloadTitle);
        } else {
            downloading = true;
            downloadButton.setText(stopDownloadTitle);
        }
    }

}
